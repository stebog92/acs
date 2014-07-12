#include <linux/module.h>
#include <linux/init.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <linux/cdev.h>
#include <asm/uaccess.h>
#include <linux/sched.h>
#include <linux/wait.h>
#include <linux/blkdev.h>
#include <linux/genhd.h>
#include <linux/delay.h>
#include <linux/crc32.h>
#include "ssr.h"

MODULE_DESCRIPTION("SO2 RAID");
MODULE_AUTHOR("Mihai Ciocan");
MODULE_LICENSE("GPL");

#define LOG_LEVEL	KERN_ALERT
#define BIO_DIR_READ 0
#define BIO_DIR_WRITE 1
#define CRC_SIZE 4
#define SECTOR_MASK		(KERNEL_SECTOR_SIZE-1)

struct block_dev {
	spinlock_t lock;
	struct request_queue* queue;
	struct gendisk *gd;
	struct work_struct submit_bio_work;
	struct bio *bio;
}	dev;

struct bio_data {
	struct completion completion;
	char data[KERNEL_SECTOR_SIZE * 8];
};

struct phys_dev {
	struct block_device *bdev;
	unsigned char buffer[KERNEL_SECTOR_SIZE * 8];
	struct completion completion;
	struct bio *pending_bio;
} dev1, dev2;

static void transfer_data(struct block_device *bdev, size_t sector, struct bio_data *data, int dir);

static inline size_t ssr_get_crc_offset(size_t data_sector)
{
	return LOGICAL_DISK_SIZE + data_sector * CRC_SIZE;
}

static inline size_t ssr_get_crc_offset_in_sector(size_t data_sector)
{
	return ssr_get_crc_offset(data_sector) & SECTOR_MASK;
}

static inline size_t ssr_get_crc_sector(size_t data_sector)
{
	return ssr_get_crc_offset(data_sector) & ~SECTOR_MASK;
}

static int block_open(struct block_device *bdev, fmode_t mode)
{
	return 0;
}

static int block_release(struct gendisk *gd, fmode_t mode)
{
	return 0;
}

struct block_device_operations block_ops = {
	.open = block_open,
	.release = block_release
};

/* read data if necesary*/
static void bi_complete(struct bio *bio, int error)
{

	struct phys_dev *dev = (struct phys_dev *)bio->bi_private;
	char *buf;
	if (bio_data_dir(bio) == BIO_DIR_READ) {
		buf = __bio_kmap_atomic(bio, 0);
		memcpy(dev->buffer, buf, KERNEL_SECTOR_SIZE * 8);
		__bio_kunmap_atomic(buf);
	}
	complete(&dev->completion);
}


/* prepare physical bios for submition*/
static void prepare_bios(struct bio *bio, struct phys_dev *dev)
{
	dev->pending_bio = bio_clone(bio, GFP_NOIO);
	dev->pending_bio->bi_bdev = dev->bdev;
	init_completion(&dev->completion);
	dev->pending_bio->bi_private = dev;
	dev->pending_bio->bi_end_io = bi_complete;
}

/* check data for errors*/
static int check_for_error(size_t sector_start, struct phys_dev *dev, struct bio_data *data ) {
	size_t sector;
	unsigned int crc_comp, crc_read;
	int offset;
	for (sector = 0; sector < 8; sector++) {
		crc_comp = crc32(0, (unsigned char*)(dev->buffer + sector * KERNEL_SECTOR_SIZE), KERNEL_SECTOR_SIZE);
		offset = ssr_get_crc_offset_in_sector(sector_start + sector);
		crc_read = * (unsigned int *) (data->data + offset);
		if (crc_read != crc_comp) {
			return 1;
		}
	}
	return 0;
}

/* submit bios and check for errors*/
static void submit_phys_bios(struct bio *bio)
{
	struct bio_vec *bvec;
	size_t crc_sector;
	struct bio_data bio_data;
	int dev1_corrupt = 0;
	int dev2_corrupt = 0;
	int i;

	submit_bio(0, dev1.pending_bio);
	wait_for_completion(&dev1.completion);

	crc_sector = ssr_get_crc_sector(bio->bi_sector) / KERNEL_SECTOR_SIZE;
	transfer_data(dev2.bdev, crc_sector, &bio_data, BIO_DIR_READ);

	bio_put(dev1.pending_bio);


	submit_bio(0, dev2.pending_bio);
	wait_for_completion(&dev2.completion);
	bio_put(dev2.pending_bio);


	if (bio_data_dir(bio) == BIO_DIR_WRITE) {
		return;
	}

	printk(LOG_LEVEL "");

	/*for (sector = 0; sector < 8; sector++) {
		crc_comp = crc32(0, (unsigned char*)(dev1.buffer + sector * KERNEL_SECTOR_SIZE), KERNEL_SECTOR_SIZE);
		offset = ssr_get_crc_offset_in_sector(bio->bi_sector + sector);
		crc_read = * (unsigned int *) (bio_data.data + offset);
		if (crc_read != crc_comp) {
			dev1_corrupt = 1;
			break;
		}
	}

	for (sector = 0; sector < 8; sector++) {
		crc_comp = crc32(0, (unsigned char*)(dev2.buffer + sector * KERNEL_SECTOR_SIZE), KERNEL_SECTOR_SIZE);
		offset = ssr_get_crc_offset_in_sector(bio->bi_sector + sector);
		crc_read = * (unsigned int *) (bio_data.data + offset);
		if (crc_read != crc_comp) {
			dev2_corrupt = 1;
			break;
		}
	}*/


	dev1_corrupt = check_for_error(bio->bi_sector, &dev1, &bio_data);
	dev2_corrupt = check_for_error(bio->bi_sector, &dev2, &bio_data);

	if (dev1_corrupt) {
		if (dev2_corrupt) {

		} else {
			memcpy(bio_data.data, dev2.buffer, KERNEL_SECTOR_SIZE * 8);
			transfer_data(dev1.bdev, bio->bi_sector, &bio_data, BIO_DIR_WRITE);
		}
	} else {
		if (dev2_corrupt) {
			memcpy(bio_data.data, dev1.buffer, KERNEL_SECTOR_SIZE * 8);
			transfer_data(dev2.bdev, bio->bi_sector, &bio_data, BIO_DIR_WRITE);

			bio_for_each_segment(bvec, bio, i) {
				char *buffer = __bio_kmap_atomic(bio, i);
					memcpy(buffer, dev1.buffer, KERNEL_SECTOR_SIZE * 8);
				__bio_kunmap_atomic(buffer);
			}

		} else {
		}
	}
}

/* read data after submition if necesary */
static void transfer_data_end(struct bio *bio, int error)
{
	struct bio_data *bio_data = (struct bio_data*)bio->bi_private;
	char *buf;
	if (bio_data_dir(bio) == BIO_DIR_READ) {
		buf = __bio_kmap_atomic(bio, 0);
			memcpy(bio_data->data, buf, KERNEL_SECTOR_SIZE * 8);
		__bio_kunmap_atomic(buf);
	}

	complete(&bio_data->completion);
}


/* transfer data block to or from bdev */
static void transfer_data(struct block_device *bdev, size_t sector, struct bio_data *data, int dir)
{
	struct bio *bio = bio_alloc(GFP_NOIO, 1);
	struct page *page;
	char *buf;

	bio->bi_bdev = bdev;
	bio->bi_sector = sector;
	init_completion(&data->completion);
	bio->bi_private = data;
	bio->bi_end_io = transfer_data_end;
	bio->bi_rw = dir;

	page = alloc_page(GFP_NOIO);
	bio_add_page(bio, page, KERNEL_SECTOR_SIZE * 8, 0);
	bio->bi_vcnt = 1;
	bio->bi_idx = 0;

	if (dir == BIO_DIR_WRITE) {
		buf = __bio_kmap_atomic(bio, 0);
		memcpy(buf, data->data, KERNEL_SECTOR_SIZE * 8);
		__bio_kunmap_atomic(buf);
	}

	submit_bio(0, bio);
	wait_for_completion(&data->completion);

	bio_put(bio);
	__free_page(page);
}

/* compute crc and store in place */
static void compute_crc(struct phys_dev *dev, struct bio *bio)
{
	int i, offset;
	size_t j;
	struct bio_vec *bvec;
	size_t current_crc_sector;
	struct bio_data crc_sector;

	bio->bi_idx = 0;
	current_crc_sector = ssr_get_crc_sector(bio->bi_sector) / KERNEL_SECTOR_SIZE;
	transfer_data(dev->bdev, current_crc_sector, &crc_sector, BIO_DIR_READ);

	bio_for_each_segment(bvec, bio, i) {
		unsigned int crc;
		char *buffer = __bio_kmap_atomic(bio, i);
		for (j = 0; j < bio_sectors(bio); j++) {
			crc = crc32(0, (unsigned char*)(buffer + (j * KERNEL_SECTOR_SIZE)), KERNEL_SECTOR_SIZE);
			offset = ssr_get_crc_offset_in_sector(bio->bi_sector + j);
			* (unsigned int *) (crc_sector.data + offset) = crc;
		}
		__bio_kunmap_atomic(buffer);
	}
	transfer_data(dev->bdev, current_crc_sector, &crc_sector, BIO_DIR_WRITE);
}

/* workqueue handler */
static void submit_bio_handler(struct work_struct *work)
{

	struct block_dev *local_dev = container_of(work, struct block_dev, submit_bio_work);
	struct bio *bio = local_dev->bio;

	prepare_bios(bio, &dev1);
	prepare_bios(bio, &dev2);

	if (bio_data_dir(bio) == BIO_DIR_WRITE) {
		compute_crc(&dev1, bio);
		compute_crc(&dev2, bio);
	}

	submit_phys_bios(bio);
	bio_endio(bio, 0);
}


/* bio transfer request */
static void make_request(struct request_queue *q, struct bio *bio)
{
	dev.bio = bio;
	schedule_work(&dev.submit_bio_work);
	flush_scheduled_work();
}

/* get physical block_device structure */
static void get_block_device(const char* disk_name, struct phys_dev *dev)
{

	dev->bdev = blkdev_get_by_path(disk_name, FMODE_READ | FMODE_WRITE | FMODE_EXCL, THIS_MODULE);
	if (IS_ERR(dev->bdev)) {
		printk(KERN_ERR "blkdev_get_by_path\n");
	}
}

/* create logical block device*/
static int create_block_device(struct block_dev *dev)
{
	int err;

	/* initialize the I/O queue */
	spin_lock_init(&dev->lock);
	dev->queue =  blk_alloc_queue(GFP_KERNEL);
	if (dev->queue == NULL) {
		printk(KERN_ERR "blk_init_queue: out of memory\n");
		err = -ENOMEM;
		goto out_blk_init;
	}
	blk_queue_logical_block_size(dev->queue, KERNEL_SECTOR_SIZE);
	dev->queue->queuedata = dev;

	/* initialize the gendisk structure */
	dev->gd = alloc_disk(SSR_NUM_MINORS);
	if (!dev->gd) {
		printk(KERN_ERR "alloc_disk: failure\n");
		err = -ENOMEM;
		goto out_alloc_disk;
	}
	blk_queue_make_request(dev->queue, make_request);
	dev->gd->major = SSR_MAJOR;
	dev->gd->first_minor = SSR_FIRST_MINOR;
	dev->gd->fops = &block_ops;
	dev->gd->queue = dev->queue;
	dev->gd->private_data = dev;
	snprintf(dev->gd->disk_name, DISK_NAME_LEN, LOGICAL_DISK_NAME);
	set_capacity(dev->gd, LOGICAL_DISK_SECTORS);

	add_disk(dev->gd);

	return 0;

out_alloc_disk:
	blk_cleanup_queue(dev->queue);
out_blk_init:
	return err;
}


static void put_block_device(struct block_device *dev)
{
	blkdev_put(dev, FMODE_READ | FMODE_WRITE | FMODE_EXCL);
}

static void delete_block_device(struct block_dev *dev)
{
	if (dev->gd) {
		del_gendisk(dev->gd);
		put_disk(dev->gd);
	}
	if (dev->queue)
		blk_cleanup_queue(dev->queue);
}

static int raid_init(void)
{
	int status = 0;
	status = register_blkdev(SSR_MAJOR, LOGICAL_DISK_NAME);
	if (status < 0) {
		return -EBUSY;
	}

	INIT_WORK(&dev.submit_bio_work, submit_bio_handler);
	create_block_device(&dev);
	get_block_device(PHYSICAL_DISK1_NAME, &dev1);
	get_block_device(PHYSICAL_DISK2_NAME, &dev2);
	if (status < 0) {
		return -ENOMEM;
	}
	return 0;
}

static void raid_exit(void)
{
	put_block_device(dev1.bdev);
	put_block_device(dev2.bdev);
	delete_block_device(&dev);
	unregister_blkdev(SSR_MAJOR, LOGICAL_DISK_NAME);
}

module_init(raid_init);
module_exit(raid_exit);

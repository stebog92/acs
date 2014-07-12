/*
 * SO2 Lab - Block device drivers (#7)
 * Linux - Exercise #4 (Relay disk - bio)
 */

#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/fs.h>
#include <linux/wait.h>
#include <linux/sched.h>
#include <linux/genhd.h>
#include <linux/blkdev.h>

MODULE_AUTHOR("SO2");
MODULE_DESCRIPTION("Relay disk");
MODULE_LICENSE("GPL");

#define KERN_LOG_LEVEL		KERN_ALERT

#define PHYSICAL_DISK_NAME	"/dev/sdb"
#define KERNEL_SECTOR_SIZE	512

#define BIO_DIR_READ		0
#define BIO_DIR_WRITE		1

#define BIO_WRITE_MESSAGE	"def"


/* pointer to physical device structure */
static struct block_device *phys_bdev;

static void bi_complete(struct bio *bio, int error)
{
	char *buf;

	if (bio->bi_rw == BIO_DIR_READ) {
		buf = __bio_kmap_atomic(bio, 0);
		printk(KERN_LOG_LEVEL "[bi_complete] read %02x %02x %02x\n",
				buf[0], buf[1], buf[2]);
		__bio_kunmap_atomic(buf);
	}

	complete((struct completion *) bio->bi_private);
}

static void send_test_bio(struct block_device *bdev, int dir)
{
	struct bio *bio = bio_alloc(GFP_NOIO, 1);
	struct completion event;
	struct page *page;
	char *buf;

	bio->bi_bdev = bdev;
	bio->bi_sector = 0;
	init_completion(&event);
	bio->bi_private = &event;
	bio->bi_end_io = bi_complete;
	bio->bi_rw = dir;

	page = alloc_page(GFP_NOIO);
	bio_add_page(bio, page, KERNEL_SECTOR_SIZE, 0);
	bio->bi_vcnt = 1;
	bio->bi_idx = 0;

	if (dir == BIO_DIR_WRITE) {
		buf = __bio_kmap_atomic(bio, 0);
		memcpy(buf, BIO_WRITE_MESSAGE, strlen(BIO_WRITE_MESSAGE));
		__bio_kunmap_atomic(buf);
	}

	submit_bio(0, bio);
	printk(KERN_LOG_LEVEL "[send_test_bio] Submited bio\n");

	wait_for_completion(&event);
	printk(KERN_LOG_LEVEL "[send_test_bio] Done bio\n");

	bio_put(bio);
	__free_page(page);
}

static struct block_device *open_disk(char *name)
{
	struct block_device *bdev;

	bdev = blkdev_get_by_path(name, FMODE_READ | FMODE_WRITE | FMODE_EXCL, THIS_MODULE);
	if (IS_ERR(bdev)) {
		printk(KERN_ERR "blkdev_get_by_path\n");
		return NULL;
	}

	return bdev;
}

static int __init relay_init(void)
{
	phys_bdev = open_disk(PHYSICAL_DISK_NAME);
	if (phys_bdev == NULL) {
		printk(KERN_ERR "[relay_init] No such device\n");
		return -EINVAL;
	}

	send_test_bio(phys_bdev, BIO_DIR_READ);

	return 0;
}

static void close_disk(struct block_device *bdev)
{
	blkdev_put(bdev, FMODE_READ | FMODE_WRITE | FMODE_EXCL);
}

static void __exit relay_exit(void)
{
	send_test_bio(phys_bdev, BIO_DIR_WRITE);
	close_disk(phys_bdev);
}

module_init(relay_init);
module_exit(relay_exit);

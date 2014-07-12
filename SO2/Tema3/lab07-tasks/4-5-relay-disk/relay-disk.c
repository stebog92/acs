/*
 * SO2 Lab - Block device drivers (#7)
 * Linux - Exercise #4, #5 (Relay disk - bio)
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

	/* TODO 4: read data (first 3 bytes) from bio buffer and print it */


	/* TODO 4: complete bio */

}

/* TODO 5: add direction parameter */
static void send_test_bio(struct block_device *bdev)
{
	struct bio *bio = bio_alloc(GFP_NOIO, 1);
	struct completion event;
	struct page *page;

	/* TODO 4: fill bio (bdev, sector, endio, direction, completion) */


	page = alloc_page(GFP_NOIO);
	bio_add_page(bio, page, KERNEL_SECTOR_SIZE, 0);
	bio->bi_vcnt = 1;
	bio->bi_idx = 0;

	/* TODO 5: write message to bio buffer if direction is write */


	/* TODO 4: submit bio and wait for completion */


	bio_put(bio);
	__free_page(page);
}

static struct block_device *open_disk(char *name)
{
	struct block_device *bdev;

	/* TODO 4: get block device in exclusive mode */


	return bdev;
}

static int __init relay_init(void)
{
	phys_bdev = open_disk(PHYSICAL_DISK_NAME);
	if (phys_bdev == NULL) {
		printk(KERN_ERR "[relay_init] No such device\n");
		return -EINVAL;
	}

	send_test_bio(phys_bdev);

	return 0;
}

static void close_disk(struct block_device *bdev)
{
	/* TODO 4: put block device */

}

static void __exit relay_exit(void)
{
	/* TODO 5: send test write bio */

	close_disk(phys_bdev);
}

module_init(relay_init);
module_exit(relay_exit);

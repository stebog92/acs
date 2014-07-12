/*
 * SO2 Lab - Interrupts (#6)
 * All tasks
 */

#include <linux/module.h>
#include <linux/init.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <linux/cdev.h>
#include <asm/io.h>
#include <asm/uaccess.h>
#include <linux/ioport.h>
#include <linux/interrupt.h>
#include <linux/spinlock.h>
#include <linux/slab.h>

MODULE_DESCRIPTION("SO2 KBD");
MODULE_AUTHOR("SO2");
MODULE_LICENSE("GPL");

#define LOG_LEVEL		KERN_ALERT
#define MODULE_NAME		"so2_kbd"

#define SO2_KBD_MAJOR		42
#define SO2_KBD_MINOR		0
#define SO2_KBD_NR_MINORS	1

#define I8042_KBD_IRQ		1
#define I8042_STATUS_REG	0x64
#define I8042_DATA_REG		0x60

#define BUFFER_SIZE		1024
#define SCANCODE_RELEASED_MASK	0x80

#define MAGIC_WORD		"root"
#define MAGIC_WORD_LEN		4

struct so2_device_data {
	struct cdev cdev;
	/* TODO 4: locking mechanism */
	char buf[BUFFER_SIZE];
        int baseport;
	atomic_t size;
	/* use passcnt to hold the no. of chars that were matched so far */
	int passcnt;
} devs[1];


/**
 * is_key_press() -- checks if a scancode corresponds to a key press or releaase
 *
 * scancode - value read from the DATA register
 * returns non-zero for a key press and 0 otherwise
 */
static int is_key_press(int scancode)
{
	return !(scancode & SCANCODE_RELEASED_MASK);
}

/**
 * get_ascii() -- returns the character of the given scancode
 * Only works for alfanumeric/space/enter; returns '?' for other chars.
 *
 * scancode - value read from the DATA register
 */
static char get_ascii(int scancode)
{
	static char *row1 = "1234567890";
	static char *row2 = "qwertyuiop";
	static char *row3 = "asdfghjkl";
	static char *row4 = "zxcvbnm";

	scancode &= ~SCANCODE_RELEASED_MASK;
	if (scancode >= 0x02 && scancode <= 0x0b)
		return *(row1 + scancode - 0x02);
	if (scancode >= 0x10 && scancode <= 0x19)
		return *(row2 + scancode - 0x10);
	if (scancode >= 0x1e && scancode <= 0x26)
		return *(row3 + scancode - 0x1e);
	if (scancode >= 0x2c && scancode <= 0x32)
		return *(row4 + scancode - 0x2c);
	if (scancode == 0x39)
		return ' ';
	if (scancode == 0x1c)
		return '\n';
	return '?';
}

/**
 * i8042_read_data() -- returns the value of the DATA register
 */
static u8 i8042_read_data(void)
{
	u8 val;
	/* TODO 3: read DATA register (8 bits) */
	val = 0;
	return val;
}

/* TODO 2: implement interrupt handler */
irqreturn_t so2_kbd_interrupt_handle(int irq_no, void *dev_id);


static int so2_kbd_open(struct inode *inode, struct file *file)
{
	struct so2_device_data *data =
		container_of(inode->i_cdev, struct so2_device_data, cdev);

	file->private_data = data;
	printk(LOG_LEVEL "%s opened\n", MODULE_NAME);
	return 0;
}

static int so2_kbd_release(struct inode *inode, struct file *file)
{
	printk(LOG_LEVEL "%s closed\n", MODULE_NAME);
	return 0;
}

static ssize_t
so2_kbd_read(struct file *file, char __user *user_buffer,
		size_t size, loff_t *offset)
{
	struct so2_device_data *data =
		(struct so2_device_data *) file->private_data;
	char *tmp;

	/* check if range is valid */
	if (*offset > atomic_read(&data->size))
		return 0;

	if (size > atomic_read(&data->size) - *offset)
		size = atomic_read(&data->size) - *offset;

	/* TODO 4: allocate the temp buffer */
	/* TODO 4: read from the device's internal buffer to temporary
	 * buffer , use synchronization */
	/* TODO 4: read from the temp buffer to user buffer */

	/* update offset */
	*offset += size;

	return size;
}

static const struct file_operations so2_kbd_fops = {
	.owner = THIS_MODULE,
	.open = so2_kbd_open,
	.release = so2_kbd_release,
	.read = so2_kbd_read,
};

static int so2_kbd_init(void)
{
	int err;

	err = register_chrdev_region(MKDEV(SO2_KBD_MAJOR, SO2_KBD_MINOR),
			SO2_KBD_NR_MINORS, MODULE_NAME);
	if (err != 0) {
		printk(LOG_LEVEL "ERROR: %s: error %d\n",
				"register_region", err);
		goto out;
	}

	/* the following piece of code will fail, so leave it commented */
	/*
	if (request_region(I8042_DATA_REG, 1, MODULE_NAME) == NULL) {
		// this will always fail, but why?
	}

	if (request_region(I8042_STATUS_REG, 1, MODULE_NAME) == NULL) {
		// same here
	}
	*/

	/* TODO 4: initialize spinlock before requesting IRQ */

	/* TODO 2: request IRQ */

	atomic_set(&devs[0].size, 0);
	cdev_init(&devs[0].cdev, &so2_kbd_fops);
	cdev_add(&devs[0].cdev, MKDEV(SO2_KBD_MAJOR, SO2_KBD_MINOR), 1);

	printk(LOG_LEVEL "Driver %s loaded\n", MODULE_NAME);
	return 0;

out2:
	unregister_chrdev_region(MKDEV(SO2_KBD_MAJOR, SO2_KBD_MINOR),
			SO2_KBD_NR_MINORS);
out:
	return err;
}

static void so2_kbd_exit(void)
{
	cdev_del(&devs[0].cdev);

	/* TODO 2: free IRQ */

	/* we didn't call request_region, so leave this commented */
	/*
	release_region(I8042_STATUS_REG, 1);
	release_region(I8042_DATA_REG, 1);
	*/

	unregister_chrdev_region(MKDEV(SO2_KBD_MAJOR, SO2_KBD_MINOR),
			SO2_KBD_NR_MINORS);
	printk(LOG_LEVEL "Driver %s unloaded\n", MODULE_NAME);
}

module_init(so2_kbd_init);
module_exit(so2_kbd_exit);


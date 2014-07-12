/*
 * A simple character device driver.
 *
 * (C) 2001 Octavian Purdila.
 * 
 * Data is kept in a circular buffer. 
 *
 * Data is put at buffer_put and taken from buffer_get.
 *
 * The full buffer condition is 
 *         (buffer_put+1)%BUFFSER_SIZE == buffer_get
 *
 * The empty buffer condition is 
 *        buffer_put == buffer_get
 * 
 * NOT SMP safe.
 *
 */

 
#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/fs.h>
#include <linux/wait.h>
#include <linux/sched.h>
#include <asm/uaccess.h>
#include <linux/slab.h>
#include <linux/cdev.h>

MODULE_AUTHOR("Octavian Purdila");
MODULE_DESCRIPTION("character stream case formater"); 
MODULE_LICENSE("GPL");

int up_case;
module_param(up_case, int, S_IRUGO);
MODULE_PARM_DESC(up_case, "0 for low case, anything else for up case");

#define CASE_MAX_MINORS           8        
#define CASE_MAJOR                42 
#define BUFFER_SIZE               PAGE_SIZE

struct case_dev {
    struct cdev cdev;
    char buffer[BUFFER_SIZE];
    unsigned put, get;
    atomic_t fill;
    wait_queue_head_t wq_reads, wq_writes;
} devs[CASE_MAX_MINORS];


static int case_open(struct inode *inode, struct file *file)
{
	struct case_dev *dev=container_of(inode->i_cdev, struct case_dev, cdev);
	
	file->private_data=dev;
        return 0;
}

static int case_close(struct inode *inode, struct file *file)
{
        return 0;
}

static int case_read(struct file *file, char *user_buffer, 
        size_t size, loff_t *offset)
{
        int i=0;
	struct case_dev *dev=(struct case_dev*)file->private_data;


	if (file->f_mode&O_NONBLOCK) {
		if (atomic_read(&dev->fill) == 0)
			return -EAGAIN;
	} else {
		if (wait_event_interruptible(dev->wq_reads, atomic_read(&dev->fill) > 0))
                        return -ERESTARTSYS;
        }

        while ((atomic_read(&dev->fill)>0) && size) {
                if (put_user(dev->buffer[dev->get], &user_buffer[i])) 
                        return -EFAULT;
                dev->get++; dev->get%=BUFFER_SIZE;
                i++; size--; atomic_dec(&dev->fill);
        }

        if (atomic_read(&dev->fill) < BUFFER_SIZE)
                wake_up(&dev->wq_writes); 

        return i;
}


static int case_write(struct file *file, const char *user_buffer, 
        size_t size, loff_t *offset)
{
        int i=0;
	struct case_dev *dev=(struct case_dev*)file->private_data;

        if (file->f_mode&O_NONBLOCK) {
		if (atomic_read(&dev->fill) == BUFFER_SIZE)
                    return -EAGAIN;
	} else {
		if (wait_event_interruptible(dev->wq_writes, atomic_read(&dev->fill) < BUFFER_SIZE))
			return -ERESTARTSYS;
        }


        while ((atomic_read(&dev->fill) < BUFFER_SIZE) && size) {
		char c;
		
                if (get_user(c, &user_buffer[i])) 
                        return -EFAULT;

                if (up_case) {
                        if (c >= 'a' && c <= 'z')
                                c=c-'a'+'A';
                } else {
                        if (c >= 'A' && c <= 'Z')
                                c=c-'A'+'a';
                }

                dev->buffer[dev->put]=c;
                dev->put=(dev->put+1)%BUFFER_SIZE;
                i++; size--; atomic_inc(&dev->fill);
        }

	if (atomic_read(&dev->fill) > 0) 
                wake_up(&dev->wq_reads);

        
        return i;
}

struct file_operations case_fops = {
	.owner		= THIS_MODULE,
	.open		= case_open,
        .read 		= case_read,
        .write 		= case_write,
	.release	= case_close
};


int init_module(void)
{
        
        int i, err;

	if ((err=register_chrdev_region(MKDEV(CASE_MAJOR, 0), CASE_MAX_MINORS, "case")))
		return err;		

        for(i=0; i<CASE_MAX_MINORS; i++) {
                init_waitqueue_head(&devs[i].wq_reads);
                init_waitqueue_head(&devs[i].wq_writes);
		cdev_init(&devs[i].cdev, &case_fops);
		cdev_add(&devs[i].cdev, MKDEV(CASE_MAJOR, i), 1);
        }

        if (up_case)
                printk(KERN_INFO"case: using upper case format\n");
        else
                printk(KERN_INFO"case: using lower case format\n");

        return 0;
}

void cleanup_module(void)
{
	int i;
	
        for(i=0; i<CASE_MAX_MINORS; i++) {
		cdev_del(&devs[i].cdev);
	}
	unregister_chrdev_region(MKDEV(CASE_MAJOR, 0), CASE_MAX_MINORS);
}

#include <linux/module.h>
#include <linux/init.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <linux/cdev.h>
#include <asm/io.h>
#include <asm/uaccess.h>
#include <linux/sched.h>
#include <linux/wait.h>
#include <linux/interrupt.h>
#include <linux/kfifo.h>
#include "uart16550.h"

MODULE_DESCRIPTION("SO2 character device");
MODULE_AUTHOR("Mihai Ciocan");
MODULE_LICENSE("GPL");

#define LOG_LEVEL	KERN_ALERT

#define UART16550_MAJOR		42

#define MODULE_NAME	"uart16550"

#define OPTION_COM1_ONLY	1
#define OPTION_COM2_ONLY	2
#define OPTION_BOTH		3

#define COM1_BASEPORT   	0x3f8
#define COM2_BASEPORT		0x2f8

#define COM1_IRQ        4
#define COM2_IRQ        3

#define BUFFER_SIZE             512

int major = UART16550_MAJOR;
int option = OPTION_BOTH;
int max_minor = 1;
int minor = 0;

module_param(option, int, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
module_param(major, int, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);

struct device_data {
	struct cdev cdev;
        int baseport;
        char read_buffer[BUFFER_SIZE];
        char write_buffer[BUFFER_SIZE];
        atomic_t read_fill;
        atomic_t write_fill;
        int read_get;
        int read_put;
        int write_put;
        int write_get;
        wait_queue_head_t wq_reads, wq_writes;
} devs[2];

irqreturn_t irq_handle(int irq_no, void *dev_id)
{
        int status;
        unsigned char c;

        struct device_data *data = (struct device_data *)dev_id;
        status = inb(data->baseport + 2);

        if ((status & 0x04) && (status & 0x02)) {
                inb(data->baseport + 2);
                return IRQ_HANDLED;
        }

        if (status & 0x04) {
                /* read from port*/
                while(atomic_read(&data->read_fill) < BUFFER_SIZE 
                                        && inb(data->baseport + 5) & 0x01) {
                        c = inb(data->baseport);
                        data->read_buffer[data->read_put] = c;
                        data->read_put=(data->read_put+1) % BUFFER_SIZE;
                        atomic_inc(&data->read_fill);
                }

                if (atomic_read(&data->read_fill) > 0)
                        wake_up(&data->wq_reads);

        } else if (status & 0x02) {
                /* write to port */
                while(atomic_read(&data->write_fill)) {
                        outb(data->write_buffer[data->write_get],
                                                        data->baseport);
                        atomic_dec(&data->write_fill);
                        data->write_get = (data->write_get + 1) % BUFFER_SIZE;
                }

               if (atomic_read(&data->write_fill) < BUFFER_SIZE)
                       wake_up(&data->wq_writes);
        }
        return IRQ_HANDLED;
}

static int uart_open(struct inode *inode, struct file *file)
{
        struct device_data *data = container_of(inode->i_cdev,
                                                struct device_data, cdev);
        file->private_data = data;
        return 0;
}

static int uart_read(struct file *file, char __user *user_buffer, 
                                                size_t size, loff_t *offset) 
{
        int i = 0;
        struct device_data *data =
                (struct device_data *) file->private_data;

        /* wait until data is available in buffer */
        if (wait_event_interruptible(data->wq_reads, 
                                        atomic_read(&data->read_fill) > 0))
                return -ERESTARTSYS;

        while ((atomic_read(&data->read_fill) > 0) && size) {
                if (put_user(data->read_buffer[data->read_get], 
                                                &user_buffer[i])) 
                        return -EFAULT;
                data->read_get++; data->read_get %= BUFFER_SIZE;
                i++; size--; atomic_dec(&data->read_fill);
        }
        return i;
}

static int uart_write(struct file *file, const char __user *user_buffer, 
                                                size_t size, loff_t * offset)
{
        int i = 0;
        struct device_data *data =
                (struct device_data *) file->private_data;

        
        /* wait until space is available in buffer */
        if (wait_event_interruptible(data->wq_writes, 
                                atomic_read(&data->write_fill) < BUFFER_SIZE))
                return -ERESTARTSYS;



        while ((atomic_read(&data->write_fill) < BUFFER_SIZE) && size) {
                char c;
                if (get_user(c, &user_buffer[i])) 
                        return -EFAULT;

                data->write_buffer[data->write_put] = c;
                data->write_put=(data->write_put+1) % BUFFER_SIZE;
                i++;
                size--;
                atomic_inc(&data->write_fill);
        }
        outb(inb(data->baseport + 1) & 0xfd, data->baseport + 1);
        outb(inb(data->baseport + 1) | 0x02, data->baseport + 1);
        return i;
}



/* uart ioctl set line */
static long uart_ioctl(struct file *file, unsigned int cmd, unsigned long arg)
{
        int err = 0;
        struct device_data *data = (struct device_data*) file->private_data;
        struct uart16550_line_info line;

        if (cmd == UART16550_IOCTL_SET_LINE) {
                if(copy_from_user(&line, (struct uart16550_line_info *) arg,
                                        sizeof(struct uart16550_line_info)))
                        return -EFAULT;

                outb(inb(data->baseport + 3) | 0x80, data->baseport + 3);
                outb(line.baud & 0xff, data->baseport + 0);
                outb(line.baud >> 8, data->baseport + 1);
                outb(inb(data->baseport + 3) | line.stop | line.len | line.par,
                                data->baseport + 3);
                outb(inb(data->baseport + 3) & 0x7F, data->baseport + 3);
        } else {
                return -ENOTTY;
        }
        return err;
}

const struct file_operations uart_fops = {
        .open = uart_open,
        .read = uart_read,
        .write = uart_write,
        .unlocked_ioctl = uart_ioctl
};


/* register and init devices */
static int uart_init(void)
{
        int err = 0;

        if (option == OPTION_BOTH) {
                max_minor = 2;
        }
        if (option == OPTION_COM2_ONLY) {
                minor = 1;
        }

        err = register_chrdev_region(MKDEV(UART16550_MAJOR, minor), 
                                                max_minor, MODULE_NAME);

        if (err) {
                printk(LOG_LEVEL "Device busy\n");
                goto out;
        }
        if (option == OPTION_BOTH || option == OPTION_COM1_ONLY) {
                if (request_region(COM1_BASEPORT, 8, MODULE_NAME) == NULL) {
                        printk(LOG_LEVEL "Cannot alloc port com1\n");
                        err = -EBUSY;
                        goto out2;
                }
                devs[0].baseport = COM1_BASEPORT;
                
                init_waitqueue_head(&devs[0].wq_reads);
                init_waitqueue_head(&devs[0].wq_writes);

                if ((err = request_irq(COM1_IRQ, irq_handle, IRQF_SHARED,
                                                MODULE_NAME, &devs[0]))) {

                        printk(LOG_LEVEL "Request irq for com1 failed\n");
                        goto out3;
                }
                /*enable interrupts*/
                outb(0x08, COM1_BASEPORT + 4);
                outb(0x03, COM1_BASEPORT + 1);

                cdev_init(&devs[0].cdev, &uart_fops);
                cdev_add(&devs[0].cdev, MKDEV(UART16550_MAJOR, 0), 1);

        }
        if (option == OPTION_BOTH || option == OPTION_COM2_ONLY) {
                if (request_region(COM2_BASEPORT, 8, MODULE_NAME) == NULL) {
                        printk(LOG_LEVEL "Cannot alloc port com2\n");
                        err = -EBUSY;
                        goto out4;
                }

                devs[1].baseport = COM2_BASEPORT;
                init_waitqueue_head(&devs[1].wq_reads);
                init_waitqueue_head(&devs[1].wq_writes);

                if ((err = request_irq(COM2_IRQ, irq_handle, IRQF_SHARED,
                                                MODULE_NAME, &devs[1]))) {

                        printk(LOG_LEVEL "Request irq for com2 failed\n");
                        goto out5;
                }

                /*enable interrupts*/
                outb(0x08, COM2_BASEPORT + 4);
                outb(0x03, COM2_BASEPORT + 1);

                cdev_init(&devs[1].cdev, &uart_fops);
                cdev_add(&devs[1].cdev, MKDEV(UART16550_MAJOR, 1), 1);
        } 
        return 0;

/* if error release memory */
out5:
        if (option == OPTION_BOTH || option == OPTION_COM2_ONLY)
                release_region(COM2_BASEPORT, 8);

out4:
        if (option == OPTION_BOTH || option == OPTION_COM1_ONLY)
                free_irq(COM1_IRQ, &devs[0]);
out3:
        if (option == OPTION_BOTH || option == OPTION_COM1_ONLY)
                release_region(COM1_BASEPORT, 8);
out2:
       	unregister_chrdev_region(MKDEV(UART16550_MAJOR, 0), 1);
out:
        return err;        
}


/* disable interrupts and free memory*/
static void uart_exit(void)
{
         outb(0, devs[0].baseport + 1); 
         outb(0, devs[1].baseport + 1); 

         outb(0, COM1_BASEPORT + 4);
         outb(0, COM2_BASEPORT + 4);

        if (option == OPTION_BOTH || option == OPTION_COM1_ONLY) {
                free_irq(COM1_IRQ, &devs[0]);
                release_region(COM1_BASEPORT, 8);
                cdev_del(&devs[0].cdev);
        }

        if (option == OPTION_BOTH || option == OPTION_COM2_ONLY) {
                free_irq(COM2_IRQ, &devs[1]);
                release_region(COM2_BASEPORT, 8);
                cdev_del(&devs[1].cdev);
        }
       	unregister_chrdev_region(MKDEV(UART16550_MAJOR, minor), max_minor);
}

module_init(uart_init);
module_exit(uart_exit);

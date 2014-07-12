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
#include <linux/interrupt.h>
#include <asm/ioctl.h>
#include "uart16550.h"

#define OPTION_COM1 1
#define OPTION_COM2 2
#define OPTION_BOTH	3

#define MY_DEV_COM1 0
#define MY_DEV_COM2 1
#define MY_DEVS 2

#define MY_IRQ_COM1 4
#define MY_IRQ_COM2 3

#define COM1_BASEPORT 0x3f8
#define COM2_BASEPORT 0x2f8

MODULE_AUTHOR("Mihail Draghici - 343C1");
MODULE_DESCRIPTION("Tema 2 - Driver UART"); 
MODULE_LICENSE("GPL");


int major = UART16550_MAJOR;
int option = OPTION_BOTH;

module_param(option, int, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
module_param(major, int, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
        
#define COM_1_MINOR               0
#define COM_2_MINOR               1 
#define BUFFER_SIZE               PAGE_SIZE

struct uart_dev {
    struct cdev cdev;
    char buffer_read[BUFFER_SIZE];
    char buffer_write[BUFFER_SIZE];
    unsigned put, get;
    atomic_t fill_read;
    atomic_t fill_write;
    wait_queue_head_t wq_reads, wq_writes;
    int port;
} devs[2];

irqreturn_t my_handler_com(int irq_no, void *dev_id)
{
    /*struct uart_dev *dev = (struct uart_dev *) dev_id;
    unsigned char val;
    unsigned char c;

    // Set DLAB(7) to 0 -> LCR (+3)    
    val = inb(dev->port + 3);
    val = val & (!(1 << 7));
    outb(val, dev->port + 3);

    // Get Received Data Available Interrupt and Transmitter Holding Register Empty Interrupt from IIR(+2) 
    val = inb(dev->port + 2);
    // If Received Data Available Interrupt is enabled
    if ((val & (1 << 2)) && (!(val & (1 << 1)))) {
        // Check Data Ready(0) -> LSR(+5)
        val = inb(dev->port + 5);
        while (val & 1){
            if ((atomic_read(&dev->fill_read) < BUFFER_SIZE)) {
                c = inb(dev->port);

                dev->buffer_write[dev->put]=c;
                dev->get=(dev->get+1)%BUFFER_SIZE;
                atomic_inc(&dev->fill_read);

                val = inb(dev->port + 5); 
            } else {
                break;
            }
        }
        wake_up(&dev->wq_reads);
    // If Transmitter Holding Register Empty Interrupt is enabled
    } else if ((!(val & (1 << 2))) && (val & (1 << 1))) {
        // Check Empty Transmitter Holding Register(5) and Empty Data Holding Registers(6)-> LSR(+5)
        val = inb(dev->port + 5);
        while (!(val & (1 << 5) || val & (1 << 6))) {
            if ((atomic_read(&dev->fill_write) > 0)) {
                outb(dev->buffer_read[dev->get], dev->port);
                
                dev->put++; dev->put%=BUFFER_SIZE;
                atomic_dec(&dev->fill_write);

                val = inb(dev->port + 5);               
            } else {
                break;
            }
        }
        wake_up(&dev->wq_writes);
    }*/
 
    return IRQ_HANDLED;
}

static int uart_open(struct inode *inode, struct file *file)
{
    struct uart_dev *dev=container_of(inode->i_cdev, struct uart_dev, cdev);
	
    file->private_data=dev;
    return 0;
}

static int uart_close(struct inode *inode, struct file *file)
{
    return 0;
}

static int uart_read(struct file *file, char *user_buffer, 
    size_t size, loff_t *offset)
{
    int i=0;
	/*struct uart_dev *dev=(struct uart_dev*)file->private_data;

	if (file->f_mode&O_NONBLOCK) {
		if (atomic_read(&dev->fill_read) == 0)
			return -EAGAIN;
    } else {
		if (wait_event_interruptible(dev->wq_reads, atomic_read(&dev->fill_read) > 0))
            return -ERESTARTSYS;
    }

    while ((atomic_read(&dev->fill_read)>0) && size) {
        if (put_user(dev->buffer_read[dev->get], &user_buffer[i])) 
            return -EFAULT;
        dev->get++; dev->get%=BUFFER_SIZE;
        i++; size--; atomic_dec(&dev->fill_read);
    }*/

    return i;
}


static int uart_write(struct file *file, const char *user_buffer, 
        size_t size, loff_t *offset)
{
    unsigned char val;
    int i=0;
	/*struct uart_dev *dev=(struct uart_dev*)file->private_data;

    if (file->f_mode&O_NONBLOCK) {
		if (atomic_read(&dev->fill_write) == BUFFER_SIZE)
            return -EAGAIN;
	} else {
		if (wait_event_interruptible(dev->wq_writes, atomic_read(&dev->fill_write) < BUFFER_SIZE))
			return -ERESTARTSYS;
    }


    while ((atomic_read(&dev->fill_write) < BUFFER_SIZE) && size) {
	    char c;
	
        if (get_user(c, &user_buffer[i])) 
                return -EFAULT;
       
        dev->buffer_write[dev->put]=c;
        dev->put=(dev->put+1)%BUFFER_SIZE;
        i++; size--; atomic_inc(&dev->fill_write);
    }

	// Enable THREI (set ETHREI)
    val = inb(dev->port + 1);
    val = val | (1 << 1);
    outb(val, dev->port + 1);

    // Set THREI(1,2) to 1, 0-> IIR(+2)
    val = inb(dev->port + 2);
    val = val | (1 << 1);
    val = val & (1 << 2);
    outb(val, dev->port + 2);
    */
    return i;
}

static long uart_ioctl (struct file *file, unsigned int cmd, unsigned long arg)
{
    int ret = 0;
    /*int baud, len, par, stop;
    unsigned char val;    
    struct uart_dev* dev;    
    struct uart16550_line_info line_info;
    
    // Get line info
    if ((ret = copy_from_user(&line_info, (struct uart16550_line_info *) arg, 
                         sizeof(struct uart16550_line_info)))) {
        return -ret;
    }

    // Get device data
    dev = (struct uart_dev *) file->private_data;

    switch (cmd) {
        case UART16550_IOCTL_SET_LINE:
            baud = line_info.baud;         
            if (!(baud == UART16550_BAUD_1200  || baud == UART16550_BAUD_2400  || 
                  baud == UART16550_BAUD_4800  || baud == UART16550_BAUD_9600  ||
                  baud == UART16550_BAUD_19200 || baud == UART16550_BAUD_38400 ||
                  baud == UART16550_BAUD_56000 || baud == UART16550_BAUD_115200)) {
                return -ENOTTY;            
            } else {
                // LCR bit 7 -> 1 (Activate Divisor Latch Access Bit)
                val = inb(dev->port + 3);
                val = val | (1 << 7);                
                outb(val, dev->port + 3);

                // Set Divisor Latch Low Byte (+0) 
                // No need to change Latch High Byte (maximum value = 96)
                val = inb(dev->port);
                val = val | baud;
                outb(val, dev->port);

                // LCR bit 7 -> 0 (Deactivate Divisor Latch Access Bit)
                val = inb(dev->port + 3);
                val = val & (!(1 << 7));
                outb(val, dev->port + 3);
            }
            
            len = line_info.len;
            if (!(len == UART16550_LEN_5 || len == UART16550_LEN_6 ||
                  len == UART16550_LEN_7 || len == UART16550_LEN_8)) {
                return -ENOTTY;
            } else {
                // Set word length(0,1) -> LCR (+3)
                val = inb(dev->port + 3);
                val = val | len;
                outb(val, dev->port + 3);
            }

            par = line_info.par;
            if (!(par == UART16550_STOP_1 || par == UART16550_STOP_1)) {
                return -ENOTTY;        
            } else {
                // Set the length of stop bit(2) -> LCR (+3)
                val = inb(dev->port + 3);
                val = val | par;
                outb(val, dev->port + 3);
            }

            stop = line_info.stop;
            if (!(stop == UART16550_PAR_NONE || stop == UART16550_PAR_ODD ||
                  stop == UART16550_PAR_EVEN || stop == UART16550_PAR_STICK)) {
                return -ENOTTY;
            } else {
                // Set parity(3, 4, 5) -> LCR (+3)
                val = inb(dev->port + 3);
                val = val | stop;
                outb(val, dev->port + 3);
            }

            break;
        default:
            return -ENOTTY;
    }*/
 
    return ret;
}

struct file_operations uart_fops = {
	.owner		= THIS_MODULE,
	.open		= uart_open,
    .read 		= uart_read,
    .write 		= uart_write,
	.release	= uart_close,
    .unlocked_ioctl = uart_ioctl
};

int register_device(void) {
    int err = 0;

	if (option == OPTION_COM1) {
        err = register_chrdev_region(MKDEV(major, MY_DEV_COM1), 1, "uart16550");
    } else if (option == OPTION_COM2) { 
        err = register_chrdev_region(MKDEV(major, MY_DEV_COM2), 1, "uart16550");
    } else if (option == OPTION_BOTH) {
        err = register_chrdev_region(MKDEV(major, MY_DEV_COM1), MY_DEVS, "uart16550");
    } else {
    }

    if (err) {
        return -err;
    }

    if (option == OPTION_BOTH || option == OPTION_COM1) {
        init_waitqueue_head(&devs[MY_DEV_COM1].wq_reads);
        init_waitqueue_head(&devs[MY_DEV_COM1].wq_writes);
        cdev_init(&devs[MY_DEV_COM1].cdev, &uart_fops);
        cdev_add(&devs[MY_DEV_COM1].cdev, MKDEV(major, MY_DEV_COM1), 1);
        devs[MY_DEV_COM1].port = COM1_BASEPORT; 
    }
    if (option == OPTION_BOTH || option == OPTION_COM2) {    
        init_waitqueue_head(&devs[MY_DEV_COM2].wq_reads);
        init_waitqueue_head(&devs[MY_DEV_COM2].wq_writes);
        cdev_init(&devs[MY_DEV_COM2].cdev, &uart_fops);
        cdev_add(&devs[MY_DEV_COM2].cdev, MKDEV(major, MY_DEV_COM2), 1);
        devs[MY_DEV_COM2].port = COM2_BASEPORT; 
    }
    return 0;
}

int register_interrupt(void) {
    int err = 0;

    if (option == OPTION_COM1 || option == OPTION_BOTH) {
        request_region(COM1_BASEPORT, 8, "uart16550");
        err = request_irq(MY_IRQ_COM1, my_handler_com, IRQF_SHARED,
                   "uart16550", &devs[MY_DEV_COM1]);
        outb(0x08, COM1_BASEPORT+4);
        outb(0x01, COM1_BASEPORT+1);
    } 
    if (option == OPTION_COM2 || option == OPTION_BOTH) { 
        request_region(COM2_BASEPORT, 8, "uart16550");
        err = request_irq(MY_IRQ_COM2, my_handler_com, IRQF_SHARED,
                   "uart16550", &devs[MY_DEV_COM2]);
        outb(0x08, COM2_BASEPORT+4);
        outb(0x01, COM2_BASEPORT+1);
    } else if (option != OPTION_COM1) {
    }

    return -err;
}

void free_device(void) {
    if (option == OPTION_BOTH || option == OPTION_COM1) {
        cdev_del(&devs[MY_DEV_COM1].cdev);
    }
    if (option == OPTION_BOTH || option == OPTION_COM2) {
        cdev_del(&devs[MY_DEV_COM2].cdev);        
    }

	if (option == OPTION_COM1) {
        unregister_chrdev_region(MKDEV(major, MY_DEV_COM1), 1);
    } else if (option == OPTION_COM2) { 
        unregister_chrdev_region(MKDEV(major, MY_DEV_COM2), 1);
    } else if (option == OPTION_BOTH) {
        unregister_chrdev_region(MKDEV(major, MY_DEV_COM1), MY_DEVS);
    }
}

void free_interrupt(void) {
    if (option == OPTION_BOTH || option == OPTION_COM1) {
        free_irq(MY_IRQ_COM1, &devs[MY_DEV_COM1]);
        release_region(COM1_BASEPORT, 8);
    }
    if (option == OPTION_BOTH || option == OPTION_COM2) {
        free_irq(MY_IRQ_COM2, &devs[MY_DEV_COM2]);    
        release_region(COM2_BASEPORT, 8);
    }
}

int init_module(void)
{
    int err = 0;
    
    if ((err = register_interrupt())) {
        return -err;
    }
    if ((err = register_device())) {
        return -err;
    }
    return -err;
}

void cleanup_module(void)
{	
    free_interrupt();
    free_device();
}

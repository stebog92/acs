#include <linux/module.h>
#include <linux/init.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <linux/cdev.h>
#include <asm/uaccess.h>
#include <linux/sched.h>
#include <linux/wait.h>
#include <linux/miscdevice.h>
#include <linux/proc_fs.h>
#include <linux/seq_file.h>
#include <linux/kprobes.h>
#include <linux/delay.h>
#include <linux/slab.h>
#include <linux/list.h>
#include "tracer.h"



MODULE_DESCRIPTION("Tema 1");
MODULE_AUTHOR("Mihai Ciocan");
MODULE_LICENSE("GPL");

#define LOG_LEVEL	KERN_ALERT
#define MY_MAJOR		        10
#define JPROBE_SCHEDULE         0
#define JPROBE_UP               1
#define JPROBE_DOWN             2
#define JPROBE_LOCK             3
#define JPROBE_UNLOCK           4

static struct proc_dir_entry *proc_file = NULL;
struct mem {
        unsigned long addr;
        int size;
};


struct _process_list {

        pid_t pid;
        struct mem dyn_mem[128];
        int mem_list_size;
        struct list_head list;
        int memory_alloc;
        int memory_free;
        int allocs;
        int frees;
        int sched;
        int up_int;
        int down_int;
        int lock;
        int unlock;
};

LIST_HEAD(process_list);

DEFINE_SPINLOCK(lock);

/* insert a new traced process in list*/
static int add_process(pid_t pid) 
{

         struct _process_list *new_entry;
         new_entry = kmalloc(sizeof *new_entry, GFP_KERNEL);
         if (!new_entry)
                 return -ENOMEM;

         new_entry->pid = pid;
         new_entry->mem_list_size = 0;
         new_entry->memory_alloc = 0;
         new_entry->memory_free = 0;
         new_entry->allocs = 0;
         new_entry->frees = 0;
         new_entry->up_int = 0;
         new_entry->down_int = 0;
         new_entry->lock = 0;
         new_entry->unlock = 0;
         new_entry->sched = 0;

         spin_lock(&lock);
         list_add(&new_entry->list, &process_list);
         spin_unlock(&lock);
         return 0;
}

/* remove a process from traced process list*/
static int delete_process(pid_t pid)
{
        struct list_head *i, *tmp;
        struct _process_list *entry;
        
        spin_lock(&lock);
        list_for_each_safe(i, tmp, &process_list) {
                entry = list_entry(i, struct _process_list, list);
                if (entry->pid == pid) {
                        list_del(i);
                        spin_unlock(&lock);
                        kfree(entry);
                        return 0;
                }
        }
        spin_unlock(&lock);
        return -EINVAL;
}

/* data between entry handler and handler */
struct data {
        int size;
};

static int kmalloc_ret_entry_handler(struct kretprobe_instance *ri,
                                     struct pt_regs *regs) 
{
        struct data *_data;
        
        spin_lock(&lock);
        _data = (struct data*) ri->data;
        _data->size = regs->ax;

        spin_unlock(&lock);
        return 0;
}

/* process kmalloc*/
static int kmalloc_ret_handler(struct kretprobe_instance *ri, 
                               struct pt_regs *regs) 
{
        struct list_head *i, *tmp;
        struct _process_list *entry;
        int size;
        //printk (LOG_LEVEL "kmalloc %d\n", current->pid);
        spin_lock(&lock);
        list_for_each_safe(i, tmp, &process_list) {
                entry = list_entry(i, struct _process_list, list);
                if (entry->pid == ri->task->pid) {
                        entry->dyn_mem[entry->mem_list_size].addr = regs->ax;
                        size = ((struct data*)ri->data)->size;
                        entry->dyn_mem[entry->mem_list_size].size = size;
                        entry->memory_alloc += size;
                        entry->mem_list_size++;
                        entry->allocs++;
                        break;
                }
        }
        spin_unlock(&lock);
        return 0;
}

/* process kfree*/
static int kfree_ret_handler(struct kretprobe_instance *ri, 
                             struct pt_regs *regs) 
{
        int j, size;
        struct list_head *i, *tmp;
        struct _process_list *entry;
        
        spin_lock(&lock);
        list_for_each_safe(i, tmp, &process_list) {
                entry = list_entry(i, struct _process_list, list);
                if (entry->pid == ri->task->pid) {
                        for (j = 0; j < entry->mem_list_size; j++) {
                                if (entry->dyn_mem[j].addr == regs->ax) {
                                        size = entry->dyn_mem[j].size;
                                        entry->memory_free += size;
                                        entry->frees++;
                                        entry->dyn_mem[j].addr = 0;
                                        break;
                                }
                        }
                        break;
                }
        }
        spin_unlock(&lock);
        return 0;
}


/* process schedule call*/
static int schedule_handler(char * filename,
                char __user *__user *argv,
                char __user *__user *envp,
                struct pt_regs * regs)
{
        struct list_head *i, *tmp;
        struct _process_list *entry;
        
        spin_lock(&lock);
        list_for_each_safe(i, tmp, &process_list) {
                entry = list_entry(i, struct _process_list, list);
                if (entry->pid == current->pid) {
                        entry->sched++;
                        break;
                }
        }
        spin_unlock(&lock);
        jprobe_return();
        return 0;
}

/*process up call*/
static int up_int_handler(char * filename,
                char __user *__user *argv,
                char __user *__user *envp,
                struct pt_regs * regs)
{
        struct list_head *i, *tmp;
        struct _process_list *entry;
        
        spin_lock(&lock);
        list_for_each_safe(i, tmp, &process_list) {
                entry = list_entry(i, struct _process_list, list);
                if (entry->pid == current->pid) {
                        entry->up_int++;
                        break;
                }
        }
        spin_unlock(&lock);
        jprobe_return();
        return 0;
}

/* process down_interruptible call */
static int down_int_handler(char * filename,
                char __user *__user *argv,
                char __user *__user *envp,
                struct pt_regs * regs)
{
        struct list_head *i, *tmp;
        struct _process_list *entry;
        
        spin_lock(&lock);
        list_for_each_safe(i, tmp, &process_list) {
                entry = list_entry(i, struct _process_list, list);
                if (entry->pid == current->pid) {
                        entry->down_int++;
                        break;
                }
        }
        spin_unlock(&lock);
        jprobe_return();
        return 0;
}

/* process mutex_lock call*/
static int mutex_lock_handler(char * filename,
                char __user *__user *argv,
                char __user *__user *envp,
                struct pt_regs * regs)
{
        struct list_head *i, *tmp;
        struct _process_list *entry;
        
        spin_lock(&lock);
        list_for_each_safe(i, tmp, &process_list) {
                entry = list_entry(i, struct _process_list, list);
                if (entry->pid == current->pid) {
                        entry->lock++;
                        break;
                }
        }
        spin_unlock(&lock);
        jprobe_return();
        return 0;
}

/* process mutex_unlock call */
static int mutex_unlock_handler(char * filename,
                char __user *__user *argv,
                char __user *__user *envp,
                struct pt_regs * regs)
{
        struct list_head *i, *tmp;
        struct _process_list *entry;
        
        spin_lock(&lock);
        list_for_each_safe(i, tmp, &process_list) {
                entry = list_entry(i, struct _process_list, list);
                if (entry->pid == current->pid) {
                        entry->unlock++;
                        break;
                }
        }
        spin_unlock(&lock);
        jprobe_return();
        return 0;
}


static struct kretprobe kmalloc_kretprobe = {
        .entry_handler = kmalloc_ret_entry_handler,
        .handler = kmalloc_ret_handler,
        .data_size = sizeof(struct data),
        .maxactive = 20
};

static struct kretprobe kfree_kretprobe = {
        .entry_handler = kfree_ret_handler
};

static struct jprobe jprobes[5];

static struct miscdevice tracer_misc_device = {
        .minor = TRACER_DEV_MINOR
};

struct dev_data {
        struct cdev cdev;
};

struct dev_data tracer_data;

/* print stats in /proc/tracer */
static int show_stats(struct seq_file *m, void *v)
{

        struct list_head *i, *tmp;
        struct _process_list *entry;
        
        spin_lock(&lock);
        seq_printf(m, "PID kmalloc kfree kmalloc_mem kfree_mem"
                        " sched up down lock unlock\n");


        list_for_each_safe(i, tmp, &process_list) {
                entry = list_entry(i, struct _process_list, list);
                seq_printf(m, "%d %d %d %d %d %d %d %d %d %d\n", entry->pid,
                                entry->allocs,
                                entry->frees,
                                entry->memory_alloc,
                                entry->memory_free,
                                entry->sched,
                                entry->up_int,
                                entry->down_int,
                                entry->lock,
                                entry->unlock);
        }
        spin_unlock(&lock);
        return 0;
}



static int file_open(struct inode *inode, struct  file *file)
{
        return single_open(file, show_stats, NULL);
}

/* get address of symbol name using kallsyms_lookup_name*/
static kprobe_opcode_t* get_addr(char* name) 
{
        return (kprobe_opcode_t *)kallsyms_lookup_name(name);
}


/* register probes when open */
static int tracer_open(struct inode *inode, struct file *file)
{

        int ret;
        printk (LOG_LEVEL "Tracer opened\n");
        kmalloc_kretprobe.kp.symbol_name = "__kmalloc";

        if ((ret = register_kretprobe(&kmalloc_kretprobe)) < 0) {
                printk(LOG_LEVEL "register_kretprobe failed, returned %d\n", ret);
                return -1;
        }
        printk(LOG_LEVEL "Planted kretprobe at %p, handler addr %p\n",
                        kmalloc_kretprobe.kp.addr, kmalloc_kretprobe.handler);

        kfree_kretprobe.kp.symbol_name = "kfree";

        if ((ret = register_kretprobe(&kfree_kretprobe)) <0) {
                printk(LOG_LEVEL "register_kretprobe failed, returned %d\n", 
                        ret);
                return -1;
        }
        printk(LOG_LEVEL "Planted kretprobe at %p, handler addr %p\n",
                        kfree_kretprobe.kp.addr, kfree_kretprobe.entry_handler);

   
        jprobes[JPROBE_SCHEDULE].kp.addr = get_addr("schedule");
        jprobes[JPROBE_SCHEDULE].entry =  (kprobe_opcode_t *)schedule_handler;
        register_jprobe(&jprobes[JPROBE_SCHEDULE]);

        jprobes[JPROBE_UP].kp.addr = get_addr("up");
        jprobes[JPROBE_UP].entry =  (kprobe_opcode_t *)up_int_handler;
        register_jprobe(&jprobes[JPROBE_UP]);

        jprobes[JPROBE_DOWN].kp.addr = get_addr("down_interruptible");
        jprobes[JPROBE_DOWN].entry =  (kprobe_opcode_t *)down_int_handler;
        register_jprobe(&jprobes[JPROBE_DOWN]);

        jprobes[JPROBE_LOCK].kp.addr = get_addr("mutex_lock_nested");
        jprobes[JPROBE_LOCK].entry =  (kprobe_opcode_t *)mutex_lock_handler;
        register_jprobe(&jprobes[JPROBE_LOCK]);

        jprobes[JPROBE_UNLOCK].kp.addr = get_addr("mutex_unlock");
        jprobes[JPROBE_UNLOCK].entry =  (kprobe_opcode_t *)mutex_unlock_handler;
        register_jprobe(&jprobes[JPROBE_UNLOCK]);

        return 0;
}

/* unregister probes */
static int tracer_release (struct inode *inode, struct file *file)
{

        unregister_kretprobe(&kfree_kretprobe);
        unregister_kretprobe(&kmalloc_kretprobe);
        unregister_jprobe(&jprobes[JPROBE_SCHEDULE]);
        unregister_jprobe(&jprobes[JPROBE_UP]);
        unregister_jprobe(&jprobes[JPROBE_DOWN]);
        unregister_jprobe(&jprobes[JPROBE_LOCK]); 
        unregister_jprobe(&jprobes[JPROBE_UNLOCK]);
        return 0;
}


/* ioctl adds and removes process for tracing*/
static long tracer_ioctl (struct file *file, 
                          unsigned int cmd, 
                          unsigned long arg)
{

        switch(cmd) {
                case TRACER_ADD_PROCESS:
                        //printk (LOG_LEVEL "Process added %d\n", (int)arg);
                        add_process((int)arg);
                        break;
                case TRACER_REMOVE_PROCESS:
                        //printk (LOG_LEVEL "Process removed %d\n", (int)arg);
                        delete_process((int)arg);
                        break;
                default:
                        return -ENOTTY;

        }
        return 0;
}

static const struct file_operations r_fops = {
    .open		= file_open,
    .read		= seq_read,
    .release	= single_release,
};

const struct file_operations tracer_fops = {
        .open = tracer_open,
        .release = tracer_release,
        .unlocked_ioctl = tracer_ioctl
};

/* registers module*/
static int tracer_init(void)
{
        int err;

        proc_file = proc_create("tracer", 0, NULL, &r_fops);
        if (!proc_file)
                return -ENOMEM;

        tracer_misc_device.fops = &tracer_fops;
        tracer_misc_device.name = TRACER_DEV_NAME; 

        err = misc_register(&tracer_misc_device);

        if (err != 0) {
                return err;
        }

        cdev_init(&tracer_data.cdev, &tracer_fops);
        cdev_add(&tracer_data.cdev, MKDEV(MY_MAJOR, TRACER_DEV_MINOR), 1);
        return 0;

}

/* unregisters module*/
static void tracer_exit(void)
{
        cdev_del(&tracer_data.cdev);
        misc_deregister(&tracer_misc_device);
        proc_remove(proc_file);
}

module_init(tracer_init);
module_exit(tracer_exit);

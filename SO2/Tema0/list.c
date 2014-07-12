#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/module.h>

#include <linux/slab.h>
#include <linux/list.h>

#include <linux/proc_fs.h>
#include <linux/seq_file.h>

#include <asm/uaccess.h>

MODULE_DESCRIPTION("List Data Structure in procfs");
MODULE_AUTHOR("Laura Vasilescu");
MODULE_LICENSE("GPL");

#define procfs_dir_name		"list"
#define procfs_file_read	"preview"
#define procfs_file_write	"management"

static struct proc_dir_entry *proc_list = NULL;
static struct proc_dir_entry *proc_list_read = NULL;
static struct proc_dir_entry *proc_list_write = NULL;

#define PROCFS_MAX_SIZE		1024

struct proc_list {
    char name[100];
    struct list_head list;
};

LIST_HEAD(name_list);

void delete_name(char* name, int all);

static int list_proc_show(struct seq_file *m, void *v)
{
    struct list_head *i, *tmp;
    struct proc_list *temp;


    list_for_each_safe(i, tmp, &name_list) {
        temp = list_entry(i, struct proc_list, list);
        seq_printf(m, "%s", temp->name);
    }

    return 0;
}

static int list_read_open(struct inode *inode, struct  file *file) {
    return single_open(file, list_proc_show, NULL);
}

static int list_write_open(struct inode *inode, struct  file *file) {
    return single_open(file, list_proc_show, NULL);
}

static ssize_t list_write(struct file *file, const char __user *buffer, size_t count,
        loff_t *offs)
{
    char local_buffer[PROCFS_MAX_SIZE];
    unsigned long local_buffer_size = 0;
    struct proc_list *new_node;

    local_buffer_size = count;
    if (local_buffer_size > PROCFS_MAX_SIZE ) {
        local_buffer_size = PROCFS_MAX_SIZE;
    }

    memset(local_buffer, 0, PROCFS_MAX_SIZE);
    if (copy_from_user(local_buffer, buffer, local_buffer_size) ) {
        return -EFAULT;
    }

    new_node = kmalloc(sizeof *new_node, GFP_KERNEL);

    if (!strncmp(local_buffer, "addf", 4)) {
        /* add name at the beginning of the list */
        strcpy(new_node->name, local_buffer + 5); 
        list_add(&new_node->list, &name_list);

    } else if (!strncmp(local_buffer, "adde", 4)) {
        /* add name at the end of the list */
        strcpy(new_node->name, local_buffer + 5);
        list_add_tail(&new_node->list, &name_list);

    } else if (!strncmp(local_buffer, "delf", 4)) {
        /* remove first instance of name in list */
        delete_name(local_buffer + 5, 0);

    } else if (!strncmp(local_buffer, "dela", 4)) {
        /* remove all instances of name in list */
        delete_name(local_buffer + 5, 1);
    }

    return local_buffer_size;
}

/* remove name from list */
void delete_name(char* name, int all) {
    struct list_head *i, *tmp;
    struct proc_list *temp;

    list_for_each_safe(i, tmp, &name_list) {
        temp = list_entry(i, struct proc_list, list);
        if (!strcmp(temp->name, name)) {
            list_del(i);
            kfree(temp);

            /* if all is not set then stop */
            if (!all)
                break;
        }
    }
}

static const struct file_operations r_fops = {
    .owner		= THIS_MODULE,
    .open		= list_read_open,
    .read		= seq_read,
    .release	= single_release,
};

static const struct file_operations w_fops = {
    .owner		= THIS_MODULE,
    .open		= list_write_open,
    .write		= list_write,
    .release	= single_release,
};

static int list_init(void)
{
    proc_list = proc_mkdir(procfs_dir_name, NULL);
    if (!proc_list)
        return -ENOMEM;

    proc_list_read = proc_create(procfs_file_read, 0, proc_list, &r_fops);
    if (!proc_list_read)
        goto proc_list_cleanup;

    proc_list_write = proc_create(procfs_file_write, 0, proc_list, &w_fops);
    if (!proc_list_write)
        goto proc_list_cleanup;

    return 0;

proc_list_cleanup:
    proc_remove(proc_list);
    return -ENOMEM;
}

static void list_exit(void)
{
    proc_remove(proc_list);
}

module_init(list_init);
module_exit(list_exit);

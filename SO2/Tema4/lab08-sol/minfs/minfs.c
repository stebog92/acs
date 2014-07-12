/*
 * SO2 Lab - Filesystem drivers (part 1)
 * Exercise #2 (dev filesystem)
 */

#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/module.h>
#include <linux/fs.h>
#include <linux/pagemap.h>
#include <linux/buffer_head.h>
#include <linux/slab.h>

#include "minfs.h"

MODULE_DESCRIPTION("Simple filesystem");
MODULE_AUTHOR("SO2");
MODULE_LICENSE("GPL");


#define LOG_LEVEL	KERN_ALERT


struct minfs_sb_info {
	__u8 version;
};

struct minfs_inode_info {
	__u16 data_block;
	struct inode vfs_inode;
};

static struct inode *minfs_iget(struct super_block *s, unsigned long ino)
{
	struct minfs_inode *mi;
	struct buffer_head *bh;
	struct inode *inode;
	struct minfs_inode_info *mii;

	/* allocate VFS inode */
	inode = iget_locked(s, ino);
	if (inode == NULL) {
		printk(LOG_LEVEL "error aquiring inode\n");
		return ERR_PTR(-ENOMEM);
	}

	/* TODO 4: read disk inode block (2nd block; index 1) */
	if (!(bh = sb_bread(s, 1)))
		goto out_bad_sb;

	/* TODO 4: interpret disk inode as minfs_inode */
	mi = (struct minfs_inode*)bh->b_data;

	/* TODO 4: fill VFS inode */
	inode->i_mode = mi->mode;
	inode->i_uid = mi->uid;
	inode->i_gid = mi->gid;
	inode->i_size = mi->size;
	inode->i_blocks = 0;
	inode->i_mtime = inode->i_atime = inode->i_ctime = CURRENT_TIME;

	if(S_ISDIR(inode->i_mode)) {
		inode->i_op = &simple_dir_inode_operations;
		inode->i_fop = &simple_dir_operations;
		inc_nlink(inode);
	}

	/* fill data for mii */
	mii = container_of(inode, struct minfs_inode_info, vfs_inode);
	mii->data_block = mi->data_block;

	/* free resources */
	brelse(bh);
	unlock_new_inode(inode);

	return inode;

out_bad_sb:
	iget_failed(inode);
	return NULL;
}

static struct inode *minfs_alloc_inode(struct super_block *s)
{
	struct minfs_inode_info *mii;

	/* TODO 3: allocate minfs_inode_info and init inode */
	mii = (struct minfs_inode_info *)
		kzalloc(sizeof(struct minfs_inode_info), GFP_KERNEL);
	if (!mii)
		return NULL;

	inode_init_once(&mii->vfs_inode);

	return &mii->vfs_inode;
}

static void minfs_destroy_inode(struct inode *inode)
{
	/* TODO 3: free minfs_inode_info */
	kfree(container_of(inode, struct minfs_inode_info, vfs_inode));
}

static const struct super_operations minfs_ops = {
	.statfs         = simple_statfs,
	/* TODO 4: add alloc and destroy inode functions */
	.alloc_inode    = minfs_alloc_inode,
	.destroy_inode  = minfs_destroy_inode,
};

static int minfs_fill_super(struct super_block *s, void *data, int silent)
{
	struct minfs_sb_info *sbi;
	struct minfs_super_block *ms;
	struct inode *root_inode;
	struct dentry *root_dentry;
	struct buffer_head *bh;
	int ret = -EINVAL;

	sbi = kzalloc(sizeof(struct minfs_sb_info), GFP_KERNEL);
	if (!sbi)
		return -ENOMEM;
	s->s_fs_info = sbi;

	/* set block size for superblock */
	if (!sb_set_blocksize(s, MINFS_BLOCK_SIZE))
		goto out_bad_blocksize;

	/* TODO 2: read block with superblock (1st block, index 0) */
	if (!(bh = sb_bread(s, 0)))
		goto out_bad_sb;

	/* TODO 2: interpret read data as minfs_super_block */
	ms = (struct minfs_super_block *) bh->b_data;

	/* TODO 2: check magic number; jump to out_bad_magic if not suitable */
	if (ms->magic != MINFS_MAGIC)
		goto out_bad_magic;

	/* TODO 2: fill sbi with information from disk superblock */
	sbi->version = ms->version;

	/* TODO 2: fill super_block with magic_number, super_operations */
	s->s_magic = MINFS_MAGIC;
	s->s_op = &minfs_ops;

	/* allocate root inode and root dentry */
	/* TODO 2: use myfs_get_inode instead of minfs_iget */
	root_inode = minfs_iget(s, 0);
	if (!root_inode)
		goto out_bad_inode;

	root_dentry = d_make_root(root_inode);
	if (!root_dentry)
		goto out_iput;
	s->s_root = root_dentry;

	brelse(bh);

	return 0;

out_iput:
	iput(root_inode);
out_bad_inode:
	printk(LOG_LEVEL "bad inode\n");
out_bad_magic:
	printk(LOG_LEVEL "bad magic number\n");
	brelse(bh);
out_bad_sb:
	printk(LOG_LEVEL "error reading buffer_head\n");
out_bad_blocksize:
	printk(LOG_LEVEL "bad block size\n");
	s->s_fs_info = NULL;
	kfree(sbi);
	return ret;
}

static struct dentry *minfs_mount(struct file_system_type *fs_type, int flags,
		const char *dev_name, void *data)
{
	/* TODO 1: register & unregister - call superblock initialization */
	return mount_bdev(fs_type, flags, dev_name, data, minfs_fill_super);
}

static struct file_system_type minfs_fs_type = {
	.owner = THIS_MODULE,
	.name = "minfs",
	/* TODO 1: register & unregister mount, kill_sb and fs_flags */
	.mount			= minfs_mount,
	.kill_sb		= kill_block_super,
	.fs_flags		= FS_REQUIRES_DEV,
};

static int __init minfs_init(void)
{
	int err;

	err = register_filesystem(&minfs_fs_type);
	if (err) {
		printk(LOG_LEVEL "register_filesystem failed\n");
		return err;
	}

	return 0;
}

static void __exit minfs_exit(void)
{
	unregister_filesystem(&minfs_fs_type);
}

module_init(minfs_init);
module_exit(minfs_exit);

/*
 * Sevastian Emma 341C4
 * Tema 4 SO2
 **/

#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/module.h>

#include <linux/fs.h>
#include <linux/pagemap.h>
#include <linux/buffer_head.h>
#include <linux/slab.h>

#include "pitix.h"

MODULE_DESCRIPTION("Driver de sistem de fisiere");
MODULE_AUTHOR("Emma Sevastian");
MODULE_LICENSE("GPL");

#define LOG_LEVEL	KERN_ALERT
#define PITIX_BLOCK_SIZE        4096

//struct pitix_inode pitixInode;

struct pitix_inode_info {
	__u16 *data_block;
	struct inode vfs_inode;
};

struct pitix_sb_info {
	unsigned long magic;
        __u8 version;
        __u8 inode_data_blocks;
        __u8 block_size_bits;
        __u8 imap_block;
        __u8 dmap_block;
        __u8 izone_block;
        __u8 dzone_block;
        __u16 bfree;
        __u16 ffree;
        struct buffer_head *sbh, *dbh, *ibh;
        __u8 *dmap, *imap;
};

struct inode *pitix_alloc_inode(struct super_block *sb)
{
	struct pitix_inode_info *pii;

	pii = kzalloc(sizeof(struct pitix_inode_info), GFP_KERNEL);
	if (pii == NULL)
		return NULL;

	inode_init_once(&pii->vfs_inode);

	return &pii->vfs_inode;	
}

static void pitix_destroy_inode(struct inode *inode)
{
	kfree(container_of(inode, struct pitix_inode_info, vfs_inode));
}

static void pitix_put_super(struct super_block *sb)
{
	struct pitix_super_block *sbi = sb->s_fs_info;

        /* free superblock buffer head */
        mark_buffer_dirty(sbi->sb_bh);
        brelse(sbi->sb_bh);

        printk(LOG_LEVEL "released superblock resources\n");
}

static int pitix_statfs(struct dentry *dentry, struct kstatfs *buf)
{
	printk(LOG_LEVEL "statfs\n");
	/*struct super_block *sb = dentry->d_sb;
	struct minix_sb_info *sbi = minix_sb(sb);

	u64 id = huge_encode_dev(sb->s_bdev->bd_dev);
	buf->f_type = sb->s_magic;
	buf->f_bsize = sb->s_blocksize;
	buf->f_blocks = (sbi->s_nzones - sbi->s_firstdatazone) << sbi->s_log_zone_size;
	
	buf->f_bfree = minix_count_free_blocks(sb);
	
	buf->f_bavail = buf->f_bfree;
	buf->f_files = sbi->s_ninodes;
	buf->f_ffree = minix_count_free_inodes(sb);
	buf->f_namelen = sbi->s_namelen;
	buf->f_fsid.val[0] = (u32)id;
	buf->f_fsid.val[1] = (u32)(id >> 32);
	*/
	return 0;
}

static const struct super_operations pitix_ops = {
	.statfs		= simple_statfs,
	.alloc_inode	= pitix_alloc_inode,
	.destroy_inode	= pitix_destroy_inode,
	.put_super	= pitix_put_super,
};

struct pitix_dir_entry *pitix_find_entry(struct dentry *dentry,
                			 struct buffer_head **bhp)
{
        struct buffer_head *bh;
        struct inode *dir = dentry->d_parent->d_inode;
        struct pitix_inode_info *mii = container_of(dir,
                        struct pitix_inode_info, vfs_inode);
        struct super_block *sb = dir->i_sb;
        const char *name = dentry->d_name.name;
        struct pitix_dir_entry *final_de = NULL;
        struct pitix_dir_entry *de;
        size_t i;

        /* read parent folder data block (contains dentries) */
        bh = sb_bread(sb, mii->data_block[0] + 
		((struct pitix_super_block*) sb->s_fs_info)->dzone_block);
        if (bh == NULL) {
                printk(LOG_LEVEL "could not read block\n");
                return NULL;
        }
        *bhp = bh;

        printk(LOG_LEVEL "Looking for dentry name %s in parent folder %s\n",
                        name, dentry->d_parent->d_name.name);
        /* traverse all entries */
        for (i = 0; i < dir_entries_per_block(sb); i++) {
                de = ((struct pitix_dir_entry *) bh->b_data) + i;
                if (de->ino != 0) {
                        /* found it */
                        if (strcmp(name, de->name) == 0) {
                                printk(LOG_LEVEL "Found entry %s on position: %zd\n",
                                                name, i);
                                final_de = de;
                                break;
                        }
                }
        }

        /* bh needs to be released by caller */
        return final_de;
}

struct dentry *pitix_lookup(struct inode *dir,
                	    struct dentry *dentry, unsigned int flags)
{
        struct super_block *sb = dir->i_sb;
        struct pitix_dir_entry *de;
        struct buffer_head *bh = NULL;
        struct inode *inode = NULL;

        dentry->d_op = sb->s_root->d_op;

        de = pitix_find_entry(dentry, &bh);
        if (de != NULL) {
                printk(LOG_LEVEL "getting entry: name: %s, ino: %d\n",
                                		    de->name, de->ino);
                inode = pitix_iget(sb, de->ino);
                if (IS_ERR(inode))
                        return ERR_CAST(inode);
        }

        d_add(dentry, inode);
        brelse(bh);

        printk(LOG_LEVEL "looked up dentry %s\n", dentry->d_name.name);

        return NULL;
}



int pitix_get_block(struct inode *inode, sector_t block,
			   struct buffer_head *bh_result, int create)
{
	struct super_block *sb = inode->i_sb;
	struct pitix_inode_info *pii = container_of(inode, struct pitix_inode_info, vfs_inode);
	
	map_bh(bh_result, sb, ((struct pitix_super_block*) sb->s_fs_info)->dzone_block 
				+ pii->data_block[block]);

	return 0;
}

static int pitix_readpage(struct file *file, struct page *page)
{
	return block_read_full_page(page, pitix_get_block);
}

struct address_space_operations pitix_aops = {
        .readpage       = pitix_readpage,
        .write_begin    = simple_write_begin,
        .write_end      = simple_write_end,
};

static int pitix_mkdir(struct inode *dir, struct dentry *dentry, umode_t mode)
{
        int ret;

//        ret = pitix_mknod(dir, dentry, mode | S_IFDIR, 0);
        if (ret != 0)
                return ret;

        inc_nlink(dir);

        return 0;
}

int pitix_getattr(struct vfsmount *mnt, struct dentry *dentry, struct kstat *stat)
{
	struct super_block *sb = dentry->d_sb;
	generic_fillattr(dentry->d_inode, stat);
	stat->blocks = 1;
	stat->blksize = sb->s_blocksize;
	stat->size = sb->s_blocksize;
	return 0;
}

struct inode_operations pitix_dir_inode_operations = {
        .lookup         = pitix_lookup,
	.unlink		= simple_unlink,
	.mkdir		= pitix_mkdir,
	.rmdir		= simple_rmdir,
	.getattr	= pitix_getattr,	
};

struct file_operations pitix_file_operations = {
        .read           = do_sync_read,
        .aio_read       = generic_file_aio_read,
        .write          = do_sync_write,
        .aio_write      = generic_file_aio_write,
        .mmap           = generic_file_mmap,
        .fsync          = noop_fsync,
        .splice_read    = generic_file_splice_read,
        .splice_write   = generic_file_splice_write,
        .llseek         = generic_file_llseek,
};

struct inode_operations pitix_file_inode_operations = {
        .getattr        = simple_getattr,
};

static int pitix_readdir(struct file *filp, struct dir_context *ctx)
{
        struct buffer_head *bh;
        struct pitix_dir_entry *de;
        struct inode *inode = file_inode(filp);
        struct pitix_inode_info *mii = container_of(inode,
                        struct pitix_inode_info, vfs_inode);
        struct super_block *sb = inode->i_sb;
        int err = 0;
        int over;

        /* read data block for directory inode */
        bh = sb_bread(sb, *mii->data_block);
        if (bh == NULL) {
                printk(LOG_LEVEL "could not read block\n");
                err = -ENOMEM;
                goto out_bad_sb;
        }
        printk(LOG_LEVEL "Read data block %d for folder %s\n", mii->data_block,
                        filp->f_path.dentry->d_name.name);

        for (; ctx->pos < dir_entries_per_block(sb); ctx->pos++) {
                de = (struct pitix_dir_entry *) bh->b_data + ctx->pos;
                if (de->ino != 0) {
                        over = dir_emit(ctx, de->name, PITIX_NAME_LEN,
                                        de->ino, DT_UNKNOWN);
                        if (over) {
                                printk(LOG_LEVEL "Read %s from folder %s, ctx->pos: %lld\n",
                                                de->name,
                                                filp->f_path.dentry->d_name.name,
                                                ctx->pos);
                                ctx->pos += 1;
                                goto done;
                        }
                }
        }

done:
        brelse(bh);
out_bad_sb:
        return err;
}

struct file_operations pitix_dir_operations = {
        .read           = generic_read_dir,
        .iterate        = pitix_readdir,
};

struct inode* pitix_iget(struct super_block *sb, unsigned long ino)
{
	struct pitix_inode *pi;
        struct buffer_head *sbh, *sbh2 = NULL, *dbh, *ibh;
        struct inode *inode;
        struct pitix_inode_info *pii;
	bool twoBlocks = false;
	__u16 begin;
	__u8 izone_block;
	printk(LOG_LEVEL "in pitix_iget\n");
	
        /* allocate VFS inode */
        inode = iget_locked(sb, ino);
        if (inode == NULL) {
                printk(LOG_LEVEL "error aquiring inode\n");
                return ERR_PTR(-ENOMEM);
        }
        if (!(inode->i_state & I_NEW))
                return inode;

	izone_block = ((struct pitix_super_block *) sb->s_fs_info)->izone_block;
        
	/* read disk inode block */
	sbh = sb_bread(sb, ino * inode_size(sb) / sb->s_blocksize + izone_block);
        if (sbh == NULL) {
                printk(LOG_LEVEL "could not read block\n");
        	return NULL;
	}

	/* if the inodes is contained by 2 blocks copy all the information */
	if (((ino * inode_size(sb)) % sb->s_blocksize) + inode_size(sb) > sb->s_blocksize) {
		
		printk(LOG_LEVEL "next\n");
		sbh2 = sb_bread(sb, ino * inode_size(sb) / sb->s_blocksize + izone_block + 1);
        	if (sbh2 == NULL) {
                	printk(LOG_LEVEL "could not read block\n");
                	return NULL;
        	}
		begin = (ino * inode_size(sb)) % sb->s_blocksize;
		pi = (void *)sbh->b_data + begin;
		/*memcpy(&pitixInode, 
		       (void *)sbh->b_data + begin, sizeof(struct pitix_inode));*/
		memcpy((void *)pi->data_blocks, 
		       (void *)sbh->b_data + begin + sizeof(struct pitix_inode), 
		       sb->s_blocksize - begin - sizeof(struct pitix_inode));
		memcpy((void *)pi->data_blocks + sb->s_blocksize
				- begin - sizeof(struct pitix_inode),
			sbh2->b_data, 
			inode_size(sb) - (sb->s_blocksize - begin));
		//pi = &pitixInode;
		brelse(sbh2);
	}
	/* else just the one contained in the first block */
	else {
		printk(LOG_LEVEL "not 2\n");
		pi = (void *)sbh->b_data + ino * inode_size(sb) % sb->s_blocksize;
		memcpy(pi->data_blocks, sbh->b_data + sizeof(struct pitix_inode) +
			ino * inode_size(sb) % sb->s_blocksize,
		  	sizeof(__u16) * ((struct pitix_super_block*) sb)->inode_data_blocks);
	}

        /* extract disk inode */
                /* fill VFS inode */

        inode->i_mode = pi->mode;
        inode->i_uid = pi->uid;
        inode->i_gid = pi->gid;
        inode->i_size = pi->size;
        inode->i_blocks = 0;
        inode->i_mtime = inode->i_atime = inode->i_ctime = CURRENT_TIME;
	inode->i_mapping->a_ops = &pitix_aops;

        if (S_ISDIR(inode->i_mode)) {
                inode->i_op = &pitix_dir_inode_operations;
                inode->i_fop = &pitix_dir_operations;

                /* directory inodes start off with i_nlink == 2 */
                //inc_nlink(inode);
        }
	if (S_ISREG(inode->i_mode)) {
                inode->i_op = &pitix_file_inode_operations;
                inode->i_fop = &pitix_file_operations;
        }

        /* fill data for mii */
        pii = container_of(inode, struct pitix_inode_info, vfs_inode);
        pii->data_block = pi->data_blocks;

        /* free resources */
        brelse(sbh);
        unlock_new_inode(inode);

        return inode;
}

int pitix_fill_super(struct super_block *sb, void *data, int silent)
{
	struct pitix_sb_info *pbi;
	struct pitix_super_block *ps;
	struct inode *root_inode;
	struct dentry *root_dentry;
	struct buffer_head *sbh, *dbh, *ibh;
	int ret = -EINVAL;
	
	pbi = kzalloc(sizeof(struct pitix_sb_info), GFP_KERNEL);
	if (!pbi)
		return -ENOMEM;
		
	/* set block size for superblock */
	if (!sb_set_blocksize(sb, PITIX_BLOCK_SIZE)) {
		printk(LOG_LEVEL "bad block size\n");
		sb->s_fs_info = NULL;
		return ret;
	}
	
	sbh = sb_bread(sb, PITIX_SUPER_BLOCK);
	if (sbh == NULL) {
		printk(LOG_LEVEL "error reading buffer_head\n");
		return ret;
	}

	/*dbh = sb_bread(sb, PITIX_DMAP_BLOCK);
	if (dbh == NULL) {
		printk(LOG_LEVEL "error reading buffer_head\n");
		return ret;
	}

	ibh = sb_bread(sb, PITIX_IMAP_BLOCK);
	if (ibh == NULL) {
		printk(LOG_LEVEL "error reading buffer_head\n");
		return ret;
	}*/

	/* extract disk superblock */
	ps = (struct pitix_super_block *) sbh->b_data;
	ps->sb_bh = sbh;
	sb->s_fs_info = ps;

	/* set new block size depending on what we have previously read */
	if (!sb_set_blocksize(sb, 2 << (ps->block_size_bits - 1))) {
		printk(LOG_LEVEL "bad block size\n");
		sb->s_fs_info = NULL;
		return ret;
	}

	pbi->version = ps->version;
	pbi->magic = ps->magic;
        pbi->inode_data_blocks = ps->inode_data_blocks;
        pbi->block_size_bits = ps->block_size_bits;
        pbi->imap_block = ps->imap_block;
        pbi->dmap_block = ps->dmap_block;
        pbi->izone_block = ps->izone_block;
        pbi->dzone_block = ps->dzone_block;
        pbi->bfree = ps->bfree;
        pbi->ffree = ps->ffree;
        pbi->dmap = ps->dmap;
	pbi->imap = ps->imap;

	/* complete stuff */
	sb->s_magic = PITIX_MAGIC;
	sb->s_op = &pitix_ops;

	root_inode = pitix_iget(sb, PITIX_ROOT_INODE);
	root_dentry = d_make_root(root_inode);

	sb->s_root = root_dentry;

	pbi->sbh = sbh;
	//pbi->dbh = dbh;
	//pbi->ibh = ibh;

	return 0;
}

static struct dentry *pitix_mount(struct file_system_type *fs_type, int flags,
				 const char *dev_name, void *data)
{
	return mount_bdev(fs_type, flags, dev_name, data, pitix_fill_super);
}

static struct file_system_type pitix_fs_type = {
	.owner 		= THIS_MODULE,
	.name  		= "pitix",
	.mount		= pitix_mount,
	.kill_sb	= kill_block_super,
	.fs_flags	= FS_REQUIRES_DEV,
};

static int pitix_init(void)
{
	int err;

	err = register_filesystem(&pitix_fs_type);
	if (err) {
		printk(LOG_LEVEL "register_filesystem failed\n");
		return err;
	}

	return 0;
}

static void pitix_exit(void)
{
	printk(LOG_LEVEL "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n");
	unregister_filesystem(&pitix_fs_type);
}

module_init(pitix_init);
module_exit(pitix_exit);

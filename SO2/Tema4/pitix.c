#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/module.h>
#include <linux/fs.h>
#include <linux/pagemap.h>
#include <linux/buffer_head.h>
#include <linux/slab.h>
#include <linux/statfs.h>

#include "pitix.h"

MODULE_DESCRIPTION("Pitix file file_system");
MODULE_AUTHOR("Mihai Ciocan");
MODULE_LICENSE("GPL");

#define LOG_LEVEL KERN_ALERT
#define DEBUG 0
#if DEBUG == 1
#define dprintk(fmt, ...)	\
	printk(LOG_LEVEL fmt, ##__VA_ARGS__)
#else
#define dprintk(fmt, ...)	\
	do {} while (0)
#endif

static int pitix_add_link(struct dentry *dentry, struct inode *inode);

static int pitix_readdir(struct file *filp, struct dir_context *ctx)
{
	struct buffer_head *bh;
	struct pitix_dir_entry *de;
	struct inode *inode = file_inode(filp);
	struct pitix_inode_info *mii = container_of(inode,
			struct pitix_inode_info, vfs_inode);
	struct super_block *sb = inode->i_sb;
	struct pitix_super_block *sbi = sb->s_fs_info;
	int err = 0;
	int over;

	/* read data block for directory inode */
	bh = sb_bread(sb, sbi->dzone_block + mii->data_block[0]);
	if (bh == NULL) {
		dprintk( "could not read block\n");
		err = -ENOMEM;
		goto out_bad_sb;
	}
	dprintk("Read data block %d for folder %s\n", mii->data_block[0],
			filp->f_path.dentry->d_name.name);

	for (; ctx->pos < dir_entries_per_block (sb); ctx->pos++) {
		de = (struct pitix_dir_entry *) bh->b_data + ctx->pos;
		if (de->ino != 0) {
			over = dir_emit(ctx, de->name, PITIX_NAME_LEN,
					de->ino, DT_UNKNOWN);
			if (over) {
				dprintk("Read %s from folder %s, ctx->pos: %lld\n",
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



void pitix_set_inode(struct inode *inode)
{
	if (S_ISREG(inode->i_mode)) {
		inode->i_op = &pitix_file_inode_operations;
		inode->i_fop = &pitix_file_operations;
		inode->i_mapping->a_ops = &pitix_aops;
	} else if (S_ISDIR(inode->i_mode)) {
		inode->i_op = &pitix_dir_inode_operations;
		inode->i_fop = &pitix_dir_operations;
		inode->i_mapping->a_ops = &pitix_aops;
	}
}


int pitix_alloc_block(struct super_block *sb)
{
	struct pitix_super_block *sbi = sb->s_fs_info;
	struct buffer_head* bh;
	int idx;

	idx = find_first_zero_bit((unsigned long*)sbi->dmap_bh->b_data,
					get_blocks(sb));
	if (idx < 0) {
		dprintk( "no space left in dmap\n");
		return -1;
	}

	__test_and_set_bit(idx, (unsigned long*)sbi->dmap_bh->b_data);
	mark_buffer_dirty(sbi->dmap_bh);
	dprintk("alloc block %d\n", 10 + idx);
	sbi->bfree--;
	return idx;
}

static int pitix_mkdir(struct inode * dir, struct dentry *dentry, umode_t mode)
{
	struct inode * inode;
	struct pitix_inode_info *mii;
	struct buffer_head* bh;
	struct pitix_super_block* sbi;
	int err = 0;


	inode = pitix_new_inode(dir, S_IFDIR | mode);
	sbi = inode->i_sb->s_fs_info;
	if (!inode)
		goto out_dir;

	pitix_set_inode(inode);

	inode_inc_link_count(inode);

	mii = container_of(inode, struct pitix_inode_info, vfs_inode);

	err = pitix_alloc_block(inode->i_sb);
	if (err < 0)
		goto out_fail;

	mii->data_block[0] = err;

	bh = sb_bread(inode->i_sb, sbi->dzone_block + mii->data_block[0]);
	memset(bh->b_data, 0, inode->i_sb->s_blocksize);
	mark_buffer_dirty(bh);
	brelse(bh);
	mark_inode_dirty(inode);

	err = pitix_add_link(dentry, inode);
	if (err)
		goto out_fail;

	d_instantiate(dentry, inode);
out:
	return err;

out_fail:
	printk("error add link\n");
	inode_dec_link_count(inode);
	iput(inode);
out_dir:
	inode_dec_link_count(dir);
	goto out;
}

struct file_operations pitix_dir_operations = {
	.read		= generic_read_dir,
	.iterate	= pitix_readdir,
};

static int pitix_block_size (struct super_block *s)
{
	struct pitix_super_block *psb = s->s_fs_info;
	switch (psb->block_size_bits) {
		case 9:
			return 512;
		case 10:
			return 1024;
		case 11:
			return 2048;
		case 12:
			return 4096;
		default:
			return 0;
	}


}

static struct pitix_dir_entry *pitix_find_entry(struct dentry *dentry,
		struct buffer_head **bhp)
{
	struct buffer_head *bh;
	struct inode *dir = dentry->d_parent->d_inode;
	struct pitix_inode_info *mii = container_of(dir,
			struct pitix_inode_info, vfs_inode);
	struct super_block *sb = dir->i_sb;
	struct pitix_super_block* sbi = sb->s_fs_info;
	const char *name = dentry->d_name.name;
	struct pitix_dir_entry *final_de = NULL;
	struct pitix_dir_entry *de;
	size_t i;

	/* read parent folder data block (contains dentries) */
	bh = sb_bread(sb, sbi->dzone_block + mii->data_block[0]);
	if (bh == NULL) {
		dprintk( "could not read block\n");
		return NULL;
	}
	*bhp = bh;

	dprintk("Looking for dentry name %s in parent folder %s\n",
			name, dentry->d_parent->d_name.name);
	/* traverse all entries */
	for (i = 0; i < dir_entries_per_block(sb); i++) {
		de = ((struct pitix_dir_entry *) bh->b_data) + i;
		if (de->ino != 0) {
			/* found it */
			if (strcmp(name, de->name) == 0) {
				dprintk("Found entry %s on position: %zd\n",
						name, i);
				final_de = de;
				break;
			}
		}
	}

	/* bh needs to be released by caller */
	return final_de;
}

static struct dentry *pitix_lookup(struct inode *dir,
		struct dentry *dentry, unsigned int flags)
{
	struct super_block *sb = dir->i_sb;
	struct pitix_dir_entry *de;
	struct buffer_head *bh = NULL;
	struct inode *inode = NULL;

	dentry->d_op = sb->s_root->d_op;
	de = pitix_find_entry(dentry, &bh);
	if (de != NULL) {
		dprintk("getting entry: name: %s, ino: %d\n",
				de->name, de->ino);
		inode = pitix_iget(sb, de->ino);
		if (IS_ERR(inode))
			return ERR_CAST(inode);
	}

	d_add(dentry, inode);
	brelse(bh);

	dprintk("looked up dentry %s\n", dentry->d_name.name);
	return NULL;
}

/*
 * create a new VFS inode, do basic initialization and fill imap
 */

struct inode *pitix_new_inode(struct inode * dir, umode_t mode)
{
	struct pitix_super_block *sbi = dir->i_sb->s_fs_info;
	struct inode *inode;
	unsigned long idx;

	idx = find_first_zero_bit((unsigned long*)sbi->imap_bh->b_data,
					 get_inodes(dir->i_sb) + 2);
	if (idx < 0) {
		dprintk( "no space left in imap\n");
		return NULL;
	}

	__test_and_set_bit(idx, (unsigned long*)sbi->imap_bh->b_data);
	mark_buffer_dirty(sbi->imap_bh);

	dprintk("new inode %lu\n", idx);

	inode = new_inode(dir->i_sb);
	inode_init_owner(inode, dir, mode);
	inode->i_uid = current_fsuid();
	inode->i_gid = current_fsgid();
	inode->i_ino = idx;
	inode->i_mtime = inode->i_atime = inode->i_ctime = CURRENT_TIME;
	inode->i_blocks = 0;

	insert_inode_hash(inode);
	mark_inode_dirty(inode);
	sbi->ffree--;
	return inode;
}

static int pitix_add_link(struct dentry *dentry, struct inode *inode)
{
	struct buffer_head *bh;
	struct inode *dir = dentry->d_parent->d_inode;
	struct pitix_inode_info *mii = container_of(dir,
			struct pitix_inode_info, vfs_inode);
	struct super_block *sb = dir->i_sb;
	struct pitix_super_block* sbi = sb->s_fs_info;
	struct pitix_dir_entry *de;
	int i;
	int err = 0;

	dprintk("adding child %s dentry to %lu\n", 
		dentry->d_name.name, dir->i_ino);
	bh = sb_bread(sb, sbi->dzone_block + mii->data_block[0]);

	for (i = 0; i < dir_entries_per_block(sb); i++) {
		de = (struct pitix_dir_entry *) bh->b_data + i;
		if (de->ino == 0)
			break;
	}

	if (i == dir_entries_per_block(sb)) {
		err = -ENOSPC;
		goto out;
	}

	de->ino = inode->i_ino;
	memcpy(de->name, dentry->d_name.name, dentry->d_name.len);
	dir->i_mtime = dir->i_ctime = CURRENT_TIME;

	mark_inode_dirty(dir);
	mark_buffer_dirty(bh);
out:
	brelse(bh);
	return err;
}

static int pitix_delete_entry(struct inode* inode, struct pitix_dir_entry *de)
{
	struct pitix_super_block* sbi = inode->i_sb->s_fs_info;
	struct pitix_inode_info* mii;
	int i;

	mii = container_of(inode, struct pitix_inode_info, vfs_inode);
	for (i = 0; i < sbi->inode_data_blocks; i++) {
		if (mii->data_block[i]) {
			__test_and_clear_bit(mii->data_block[i],
					(unsigned long*)sbi->dmap_bh->b_data);
			mii->data_block[i] = 0;
			sbi->bfree++;
		} else {
			break;
		}
	}

	inode->i_size = 0;
	__test_and_clear_bit(de->ino, (unsigned long*)sbi->imap_bh->b_data);

	inode_dec_link_count(inode);

	dprintk("pitix_delete_entry %d\n", de->ino);
	mark_inode_dirty(inode);
	de->ino = 0;
	mark_buffer_dirty(sbi->dmap_bh);
	mark_buffer_dirty(sbi->imap_bh);
	return 0;

}

static int pitix_unlink(struct inode * dir, struct dentry *dentry)
{
	int err = -ENOENT;
	struct inode * inode = dentry->d_inode;
	struct page * page;
	struct pitix_dir_entry * de;
	struct buffer_head *bh;

	de = pitix_find_entry(dentry, &bh);
	if (!de)
		goto end_unlink;
	pitix_delete_entry(inode, de);
	mark_buffer_dirty(bh);
	brelse(bh);
	((struct pitix_super_block*)dir->i_sb->s_fs_info)->ffree++;
	return 0;
end_unlink:
	return err;
}

int pitix_getattr(struct vfsmount *mnt, struct dentry *dentry, struct kstat *stat)
{
	struct super_block *sb = dentry->d_sb;
	generic_fillattr(dentry->d_inode, stat);
	stat->blksize = sb->s_blocksize;
	stat->blocks = 1;
	stat->size = sb->s_blocksize;
	dprintk("get stats for %s\n", dentry->d_iname);
	return 0;
}

int pitix_empty_dir(struct inode *dir)
{
	struct pitix_inode_info *mii = container_of(dir,
			struct pitix_inode_info, vfs_inode);
	struct super_block *sb = dir->i_sb;
	struct pitix_super_block *sbi = sb->s_fs_info;
	struct pitix_dir_entry *de;
	struct buffer_head *bh;
	size_t i;

	/* read parent folder data block (contains dentries) */
	bh = sb_bread(sb, sbi->dzone_block + mii->data_block[0]);
	if (bh == NULL) {
		dprintk( "could not read block\n");
		return 0;
	}

	/* traverse all entries */
	for (i = 0; i < dir_entries_per_block(sb); i++) {
		de = ((struct pitix_dir_entry *) bh->b_data) + i;
		if (de->ino != 0) {
			brelse(bh);
			return 0;
		}
	}
	brelse(bh);
	return 1;
}

static int pitix_rmdir(struct inode * dir, struct dentry *dentry)
{
	struct inode * inode = dentry->d_inode;
	int err = -ENOTEMPTY;

	dprintk(LOG_LEVEL "rmdir inode %d\n", inode->i_ino);
	if (pitix_empty_dir(inode)) {
		err = pitix_unlink(dir, dentry);
	}
	return err;
}

static int pitix_create(struct inode *dir, struct dentry *dentry,
		umode_t mode, bool excl)
{
	struct inode *inode;
	struct pitix_inode_info *mii;
	int err;

	inode = pitix_new_inode(dir, S_IFREG | mode);
	if (inode == NULL) {
		dprintk( "error allocating new inode\n");
		err = -ENOMEM;
		goto err_new_inode;
	}

	pitix_set_inode(inode);

	inode_inc_link_count(inode);
	err = pitix_add_link(dentry, inode);
	if (err != 0)
		goto err_add_link;


	mark_inode_dirty(inode);
	d_instantiate(dentry, inode);

	dprintk("new file inode created (ino = %lu)\n", inode->i_ino);

	return 0;

err_add_link:
	dprintk("error creating file\n");
	inode_dec_link_count(inode);
	((struct pitix_super_block*)dir->i_sb->s_fs_info)->ffree++;
	iput(inode);
err_new_inode:
	return err;
}



struct inode_operations pitix_dir_inode_operations = {
	.create		= pitix_create,
	.lookup		= pitix_lookup,
	.mkdir		= pitix_mkdir,
	.unlink         = pitix_unlink,
	.getattr        = pitix_getattr,
	.rmdir		= pitix_rmdir,
};

struct file_operations pitix_file_operations = {
	.read		= do_sync_read,
	.aio_read	= generic_file_aio_read,
	.write		= do_sync_write,
	.aio_write	= generic_file_aio_write,
	.mmap		= generic_file_mmap,
	.fsync		= noop_fsync,
	.splice_read	= generic_file_splice_read,
	.splice_write	= generic_file_splice_write,
	.llseek		= generic_file_llseek,
};

int pitix_get_block(struct inode *inode, sector_t block,
		struct buffer_head *bh_result, int create)
{
	struct pitix_super_block *sbi = inode->i_sb->s_fs_info;
	struct pitix_inode_info *mii = container_of(inode,
				struct pitix_inode_info, vfs_inode);

	if(create) {
		if (!mii->data_block[block])
			mii->data_block[block] = pitix_alloc_block(inode->i_sb);
	}

	map_bh(bh_result, inode->i_sb, sbi->dzone_block+mii->data_block[block]);
	return 0;
}

static int pitix_readpage(struct file *file, struct page *page)
{
	return block_read_full_page(page,pitix_get_block);
}

static int pitix_writepage(struct page *page, struct writeback_control *wbc)
{
	return block_write_full_page(page, pitix_get_block, wbc);
}

static int pitix_write_begin(struct file *file, struct address_space *mapping,
		loff_t pos, unsigned len, unsigned flags,
		struct page **pagep, void **fsdata)
{
	int ret;

	dprintk("write begin\n");
	ret = block_write_begin(mapping, pos, len, flags, pagep,
			pitix_get_block);
	return ret;
}

struct address_space_operations pitix_aops = {
	.readpage       = pitix_readpage,
	.writepage	= pitix_writepage,
	.write_begin    = pitix_write_begin,
	.write_end      = simple_write_end,
};

void pitix_truncate(struct inode * inode)
{
	struct pitix_super_block* sbi = inode->i_sb->s_fs_info;
	struct pitix_inode_info* mii;
	int i;
	if (!(S_ISREG(inode->i_mode) || S_ISDIR(inode->i_mode) || S_ISLNK(inode->i_mode)))
		return;

	mii = container_of(inode, struct pitix_inode_info, vfs_inode);
	for (i = 0; i < sbi->inode_data_blocks; i++) {
		if (mii->data_block[i]) {
			dprintk("clear block %d\n", mii->data_block[i]);
			__test_and_clear_bit(mii->data_block[i],
					(unsigned long*)sbi->dmap_bh->b_data);
			mii->data_block[i] = 0;
			sbi->bfree++;
		} else {
			break;
		}
	}

	mark_buffer_dirty(sbi->dmap_bh);
	block_truncate_page(inode->i_mapping, inode->i_size, pitix_get_block);
	mark_inode_dirty(inode);
}

static int pitix_setattr(struct dentry *dentry, struct iattr *attr)
{
	struct inode *inode = dentry->d_inode;
	int error;

	error = inode_change_ok(inode, attr);
	if (error)
		return error;

	if ((attr->ia_valid & ATTR_SIZE) &&
			attr->ia_size != i_size_read(inode)) {
		error = inode_newsize_ok(inode, attr->ia_size);
		if (error)
			return error;

		truncate_setsize(inode, attr->ia_size);
		pitix_truncate(inode);
	}
	setattr_copy(inode, attr);
	mark_inode_dirty(inode);
	return 0;
}

struct inode_operations pitix_file_inode_operations = {
	.getattr	= simple_getattr,
	.setattr	= pitix_setattr,
};


struct inode *pitix_iget(struct super_block *s, unsigned long ino)
{
	struct pitix_inode *mi;
	struct buffer_head *bh, *bh2 = NULL;
	struct inode *inode;
	struct pitix_inode_info *mii;
	struct pitix_super_block *sbi;

	int block_offset, block_id, data_size;

	/* allocate VFS inode */
	inode = iget_locked(s, ino);
	if (inode == NULL) {
		dprintk( "error aquiring inode\n");
		return ERR_PTR(-ENOMEM);
	}
	if (!(inode->i_state & I_NEW))
		return inode;
	sbi = s->s_fs_info;
	get_offsets(ino, &block_id, &block_offset, s);

	dprintk( "offset: %d block id: %d\n", block_offset, block_id);


	/* read disk inode block */
	bh = sb_bread(s, block_id);
	if (bh == NULL) {
		dprintk( "could not read block\n");
		goto out_bad_sb;
	}

	/* extract disk inode */
	if (block_offset + inode_size(s) > s->s_blocksize) {
		bh2 = sb_bread(s, block_id + 1);
		if (bh2 == NULL) {
			printk( "could not read block\n");
			goto out_bad_sb;
		}

		if (block_offset + sizeof(struct pitix_inode) > s->s_blocksize) {
			struct pitix_inode mi2;
			int first_part_size = s->s_blocksize - block_offset;
			memcpy(&mi2, (void*)bh->b_data + block_offset, first_part_size);
			memcpy((void*)&mi2 + first_part_size, bh2->b_data, inode_size(s) - first_part_size);
			mi = &mi2;
		} else {
			mi = (void*)bh->b_data + block_offset;
			data_size = s->s_blocksize - block_offset - sizeof(struct pitix_inode);
			memcpy((void*)mi->data_blocks + data_size, bh2->b_data, get_data_size(s) - data_size);
		}

		brelse(bh2);

	} else  {
		mi = (void*)bh->b_data + block_offset;
		memcpy(mi->data_blocks, mi->data_blocks, get_data_size(s));
	}

	/* fill VFS inode */
	inode->i_mode = mi->mode;
	inode->i_uid = mi->uid;
	inode->i_gid = mi->gid;
	inode->i_size = mi->size;
	inode->i_blocks = 0;
	inode->i_mtime = inode->i_atime = inode->i_ctime = CURRENT_TIME;

	pitix_set_inode(inode);

	/* fill data for mii */
	mii = container_of(inode, struct pitix_inode_info, vfs_inode);
	memcpy(mii->data_block, mi->data_blocks, get_data_size(s));

	/* free resources */
	brelse(bh);
	unlock_new_inode(inode);

	dprintk("got inode %lu\n", ino);

	return inode;

out_bad_sb:
	iget_failed(inode);
	return NULL;
}

static void pitix_destroy_inode(struct inode *inode)
{
	kfree(container_of(inode, struct pitix_inode_info, vfs_inode));
}

static void pitix_put_super(struct super_block *sb)
{
	struct pitix_super_block *sbi = sb->s_fs_info;
	struct buffer_head* bh;
	struct pitix_super_block *ms;

	sb_set_blocksize(sb, PITIX_BLOCK_SIZE);

	bh = sb_bread(sb, PITIX_SUPER_BLOCK);

	ms = (struct pitix_super_block *) bh->b_data;

	ms->ffree = sbi->ffree;
	ms->bfree = sbi->bfree;

	mark_buffer_dirty(bh);
	brelse(sbi->sb_bh);
	brelse(bh);

	sb_set_blocksize(sb, 512);
	brelse(sbi->dmap_bh);
	brelse(sbi->imap_bh);


	dprintk("released superblock resources\n");
	sb->s_fs_info = NULL;
}

struct inode *pitix_alloc_inode(struct super_block *s)
{
	struct pitix_inode_info *mii;
	/* allocate pitix_inode_info */

	mii = kzalloc(sizeof(struct pitix_inode_info) + get_data_size(s),
	 GFP_KERNEL);
	if (mii == NULL)
		return NULL;

	inode_init_once(&mii->vfs_inode);

	return &mii->vfs_inode;
}

int pitix_write_inode(struct inode *inode, struct
		writeback_control *wbc)
{
	struct super_block *sb = inode->i_sb;
	struct pitix_inode *mi;
	struct pitix_inode_info *mii = container_of(inode,
			struct pitix_inode_info, vfs_inode);
	struct buffer_head *bh,*bh2;
	struct pitix_super_block *sbi = sb->s_fs_info;
	int err = 0, block_id, block_offset;

	get_offsets(inode->i_ino, &block_id, &block_offset, sb);


	dprintk("writing inode block ino: %d id : %d offset: %d\n",
					inode->i_ino, block_id, block_offset);
	bh = sb_bread(sb, block_id);
	if (bh == NULL) {
		dprintk( "could not read block\n");
		err = -ENOMEM;
		goto out;
	}

	mi = (void*)bh->b_data + block_offset;

	/* fill disk inode */
	mi->mode = inode->i_mode;
	mi->uid = inode->i_uid;
	mi->gid = inode->i_gid;
	mi->size = inode->i_size;

	if (block_offset + sizeof(struct pitix_inode) > sb->s_blocksize) {
		struct pitix_inode split_mi;
		int first_part_size = sb->s_blocksize - block_offset;
		int last_part_size = sizeof(struct pitix_inode) - first_part_size;
		split_mi.mode = inode->i_mode;
		split_mi.uid = inode->i_uid;
		split_mi.gid = inode->i_gid;
		split_mi.size = inode->i_size;
		bh2 = sb_bread(sb, block_id + 1);

		memcpy((void*)bh->b_data + block_offset, &split_mi, first_part_size);
		memcpy(bh2->b_data, (void*)(&split_mi) + first_part_size, last_part_size);
		memcpy((void*)bh2->b_data + last_part_size, mii->data_block, inode_size(sb) - sizeof(struct pitix_inode));
		mark_buffer_dirty(bh2);
		sync_dirty_buffer(bh2);
		brelse(bh2);
	} else if (block_offset + inode_size(sb) > sb->s_blocksize) {
		int first_part_size = sb->s_blocksize - block_offset - sizeof(struct pitix_inode);
		int last_part_size = (block_offset + inode_size(sb)) % sb->s_blocksize;
		memcpy(mi->data_blocks, mii->data_block, first_part_size);
		bh2 = sb_bread(sb, block_id + 1);
		memcpy(bh2->b_data, (void*)mii->data_block + first_part_size, last_part_size);

		mark_buffer_dirty(bh2);
		sync_dirty_buffer(bh2);
		brelse(bh2);
	} else {
		memcpy(mi->data_blocks, mii->data_block, get_data_size(sb));
	}

	mark_buffer_dirty(bh);
	sync_dirty_buffer(bh);
	brelse(bh);
out:
	return err;
}

static int pitix_statfs(struct dentry *dentry, struct kstatfs *buf)
{
	struct super_block *sb = dentry->d_sb;
	struct pitix_super_block *sbi = sb->s_fs_info;

	buf->f_type = sb->s_magic;
	buf->f_bsize = sb->s_blocksize;
	buf->f_blocks = get_blocks(sb);
	buf->f_bfree = sbi->bfree;
	buf->f_files = get_inodes(sb);
	buf->f_ffree = sbi->ffree;
	buf->f_namelen = PITIX_NAME_LEN;
	return 0;
}


static const struct super_operations pitix_ops = {
	.statfs		= pitix_statfs,
	.alloc_inode	= pitix_alloc_inode,
	.destroy_inode	= pitix_destroy_inode,
	.write_inode	= pitix_write_inode,
	.put_super	= pitix_put_super,
};

int pitix_fill_super(struct super_block *s, void *data, int silent)
{
	struct pitix_super_block *ms;
	struct inode *root_inode;
	struct dentry *root_dentry;
	struct buffer_head *bh;
	int ret = -EINVAL;

	/* set block size for superblock */
	if (!sb_set_blocksize(s, PITIX_BLOCK_SIZE))
		goto out_bad_blocksize;

	/* read first block from disk (contains disk superblock) */
	bh = sb_bread(s, PITIX_SUPER_BLOCK);
	if (bh == NULL)
		goto out_bad_sb;

	/* extract disk superblock */
	ms = (struct pitix_super_block *) bh->b_data;

	/* store superblock buffer_head for further use */
	ms->sb_bh = bh;

	/* fill sbi with information from disk superblock */
	if (ms->magic != PITIX_MAGIC)
		goto out_bad_magic;

	s->s_fs_info = ms;
	s->s_magic = PITIX_MAGIC;
	s->s_op = &pitix_ops;

	if (!sb_set_blocksize(s, pitix_block_size(s)))
		goto out_bad_sb;


	ms->imap_bh = sb_bread(s, ms->imap_block);
	ms->dmap_bh = sb_bread(s, ms->dmap_block);

	/* allocate root inode and root dentry */
	root_inode = pitix_iget(s, PITIX_ROOT_INODE);
	if (!root_inode)
		goto out_bad_inode;

	root_dentry = d_make_root(root_inode);
	if (!root_dentry)
		goto out_iput;
	s->s_root = root_dentry;
	mark_buffer_dirty(bh);

	dprintk("superblock filled\n");

	return 0;

out_iput:
	iput(root_inode);
out_bad_inode:
	dprintk( "bad inode\n");
out_bad_magic:
	dprintk( "bad magic number\n");
	brelse(bh);
out_bad_sb:
	dprintk( "error reading buffer_head\n");
out_bad_blocksize:
	dprintk( "bad block size\n");
	s->s_fs_info = NULL;
	return ret;
}


static struct dentry *pitix_mount(struct file_system_type *fs_type,
		int flags, const char *dev_name, void *data)
{
	return mount_bdev(fs_type, flags, dev_name, data, pitix_fill_super);
}

static struct file_system_type pitix_fs_type = {
	.owner = THIS_MODULE,
	.name = "pitix",
	.mount	= pitix_mount,
	.kill_sb = kill_block_super,
	.fs_flags = FS_REQUIRES_DEV,
};

static int __init pitix_init(void)
{
	int err;
	err = register_filesystem(&pitix_fs_type);
	if (err) {
		dprintk( "register_filesystem failed\n");
		return err;
	}
	return 0;
}

static void __exit pitix_exit(void)
{
	unregister_filesystem(&pitix_fs_type);
}

module_init(pitix_init);
module_exit(pitix_exit);

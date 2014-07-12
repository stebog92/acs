#ifndef _PITIX_H
#define _PITIX_H

#define PITIX_MAGIC 		0x58495450 /* ascii little endian for PTIX */
#define IZONE_BLOCKS		32
#define PITIX_NAME_LEN 		16

/*
 * filesystem layout:
 *
 *      SB      IMAP        DMAP       IZONE 	     DATA
 *    ^	    ^ (1 block)  (1 block)  (32 blocks)		
 *    |     |
 *    +-0   +-- 4096
 */

struct pitix_super_block {
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
#ifdef __KERNEL__
	struct buffer_head *sb_bh, *dmap_bh, *imap_bh;
	__u8 *dmap, *imap;
#endif
};

struct pitix_dir_entry {
	__u32 ino;
	char name[PITIX_NAME_LEN];
};

struct pitix_inode {
	__u32 mode;
	__u32 uid;
	__u32 gid;
	__u32 size;
	__u32 time;
	__u16 data_blocks[0];
};

#ifdef __KERNEL__
static inline int inode_size(struct super_block *sb)
{
	struct pitix_super_block *psb=(struct pitix_super_block*)sb->s_fs_info;
	return sizeof(struct pitix_inode)+sizeof(__u16)*psb->inode_data_blocks;
}

static inline int dir_entry_size(struct super_block *sb)
{
	return sizeof(struct pitix_dir_entry);
}

static inline int dir_entries_per_block(struct super_block *sb)
{
	return sb->s_blocksize/dir_entry_size(sb);
}

static inline long get_blocks(struct super_block *sb)
{
	return 8*sb->s_blocksize;
}

static inline long get_inodes(struct super_block *sb)
{
	return IZONE_BLOCKS*sb->s_blocksize/inode_size(sb);
}

/* Bitmap operations */
extern int pitix_alloc_block(struct super_block *sb);
extern void pitix_free_block(struct super_block *sb, int block);
extern int pitix_alloc_inode(struct super_block *sb);
extern void pitix_free_inode(struct super_block *sb, int ino);
extern int pitix_get_block(struct inode * inode, sector_t block, struct buffer_head *bh_result, int create);
extern struct address_space_operations pitix_aops;

/* Dir operations */
extern struct inode_operations pitix_dir_inode_operations;
extern struct file_operations pitix_dir_operations;

/* File operations */
extern struct file_operations pitix_file_operations;
extern struct inode_operations pitix_file_inode_operations;

/* Inode operations */
extern struct inode *pitix_new_inode(struct super_block *sb);
extern int pitix_write_inode(struct inode * inode, struct writeback_control *wbc);
extern void pitix_evict_inode(struct inode *inode);

extern struct inode *pitix_iget(struct super_block *sb, unsigned long ino);

/* Super operations */
extern int pitix_fill_super(struct super_block *sb, void *data, int silent);
extern struct super_operations pitix_sops;
#endif

#endif 

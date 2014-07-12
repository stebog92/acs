#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <sys/stat.h>
#include <linux/types.h>

#include "minfs.h"

#define FS_DEVICE		"/dev/sdb"

/*
 * mk_minfs file
 */

int main(void)
{
	FILE *file;
	char buffer[MINFS_BLOCK_SIZE];
	struct minfs_super_block msb;
	struct minfs_inode root_inode;
	int i;

	file = fopen(FS_DEVICE, "w+");
	if (file == NULL) {
		perror("fopen");
		exit(EXIT_FAILURE);
	}

	memset(&msb, 0, sizeof(struct minfs_super_block));

	msb.magic = MINFS_MAGIC;
	msb.version = 1;

	/* zero disk  */
	memset(buffer, 0,  MINFS_BLOCK_SIZE);
	for (i = 0; i < 128; i++)
		fwrite(buffer, 1, MINFS_BLOCK_SIZE, file);

	fseek(file, 0, SEEK_SET);

	/* initialize super block */
	fwrite(&msb, sizeof(msb), 1, file);

	/* initialize root inode */
	memset(&root_inode, 0, sizeof(root_inode));
	root_inode.mode = S_IFDIR;
	root_inode.size = MINFS_BLOCK_SIZE;

	fseek(file, 4096, SEEK_SET);
	fwrite(&root_inode, sizeof(root_inode), 1, file);

	fclose(file);

	return 0;
}

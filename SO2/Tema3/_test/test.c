/*
 * Simple Software RAID: test suite
 *
 * Operating Systems 2, 2014
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <errno.h>
#include <assert.h>
#include <sys/ioctl.h>

#include "ssr.h"


#if INTERNAL_TESTING == 1
#define INTERNAL_BYTE_INDEX	1
#define INTERNAL_FIXED_VALUE	0xa
#define INTERNAL_TESTING_SECTOR	0
#endif

#if DEBUG == 1
#define Dprintf(format, ...)	\
	fprintf(stderr, "[DEBUG] %d: " format, __LINE__, ##__VA_ARGS__)
#else
#define Dprintf(format, ...)   do { } while(0)
#endif


#define SECTOR_SIZE		KERNEL_SECTOR_SIZE
#define SECTOR_MASK		(KERNEL_SECTOR_SIZE-1)

#define CRC_SIZE		4

#define NUM_SUBTESTS		3
#define NUM_TEST_SECTORS	1

#define SSR_BASE_NAME		"ssr"
#define SSR_LIN_EXT		".ko"
#define SSR_MOD_NAME		SSR_BASE_NAME SSR_LIN_EXT


static void test(const char *msg, int test_val)
{
	size_t i;

        printf("test: %s", msg);
	fflush(stdout);

	for (i = 0; i < 60-strlen(msg); i++)
		putchar('.');
	if (!test_val)
	        printf("failed: %s\n", strerror(errno));
	else
		printf("passed\n");

	fflush(stdout);
}

/* file descriptors for logical device and the two physical devices */
static int log_fd, phys_fd1, phys_fd2;

/* buffers for data regarding the three devices (logical + 2 physical) */
static unsigned char log_buffer[NUM_TEST_SECTORS * SECTOR_SIZE],
		     phys_buffer1[NUM_TEST_SECTORS * SECTOR_SIZE],
		     phys_buffer2[NUM_TEST_SECTORS * SECTOR_SIZE],
		     crc_buffer[SECTOR_SIZE];

/* start sectors array for test cases */
static size_t start_sector_v[NUM_SUBTESTS];


/*
 * compute CRC32
 */

static unsigned int crc32(unsigned int seed,
		const unsigned char *p, unsigned int len)
{
	size_t i;
	unsigned int crc = seed;

	while (len--) {
		crc ^= *p++;
		for (i = 0; i < 8; i++)
			crc = (crc >> 1) ^ ((crc & 1) ? 0xedb88320 : 0);
	}

	return crc;
}

static inline unsigned int uint_rand(void)
{
	return (((unsigned char) rand()) << 24 |
			((unsigned char) rand()) << 16 |
			((unsigned char) rand()) << 8 |
			((unsigned char) rand()));
}

/*
 * random aligned sector position; multiple of SECTOR_SIZE / CRC_SIZE
 * such that corresponding CRC value is "aligned" to a disk sector
 */

static inline size_t ssr_get_random_aligned_sector(void)
{
	size_t s;

	s = uint_rand() %
		(LOGICAL_DISK_SECTORS - 2 * SECTOR_SIZE / CRC_SIZE) +
		(SECTOR_SIZE / CRC_SIZE);
	s -= (s % (SECTOR_SIZE / CRC_SIZE));

	return s;
}

/*
 * generate start positions in start_sector_v array
 */

static inline void ssr_gen_start_sectors(void)
{
	size_t i;
	unsigned int basic_start = ssr_get_random_aligned_sector() % (LOGICAL_DISK_SECTORS / 4);

	for (i = 0; i < NUM_SUBTESTS; i++) {
#if INTERNAL_TESTING == 1
		start_sector_v[i] = INTERNAL_TESTING_SECTOR + i;
#else
		/* Avoid overlap. */
		start_sector_v[i] = basic_start + 100 * i + i;
#endif
	}
}

/*
 * "upgraded" read routine
 */

static inline size_t xread(int fd, void *buffer, size_t len)
{
	ssize_t ret;
	size_t n;

	n = 0;
	while (n < len) {
		ret = read(fd, (char *) buffer + n, len - n);
		assert(ret >= 0);
		if (ret == 0)
			break;
		n += ret;
	}

	return n;
}

/*
 * "upgraded" write routine
 */

static inline size_t xwrite(int fd, void *buffer, size_t len)
{
	ssize_t ret;
	size_t n;

	n = 0;
	while (n < len) {
		ret = write(fd, (char *) buffer + n, len - n);
		assert(ret >= 0);
		if (ret == 0)
			break;
		n += ret;
	}
	return n;
}

static inline void ssr_test_open(void)
{
	log_fd = open(LOGICAL_DISK_NAME, O_RDWR);
	test("open " LOGICAL_DISK_NAME, log_fd > 0);

	phys_fd1 = open(PHYSICAL_DISK1_NAME, O_RDWR);
	assert(phys_fd1 > 0);
	phys_fd2 = open(PHYSICAL_DISK2_NAME, O_RDWR);
	assert(phys_fd2 > 0);
}

static inline void ssr_open_nocheck(void)
{
	log_fd = open(LOGICAL_DISK_NAME, O_RDWR);
	assert(log_fd > 0);
	phys_fd1 = open(PHYSICAL_DISK1_NAME, O_RDWR);
	assert(phys_fd1 > 0);
	phys_fd2 = open(PHYSICAL_DISK2_NAME, O_RDWR);
	assert(phys_fd2 > 0);
}

static inline void ssr_test_close(void)
{
	test("close " LOGICAL_DISK_NAME, close(log_fd) == 0);
	test("close " LOGICAL_DISK_NAME, close(log_fd) != 0);
	assert(close(phys_fd1) == 0);
	assert(close(phys_fd2) == 0);
}

static inline void ssr_close_nocheck(void)
{
	assert(close(log_fd) == 0);
	assert(close(phys_fd1) == 0);
	assert(close(phys_fd2) == 0);
}

static inline size_t ssr_get_crc_offset(size_t data_sector)
{
	return LOGICAL_DISK_SIZE + data_sector * CRC_SIZE;
}

static inline size_t ssr_get_crc_offset_in_sector(size_t data_sector)
{
	return ssr_get_crc_offset(data_sector) & SECTOR_MASK;
}

static inline size_t ssr_get_crc_sector(size_t data_sector)
{
	return ssr_get_crc_offset(data_sector) & ~SECTOR_MASK;
}

/*
 * read sectors from logical disk
 */

static inline size_t ssr_read_log_sectors(int fd,
		void *buffer, size_t start_sector, size_t num_sectors)
{
	size_t n;

	lseek(fd, start_sector * SECTOR_SIZE, SEEK_SET);
	n = xread(fd, buffer, num_sectors * SECTOR_SIZE);


	return n;
}

/*
 * write sectors to logical disk
 */

static inline size_t ssr_write_log_sectors(int fd,
		void *buffer, size_t start_sector, size_t num_sectors)
{
	size_t n;

	lseek(fd, start_sector * SECTOR_SIZE, SEEK_SET);
	n = xwrite(fd, buffer, num_sectors * SECTOR_SIZE);

	return n;
}

/*
 * read sector from physical disk
 */

static inline size_t ssr_read_phys_sector(int fd, void *buffer, size_t sector)
{
	unsigned int crc_comp, crc_read;
	size_t n;

	lseek(fd, sector * SECTOR_SIZE, SEEK_SET);
	n = xread(fd, buffer, SECTOR_SIZE);
	crc_comp = crc32(0, (unsigned char *) buffer, SECTOR_SIZE);


	Dprintf("crc offset read: %d and sector: %d and offset %d\n", ssr_get_crc_sector(sector), sector,
			ssr_get_crc_offset_in_sector(sector));
	/* adjust offset for sector alignment */
	lseek(fd, ssr_get_crc_sector(sector), SEEK_SET);
	n += xread(fd, crc_buffer, SECTOR_SIZE);

	crc_read = * (unsigned int *) (crc_buffer +
			ssr_get_crc_offset_in_sector(sector));
	//printf("crc_read = %08x, crc_comp = %08x\n", crc_read, crc_comp);


	test("crc check", crc_read == crc_comp);

	return n;
}

/*
 * write sector to physical disk
 */

static inline size_t ssr_write_phys_sector(int fd, void *buffer, size_t sector)
{
	unsigned int crc_comp;
	size_t n;
	size_t crc_offset;

	lseek(fd, sector * SECTOR_SIZE, SEEK_SET);
	n = xwrite(fd, buffer, SECTOR_SIZE);
	crc_comp = crc32(0, buffer, SECTOR_SIZE);

	/* adjust offset for sector alignment */
	lseek(fd, ssr_get_crc_sector(sector), SEEK_SET);
	n += xread(fd, crc_buffer, SECTOR_SIZE);

	* (unsigned int *) (crc_buffer +
			ssr_get_crc_offset_in_sector(sector)) = crc_comp;

	lseek(fd, crc_offset, SEEK_SET);
	n += xwrite(fd, crc_buffer, SECTOR_SIZE);

	return n;
}

/*
 * fill buffer with random data
 */

static inline void buf_fill_fixed(unsigned char *buf, size_t len,
		unsigned char value)
{
	size_t i;

	for (i = 0; i < len; i++)
		buf[i] = value;
}

/*
 * fill buffer with random data
 */

static inline void buf_fill_random(unsigned char *buf, size_t len)
{
	size_t i;

	for (i = 0; i < len; i++)
		buf[i] = (unsigned char) rand();
}

/*
 * test writes: write on RAID virtual disk and check the physical disks
 */

static void ssr_test_writes(size_t start_sector, size_t num_sectors)
{
	size_t count = 0;
	size_t i;

again:
	/* write log_buffer to RAID device */
#if INTERNAL_TESTING == 1
	buf_fill_fixed(log_buffer, sizeof(log_buffer), INTERNAL_FIXED_VALUE);
#else
	buf_fill_random(log_buffer, sizeof(log_buffer));
#endif
	ssr_write_log_sectors(log_fd, log_buffer, start_sector, num_sectors);
	Dprintf("write (logical): start_sector: %zd, num_sectors: %zd, buffer: 0x%02x 0x%02x 0x%02x 0x%02x 0x%02x\n", start_sector, num_sectors, log_buffer[0], log_buffer[1], log_buffer[2], log_buffer[3], log_buffer[4]);

	ssr_close_nocheck();
	system("rmmod " SSR_BASE_NAME);
	sync();
	system("echo 1 > /proc/sys/vm/drop_caches");
	system("insmod " SSR_MOD_NAME);
	ssr_open_nocheck();

	for (i = 0; i < num_sectors; i++) {
		/* read data from physical devices */
		ssr_read_phys_sector(phys_fd1, phys_buffer1, start_sector + i);
		ssr_read_phys_sector(phys_fd2, phys_buffer2, start_sector + i);
		Dprintf("read (phys1): buffer: 0x%02x 0x%02x 0x%02x 0x%02x 0x%02x\n", phys_buffer1[0], phys_buffer1[1], phys_buffer1[2], phys_buffer1[3], phys_buffer1[4]);
		Dprintf("read (phys2): buffer: 0x%02x 0x%02x 0x%02x 0x%02x 0x%02x\n", phys_buffer2[0], phys_buffer2[1], phys_buffer2[2], phys_buffer2[3], phys_buffer2[4]);

		/* compare data */
		test("test write1", memcmp(log_buffer + i * SECTOR_SIZE, phys_buffer1, SECTOR_SIZE) == 0);
		test("test write2", memcmp(log_buffer + i * SECTOR_SIZE, phys_buffer2, SECTOR_SIZE) == 0);
	}

	count++;
	if (count < 1)		/* twice on the same area */
		goto again;
}

/*
 * test reads: read data from physical disks and check the RAID virtual disk
 */

static void ssr_test_reads(size_t start_sector, size_t num_sectors)
{
	size_t i;

	/* read data from RAID virtual disk */
	ssr_read_log_sectors(log_fd, log_buffer, start_sector, num_sectors);
	Dprintf("read (logical): start_sector: %zd, num_sectors: %zd, buffer: 0x%02x 0x%02x 0x%02x 0x%02x 0x%02x\n", start_sector, num_sectors, log_buffer[0], log_buffer[1], log_buffer[2], log_buffer[3], log_buffer[4]);

	ssr_close_nocheck();
	system("rmmod " SSR_BASE_NAME);
	sync();
	system("echo 1 > /proc/sys/vm/drop_caches");
	system("insmod " SSR_MOD_NAME);
	ssr_open_nocheck();

	for (i = 0; i < num_sectors; i++) {
		/* read data from physical disks */
		ssr_read_phys_sector(phys_fd1, phys_buffer1, start_sector + i);
		ssr_read_phys_sector(phys_fd2, phys_buffer2, start_sector + i);
		Dprintf("read (phys1): buffer: 0x%02x 0x%02x 0x%02x 0x%02x 0x%02x\n", phys_buffer1[0], phys_buffer1[1], phys_buffer1[2], phys_buffer1[3], phys_buffer1[4]);
		Dprintf("read (phys2): buffer: 0x%02x 0x%02x 0x%02x 0x%02x 0x%02x\n", phys_buffer2[0], phys_buffer2[1], phys_buffer2[2], phys_buffer2[3], phys_buffer2[4]);

		/* compare data */
		test("test read1", memcmp(log_buffer + i * SECTOR_SIZE, phys_buffer1, SECTOR_SIZE) == 0);
		test("test read2", memcmp(log_buffer + i * SECTOR_SIZE, phys_buffer2, SECTOR_SIZE) == 0);
	}
}

/*
 * test read/write operations on device
 */

static void ssr_test_ops(void)
{
	size_t rand_sect;
	int i;

	ssr_test_open();

	for (i = 0; i < NUM_SUBTESTS; i++) {
#if INTERNAL_TESTING == 1
		rand_sect = INTERNAL_TESTING_SECTOR;
		buf_fill_fixed(phys_buffer1, sizeof(phys_buffer1), i+1);
#else
		rand_sect = rand() % LOGICAL_DISK_SECTORS;
		buf_fill_random(phys_buffer1, sizeof(phys_buffer1));
#endif

		lseek(log_fd, rand_sect * SECTOR_SIZE, SEEK_SET);
		test("simple write", xwrite(log_fd, phys_buffer1, SECTOR_SIZE) == SECTOR_SIZE);

		ssr_close_nocheck();
		system("rmmod " SSR_BASE_NAME);
		sync();
		system("echo 1 > /proc/sys/vm/drop_caches");
		system("insmod " SSR_MOD_NAME);
		ssr_open_nocheck();

		lseek(log_fd, rand_sect * SECTOR_SIZE, SEEK_SET);
		test("simple read", xread(log_fd, phys_buffer2, SECTOR_SIZE) == SECTOR_SIZE);
		test("simple compare", memcmp(phys_buffer1, phys_buffer2, SECTOR_SIZE) == 0);
	}

	ssr_test_close();
}

static void ssr_test_mirror(void)
{
	size_t i;

	ssr_test_open();

	ssr_gen_start_sectors();

	for (i = 0; i < NUM_SUBTESTS; i++)
		ssr_test_writes(start_sector_v[i], NUM_TEST_SECTORS);
	for (i = 0; i < NUM_SUBTESTS; i++)
		ssr_test_reads(start_sector_v[i], NUM_TEST_SECTORS);

	ssr_test_close();
}

/*
 * add random amount of data to byte; make sure the result value
 * is different from the original one
 */

static inline unsigned char corrupt_byte(unsigned char byte)
{
	return byte + ((rand() % 128) + 1);
}

/*
 * corrupt physical sector
 */

static void ssr_corrupt(int fd, size_t sector)
{
	size_t corrupt_byte_idx;

#if INTERNAL_TESTING == 1
	corrupt_byte_idx = INTERNAL_BYTE_INDEX;
#else
	corrupt_byte_idx = rand() % SECTOR_SIZE;
#endif
	lseek(fd, sector * SECTOR_SIZE, SEEK_SET);
	xread(fd, phys_buffer1, SECTOR_SIZE);

	phys_buffer1[corrupt_byte_idx] =
		corrupt_byte(phys_buffer1[corrupt_byte_idx]);

	Dprintf("[corrupt] fd: %d, sector: %d, idx: %d, byte: %02x\n",
			fd, sector, corrupt_byte_idx,
			phys_buffer1[corrupt_byte_idx]);
	Dprintf("[corrupt] sector: %zd, buffer: 0x%02x 0x%02x 0x%02x 0x%02x 0x%02x\n", sector, phys_buffer1[0], phys_buffer1[1], phys_buffer1[2], phys_buffer1[3], phys_buffer1[4]);
	lseek(fd, sector * SECTOR_SIZE, SEEK_SET);
	xwrite(fd, phys_buffer1, SECTOR_SIZE);
}

/*
 * check sector corruption
 */

static void ssr_check_corrupt(int fd, size_t sector)
{
	unsigned int crc_read1, crc_read2, crc_comp1, crc_comp2;

	lseek(phys_fd1, sector * SECTOR_SIZE, SEEK_SET);
	xread(phys_fd1, phys_buffer1, SECTOR_SIZE);
	crc_comp1 = crc32(0, phys_buffer1, SECTOR_SIZE);

#if INTERNAL_TESTING == 1
	Dprintf("[check_corrupt] buff1[0] = %02x\n", phys_buffer1[0]);
#endif
	/* adjust offset for sector alignment */
	lseek(phys_fd1, ssr_get_crc_sector(sector), SEEK_SET);
	xread(phys_fd1, crc_buffer, SECTOR_SIZE);
	crc_read1 = * (unsigned int *) (crc_buffer +
			ssr_get_crc_offset_in_sector(sector));

	lseek(phys_fd2, sector * SECTOR_SIZE, SEEK_SET);
	xread(phys_fd2, phys_buffer2, SECTOR_SIZE);
	crc_comp2 = crc32(0, phys_buffer2, SECTOR_SIZE);

#if INTERNAL_TESTING == 1
	Dprintf("[check_corrupt] buff2[0] = %02x\n", phys_buffer2[0]);
#endif
	/* adjust offset for sector alignment */
	lseek(phys_fd2, ssr_get_crc_sector(sector), SEEK_SET);
	xread(phys_fd2, crc_buffer, SECTOR_SIZE);
	crc_read2 = * (unsigned int *) (crc_buffer +
			ssr_get_crc_offset_in_sector(sector));

	Dprintf("check_corrupt (phys1): buffer: 0x%02x 0x%02x 0x%02x 0x%02x 0x%02x\n", phys_buffer1[0], phys_buffer1[1], phys_buffer1[2], phys_buffer1[3], phys_buffer1[4]);
	Dprintf("check_corrupt (phys2): buffer: 0x%02x 0x%02x 0x%02x 0x%02x 0x%02x\n", phys_buffer2[0], phys_buffer2[1], phys_buffer2[2], phys_buffer2[3], phys_buffer2[4]);
	Dprintf("crc_comp1 = %08x, crc_read1 = %08x, "		\
			"crc_comp2 = %08x, crc_read2 = %08x\n",
			crc_comp1, crc_read1, crc_comp2, crc_read2);
	if (fd == phys_fd1)
		test("check corrupt",
			crc_comp1 != crc_read1 && crc_comp2 == crc_read2);
	else
		test("check corrupt",
			crc_comp1 == crc_read1 && crc_comp2 != crc_read2);
}

static void ssr_test_recovery_fd(int fd)
{
	size_t i;

	ssr_gen_start_sectors();

	/* write consistent data */
	for (i = 0; i < NUM_SUBTESTS; i++) {
#if INTERNAL_TESTING == 1
		buf_fill_fixed(log_buffer, sizeof(log_buffer), INTERNAL_FIXED_VALUE);
#else
		buf_fill_random(log_buffer, sizeof(log_buffer));
#endif
		ssr_write_log_sectors(log_fd, log_buffer, start_sector_v[i], NUM_TEST_SECTORS);
		Dprintf("write (logical): start_sector: %zd, num_sectors: %zd, buffer: 0x%02x 0x%02x 0x%02x 0x%02x 0x%02x\n", start_sector_v[i], 1, log_buffer[0], log_buffer[1], log_buffer[2], log_buffer[3], log_buffer[4]);
	}

	ssr_close_nocheck();
	system("rmmod " SSR_BASE_NAME);
	sync();
	system("echo 1 > /proc/sys/vm/drop_caches");
	system("insmod " SSR_MOD_NAME);
	ssr_open_nocheck();

	for (i = 0; i < NUM_SUBTESTS; i++)
		ssr_corrupt(fd, start_sector_v[i]);

	for (i = 0; i < NUM_SUBTESTS; i++)
		ssr_check_corrupt(fd, start_sector_v[i]);

	ssr_close_nocheck();
	system("rmmod " SSR_BASE_NAME);
	sync();
	system("echo 1 > /proc/sys/vm/drop_caches");
	system("insmod " SSR_MOD_NAME);
	ssr_open_nocheck();

	for (i = 0; i < NUM_SUBTESTS; i++)
		ssr_read_log_sectors(log_fd, log_buffer, start_sector_v[i], NUM_TEST_SECTORS);

	for (i = 0; i < NUM_SUBTESTS; i++)
		ssr_test_reads(start_sector_v[i], NUM_TEST_SECTORS);
}

static inline void ssr_test_recovery(void)
{
	ssr_test_open();
	ssr_test_recovery_fd(phys_fd1);
	ssr_test_close();

	ssr_test_open();
	ssr_test_recovery_fd(phys_fd2);
	ssr_test_close();
}

static void ssr_test_out_of_bounds(void)
{
	ssr_test_open();

	lseek(log_fd, LOGICAL_DISK_SIZE - SECTOR_SIZE, SEEK_SET);

	test("read limit", read(log_fd, log_buffer, SECTOR_SIZE) == SECTOR_SIZE);
	test("read out of bounds", read(log_fd, log_buffer, SECTOR_SIZE) == 0);

	lseek(log_fd, LOGICAL_DISK_SIZE - SECTOR_SIZE, SEEK_SET);

	test("write limit", write(log_fd, log_buffer, SECTOR_SIZE) == SECTOR_SIZE);
	test("write out of bounds", write(log_fd, log_buffer, SECTOR_SIZE) < 0);

	ssr_test_close();
}

static inline void ssr_test_insmod(void)
{
	test("insmod " SSR_MOD_NAME, system("insmod " SSR_MOD_NAME) == 0);
	test("/proc/devices entry",
			system("cat /proc/devices | grep " SSR_BASE_NAME
				" > /dev/null") == 0);
}

static inline void ssr_test_rmmod(void)
{
	test("rmmod " SSR_BASE_NAME, system("rmmod " SSR_BASE_NAME) == 0);
	test("no /proc/devices entry",
			system("cat /proc/devices | grep " SSR_BASE_NAME
				" > /dev/null") != 0);
}

static inline void ssr_mknod(void)
{
	char local_buffer[128];

	snprintf(local_buffer, 128,
			"mknod " LOGICAL_DISK_NAME " b %d 0", SSR_MAJOR);

	test("mknod " LOGICAL_DISK_NAME, system(local_buffer) == 0);
}

static inline void ssr_rmnod(void)
{
	test("rm " LOGICAL_DISK_NAME,
			system("rm " LOGICAL_DISK_NAME) == 0);
}

int main(void)
{
	printf("\nTEST BASIC START\n\n");
	ssr_mknod();
	ssr_test_insmod();
	ssr_test_rmmod();

	srand(time(NULL));

	ssr_test_insmod();

	printf("\nTEST OPS\n\n");
	ssr_test_ops();
	printf("\nTEST MIRROR\n\n");
	ssr_test_mirror();
	printf("\nTEST RECOVERY\n\n");
	ssr_test_recovery();
	printf("\nTEST OUT OF BOUNDS\n\n");
	ssr_test_out_of_bounds();

	printf("\nTEST BASIC END\n\n");
	ssr_test_rmmod();
	ssr_rmnod();

	return 0;
}

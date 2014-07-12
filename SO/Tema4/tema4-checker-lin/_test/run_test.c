/*
 * Testing framework
 *
 * 2012, Operating Systems
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>
#include <time.h>

/* tests functions */
#include "run_test.h"
/* custom tests */
#include "scheduler_test.h"


/* Enable/disable exiting when program fails. */
//#define EXIT_IF_FAIL
#define max_points 90

/* global variables used by the test */
static char *description;
static int points;
static unsigned long test_index;


/* prints the header specified aligned with the other output */
static void print_header(const char * header)
{
	int i, padding;
	int header_len = strlen(header);

	printf("\n");
	if (header_len < 71) {
		padding = (71 - header_len) / 2;
		for (i = 0; i < padding; i++)
			printf(" ");
	}
	printf("= %s =\n\n", header);
}


void test_do_fail(void)
{
	printf("failed  [ 0/%02d]\n", max_points);
#ifdef EXIT_IF_FAIL
	exit(EXIT_FAILURE);
#endif
}

void test_do_pass(void)
{
	printf("passed  [%02d/%02d]\n", points, max_points);
}

void basic_test(int true)
{
	int i;
	int desc_len = strlen(description);

	printf("%02lu) %s", test_index + 1, description);
	for (i = 0; i < 56 - desc_len; i++)
		printf(".");
	if (true)
		test_do_pass();
	else
		test_do_fail();
}

static int init_world(void)
{
	print_header("Testing - threads scheduler");
	return 0;
}

static int cleanup_world(void)
{
	return 0;
}


struct run_test_t test_fun_array[] = {
	{ test_integrity,		"Test integrity",				10 },
	{ test_thread_id,		"Test thread identifiers",		10 },
	{ test_multiple_runs,	"Test multiple runs",			10 },
	{ test_rr,				"Test Round Robin order",		10 },
	{ test_preemption,		"Test preemption",				10 },
	{ test_priorities,		"Test priorities",				10 },
	{ test_io_dev,			"Test IO devices",				10 },
	{ test_io_prio,			"Test priorities and IO",		10 },
	{ test_rr_stress,		"Test random values",			10 },
};

/* custom main testing thread */
int main(int argc, char **argv)
{
	unsigned long last_test;

	if (argc != 2) {
		fprintf(stderr, "Usage: %s <test_number> | init | cleanup\n", argv[0]);
		return -1;
	}

	/* check init and cleanup execution */
	if (strcmp(argv[1], "init") == 0) {
		if (init_world() < 0) {
			fprintf(stderr, "test init failed\n");
			return -1;
		}
		return 0;
	} else if (strcmp(argv[1], "cleanup") == 0) {
		if (cleanup_world() < 0) {
			fprintf(stderr, "test cleanup failed\n");
			return -1;
		}
		return 0;
	}

	test_index = strtoul(argv[1], NULL, 10) - 1;
	if (errno == EINVAL || errno == ERANGE) {
		fprintf(stderr, "%s not a number\n", argv[1]);
		return -1;
	}

	last_test = sizeof(test_fun_array) / sizeof(struct run_test_t) - 1;
	if (test_index < 0 || test_index > last_test) {
		fprintf(stderr, "Error: Test index is out of range"
				"(1 < test_index <= %lu).\n", last_test);
		return -1;
	}

	/* randomize time quantums */
	srand((unsigned long)time(NULL));

	description = test_fun_array[test_index].description;
	points = test_fun_array[test_index].points;
	test_fun_array[test_index].function();

	return 0;
}

/*
 * Threads scheduler test 4
 *
 * 2012, Operating Systems
 */

#include "scheduler_test.h"
#include <stdlib.h>

static unsigned rand_tests;
static unsigned exec_idx = 0;
static unsigned execution_status = SO_TEST_FAIL;
static tid_t exec_tids[SO_MAX_UNITS];
static tid_t test_tids[SO_MAX_UNITS];

static void test4master(unsigned);
static void test4worker(unsigned);

/* tests if the round robin algorithm is properly implemented */
void test_rr(void)
{
	unsigned i;

	rand_tests = get_rand(1, SO_MAX_UNITS - 1);

	/* set a huge preemption so the first thread can spawn all childs */
	so_init(SO_MAX_UNITS, 0);

	so_fork(test4master, 0);

	sched_yield();
	so_end();

	if (execution_status == SO_TEST_SUCCESS) {
		/* check threads order */
		for ( i = 0; i <= rand_tests; i++) {
			if (!equal_tids(exec_tids[i], test_tids[i])) {
				execution_status = SO_TEST_FAIL;
				break;
			}
		}
	}

	basic_test(execution_status);
}

static void test4master(unsigned dummy)
{
	unsigned i;

	/*
	 * this thread should not be preempted as it executes maximum
	 * SO_MAX_UNITS - 1, and the quantum time is SO_MAX_UNITS
	 */
	for (i = 0; i < rand_tests; i++)
		test_tids[i] = so_fork(test4worker, 0);

	/* use a cannary value to detect overflow */
	test_tids[rand_tests] = exec_tids[rand_tests] = get_tid();
}

static void test4worker(unsigned dummy)
{
	/* signal that he's the one that executes in round exec_idx */
	exec_tids[exec_idx++] = get_tid();
	so_exec();
	execution_status = SO_TEST_SUCCESS;
}

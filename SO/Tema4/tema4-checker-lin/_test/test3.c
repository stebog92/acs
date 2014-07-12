/*
 * Threads scheduler test 3
 *
 * 2012, Operating Systems
 */

#include "scheduler_test.h"

#include <stdlib.h>

static tid_t test_tid = INVALID_TID;

static void test3(unsigned dummy);

/* tests if the scheduler allows multiple runs */
void test_multiple_runs(void)
{
	tid_t local_tid = INVALID_TID;
	/* run multiple times */
	int nr_iterations = get_rand(SO_MAX_UNITS, SO_MAX_UNITS * SO_MAX_UNITS);

	while (nr_iterations > 0) {
		so_init(SO_MAX_UNITS, 0);
	
		local_tid = so_fork(test3, 0);

		sched_yield();
		so_end();

		if (this_tid(local_tid) || this_tid(test_tid) ||
				!equal_tids(test_tid, local_tid))
			break;
		nr_iterations--;
	}

	basic_test(nr_iterations == 0);
}

static void test3(unsigned dummy)
{
	test_tid = get_tid();
	so_exec();
}

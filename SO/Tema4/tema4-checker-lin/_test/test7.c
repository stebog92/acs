/*
 * Threads scheduler test 7
 *
 * 2012, Operating Systems
 */

#include "scheduler_test.h"

#define DEV0				0
#define SO_PREEMPT_UNITS	3

static unsigned exec_time = 0;
static unsigned status = SO_TEST_FAIL;

static void test7wait(unsigned);
static void test7signal(unsigned);

/* tests the IO functionality */
void test_io_dev(void)
{
	/* ensure that the thread gets to execute wait */
	so_init(SO_PREEMPT_UNITS, 1);

	so_fork(test7wait, 0);

	sched_yield();
	so_end();

	basic_test(status);
}

static void test7wait(unsigned dummy)
{
	exec_time++;
	so_fork(test7signal, 0);
	exec_time++;
	so_wait(DEV0);

	/* check if I waited more than preemption time */
	if (exec_time < SO_PREEMPT_UNITS + 2) {
		so_error("scheduled while waiting");
		return;
	}

	so_exec();
	status = SO_TEST_SUCCESS;
}

static void test7signal(unsigned dummy)
{
	unsigned step;

	/* check if wait was called */
	if (exec_time != 2) {
		so_error("thread didn't execute wait");
		return;
	}

	/* keep the other thread waiting longer than the preemption time */
	for (step = 0; step <= SO_PREEMPT_UNITS; step++) {
		exec_time++;
		so_exec();
	}

	/* if executed before signal, fail */
	if (status == SO_TEST_SUCCESS)
		status = SO_TEST_FAIL;
	/* finally release the waiting thread */
	so_signal(DEV0);
}

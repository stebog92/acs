/*
 * Threads scheduler test 5
 *
 * 2012, Operating Systems
 */

#include "scheduler_test.h"

static unsigned last_id = 0;
static unsigned status = SO_TEST_FAIL;

static void test5first(unsigned);
static void test5second(unsigned);


/* tests if the scheduler preempts the tasks */
void test_preemption(void)
{
	so_init(2, 0);

	so_fork(test5first, 0);

	sched_yield();
	so_end();

	basic_test(status);
}

#define TEST_AND_SET(_id, _new) \
	do { \
		if (last_id != (_id)) \
			so_fail("invalid tasks order"); \
		last_id = (_new); \
	} while (0);


static void test5first(unsigned dummy)
{
	last_id = 1;
	so_fork(test5second, 0);

	/* I should have runned previously */
	TEST_AND_SET(1, 1);
	so_exec();
	TEST_AND_SET(2, 1);
	so_exec();
	TEST_AND_SET(1, 1);
	so_exec();
	TEST_AND_SET(2 ,1);
	so_exec();
}

static void test5second(unsigned dummy)
{
	TEST_AND_SET(1, 2);
	so_exec();
	TEST_AND_SET(2, 2);
	so_exec();
	TEST_AND_SET(1, 2);
	so_exec();
	TEST_AND_SET(2, 2);
	so_exec();
	TEST_AND_SET(1, 2);
	so_exec();
	status = SO_TEST_SUCCESS;
}



/*
 * Threads scheduler test 8
 *
 * 2012, Operating Systems
 */

#include "scheduler_test.h"

#define DEV0	0
#define DEV1	1
#define DEV2	2
#define DEV3	3

static unsigned last_priority = 0;
static unsigned status = SO_TEST_SUCCESS;

static void test8(unsigned);
static void test8(unsigned);

/* tests the IO and priorities */
void test_io_prio(void)
{
	so_init(1, 3);

	so_fork(test8, 2);

	sched_yield();
	so_end();

	basic_test(status && last_priority == 1);
}

/* fails if the last priority set is not _p */
#define FAIL_IF_NOT_PRIO(_p, _m) \
	do { \
		if ((_p) != last_priority) \
			so_fail(_m); \
		last_priority = priority;\
	} while (0)


/*
 * Threads are mixed to wait/signal lower/higher priorities
 * P2 refers to the task with priority 2
 */
static void test8(unsigned priority)
{
	switch (priority) {
	case 1:
		/* P2 should be the one that executed last */
		FAIL_IF_NOT_PRIO(2, "should have been woke by P2");
		if (so_signal(DEV3) == 0)
			so_fail("dev3 does not exist");
		so_exec();
		FAIL_IF_NOT_PRIO(1, "invalid preemption");
		if (so_signal(DEV0) != 2)
			so_fail("P1 should wake P3 and P4 (dev0)");
		FAIL_IF_NOT_PRIO(2, "preempted too early");
		if (so_signal(DEV1) != 1)
			so_fail("P1 should wake P3 (dev1)");
		FAIL_IF_NOT_PRIO(2, "woke by someone else");
		if (so_signal(DEV0) != 1)
			so_fail("P1 should wake P4 (dev0)");
		FAIL_IF_NOT_PRIO(4, "should be the last one");
		so_exec();
		FAIL_IF_NOT_PRIO(1, "someone else was running");
		break;

	case 2:
		last_priority = 2;
		/* wait for dev 3 - invalid device */
		if (so_wait(DEV3) == 0)
			so_fail("dev3 does not exist");
		/* spawn all the tasks */
		so_fork(test8, 4);
		so_fork(test8, 3);
		so_fork(test8, 1);
		so_exec();
		so_exec();

		/* no one should have ran until now */
		FAIL_IF_NOT_PRIO(2, "somebody else ran before P2");
		if (so_wait(DEV1) != 0)
			so_fail("cannot wait on dev1");
		FAIL_IF_NOT_PRIO(3, "should run after P3");
		if (so_wait(DEV2) != 0)
			so_fail("cannot wait on dev2");
		FAIL_IF_NOT_PRIO(3, "only P3 could wake me");
		so_exec();
		break;

	case 3:
		if (so_wait(DEV0) != 0)
			so_fail("P3 cannot wait on dev0");
		FAIL_IF_NOT_PRIO(4, "priority order violated");
		if (so_wait(DEV1) != 0)
			so_fail("P3 cannot wait on dev1");
		FAIL_IF_NOT_PRIO(1, "someone else woke P3");
		if (so_signal(DEV2) != 1)
			so_fail("P3 should wake P2 (dev2)");
		break;

	case 4:
		if (so_wait(DEV0) != 0)
			so_fail("P4 cannot wait on dev0");
		FAIL_IF_NOT_PRIO(1, "lower priority violation");
		if (so_signal(DEV1) != 1)
			so_fail("P4 should wake P2 (dev1)");
		if (so_wait(DEV0) != 0)
			so_fail("P4 cannot wait on dev0");
		FAIL_IF_NOT_PRIO(1, "someone else woke dev0");
		break;
	}
}

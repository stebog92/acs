/*
 * Threads scheduler test 6
 *
 * 2012, Operating Systems
 */

#include "scheduler_test.h"

/* last priority that runned */
static unsigned last_priority = 0;
static unsigned status = SO_TEST_SUCCESS;

static void test6(unsigned);

/* tests if the scheduler preempts based on priorities */
void test_priorities(void)
{
	so_init(1, 0);

	last_priority = 2;
	so_fork(test6, 2);

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
 * Note: P1 means tasks with priority 1
 *
 * Scenario:
 * - P2 spawns P4
 * - P4 spawns P3
 * - P3 spawns P1
 */
static void test6(unsigned priority)
{
	switch (priority) {
	case 1:
		/* test if I was scheduled before P2 */
		FAIL_IF_NOT_PRIO(2, "scheduled a test with a bogus priority" );
		break;

	case 2:
		/* fork P4 */
		so_fork(test6, 4);

		/* if I was not preempted or P3 didn't run - error */
		FAIL_IF_NOT_PRIO(3, "task 2 was not preempted" );
		break;

	case 3:
		/* test if someobdy else run except P4 */
		FAIL_IF_NOT_PRIO(4, "highest priority was not scheduled");
		so_fork(test6, 1);

		/* P1 < P3 - I still have to run */
		FAIL_IF_NOT_PRIO(3, "somebody else was scheduled instead of task 3");
		break;

	case 4:
		last_priority = 4;

		/* fork lower priority P3 */
		so_fork(test6, 3);

		/* I shouldn't have been preempted */
		FAIL_IF_NOT_PRIO(4, "somebody else was scheduled instead of task 4");
		break;
	}

	so_exec();
}

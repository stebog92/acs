/*
 * Threads scheduler test 1
 *
 * 2012, Operating Systems
 */

#include "scheduler_test.h"

#include <stdio.h>
#include <stdlib.h>

static int exec_units = 0;
static int exec_priority = 0;
static int exec_devs = 0;

static void test1(unsigned);

/* tests if the handler runs all the exec's */
void test_integrity(void)
{
	exec_units = get_rand(1, SO_MAX_UNITS);
	exec_devs = get_rand(1, SO_MAX_UNITS);
	exec_priority = get_rand(1, SO_MAX_PRIO);

	if (so_init(0, 0) == 0) {
		so_error("invalid time quantum");
		goto test;
	}

	if (so_init(SO_MAX_UNITS, exec_devs) < 0) {
		so_error("initialization failed");
		goto test;
	}

	/* invalid handler */
	if (so_fork(0, 0) != INVALID_TID) {
		so_error("invalid handler");
		goto test;
	}

	if (so_fork(test1, exec_priority) == INVALID_TID) {
		so_error("cannot create new task");
		goto test;
	}

test:
	sched_yield();
	so_end();

	basic_test(exec_units == 0);
}

/* forked functions */
static void test1(unsigned prio)
{
	if (prio != exec_priority)
		so_fail("invalid exec priority");

	if (so_wait(exec_devs) == 0)
		so_fail("invalid waiting device");

	if (so_signal(exec_devs) >= 0)
		so_fail("invalid signalling device");

	while (--exec_units)
		so_exec();
}


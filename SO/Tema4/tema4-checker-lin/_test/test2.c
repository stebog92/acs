/*
 * Threads scheduler test 2
 *
 * 2012, Operating Systems
 */

#include "scheduler_test.h"

/*
 * these global variables should not be guarded, because the mutual
 * exclusion from the handler functions should guarantee synchronization
 */
static tid_t test_tid = INVALID_TID;
static tid_t reported_test_tid = INVALID_TID;
static tid_t reported_local_tid = INVALID_TID;

static void test2a(unsigned dummy);
static void test2b(unsigned dummy);

/* tests if threads are actually created and not simulated */
void test_thread_id(void)
{
	tid_t local_tid;

	so_init(5, 0);
	
	local_tid = so_fork(test2a, 0);

	sched_yield();
	so_end();

	/* check if a different thread was created each time */
	basic_test(!this_tid(local_tid) && !this_tid(test_tid) &&
			!equal_tids(local_tid, test_tid) &&
			!equal_tids(reported_local_tid, INVALID_TID) &&
			!equal_tids(reported_test_tid, INVALID_TID) &&
			equal_tids(local_tid, reported_local_tid) &&
			equal_tids(test_tid, reported_test_tid));
}


static void test2a(unsigned dummy)
{
	test_tid = so_fork(test2b, 0);
	reported_local_tid = get_tid();
	so_exec();
    fprintf(stderr, "test2a");
}

static void test2b(unsigned dummy)
{
	reported_test_tid = get_tid();
    fprintf(stderr, "test2b");
}




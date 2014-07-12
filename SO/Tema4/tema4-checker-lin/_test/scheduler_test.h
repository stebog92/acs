/*
 * Threads scheduler functions
 *
 * 2012, Operating Systems
 */

#ifndef _SCHED_TEST_H_
#define _SCHED_TEST_H_

#define DLL_IMPORTS

#include "run_test.h"
#include "so_scheduler.h"
#include <stdio.h>

/* comment this if you don't want error debugging */
#define SO_VERBOSE_ERROR yes

#define SO_TEST_FAIL	0
#define SO_TEST_SUCCESS	1
#define SO_MAX_UNITS	32

/* functions tested */
extern void test_integrity(void);
extern void test_thread_id(void);
extern void test_multiple_runs(void);
extern void test_rr(void);
extern void test_preemption(void);
extern void test_priorities(void);
extern void test_rr_stress(void);
extern void test_io_dev(void);
extern void test_io_prio(void);
extern void test_sched(void);


/* debugging macro */
#ifdef SO_VERBOSE_ERROR
 #define so_error(_m, ...) fprintf(stderr, "ERR: " _m "\n", ##__VA_ARGS__);
#else
 #define so_error(_m, ...)
#endif

/* shows the message and exits  */
#define so_fail(_m) \
	do { \
		so_error(_m); \
		return; \
	} while (0)

/* returns unsigned random value between _min and _max - 1 */
#define get_rand(_min, _max) ((rand() % (_max - _min)) + _min)

/* architecture dependent functions */
#ifdef __linux__

static inline tid_t get_tid(void)
{
	return pthread_self();
}

static inline int equal_tids(tid_t t1, tid_t t2)
{
	return pthread_equal(t1, t2);
}

/* useful defines */
static inline int this_tid(tid_t t)
{
	return pthread_equal(t, get_tid());
}

#elif defined _WIN32

#define inline __inline


static inline tid_t get_tid(void)
{
	return GetCurrentThreadId();
}

static inline int equal_tids(tid_t t1, tid_t t2)
{
	return t1 == t2;
}

/* useful defines */
static inline int this_tid(tid_t t)
{
	return equal_tids(t, get_tid());
}

static inline void sched_yield(void)
{
	Sleep(0);
}

#else
 #error "Unknown platform"
#endif


#endif /* _SCHED_TEST_H_ */

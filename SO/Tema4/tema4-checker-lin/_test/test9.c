/*
 * Threads scheduler test 9
 *
 * 2012, Operating Systems
 */

#include "scheduler_test.h"
#include "stdlib.h"
#include "string.h"
#include "stdio.h"

struct so_task_info_t {
	unsigned creation_time;
	unsigned priority;
	unsigned executed;
	unsigned runtime;
	tid_t tid;
};

/* maximum iterations of the algorithm */
#define SO_MAX_EXECUTION_TIME (SO_MAX_UNITS * SO_MAX_UNITS)

/*
 * again, these variables should be guarded by the scheduler itself,
 * because only one function can be executed at a certain moment
 */
static unsigned random_q = 0;
static unsigned exec_time = 0;
static unsigned tasks_no = 1;
static struct so_task_info_t tasks_info[SO_MAX_EXECUTION_TIME];
static struct so_task_info_t * tasks_history[SO_MAX_EXECUTION_TIME];


static unsigned status = SO_TEST_FAIL;
static void test9(unsigned dummy);
static void test9check(void);

void test_rr_stress(void)
{

	/* random execution runtime */
	random_q = get_rand(1, SO_MAX_UNITS / 2);

	so_init(random_q, 0);

	/* spawn task */
	so_fork(test9, get_rand(0, SO_MAX_PRIO));

	sched_yield();
	so_end();

	test9check();

	basic_test(status);
}

static void test9(unsigned priority)
{
	unsigned executed_fork = 0;
	unsigned rand_iterations;
	struct so_task_info_t * my_info;

	/* fill info about my task */
	my_info = &tasks_info[tasks_no++];
	my_info->creation_time = exec_time;
	my_info->priority = priority;
	my_info->tid = get_tid();
	my_info->executed = 0;

	/* get a rand number of iterations to do */
	rand_iterations = get_rand(1, SO_MAX_UNITS);

	while(rand_iterations-- && exec_time < SO_MAX_EXECUTION_TIME) {

		/* fill in history */
		my_info->executed++;
		tasks_history[exec_time++] = my_info;

		/*
		 * here we force all tasks to execute at least one fork
		 * if it was executed previously, then we offer random
		 * chances to execute either fork or exec
		 */
		if ((!executed_fork && rand_iterations == 0) || rand() % 2) {
			/* create new task with random priority */
			so_fork(test9, get_rand(0, SO_MAX_PRIO));
			executed_fork = 1;
		} else {
			so_exec();
		}
		
	}
	if (exec_time == SO_MAX_EXECUTION_TIME)
		status = SO_TEST_SUCCESS;
}

/* checks if there is a higher priority waiting to run */
static inline int is_higher(unsigned *vec, unsigned prio)
{
	for (prio++; prio < SO_MAX_PRIO; prio++)
		if (vec[prio] != 0)
			return 1;
	return 0;
}

static void test9check(void)
{
	unsigned priority_stats[SO_MAX_PRIO];
	unsigned idx;
	unsigned total_exec_time = 0;
	struct so_task_info_t *last_task = 0;
	struct so_task_info_t *current_task;

	/* check to see if anything was executed */
	if (exec_time == 0)
		so_fail("nothing was executed");

	/* integrity check - all the tasks had been consumed */
	for (idx = 0; idx < tasks_no; idx++) {
		total_exec_time += tasks_info[idx].executed;
	}

	if (total_exec_time > SO_MAX_EXECUTION_TIME ||
			total_exec_time != exec_time ||
			(exec_time == SO_MAX_EXECUTION_TIME &&
			 status != SO_TEST_SUCCESS)) {
		so_fail("total execution time mismatch");
	}

	status = SO_TEST_FAIL;
	memset(priority_stats, 0, sizeof(priority_stats));

	/* for each time unit elapsed */
	for (idx = 0; idx < exec_time; idx++) {
		current_task = tasks_history[idx];

		/* if I wasn't the last task, then I was preempted */
		if (last_task != current_task) {
			/* if they have the same priority */
			if (last_task && last_task->executed != 0 &&
					current_task->creation_time != idx &&
					last_task->priority == current_task->priority &&
					last_task->runtime != random_q)
				so_fail("previous task did not complete it's quantum");
			last_task = current_task;
			current_task->runtime = 0;
		}
		/* check if it has nothing more to execute */
		if (current_task->executed == 0) {
			so_fail("tasks has nothing more to execute");
		}

		/* check if the task was created */
		if (idx == current_task->creation_time) {
			priority_stats[current_task->priority]++;
		}

		/* check if there is a task with higher priority waiting */
		if (is_higher(priority_stats, current_task->priority)) {
			so_fail("higher priority waiting in queue");
		}

		current_task->executed--;
		current_task->runtime++;

		/* if nothing more to execute -> leave */
		if (current_task->executed == 0) {
			priority_stats[current_task->priority]--;
		} else if (priority_stats[current_task->priority] > 1 &&
				current_task->runtime > random_q) {
			so_fail("task was not preempted");
		}
	}
	status = SO_TEST_SUCCESS;
}

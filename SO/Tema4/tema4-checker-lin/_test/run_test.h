/*
 * Testing framework headers
 *
 * 2012, Operating Systems
 */

#ifndef _RUN_TEST_H_
#define _RUN_TEST_H_

/* functions exported by the framework */
extern void init_test(void);
extern void cleanup_test(void);
extern void basic_test(int true);

/* prototype executed for each test */
typedef void (run_test_f)(void);

struct run_test_t {
	run_test_f * function;		/* testing function */
	char * description;			/* test description */
	int points;					/* points for each test */
};

/* tests array with each test */
extern struct run_test_t test_fun_array[];

#endif /* _RUN_TEST_H_ */

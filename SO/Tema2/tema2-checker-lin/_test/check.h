#ifndef CHECK_H_
#define CHECK_H_

#define fail_if(cond)			\
do {					\
	if ((cond)) {			\
		fprintf(stderr, "Assertion fail_if(%s) failed at %s:%d\n", #cond, __FILE__, __LINE__); \
		exit(1);		\
	}				\
} while(0)

#define fail_unless(cond)			\
do {					\
	if (!(cond)) {			\
		fprintf(stderr, "Assertion fail_unless(%s) failed at %s:%d\n", #cond, __FILE__, __LINE__); \
		exit(1);		\
	}				\
} while(0)

#endif

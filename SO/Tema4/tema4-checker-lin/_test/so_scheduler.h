/*
 * Threads scheduler header
 *
 * 2012, Operating Systems
 */

#ifndef _SO_SCHEDULER_H_
#define _SO_SCHEDULER_H_

/* OS dependent stuff */
#ifdef __linux__

 #include <pthread.h>

 typedef pthread_t tid_t;

 #define DECL_PREFIX
#elif defined (_WIN32)

 #include <windows.h>

 typedef DWORD tid_t;

 #ifdef DLL_IMPORTS
  #define DECL_PREFIX __declspec(dllimport)
 #else
  #define DECL_PREFIX __declspec(dllexport)
 #endif
#else
 #error "Unknown platform"
#endif

/*
 * the maximum priority that can be assigned to a thread
 */
#define SO_MAX_PRIO		5

/*
 * return value of failed tasks
 */
#define INVALID_TID ((tid_t)-1)

#ifdef __cplusplus
 extern "C" {
#endif

/*
 * handler prototype
 */
typedef void (so_handler)(unsigned);

/*
 * creates and initializes scheduler
 * + time quantum for each thread
 * + number of IO devices supported
 * returns: 0 on success or negative on error
 */
DECL_PREFIX int so_init(unsigned, unsigned);

/*
 * creates a new so_task_t and runs it according to the scheduler
 * + handler function
 * + priority
 * returns: tid of the new task if successful or INVALID_TID
 */
DECL_PREFIX tid_t so_fork(so_handler *, unsigned);

/*
 * waits for an IO device
 * + device index
 * returns: -1 if the device does not exist or 0 on success
 */
DECL_PREFIX int so_wait(unsigned);

/*
 * signals an IO device
 * + device index
 * return the number of tasks woke or -1 on error
 */
DECL_PREFIX int so_signal(unsigned);

/*
 * does whatever operation
 */
DECL_PREFIX void so_exec(void);

/*
 * destroys a scheduler
 */
DECL_PREFIX void so_end(void);

#ifdef __cplusplus
}
#endif

#endif /* _SO_SCHEDULER_H_ */

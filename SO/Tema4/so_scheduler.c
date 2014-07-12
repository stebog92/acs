/* Mihai Ciocan
 * 334CA
 */

#include "so_scheduler.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <semaphore.h>


static so_task_t tasks[MAX_NUM];
static int size, num_devices;
static int running_time;
static int queue[MAX_NUM];
static so_device_t devices[MAX_NUM];
pthread_mutex_t master_lock;
pthread_cond_t master_cond;
int terminated;

int begin, end;
sem_t *queue_lock;
sem_t *fork_lock;
sem_t *cpu_occupied;

/* get queue size */
static int queue_size()
{
    if (end < begin) {
        return end + MAX_NUM - begin;
    } else {
        return end - begin;
    }
}

/* insert id considering priority */
static void insert(int id, int prio)
{
    int i;
    if (queue_size() + 1 == MAX_NUM) {
        return;
    }
    sem_wait(queue_lock);
    if (end == MAX_NUM)
        end = 0;

    i = begin;
    while(i != end) {
        if (tasks[queue[i]].priority < prio) {
            int j = end + 1;
            if (j == MAX_NUM) {
                j = 0;
            }
            while (j != i) {
                if (j == 0) {
                    queue[j] = queue[MAX_NUM - 1];
                } else {
                    queue[j] = queue[j - 1];
                }
                j--;
                if (j < 0) {
                    j = MAX_NUM;
                }
            }
            break;
        }
        i++;
        if (i == MAX_NUM) {
            i = 0;
        }
    }
    end++;
    queue[i] = id;
    sem_post(queue_lock);
}

/* get and pop next thread id in queue*/
static void pop(int *id)
{
    sem_wait(queue_lock);
    if (begin == MAX_NUM)
        begin = 0;
    *id = queue[begin++];
    sem_post(queue_lock);
}

/* get so_task_t structure by id */
static void get_task(tid_t id, so_task_t **task)
{
    int i;
    for (i = 0; i < size; i++) {
        if (tasks[i].thread_id == id) {
            *task = &tasks[i];
            return;
        }
    }
    *task = NULL;
}

/* insert thread in queue */
static void preempt_thread(so_task_t *task)
{
    task->state = READY;
    insert(task->tid, task->priority);
}

/* choose next thread to be processed */
static void schedule_next_thread()
{
    int next_id;
    so_task_t *next_task;
    pop(&next_id);
    next_task = &tasks[next_id];
    pthread_mutex_lock(&next_task->lock);
    next_task->time_units_left = running_time;
    next_task->state = RUNNING;
    pthread_cond_signal(&next_task->not_ready);
    pthread_mutex_unlock(&next_task->lock);
}

/* wait to be scheduled */
static void wait_to_be_scheduled(so_task_t *task)
{
    pthread_mutex_lock(&task->lock);
    while (task->state == READY) {
        pthread_cond_wait(&task->not_ready, &task->lock);
    }
    pthread_mutex_unlock(&task->lock);
}

/* scheduler handles the threads */
static void check_scheduler ()
{
    so_task_t *task;
    tid_t id = pthread_self();
    get_task(id, &task);

    if (task == NULL) {
        pthread_mutex_lock(&master_lock);
        while(terminated == 0) {
            pthread_cond_wait(&master_cond, &master_lock);
        }
        pthread_mutex_unlock(&master_lock);
        return;
    }

    /* if time remaining on cpu is 0 preempt and 
        schedule next thread */
    task->time_units_left --;
    if (task->time_units_left == 0) {
        preempt_thread(task);
        schedule_next_thread();
        wait_to_be_scheduled(task);
    }
}

/* here thread wait to be scheduled and runs handler */
static void *start_thread(void *args)
{
    so_task_t *task = (so_task_t*) args;
    task->created = 1;
    /* waits to be scheduled for the first time */
    wait_to_be_scheduled(task);
    task->handler(task->priority);

    /* schedule next thread when handler finishes */
    if (queue_size() != 0) {
        schedule_next_thread();
    } else {   
        pthread_mutex_lock(&master_lock);
        terminated = 1;
        pthread_cond_signal(&master_cond);
        pthread_mutex_unlock(&master_lock);
    }
    return NULL;
}

/* init scheduler */
DECL_PREFIX int so_init(unsigned time, unsigned num)
{

    int i;
    if (time <= 0) {
        return -1;
    }
    size = 0;
    running_time = time;
    num_devices = num;

    fork_lock = sem_open("/FORK_LOCK", O_CREAT, 0644, 1);
    cpu_occupied = sem_open("/CPU_LOCK", O_CREAT, 0644, 1);
    queue_lock = sem_open("/QUEUE_LOCK", O_CREAT, 0644, 1);
    
    pthread_mutex_init(&master_lock, NULL);
    pthread_cond_init(&master_cond, NULL);

    for (i = 0; i < num; i++) {
        pthread_mutex_init(&devices[i].lock, NULL);
        pthread_cond_init(&devices[i].signal, NULL);
    }
    return 0;
}

/* create a new thread */
DECL_PREFIX tid_t so_fork(so_handler *handler, unsigned priority)
{
    so_task_t *task;
    if (handler == NULL) {
        return INVALID_TID;
    }
    sem_wait(fork_lock);
    task = &tasks[size];
    task->tid = size;
    size++;
    sem_post(fork_lock);

    /* initialize structure */
    task->handler = handler;
    task->priority = priority;
    task->state = NEW;
    pthread_mutex_init(&task->lock, NULL);
    pthread_cond_init(&task->not_ready, NULL);
    if(task->tid != 0) {
        insert(task->tid, task->priority);
        task->state = READY;
    } else {
        task->time_units_left = running_time;
        task->state = RUNNING;
    }
    /* start thread */
    pthread_create(&task->thread_id, NULL, &start_thread, task);
    while(!task->created);

    check_scheduler();
    return task->thread_id;
}

/* wait incoming events on device */
DECL_PREFIX int so_wait(unsigned device)
{
    so_task_t *task, *next_task;
    int next_id;

    /* return if device doesn't exist */
    if (device >= num_devices) {
        check_scheduler();
        return -1;
    }
    get_task(pthread_self(), &task);

    task->state = WAITING;
    pthread_mutex_lock(&devices[device].lock);

    devices[device].thread_ids[devices[device].occupied++] = task->tid;
    
    /* schedule next task */
    pop(&next_id);
    next_task = &tasks[next_id];

    pthread_mutex_lock(&next_task->lock);
    next_task->time_units_left = running_time;
    next_task->state = RUNNING;
    pthread_cond_signal(&next_task->not_ready);
    pthread_mutex_unlock(&next_task->lock);

    /* wait current thread to be scheduled */
    while(task->state == WAITING) {
        pthread_cond_wait(&devices[device].signal, &devices[device].lock);
    }
    pthread_mutex_unlock(&devices[device].lock);
    pthread_mutex_lock(&task->lock);
    while (task->state != RUNNING) {
        pthread_cond_wait(&task->not_ready, &task->lock);
    }
    pthread_mutex_unlock(&task->lock);
    check_scheduler();
    return 0;
}

/* signal waiting threads */
DECL_PREFIX int so_signal(unsigned event)
{
    int blocked, i, prio;
    if (event >= num_devices) {
        check_scheduler();
        return -1;
    }
    pthread_mutex_lock(&devices[event].lock);
    blocked = devices[event].occupied;
    i = devices[event].occupied;
    while((i--) != 0) {
        prio = tasks[devices[event].thread_ids[i]].priority;
        insert(devices[event].thread_ids[i], prio);
        tasks[devices[event].thread_ids[i]].state = READY;
    }
    devices[event].occupied = 0;
    /* signal all threads */
    if (blocked == 1) {
        pthread_cond_signal(&devices[event].signal);
    } else {
        pthread_cond_broadcast(&devices[event].signal);
    }
    pthread_mutex_unlock(&devices[event].lock);

    check_scheduler();
    /* return the number of blocked threads*/
    return blocked;
}

/* Dummy instruction */
DECL_PREFIX void so_exec(void)
{
    check_scheduler();
}

/* Freeing the memory */
DECL_PREFIX void so_end(void)
{

    int i;
    for (i = 0; i < size; i++) {
        pthread_join(tasks[i].thread_id, NULL);
        pthread_mutex_destroy(&tasks[i].lock);
        pthread_cond_destroy(&tasks[i].not_ready);
    }
    memset(tasks, 0, MAX_NUM * sizeof(so_task_t));

    pthread_mutex_destroy(&master_lock);
    pthread_cond_destroy(&master_cond);

    sem_close(fork_lock);
    sem_unlink("/FORK_LOCK");
    sem_close(cpu_occupied);
    sem_unlink("/CPU_LOCK");
    sem_close(queue_lock);
    sem_unlink("/QUEUE_LOCK");
}

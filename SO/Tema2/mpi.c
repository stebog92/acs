/* Tema 2 MPI
 * 
 * Ciocan Mihai
 * 334CA
 */

#include <stdio.h>
#include <sys/mman.h>
#include <unistd.h>
#include <sys/stat.h>
#include <string.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/types.h>
#include <semaphore.h>
#include <mqueue.h>
#include <errno.h>
#include "mpi.h"
#include "mpi_err.h"


MPI_Comm mpi_comm_world;
struct mpi_process_utils mpi_utils;

/* Open semaphore by name and set sem if NULL */
static int set_sem(char *sem_name, sem_t** sem, int value)
{
    if (*sem == NULL) {
        *sem = sem_open (sem_name, O_CREAT, 0666, value);
        if (*sem == SEM_FAILED) {
            return -1;
        }
    }
    return 0;
}

/* Set shared variable mpi_comm_world if NULL */
static int set_mpi_comm() 
{
    int fd_mpi_comm;
    if (mpi_comm_world == NULL) {
        fd_mpi_comm = shm_open(SHM_MPI_NAME, O_RDWR, 0666);
        if (fd_mpi_comm == -1) {
            return -1;
        }
        mpi_comm_world = mmap (NULL, sizeof(MPI_Comm), PROT_READ|PROT_WRITE, MAP_SHARED, fd_mpi_comm, 0);
        if (mpi_comm_world == MAP_FAILED) {
            return -1;
        }
    }
    return 0;
}

/* Open message queue by name and set mq if 0 */
static int set_mq(char *name, mqd_t *mq, int size)
{
    struct mq_attr attr;
    if (*mq == 0) {
        attr.mq_flags = 0;
        attr.mq_maxmsg = 5;
        attr.mq_msgsize = size;
        attr.mq_curmsgs = 0;
        *mq = mq_open (name, O_CREAT | O_RDWR, 0666, &attr);
        if (*mq == -1) {
            return -1;
        }
    }
    return 0;
}

/* Return sizeof dataype */
static int size_of(MPI_Datatype datatype) {
    if (datatype == MPI_CHAR) {
        return 1;
    }
    return datatype * 4;
}

static int check_init_finalize(int *init, int *fin) {
    if (MPI_Initialized(init) != 0) {
        return -1;
    }

    if (MPI_Finalized(fin) != 0) {
        return -1;
    }
    return 0;
}

/* Unmaps shared memory for mpi_comm_world and deletes it */
static int delete_mpi_comm()
{
    return munmap(mpi_comm_world, sizeof(mpi_comm_world));
}

int MPI_Init(int *argc, char ***argv)
{
    if (set_mpi_comm() == -1) {
        return MPI_ERR_IO;
    }

    /* If initialized return error */
    if (mpi_utils.initialized == 1) {
        return MPI_ERR_OTHER;
    }

    if(set_sem(SEM_MPI_NAME, &mpi_utils.sem_mpi_comm, 1) == -1) {
        return MPI_ERR_IO;
    }

    if (set_sem(SEM_INIT_NAME, &mpi_utils.sem_mpi_init, 0) == -1) {
        return MPI_ERR_IO;
    }

    sem_wait(mpi_utils.sem_mpi_comm);
    
    mpi_comm_world->MPI_INIT_CALLS++;
    /* If all processes called MPI_Init set MPI_INIT to 1 */
    if (mpi_comm_world->MPI_INIT_CALLS == mpi_comm_world->MPI_SIZE) {
        mpi_comm_world->MPI_INIT = 1;
        sem_post(mpi_utils.sem_mpi_init);
    }
    sem_post(mpi_utils.sem_mpi_comm);
    
    /* Wait until all other processes call MPI_INIT */
    sem_wait(mpi_utils.sem_mpi_init);
    mpi_utils.initialized = 1;
    sem_post(mpi_utils.sem_mpi_init);
    
    return MPI_SUCCESS;
}

/* Set flag to 1 if initialized or 0 otherwise */
int  MPI_Initialized(int *flag)
{
    *flag = mpi_utils.initialized;
    return MPI_SUCCESS;
}

/* Return # of processes in size variable */
int  MPI_Comm_size(MPI_Comm comm, int *size)
{
    int init, fin;
    if (comm != mpi_comm_world) {
        return MPI_ERR_OTHER;
    }

    if (check_init_finalize(&init, &fin) == -1) {
        return MPI_ERR_OTHER;
    }

    if (init && !fin) {
        *size = comm->MPI_SIZE;
        return MPI_SUCCESS;
    }
    return MPI_ERR_OTHER;
}

/* Return rank of current process in rank variable */
int  MPI_Comm_rank(MPI_Comm comm, int *rank)
{
    int i, current_pid = getpid(), init, fin;
    if (comm != mpi_comm_world) {
        return MPI_ERR_COMM;
    }
    if (check_init_finalize(&init, &fin) == -1) {
        return MPI_ERR_OTHER;
    }
    
    if (init && !fin) {
        for (i = 0; i < comm->MPI_SIZE; i++) {
            if (comm->MPI_PIDS[i] == current_pid) {
                *rank = i;
                return MPI_SUCCESS;
            }
        }
    }
    return MPI_ERR_OTHER;
}

/* End of parallel processing */
int  MPI_Finalize()
{
    if (mpi_utils.finalized) {
        return MPI_ERR_OTHER;
    }
    if (set_sem(SEM_FIN_NAME, &mpi_utils.sem_mpi_fin, 0) == -1) {
        return MPI_ERR_IO;
    }

    sem_wait(mpi_utils.sem_mpi_comm);
    mpi_comm_world->MPI_INIT_CALLS--;
    /* If all processes called MPI_Finalize set MPI_FIN to 1*/
    if (mpi_comm_world->MPI_INIT_CALLS == 0) {
        mpi_comm_world->MPI_FIN = 1;
        sem_post (mpi_utils.sem_mpi_fin);
    }
    sem_post(mpi_utils.sem_mpi_comm);
    /* Wait until all other processes call MPI_Finalize */
    sem_wait(mpi_utils.sem_mpi_fin);
    mpi_utils.finalized = 1;
    sem_post(mpi_utils.sem_mpi_fin);

    /* Close all semaphores */
    sem_close(mpi_utils.sem_mpi_fin);
    sem_close(mpi_utils.sem_mpi_comm);
    sem_close(mpi_utils.sem_mpi_init);

    /* Unlink message queue */
    if (strlen(mpi_utils.mq_name)) {
        mq_unlink(mpi_utils.mq_name);
    }
    /* Unmap shared memory */
    if (delete_mpi_comm() == -1) {
        return MPI_ERR_IO;
    }
    return MPI_SUCCESS;
}

/* Set flag to 1 if initialized, 0 otherwise */
int MPI_Finalized(int *flag)
{
    *flag = mpi_utils.finalized;
    return MPI_SUCCESS;
}

/* Send message to destination process */
int  MPI_Send(void *buf, int count, MPI_Datatype datatype, int dest,
		      int tag, MPI_Comm comm)
{
    int init, fin, rank;
    char name[20];
    if (check_init_finalize (&init, &fin) == -1) {
        return MPI_ERR_IO;
    }
    if (init && !fin) {
        /* Check for rank errors */
        if (dest > mpi_comm_world->MPI_SIZE) {
            return MPI_ERR_RANK;
        }

        /* Check for comm errors */
        if (comm != mpi_comm_world) {
            return MPI_ERR_COMM;
        }
 
        /* Check for type errors */
        if (datatype > MPI_DOUBLE || datatype < MPI_CHAR) {
            return MPI_ERR_TYPE;
        }
        /* Get rank of the process*/
        if (MPI_Comm_rank(mpi_comm_world, &rank) != 0) {
            return MPI_ERR_IO;
        }
       
        /* Set queue for send operation */
        sprintf (name, "/MQ_%d", dest);
        set_mq(name, &(mpi_utils.mq_mpi), sizeof(struct msg));

        /* Build message */
        memcpy (mpi_utils.message.mess, buf, count * size_of(datatype));
        mpi_utils.message.destination = dest;
        mpi_utils.message.source = rank;
        mpi_utils.message.tag = tag;

        /*Send message */
        mq_send(mpi_utils.mq_mpi, (void *)&(mpi_utils.message), 
                sizeof(struct msg), 0);

        /*Close message queue */
        mq_close (mpi_utils.mq_mpi);
        mpi_utils.mq_mpi = 0;
    } else {
        /* Return error if not initialized or finalized already*/
        return MPI_ERR_OTHER;
    }
    return MPI_SUCCESS;
}

/* Recv message from other processes */
int  MPI_Recv(void *buf, int count, MPI_Datatype datatype,
		      int source, int tag, MPI_Comm comm, MPI_Status *status)
{
    int init, fin, length, rank, size, i;
    char name[20];

    if (check_init_finalize(&init, &fin) == -1) {
        return MPI_ERR_OTHER;
    }

    if (init && !fin) {
        /* Get rank */
        if (MPI_Comm_rank(comm, &rank) != 0) {
            return MPI_ERR_OTHER;
        }
        /* Check for Type errors */
        if (datatype > MPI_DOUBLE || datatype < MPI_CHAR) {
        return MPI_ERR_TYPE;
        }
        /* Check for tag errors */
        if (tag != MPI_ANY_TAG) {
            return MPI_ERR_TAG;
        }
        /* Check for source errors */
        if (source != MPI_ANY_SOURCE) {
            return MPI_ERR_RANK;
        }

        /*Check for comm errors */
        if (comm != mpi_comm_world) {
            return MPI_ERR_COMM;
        }

        /* Get process's own queue for receiving */
        sprintf (name, "/MQ_%d", rank);
        set_mq(name, &mpi_utils.mq_mpi, sizeof(struct msg));
        if (!strlen (mpi_utils.mq_name)) {
            strcpy(mpi_utils.mq_name, name);
        }

        /* Receive the message */
        mq_receive(mpi_utils.mq_mpi, (void*)&(mpi_utils.message),
                    sizeof (struct msg), 0);

        /* Copy message in buf */
        memcpy (buf, mpi_utils.message.mess, count * size_of(datatype));
        
        /* Set status if necessary */
        if (status != MPI_STATUS_IGNORE) {    
            status->MPI_SOURCE = mpi_utils.message.source;
            status->MPI_TAG = mpi_utils.message.tag;
            status->_size = count * size_of(datatype);
        }

        /* Close message queue */
        mq_close(mpi_utils.mq_mpi);
        mpi_utils.mq_mpi = 0;
    } else {
        return MPI_ERR_OTHER;
    }
    return MPI_SUCCESS;
}

/* Return in count the # elements of 'datatype' type  received */
int  MPI_Get_count(MPI_Status *status, MPI_Datatype datatype, int *count)
{
    *count = status->_size / size_of(datatype);
    return MPI_SUCCESS;
}

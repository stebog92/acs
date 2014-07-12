/* Tema 2 MPI
 * Ciocan Mihai
 * 334CA
 */


#ifndef MPI_H_
#define MPI_H_

#include "mpi_err.h"

#if defined(__linux__)

#define DECLSPEC

#elif defined(_WIN32)

#ifdef EXPORTS
#define DECLSPEC __declspec(dllexport)
#else
#define DECLSPEC __declspec(dllimport)
#endif

#else
#error "Unknown platform"
#endif

#define MESSAGE_SIZE 4096
#define MQ_NAME_SIZE 256
#define PROC_NUM 10
static char SEM_MPI_NAME[] = "/SEM_MPI";
static char SEM_INIT_NAME[] = "/SEM_INIT";
static char SEM_FIN_NAME[] = "/SEM_FIN";
static char SHM_MPI_NAME[] = "/SHM_MPI";



struct mpi_comm {
    int MPI_INIT;
    int MPI_FIN;
    int MPI_SIZE;
    int MPI_PIDS[PROC_NUM];
    int MPI_INIT_CALLS;
};
typedef struct mpi_comm *MPI_Comm;

extern DECLSPEC struct mpi_comm *mpi_comm_world;

#define MPI_COMM_WORLD (mpi_comm_world)

typedef unsigned char MPI_Datatype;

#define MPI_CHAR	0
#define MPI_INT		1
#define MPI_DOUBLE	2

struct mpi_status {
	int MPI_SOURCE;
	int MPI_TAG;
	int _size;
};

struct msg {
    int source;
    int destination;
    int tag;
    char mess[MESSAGE_SIZE];
};

struct mpi_process_utils {
    sem_t *sem_mpi_comm;
    sem_t *sem_mpi_init;
    sem_t *sem_mpi_fin;
    mqd_t mq_mpi;
    struct msg message;
    int finalized;
    int initialized;
    char mq_name[MQ_NAME_SIZE];
};




typedef struct mpi_status MPI_Status;

#define MPI_ANY_SOURCE	(0xffffeeee)
#define MPI_ANY_TAG	(0xaaaabbbb)
#define MPI_STATUS_IGNORE ((MPI_Status *)0xabcd1234)

int DECLSPEC MPI_Init(int *argc, char ***argv);
int DECLSPEC MPI_Initialized(int *flag);
int DECLSPEC MPI_Comm_size(MPI_Comm comm, int *size);
int DECLSPEC MPI_Comm_rank(MPI_Comm comm, int *rank);
int DECLSPEC MPI_Finalize();
int DECLSPEC MPI_Finalized(int *flag);

int DECLSPEC MPI_Send(void *buf, int count, MPI_Datatype datatype, int dest,
		      int tag, MPI_Comm comm);

int DECLSPEC MPI_Recv(void *buf, int count, MPI_Datatype datatype,
		      int source, int tag, MPI_Comm comm, MPI_Status *status);


int DECLSPEC MPI_Get_count(MPI_Status *status, MPI_Datatype datatype, int *count);

#endif

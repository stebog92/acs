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

struct mpi_comm;
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

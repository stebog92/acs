/* Tema 2 MPI
 * 
 * Ciocan Mihai
 * 334CA
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <mqueue.h>
#include <unistd.h>
#include <semaphore.h>
#include "mpi.h"
#include "mpi_err.h"

int main(int argc, char** argv)
{
    int pid, i, fd_shm_mpi;
    /* Too few arguments */
    if (argc < 4) {
        exit(EXIT_FAILURE);
    }

    /* Share mpi_comm_world to other processes */
    fd_shm_mpi = shm_open(SHM_MPI_NAME, O_CREAT | O_RDWR, 0666);
    ftruncate(fd_shm_mpi, sizeof (mpi_comm_world));
    mpi_comm_world = mmap(NULL, sizeof (mpi_comm_world), PROT_READ|PROT_WRITE, MAP_SHARED, fd_shm_mpi, 0);
    mpi_comm_world->MPI_SIZE = atoi(argv[2]);
    
    /* Create processes */
    for (i = 0; i < mpi_comm_world->MPI_SIZE; i++) {
        pid = fork();
        switch (pid) {
            case -1:
                fprintf (stderr, "fork failed\n");
                exit(EXIT_FAILURE);
            case 0:
                if (execvp(argv[3], argv + 3) == -1) {
                    fprintf (stderr, "exec error");
                    exit(EXIT_FAILURE);
                }
            default:
                /* Create list with all processes for this mpi world */
                mpi_comm_world->MPI_PIDS[i] = pid;
        }
    }
    for (i = 0; i < mpi_comm_world->MPI_SIZE; i++) {
        waitpid(mpi_comm_world->MPI_PIDS[i], NULL, 0);
    }

    /* Unmap shared memory */
    munmap (mpi_comm_world, sizeof(mpi_comm_world));

    /* Unlink all sync objects */
    shm_unlink(SHM_MPI_NAME);
    sem_unlink(SEM_INIT_NAME);
    sem_unlink(SEM_FIN_NAME);
    sem_unlink(SEM_MPI_NAME);

    return 1;
}

#include <stdio.h>
#include <stdlib.h>

#include "mpi.h"
#include "check.h"

#define NUM_PROCS 8
#define TAG 0x1001

int main(int argc, char *argv[])
{
	int rank, size;
	int x;
	int final;
	int ret;
	MPI_Status stat;

	ret = MPI_Init(&argc, &argv);
	fail_unless(ret == MPI_SUCCESS);

	ret = MPI_Comm_size(MPI_COMM_WORLD, &size);
	fail_unless(ret == MPI_SUCCESS);

	ret = MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	fail_unless(ret == MPI_SUCCESS);

	if (rank == 0) {
		x = 0;

		ret = MPI_Send(&x, 1, MPI_INT, 1, TAG, MPI_COMM_WORLD);
		fail_unless(ret == MPI_SUCCESS);

		ret = MPI_Recv(&final, 1, MPI_INT, MPI_ANY_SOURCE, MPI_ANY_TAG,
			       MPI_COMM_WORLD, &stat);
		fail_unless(ret == MPI_SUCCESS);

		fail_unless(final == NUM_PROCS-1);
	} else {
		ret = MPI_Recv(&x, 1, MPI_INT, MPI_ANY_SOURCE, MPI_ANY_TAG,
			       MPI_COMM_WORLD, &stat);
		fail_unless(ret == MPI_SUCCESS);

		fail_if(stat.MPI_SOURCE != rank - 1);
		fail_if(stat.MPI_TAG != TAG);

		x++;
		ret = MPI_Send(&x, 1, MPI_INT, (rank + 1) % NUM_PROCS, TAG, MPI_COMM_WORLD);
		fail_unless(ret == MPI_SUCCESS);
	}

	ret = MPI_Finalize();
	fail_unless(ret == MPI_SUCCESS);

	printf("success\n");

	return 0;
}

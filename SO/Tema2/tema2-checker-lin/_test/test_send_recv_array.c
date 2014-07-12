#include <stdio.h>
#include <stdlib.h>

#include "mpi.h"
#include "check.h"

#define MAGIC	0xabba1974
#define TAG	0x1000
#define SIZE	128

int main(int argc, char *argv[])
{
	int v[256];
	int count;
	int ret;
	int rank, size;
	int i;
	MPI_Status stat;

	ret = MPI_Init(&argc, &argv);
	fail_unless(ret == MPI_SUCCESS);

	ret = MPI_Comm_size(MPI_COMM_WORLD, &size);
	fail_unless(ret == MPI_SUCCESS);
	fail_unless(size == 2);

	ret = MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	fail_unless(ret == MPI_SUCCESS);

	/* rank 0 sends */
	if (rank == 0) {
		for (i = 0; i < SIZE; i++)
			v[i] = i * 3 + 5;

		ret = MPI_Send(v, SIZE, MPI_INT, 1, TAG, MPI_COMM_WORLD);
		fail_unless(ret == MPI_SUCCESS);
	}
	/* rank 1 receives */
	if (rank == 1) {
		ret = MPI_Recv(v, SIZE, MPI_INT, MPI_ANY_SOURCE,
			       MPI_ANY_TAG, MPI_COMM_WORLD, &stat);
		fail_unless(ret == MPI_SUCCESS);

		fail_if(stat.MPI_SOURCE != 0);
		fail_if(stat.MPI_TAG != TAG);

		ret = MPI_Get_count(&stat, MPI_INT, &count);
		fail_unless(ret == MPI_SUCCESS);
		fail_if(count != SIZE);

		for (i = 0; i < SIZE; i++)
			fail_if(v[i] != i * 3 + 5);
	}

	ret = MPI_Finalize();
	fail_unless(ret == MPI_SUCCESS);

	printf("success\n");

	return 0;
}

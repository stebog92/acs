#include <stdio.h>
#include <stdlib.h>
#include "mpi.h"
#include "check.h"

int main(int argc, char *argv[])
{
	int ret;

	ret = MPI_Init(&argc, &argv);
	fail_unless(ret == MPI_SUCCESS);

	ret = MPI_Finalize();
	fail_unless(ret == MPI_SUCCESS);

	ret = MPI_Finalize();
	fail_unless(ret == MPI_ERR_OTHER);

	printf("success\n");
	fflush(stdout);

	return 0;
}

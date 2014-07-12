#include <stdio.h>
#include <stdlib.h>
#include "mpi.h"
#include "check.h"

static void check_other()
{
	int dummy;
	int ret;

	ret = MPI_Comm_size(MPI_COMM_WORLD, &dummy);
	fail_unless(ret == MPI_ERR_OTHER);

	ret = MPI_Comm_rank(MPI_COMM_WORLD, &dummy);
	fail_unless(ret == MPI_ERR_OTHER);

	ret = MPI_Send(&dummy, dummy, (MPI_Datatype)dummy, dummy,
		       dummy, (MPI_Comm)&dummy);
	fail_unless(ret == MPI_ERR_OTHER);

	ret = MPI_Recv(&dummy, dummy, (MPI_Datatype)dummy, dummy,
		       dummy, (MPI_Comm)&dummy, (MPI_Status *)&dummy);
	fail_unless(ret == MPI_ERR_OTHER);
}

int main(int argc, char *argv[])
{
	int ret;
	int flag;

	/*------- before INIT ------------*/

	/* MPI_Initialized before Init returns 0 */
	ret = MPI_Initialized(&flag);
	fail_unless(ret == MPI_SUCCESS);
	fail_unless(flag == 0);

	/* MPI_Finalized before Init returns 0 */
	ret = MPI_Finalized(&flag);
	fail_unless(ret == MPI_SUCCESS);
	fail_unless(flag == 0);

	/* Any other call before Init returns error */
	check_other();

	/*----------- INIT ---------------*/
	ret = MPI_Init(&argc, &argv);
	fail_unless(ret == MPI_SUCCESS);
	/*-------- after INIT ------------*/

	/* MPI_Initialized after Init returns 1 */
	ret = MPI_Initialized(&flag);
	fail_unless(ret == MPI_SUCCESS);
	fail_unless(flag == 1);

	/* MPI_Finalized after Init returns 0 */
	ret = MPI_Finalized(&flag);
	fail_unless(ret == MPI_SUCCESS);
	fail_unless(flag == 0);

	/*--------- FINALIZE -------------*/
	ret = MPI_Finalize();
	fail_unless(ret == MPI_SUCCESS);
	/*------ after FINALIZE ----------*/

	/* MPI_Initialized after Finalize returns 1 */
	ret = MPI_Initialized(&flag);
	fail_unless(ret == MPI_SUCCESS);
	fail_unless(flag == 1);

	/* MPI_Finalized after Finalize returns 1 */
	ret = MPI_Finalized(&flag);
	fail_unless(ret == MPI_SUCCESS);
	fail_unless(flag == 1);

	/* Any other call after Finalize returns error */
	check_other();

	printf("success\n");
	fflush(stdout);

	return 0;
}

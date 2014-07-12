#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mpi.h"
#include "check.h"

#define NRA 10
#define NRB 20
#define NRC 15

int a[NRA][NRB] = {
	{ 99, 155, 29, 45, 29, 203, 220, 223, 255, 74, 208, 199, 129, 132, 130, 252, 26, 135, 190, 147 },
	{ 154, 233, 48, 202, 216, 98, 232, 202, 128, 40, 255, 197, 159, 76, 148, 35, 50, 125, 129, 0 },
	{ 229, 52, 36, 156, 179, 190, 103, 198, 203, 129, 197, 29, 19, 80, 32, 75, 181, 95, 97, 205 },
	{ 204, 152, 96, 136, 144, 161, 66, 181, 120, 163, 19, 192, 218, 17, 81, 159, 43, 129, 188, 171 },
	{ 150, 40, 24, 82, 121, 113, 182, 121, 253, 173, 103, 129, 25, 99, 180, 181, 75, 237, 159, 34 },
	{ 238, 17, 124, 194, 229, 98, 192, 199, 255, 46, 176, 19, 209, 194, 107, 194, 153, 94, 64, 181 },
	{ 92, 82, 146, 6, 133, 135, 47, 246, 144, 40, 28, 8, 13, 237, 156, 105, 83, 178, 98, 218 },
	{ 2, 81, 205, 101, 198, 135, 247, 146, 211, 150, 71, 50, 16, 11, 207, 218, 91, 86, 152, 96 },
	{ 88, 41, 36, 157, 12, 100, 61, 100, 243, 191, 123, 17, 160, 59, 12, 43, 71, 215, 41, 1 },
	{ 129, 247, 197, 214, 40, 75, 223, 92, 62, 234, 95, 7, 98, 65, 60, 169, 146, 198, 99, 215 },
};

int b[NRB][NRC] = {
	{ 36, 35, 132, 47, 45, 60, 71, 175, 6, 106, 46, 165, 77, 162, 215 },
	{ 179, 34, 55, 196, 182, 204, 10, 83, 44, 195, 41, 50, 86, 206, 11 },
	{ 231, 31, 221, 58, 157, 131, 237, 141, 211, 96, 82, 165, 199, 236, 147 },
	{ 5, 23, 66, 165, 113, 175, 6, 239, 253, 42, 121, 202, 169, 204, 13 },
	{ 113, 122, 172, 145, 115, 28, 81, 3, 180, 82, 12, 159, 154, 154, 58 },
	{ 131, 133, 75, 51, 212, 114, 171, 136, 114, 242, 34, 112, 18, 250, 37 },
	{ 113, 146, 235, 214, 26, 45, 95, 209, 25, 114, 243, 110, 38, 43, 213 },
	{ 147, 248, 5, 236, 210, 147, 162, 126, 54, 118, 11, 139, 56, 223, 52 },
	{ 160, 82, 98, 5, 63, 7, 249, 208, 158, 121, 10, 181, 206, 40, 124 },
	{ 229, 230, 19, 46, 191, 88, 153, 107, 200, 251, 162, 72, 167, 59, 248 },
	{ 152, 194, 90, 209, 216, 29, 55, 195, 31, 160, 109, 32, 36, 230, 82 },
	{ 4, 152, 51, 203, 26, 198, 32, 7, 188, 169, 182, 17, 175, 254, 0 },
	{ 224, 162, 88, 196, 151, 9, 120, 136, 225, 196, 101, 29, 42, 180, 247 },
	{ 232, 58, 253, 16, 129, 158, 164, 150, 16, 99, 131, 221, 152, 38, 82 },
	{ 17, 163, 250, 215, 52, 172, 222, 116, 192, 90, 7, 141, 217, 250, 195 },
	{ 28, 137, 10, 201, 240, 146, 199, 33, 172, 192, 26, 223, 101, 200, 53 },
	{ 104, 169, 126, 79, 132, 137, 198, 59, 55, 146, 211, 188, 160, 84, 241 },
	{ 144, 92, 195, 175, 221, 206, 105, 125, 157, 216, 84, 164, 90, 247, 156 },
	{ 33, 250, 70, 255, 50, 154, 188, 132, 37, 178, 255, 50, 25, 32, 205 },
	{ 192, 39, 175, 237, 176, 204, 165, 72, 253, 72, 171, 119, 60, 114, 190 },
};

int c[NRA][NRC];

int result[NRA][NRC] = {
	{ 341326, 391475, 307659, 461920, 393911, 341389, 397869, 364531, 336059, 440427, 283519, 343486, 279522, 460852, 338114 },
	{ 305894, 353195, 313691, 448379, 340256, 303052, 291833, 358209, 310910, 382174, 262852, 308689, 283801, 469147, 302346 },
	{ 305116, 309462, 277785, 338410, 347576, 271395, 332447, 325137, 292229, 341445, 240522, 337579, 256861, 374789, 318836 },
	{ 309708, 336209, 264080, 411177, 356655, 323433, 340556, 303365, 371578, 398912, 255832, 311193, 273637, 440863, 337032 },
	{ 272011, 335221, 296110, 355129, 315218, 286197, 352957, 316279, 304955, 372604, 240094, 333057, 288060, 376673, 329351 },
	{ 374438, 350785, 378012, 417828, 397183, 304484, 415543, 397138, 379253, 384345, 272622, 427561, 329072, 450811, 395444 },
	{ 305263, 259449, 303725, 309522, 321839, 294067, 349410, 258910, 269417, 295185, 183948, 323992, 247287, 345451, 275717 },
	{ 292020, 330931, 305911, 375764, 327914, 283810, 381901, 301350, 341847, 347753, 236061, 335718, 296431, 383077, 320157 },
	{ 247865, 227230, 182321, 220271, 260609, 180097, 237841, 265441, 239811, 281921, 165776, 229639, 199353, 271731, 245102 },
	{ 357503, 301173, 316393, 402121, 393473, 345823, 341601, 344553, 355353, 390743, 299172, 346484, 284815, 414615, 370493 },
};

#define NUM_PROCS 6
#define TAG 0x1000

void do_master(int size)
{
	int i, j, k;
	int chunksize = NRA / (size - 1);
	int ret;
	int *v;
	MPI_Status stat;

	/* send chunksize lines of a */
	for (i = 0; i < size-1; i++) {
		ret = MPI_Send(a[chunksize*i], chunksize * NRB, MPI_INT, i + 1,
			       TAG, MPI_COMM_WORLD);
		fail_unless(ret == MPI_SUCCESS);
	}

	/* send entire b */
	for (i = 0; i < size-1; i++) {
		ret = MPI_Send(b, NRB * NRC, MPI_INT, i + 1,
			       TAG, MPI_COMM_WORLD);
		fail_unless(ret == MPI_SUCCESS);
	}

	v = malloc(chunksize * NRC * sizeof(int));

	/* receive chunksize lines of product matrix */
	for (i = 0; i < size-1; i++) {
		ret = MPI_Recv(v, chunksize * NRC, MPI_INT, MPI_ANY_SOURCE,
			       MPI_ANY_TAG, MPI_COMM_WORLD, &stat);
		fail_unless(ret == MPI_SUCCESS);

		k = stat.MPI_SOURCE - 1;
		memcpy(c[k * chunksize], v, chunksize * NRC * sizeof(int));
	}

	free(v);

	/* check result */
	for (i = 0; i < NRA; i++) {
		for (j = 0; j < NRC; j++)
			fail_if(c[i][j] != result[i][j]);
	}
}

int **alloc_contig_matrix(int x, int y)
{
	int **m;
	int *tmp;
	int i;

	tmp = malloc(x * y * sizeof(int));
	m = malloc(x * sizeof(int *));

	for (i = 0; i < x; i++)
		m[i] = &tmp[i * y];

	return m;
}

void do_worker(int rank, int size)
{
	int **v;
	int **result;
	int **b;
	int chunksize = NRA / (size - 1);
	int ret;
	int i, j, k;
	int sum;

	v = alloc_contig_matrix(chunksize, NRB);
	b = alloc_contig_matrix(NRB, NRC);
	result = alloc_contig_matrix(chunksize, NRC);

	/* receive chunksize lines of a */
	ret = MPI_Recv(v[0], chunksize * NRB, MPI_INT, MPI_ANY_SOURCE,
		       MPI_ANY_TAG, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
	fail_unless(ret == MPI_SUCCESS);

	/* receive entire b */
	ret = MPI_Recv(b[0], NRB * NRC, MPI_INT, MPI_ANY_SOURCE,
		       MPI_ANY_TAG, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
	fail_unless(ret == MPI_SUCCESS);

	/* multiply them */
	for (i = 0; i < chunksize; i++)
		for (j = 0; j < NRC; j++) {
			sum = 0;
			for (k = 0; k < NRB; k++)
				sum += v[i][k] * b[k][j];
			result[i][j] = sum;
		}

	/* send result back */
	ret = MPI_Send(result[0], chunksize * NRC, MPI_INT, 0, TAG,
		       MPI_COMM_WORLD);
	fail_unless(ret == MPI_SUCCESS);

	free(v[0]);
	free(b[0]);
	free(result[0]);
	free(v);
	free(b);
	free(result);
}

int main(int argc, char *argv[])
{
	int ret;
	int size;
	int rank;

	ret = MPI_Init(&argc, &argv);
	fail_unless(ret == MPI_SUCCESS);

	ret = MPI_Comm_size(MPI_COMM_WORLD, &size);
	fail_unless(ret == MPI_SUCCESS);
	fail_if(size != NUM_PROCS);

	ret = MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	fail_unless(ret == MPI_SUCCESS);

	if (rank == 0) {
		do_master(size);
	} else {
		do_worker(rank, size);
	}

	ret = MPI_Finalize();
	fail_unless(ret == MPI_SUCCESS);

	printf("success\n");
	fflush(stdout);

	return 0;
}

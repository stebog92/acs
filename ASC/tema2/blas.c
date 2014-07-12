#include <stdio.h>
#include <time.h>
#include "cblas.h"
#include <stdlib.h>

int N;
double ALPHA, *A, *X, BETA, *Y;

int main(int argc, char **argv)
{
    int values, i, j;
    double value;
    clock_t start, end;
    FILE *f = fopen(argv[1], "w");
    scanf("%*[^1-9] %d %*d %d", &N, &values);
    A = calloc (N*N, sizeof(double));
    X = calloc (N, sizeof(double));
    Y = calloc (N, sizeof(double));
    while (values--) {
        scanf("%d %d %lf", &i, &j, &value);
        A[((i - 1)*N) + j - 1] = value;
    }
    ALPHA = 2.433;
    BETA = 3.777;
    for (i = 0; i < N; i++) {
        X[i] = (((double)i)*3.0)/7.0;
	    Y[i] = (((double)i)*5.0)/11.0;
    }
    start = clock();
    cblas_dsymv(CblasRowMajor, CblasUpper, N, ALPHA, A, N, X, 1, BETA, Y, 1);
    end = clock();
    printf ("%d %.3f\n", N, ((double)end - (double)start)/CLOCKS_PER_SEC);
    for (i = 0; i < N; i++) {
        fprintf (f, "%lf\n", Y[i]);
    }
    free(A);
    free(X);
    free(Y);
    return 0;
}

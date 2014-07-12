#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int N;
double ALPHA, *A, *X, BETA, *Y;

void dsymv(double ALPHA, double* A, double *X, double BETA, double *Y)
{
    int i, j;
    double temp1, temp2;
    if (BETA != 1) {
        if (BETA == 0) {
            for (i = 0; i < N; i++) {
                Y[i] = 0;
            }
        } else {
            for (i = 0; i < N; i++) {
                Y[i] = BETA * Y[i];
            }
        }
    }
    if (ALPHA == 0) {
        return;
    }
    for (j = 0; j < N; j++) {
        temp1 = ALPHA * X[j];
        temp2 = 0;
        for (i = 0; i < j - 1; i++) {
            Y[i] += temp1 * A[i * N +j];
            temp2 += A[i * N + j] * X[i];
        }
        Y[j] += temp1 * A[j * N + j] + ALPHA * temp2;
    }
}

int main(int argc, char **argv)
{
    int values, i, j;
    double value;
    clock_t start, end;
    scanf("%*[^1-9] %d %*d %d", &N, &values);
    FILE *f = fopen(argv[1], "w");
    X = calloc (N, sizeof(double));
    Y = calloc (N, sizeof(double));
    A = calloc (N*N, sizeof(double));
    while (values--) {
        scanf("%d %d %lf", &i, &j, &value);
        A[(i - 1) * N + j - 1] = value;
    }
    ALPHA = 2.433;
    BETA = 3.777;
    for (i = 0; i < N; i++) {
        X[i] = ((double)i*3.0)/7.0;
	    Y[i] = ((double)i*5.0)/11.0;
    }
    start = clock();
    dsymv(ALPHA, A, X, BETA, Y);
    end = clock();
    printf ("%d %.3f\n", N, ((double)end - (double)start)/CLOCKS_PER_SEC);
    for (i = 0; i < N; i++) {
        fprintf (f, "%lf\n", Y[i]);
    }
    free(A);
    free(Y);
    free(X);
    fclose(f);
return 0;
}

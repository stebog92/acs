#include <stdio.h>
#include <math.h>
#include "prime.h"

int isPrime(int n) {
    int i = 2;
    while ((double)i <= sqrt(n)) {
        if (n % i == 0) {
            return 0;
        }
        i++;
    }
    return 1;
}

int * get_prime_1_svc(Data *data, struct svc_req* dummy) {
    int i = 1;
    static int prime;
    if (isPrime(data->n)) {
        prime = data->n;
        return &prime;
    }
    while(1) {
        if (data->n - i > 1 && isPrime(data->n - i)) {
            prime = data->n - i;
            break;
        }
        if (isPrime(data->n + i)) {
            prime = data->n + i;
            break;
        }
        i++;
    }
    return &prime;
}

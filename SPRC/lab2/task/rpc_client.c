#include <stdio.h>
#include <rpc/rpc.h> 
#include <stdlib.h>
#include "prime.h"

int main(int argc, char** argv) {
    CLIENT *handle;
    int *prime;
    struct Data x;
    strcpy(x.name, argv[2]);
    x.n = atoi(argv[3]);
    handle = clnt_create(
            argv[1],
            PRIME_PROG,
            PRIME_VERS, 
            "tcp");

    if (handle == NULL) {
        perror("");
        return 1;
    }


    prime = get_prime_1(&x, handle);
    printf("The prime number is %d\n", *prime);
    return 0;
}

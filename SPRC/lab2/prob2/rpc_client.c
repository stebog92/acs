#include <stdio.h>
#include <rpc/rpc.h> 
#include <stdlib.h>
#include "arb.h"

int main(int argc, char** argv) {
    CLIENT *handle;
    int n;
    handle = clnt_create(
            argv[1],
            ARB_PROG,
            ARB_VERS, 
            "tcp");

    if (handle == NULL) {
        perror("");
        return 1;
    }
    if (strcmp(argv[2], "insert") == 0) {
        n = atoi(argv[3]);
        insert_1(&n, handle);
    } else {
        display_1(NULL, handle);
    }
    return 0;
}

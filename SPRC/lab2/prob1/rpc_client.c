#include <stdio.h>
#include <rpc/rpc.h> 
#include <stdlib.h>
#include "who.h"

int main(int argc, char** argv) {
    Data *data;
    CLIENT *handle;
    handle = clnt_create(
            argv[1],
            WHO_PROG,
            WHO_VERS, 
            "tcp");

    if (handle == NULL) {
        perror("");
        return 1;
    }


    data = get_users_1(NULL, handle);
    printf("The active users are %s\n", data->users);
    return 0;
}

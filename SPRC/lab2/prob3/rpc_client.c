#include <stdio.h>
#include <rpc/rpc.h> 
#include <stdlib.h>
#include "shell.h"

int main(int argc, char** argv) {
    Data data, *response;
    CLIENT *handle;
    strcpy(data.cmd, argv[2]);
    data.max = atoi(argv[3]);
    handle = clnt_create(
            argv[1],
            SHELL_PROG,
            SHELL_VERS, 
            "tcp");

    if (handle == NULL) {
        perror("");
        return 1;
    }


    response = get_output_1(&data, handle);
    printf("%s\n", response->cmd);
    return 0;
}

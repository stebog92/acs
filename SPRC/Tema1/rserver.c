#include <stdio.h>
#include <rpc/rpc.h>
#include <unistd.h>
#include <sys/wait.h>
#include "rshell.h"


/* get server path */
response *get_remote_pwd_1_svc(void *p, struct svc_req *cl) {
    FILE *fp;
    static response res;
    memset(res.data, 0, 100);
    fp = popen ("pwd", "r");
    fread(res.data, 100, 1, fp);
    res.data[strlen(res.data) - 1] = '\0';
    pclose(fp);
    return &res;
}

/* execute simple command */
void execute_command(char cmd[1024], char *result, int *return_value) {
    int i, j, length;
    char temp[100];
    char *args[10];
    int pid;
    
    if (strncmp(cmd, "cd", 2) == 0) {
        chdir(cmd + 3);
    } else {

        FILE *output = popen(cmd, "r");
        fread(result, 1024, 1, output);
        *return_value = pclose(output);
    }
}


/* execute simple command for client */
response *execute_simple_cmd_1_svc(rpc_cmd_data *cmd, struct svc_req *cl) {
    static response res;
    memset(res.data, 0, 100);
    
    execute_command(cmd->data, res.data, &res.return_value);
    fflush(stdout);
    return &res;
}

/* free linked list */
void free_prev_res(list_value *res) {
    linked_list_data *list = res->list, *tmp;
    while(list != NULL) {
        tmp = list->next_data;
        free(list);
        list = tmp;
    }
}


list_value *execute_multiple_cmd_1_svc(linked_list_data *cmd, struct svc_req *cl) {
    static list_value res;
    linked_list_data *results, *cpy;

    /* free prev response */
    free_prev_res(&res);

    results = malloc(sizeof(linked_list_data));
    cpy = results;
    while(cmd != NULL) {
        memset(cpy->data, 0, 1024);
        execute_command(cmd->data, cpy->data, &cpy->return_value);
        cmd = cmd->next_data;
        if (cmd != NULL) {
            cpy->next_data = malloc(sizeof(linked_list_data));
            cpy = cpy->next_data;
        } else {
            cpy->next_data = NULL;
        }
    }
    res.list = results;
    return &res;
}

#include <stdio.h> 
#include <rpc/rpc.h>
#include <string.h>
#include "rshell.h"

#define BUFSIZE 100
#define EXIT 0
#define SIMPLE_COMMAND 1
#define MULTIPLE_COMMAND 2

void prompt(response *r) {
    printf("%s > ", r->data);
}

/* get user command*/
char* get_user_cmd() {
    size_t n;
    char *buffer = NULL;
    getline(&buffer, &n, stdin);
    return buffer;
}


/* returns command type*/
int command_type (char *cmd) {
    if (strncmp(cmd, "exit", 4) == 0) {
        return EXIT;
    } else if(strchr(cmd, ';') != NULL) {
        return MULTIPLE_COMMAND;
    } else {
        return SIMPLE_COMMAND;
    }
}


int main(int argc, char** argv) {

    CLIENT *handle;
    response *res;
    rpc_cmd_data cmd;
    char *buffer, *command;
    linked_list_data *multiple_command;
    list_value *results;

	
	handle=clnt_create(
		argv[1],
		SHELL_PROG,
		SHELL_VERS,
		"tcp");

    while(1) {
        /* get pwd */
        res = get_remote_pwd_1(NULL, handle);
        prompt(res);

        /* get user command */
        buffer = get_user_cmd();

        switch(command_type(buffer)) {
            case EXIT:
                clnt_destroy(handle);
                return 0;
            case SIMPLE_COMMAND:
                memset(cmd.data, 0, 100);
                strncpy(cmd.data, buffer, strlen(buffer) - 1);
                
                /* execute simple command*/
                res = execute_simple_cmd_1(&cmd, handle);

                /* check if command encountered error */
                if (res->return_value == 0) {
                    printf("%s\n", res->data);
                } else {
                    printf("%s: error\n", cmd.data);
                }
                break;
            case MULTIPLE_COMMAND:

                /* build list for multiple commands*/
                multiple_command = malloc(sizeof(linked_list_data));
                strcpy(multiple_command->data, strtok(buffer, ";"));
                linked_list_data *tmp = multiple_command;
                while(1) {
                    command = strtok(NULL, ";");
                    if (command == NULL) {
                        tmp->next_data = NULL;
                        tmp->data[strlen(tmp->data) - 1] = '\0';
                        break;
                    }
                    tmp->next_data = malloc(sizeof(linked_list_data));
                    strcpy(tmp->next_data->data, command);
                    tmp = tmp->next_data;
                }

                /* execute multiple commands*/
                results = execute_multiple_cmd_1(multiple_command, handle);
                linked_list_data *list = results->list;

                /* print results */
                while(list != NULL) {
                    if (list->return_value == 0) {
                        printf("%s", list->data);
                    } else {
                        printf("%s : error\n", multiple_command->data);
                    }
                    tmp = multiple_command->next_data;
                    free(multiple_command);
                    multiple_command = tmp;
                    list = list->next_data;
                }
                break;
        }
        free(buffer);
    }
    return 0;
}

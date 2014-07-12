/**
 * Operating Systems 2013 - Assignment 1
 * Ciocan Mihai
 * 334CA
 *
 */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>

#include "utils.h"

#define PROMPT_STRING   "> "
#define MAX_CMD_LEN     1024
#define READ 0
#define WRITE 1
/* Prints error */
void parse_error(const char *str, const int where)
{
	fprintf(stderr, "Parse error near %d: %s\n", where, str);
}

int main ()
{
    char line[MAX_CMD_LEN];
    command_t *root;
    while (true) {
        printf(PROMPT_STRING); fflush(stdout);
        if (fgets(line, sizeof(line), stdin) == NULL)
        {
            fprintf (stderr, "End of file!\n");
            return EXIT_SUCCESS;
        }

        root = NULL;
        if (parse_line(line, &root)) {
            if (root == NULL) {
                printf("Command is empty!\n");
            }
            else {
                /* root points to a valid command tree that we can use */
                process_command(root);
            }
        }
        free_parse_memory();
    }
	return EXIT_SUCCESS;
}

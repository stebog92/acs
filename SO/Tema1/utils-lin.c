/**
 * Operating Sytems 2013 - Assignment 1
 * Ciocan Mihai
 * 334CA
 *
 */

#include <assert.h>

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>

#include <fcntl.h>
#include <unistd.h>
#include <errno.h>

#include "utils.h"


#define READ		0
#define WRITE		1



/**
 * Concatenate parts of the word to obtain the command
 */
static char *get_word(word_t *s)
{
	int string_length = 0;
	int substring_length = 0;

	char *string = NULL;
	char *substring = NULL;

	while (s != NULL) {
		substring = strdup(s->string);

		if (substring == NULL) {
			return NULL;
		}

		if (s->expand == true) {
			char *aux = substring;
			substring = getenv(substring);

			/* prevents strlen from failing */
			if (substring == NULL) {
				substring = calloc(1, sizeof(char));
				if (substring == NULL) {
					free(aux);
					return NULL;
				}
			}

			free(aux);
		}

		substring_length = strlen(substring);

		string = realloc(string, string_length + substring_length + 1);
		if (string == NULL) {
			if (substring != NULL)
				free(substring);
			return NULL;
		}

		memset(string + string_length, 0, substring_length + 1);

		strcat(string, substring);
		string_length += substring_length;

		if (s->expand == false) {
			free(substring);
		}

		s = s->next_part;
	}

	return string;
}

/**
 * Concatenate command arguments in a NULL terminated list in order to pass
 * them directly to execv.
 */
static char **get_argv(simple_command_t *command, int *size)
{
	char **argv;
	word_t *param;

	int argc = 0;
	argv = calloc(argc + 1, sizeof(char *));
	assert(argv != NULL);

	argv[argc] = get_word(command->verb);
	assert(argv[argc] != NULL);

	argc++;

	param = command->params;
	while (param != NULL) {
		argv = realloc(argv, (argc + 1) * sizeof(char *));
		assert(argv != NULL);

		argv[argc] = get_word(param);
		assert(argv[argc] != NULL);

		param = param->next_word;
		argc++;
	}

	argv = realloc(argv, (argc + 1) * sizeof(char *));
	assert(argv != NULL);

	argv[argc] = NULL;
	*size = argc;

	return argv;
}

/* Waits a process and returns its exit status */
static int wait_process(simple_command_t *cmd, int pid)
{
    int status;
    waitpid (pid, &status, 0);
    if (WIFEXITED(status)) {
        return  WEXITSTATUS(status);
    }
    return 1;
}

/* Checks if command sets an environment variable */
static bool check_set_env(simple_command_t *scmd)
{
    if (!scmd->verb->next_part) {
        return false;
    }
    return (strcmp(scmd->verb->next_part->string, "=") == 0);
}

/* Checks if command is 'exit' or 'quit' */
static bool check_exit(simple_command_t *cmd)
{
    const char *command = cmd->verb->string;
    return (strcmp(command, "exit") == 0 || strcmp(command, "quit") == 0);
}

/* Checks if command is 'cd' */
static bool check_change_dir(simple_command_t *cmd)
{
    return (strcmp(cmd->verb->string, "cd") == 0);
}


/* Change or add an environment variable */
static bool set_env(word_t *var)
{
    if (!setenv(var->string, var->next_part->next_part->string, 1)) {
        return false;
    }
    return true;
}

/* Frees the memory space pointed to by parameters */
static void delete_parameter_list(char ***parameters)
{
    int i = 0;
    while ((*parameters)[i]) {
        free((*parameters)[i]);
        i++;
    }
    free((*parameters)[i]);
    free(*parameters);
}

/* Checks if input must be redirected and
 * modify the file descriptors accordingly */
static bool handle_input(simple_command_t *cmd)
{
    char *file_name;
    if (cmd->in != NULL) {
        file_name = get_word(cmd->in);
        cmd->d_in = open(file_name, O_RDONLY);
        cmd->std_in = STDIN_FILENO;
        free(file_name);
        if (dup2(cmd->d_in, STDIN_FILENO) == -1) {
            return false;
        }
        if (cmd->d_in == -1) {
            return false;
        }
    }
    return true;
}

/* Checks if output must be redirected and
 * modify the file descriptors accordingly*/
static bool handle_output(simple_command_t *cmd)
{
    char *file_name;
    if (cmd->out != NULL) {
        file_name = get_word(cmd->out);
        if (cmd->io_flags == 1) {
            /* check if operator '>>' is used and open file in append mode */
            cmd->d_out = open(file_name, O_WRONLY | O_CREAT | O_APPEND, 0644);
        } 
        else {
            /* open file and truncate it */
            cmd->d_out = open(file_name, O_WRONLY | O_CREAT | O_TRUNC, 0644);
        }

        cmd->std_out = dup(STDOUT_FILENO);
        free(file_name);
        if (dup2(cmd->d_out, STDOUT_FILENO) == -1) {
            return false;
        }
        if (cmd->d_out == -1) {
            return false;
        }
    }
    return true;
}

/* Checks if stderr must be redirected and
 * modify the file descriptors accordingly */
static bool handle_error(simple_command_t *cmd)
{
    char *file_name, *file_out;
    if (cmd->err != NULL) {
        file_name = get_word(cmd->err);
        file_out = get_word(cmd->out);
        if (cmd->out && strcmp(file_name, file_out) == 0) {
            /* check if operator '&>' is used*/
            cmd->d_err = cmd->d_out;
        }
        else {
            if (cmd->io_flags == 2) {
                /* check if operator '2>>' is used and open file in append mode*/
                cmd->d_err = open(file_name, O_WRONLY | O_CREAT | O_APPEND, 0644);
            }
            else {
                /* open file and truncate it */
                cmd->d_err = open(file_name, O_WRONLY | O_CREAT | O_TRUNC, 0644);
            }
        }
 
        cmd->std_err = dup(STDERR_FILENO);
        free(file_name);
        free(file_out);

        if(dup2(cmd->d_err, STDERR_FILENO) == -1) {
            return false;
        }
        if (cmd->std_err == -1) {
            return false;
        }
    }
    return true;
}

/* Checks if command is in a pipe construction
 * and handles the pipe
 */
static bool handle_pipe(simple_command_t *cmd)
{
    int *fd;
    command_t *root = cmd->up;
    if (root->up && root->up->op == OP_PIPE && root->up->cmd1 == root) {
        /* cmd is the command that redirects stdout to pipe write end
         *           and closes the read end */
        fd = (int*)root->aux;
        if (close(fd[READ]) == -1) {
            return false;
        }
        cmd->d_out = fd[WRITE];
        cmd->std_out = STDOUT_FILENO;
        if (dup2(fd[WRITE], STDOUT_FILENO) == -1) {
            return false;
        }
    }
    if (root->up && root->up->op == OP_PIPE && root->up->cmd2 == root) {
        /* cmd is the command that redirects the pipe read end to 
         * stdin and closes the write end*/
        fd = (int*)root->aux;
        if (close(fd[WRITE]) == -1) {
            return false;
        }
        cmd->d_in = fd[READ];
        cmd->std_in = STDIN_FILENO;
        if (dup2(fd[READ], STDIN_FILENO) == -1) {
            return false;
        }
        if (root->up->aux != NULL) {
            /* the parent of cmd is also a part of 
             * a piped construction - cmd must redirect stdout
             * to the parent's pipe write end*/
            int *fd2 = (int*) root->up->aux;
            if(close(fd[READ]) == -1) {
                return false;
            }
            cmd->d_out = fd2[WRITE];
            cmd->std_out = STDOUT_FILENO;
            if (dup2(fd2[WRITE], STDOUT_FILENO) == -1) {
                return false;
            }
        }
    }
    return true;
}

/** Change current directory 
 *  returns 0 if successful and 1 if not 
 */
static int change_dir(simple_command_t *cmd)
{
    if (chdir(cmd->params->string) == -1) {
        /* chdir failed - print error to output */
        if (errno == ENOENT) {
            /*check and redirect the stdout if necessary */
            handle_output(cmd);
            fprintf(stdout, 
            "bash: cd: %s: No such file or directory\n",
            cmd->params->string);
            if(cmd->d_out != -1) {
                dup2(cmd->std_out, STDOUT_FILENO);
            }
            return 1;
        }
    }
    return 0;
}

static int run_simple_command(simple_command_t *cmd)
{

    int pid, *fd, size;
    char**parameters; 

    if (check_set_env(cmd)) {
        return set_env(cmd->verb); 
    }
    if (check_exit(cmd)) {
        exit(EXIT_SUCCESS);
    }
    if (check_change_dir(cmd)) {
        return change_dir(cmd);
    }
    
    pid = fork();
    if (pid == 0) {

        if (!handle_input(cmd) ||
            !handle_output(cmd) ||
            !handle_error(cmd) ||
            !handle_pipe(cmd)) {
            exit(EXIT_FAILURE);
        }

        parameters = get_argv(cmd, &size);

        if (execvp(cmd->verb->string, parameters) == -1) {
            fprintf(stderr, "Execution failed for '%s'\n", cmd->verb->string);
            delete_parameter_list(&parameters);
            exit(EXIT_FAILURE);
        }

    } else if (pid > 0) {
        command_t *parent = cmd->up->up;
        if (parent && parent->op == OP_PIPE && parent->cmd2 == cmd->up) {
            fd = cmd->up->aux;
            if (close(fd[WRITE]) == -1) {
                return 1;
            }
        }
        return wait_process(cmd, pid);
    } else if (pid == -1) {
        return 1;
    }
    return 0;
}

/* Execute commands simultaneously */
static int run_parallel(command_t *cmd1, command_t *cmd2)
{
    int pid, status;
    pid = fork();
    switch(pid) {
        case -1:
            /* fork error */
            return 1;
        case 0:
            process_command(cmd1);
            exit(EXIT_SUCCESS);
            break;
        default:
            process_command(cmd2);
            waitpid(pid, &status, 0);
    }
    return 0;
}

/* Execute piped commands */
static int run_piped_commands(command_t *cmd1, command_t *cmd2)
{
    int fd_pipe[2];
    if (pipe(fd_pipe) == -1) {
        return 1;
    }
    cmd1->aux = &fd_pipe;
    cmd2->aux = &fd_pipe;
    return run_parallel(cmd1, cmd2);
}

/**
 * Parse and execute a command.
 */
int process_command (command_t *root)
{
    switch(root->op) {
        case OP_NONE:
            /* Execute a simple command*/
            return run_simple_command(root->scmd);
        case OP_SEQUENTIAL:
            /* Execute the commands one after the other */
            process_command(root->cmd1);
            process_command(root->cmd2);
            return 0;
        case OP_CONDITIONAL_ZERO:
            /* Execute the second command only if the
                first one returns zero */
            if (!process_command(root->cmd1)) {
                return process_command(root->cmd2);
            }
            return 1;
        case OP_CONDITIONAL_NZERO:
            /* Execute the second command only if the
                first one returns non zero*/
            if (process_command(root->cmd1)) {
                return process_command(root->cmd2);
            }
            return 0;
        case OP_PARALLEL:
            /* Execute the commands simultaneously */
            return run_parallel(root->cmd1, root->cmd2);
        case OP_PIPE:
           /* Execute piped commands */
           return run_piped_commands(root->cmd1, root->cmd2);
        case OP_DUMMY:
            break;
        }
    return 1;
}


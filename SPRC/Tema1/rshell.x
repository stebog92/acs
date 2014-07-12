struct response {
    char data[1024];
    int return_value;
};

struct rpc_cmd_data {
    char data[1024];
};

struct linked_list_data {
    struct linked_list_data *next_data;
    char data[1024];
    int return_value;
};

struct list_value {
    struct linked_list_data *list;
};
program SHELL_PROG {
	version SHELL_VERS {
		response GET_REMOTE_PWD(void) = 1;
        response EXECUTE_SIMPLE_CMD(rpc_cmd_data) = 2;
        list_value EXECUTE_MULTIPLE_CMD(linked_list_data) = 3;
	} = 1;
} = 1234;

struct Data {
    string cmd;
    int max;
};
program SHELL_PROG {
    version SHELL_VERS {
        Data GET_OUTPUT (Data) = 1;
    } = 1;
} = 13;

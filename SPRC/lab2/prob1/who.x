struct Data {
    char users[100];
};
program WHO_PROG {
    version WHO_VERS {
        Data GET_USERS (void) = 1;
    } = 1;
} = 13;

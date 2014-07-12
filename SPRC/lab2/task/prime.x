struct Data {
    char name[32];
    int n;
};

program PRIME_PROG {
    version PRIME_VERS {
        int GET_PRIME(Data) = 1;
    } = 1;
} = 12;

/*struct Arbr {
    int val;
    struct Arbr *left;
    struct Arbr *right;
};*/
program ARB_PROG {
    version ARB_VERS {
        int INSERT (int) = 1;
        int DISPLAY (void) = 2;
    } = 1;
} = 15;

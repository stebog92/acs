#include <stdio.h>
#include "who.h"

Data * get_users_1_svc(void *p, struct svc_req* dummy) {
    static Data data;
    char buffer[100];
    FILE *input;
    input = popen("w | cut -d ' ' -f1", "r");
    while (fscanf(input, "%s", buffer) != EOF) {
        strcat (data.users, buffer);
        strcat (data.users, "\n");
    }
    fclose(input);
    return &data;
}

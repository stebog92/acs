#include <stdio.h>
#include "shell.h"

Data * get_output_1_svc(Data *data, struct svc_req* dummy) {
    static Data response;
    FILE *input;
    memset(response.cmd, 0, 100);
    input = popen(data->cmd, "r");
    fread(response.cmd, data->max, 1, input);
    fclose(input);
    return &response;
}

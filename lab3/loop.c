#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void heavy_task(int task_num) {
    printf("\tSequential task #%d started\n", task_num);

    int result = 0;

    for (int i = 0; i < 1e8; i++) {
        sqrt(i);
    }

    printf("\tSequential task #%d started\n", task_num);
}

void loop(int sequence_len) {
    for (int i = 0; i < sequence_len; i++) {
        printf("MAIN: starting sequential task #%d\n", i);
        heavy_task(i);
    }
}

int main(int argc, char** argv) {

    int tasks_num = atoi(argv[1]);

    loop(tasks_num);

    return 0;
}
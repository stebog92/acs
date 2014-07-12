#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include "alarm.h"

typedef struct package {
        int bit_s;
        char x;
} pack;

int main()  {

int fd1, fd2;

char buf[1];
char ack[2];
ack[0] = 1;


fd2 = open ("mypipe", O_RDONLY);
fd1 = open ("my_pipe", O_WRONLY);

pack x;
int l = 0;

while (1) {
	read (fd2, &x, sizeof(x));
	if (x.bit_s == l)
	{
		printf ("%c", x.x);
		ack[1] = 1 - l;
		l = 1 - l;
		write (fd1, ack, 2);
	}
}
return 0;
}

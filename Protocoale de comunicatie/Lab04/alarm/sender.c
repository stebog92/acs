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
char *phrase = "Stuff this in your pipe and smoke it";

int main () {
int fd1, fd2;
char buf[2];

fd1 = open ("mypipe", O_WRONLY);
fd2 = open ("my_pipe", O_RDONLY);

int l = 0, i;
init_alarm();
for (i = 0; i < 3; i++)
{
	const int *was_timeout;
	pack _package;
	_package.x = phrase[i];
	_package.bit_s = l;
	write (fd1, &_package, sizeof(_package));
	was_timeout = set_alarm(10);
	read (fd2, buf, 2);
	cancel_alarm();
	if (*was_timeout == 0)
	{
		if (buf[0] == 1)
		{
			if (buf[1] == 1 - l)
			{
				printf ("Package %d sended successfully", i);
				l = 1 - l;
			}
		}
	}
	else
	{
		i--;
		printf ("Resending package %d", i);
	}
}
close (fd1);
cleanup_alarm();
return 0;
}
	

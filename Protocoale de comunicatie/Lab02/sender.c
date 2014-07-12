#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>

char *phrase = "0Stuff this in your pipe and smoke it";

int main () 
{
int fd1, fd2;
char buf[2];
fd1 = open ("mypipe", O_WRONLY);
fd2 = open ("secpipe", O_RDONLY);
while (1) {
	write (fd1, phrase, strlen (phrase) + 1);
	read (fd2, buf, 2);
	if (buf[0] == '0')
	{
		printf ("Success\n");
		break;
	}
}

close (fd1);
return 0;
}

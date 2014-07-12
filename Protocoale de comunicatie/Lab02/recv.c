#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
int main () {

int fd1, fd2;

char buf[100];
fd1 = open ("mypipe", O_RDONLY);
fd2 = open ("secpipe", O_WRONLY);
while (1) 
{
	read (fd1, buf, 100);
	if (strlen (buf) > 0)
	{
		write (fd2, "0", 2);
		printf ("%s\n", buf);
		break;
	}
}
return 0;
}

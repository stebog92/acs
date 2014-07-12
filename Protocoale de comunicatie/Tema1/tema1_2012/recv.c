#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include "lib.h"
#include "crc.h"

#define HOST "127.0.0.1"
#define PORT 10001
int window_size = 0;
int fd;
int fs;
word *tabel;
//astepta header si trimite ack marimea ferestrei
// min intre fereastra recv (daca e data ca parametru)
// si a senderului

int receive_header (int argc, char **argv)
{
	msg *r,t;
	char filename[1400], filesize[1400], windowsize[1400];
	int sender_wins;
	
	if (argc == 2)
	{
		window_size = atoi(argv[1] + 7);
	}
	
	r = receive_message();
	while (!check_crc (r, tabel))
	{
		r = receive_message();
	}
	if (!r)
	{
		perror("Receive message");
		return -1;
	}
	//find the filename;
	int name = 1, size = 0, window = 0, crt = 0,i;

	if (r->type!=-1)
	{
		printf("Expecting filename and size message\n");
		return -1;
	}

	for (i = 0;i < r->len; i++)
	{
		if (crt >= 1400)
		{
			printf("Malformed message received! Bailing out");
			return -1;
		}
		if (name)
		{
			if (r->payload[i]=='\n') 
			{
				name = 0;
				size = 1;
				filename[crt] = 0;
				crt = 0;
			}
			else
			filename[crt++] = r->payload[i];
    		}
		else
		if (size)
		{
			if (r->payload[i]=='\n')
			{
				size = 0;
				window = 1;
				filesize[crt] = 0;
				crt = 0;
			}
			else 
			filesize[crt++] = r->payload[i];
		}
		else
		if (window)
		{
			if (r->payload[i]=='\n')
			{
				window = 0;
				windowsize[crt] = 0;
				crt = 0;
			}
			else 
			windowsize[crt++] = r->payload[i];
		}
	}
	fs = atoi(filesize);
	char fn[2000];

	sprintf(fn,"recv_%s", filename);
	printf("Receiving file %s of size %d\n",fn,fs);	
	fd = open(fn,O_WRONLY|O_CREAT,0644);
	if (fd<0) 
	{
		perror("Failed to open file\n");
	}
	sender_wins = atoi (windowsize);
	if (window_size != 0)
	{
		if (window_size < sender_wins)
			sender_wins = window_size;
		else
			window_size = sender_wins;
	}
	else
	{
		window_size = sender_wins;
	}
	sprintf (t.payload, "%d", sender_wins);
	t.len = strlen (t.payload) + 1;
	send_message (&t);
	return 0;	
}

// asteptare continutul fisierului
void receive_content ()
{
	int packet;
	int begin = 0, end = window_size ;
	_buffer buffer = make_buffer(window_size);
	msg *r, m;
	char *tmp;
	while (fs)
	{
		r = receive_message();
		if (r->type > 0 && check_crc(r, tabel)) //daca nu e corupt e prelucrat
		{
		sscanf(r->payload, "%d", &packet);
		tmp = r->payload + r->type;
			
		if (packet >= begin && packet < end) //daca e in afara ferestrei se pierde
		{
			//trimitere ack == numarul de ordine al packetului
			m.type = 0;
			sprintf (m.payload, "%d", packet);
			m.len = strlen (m.payload) + 1;
			send_message (&m);
			
			if (packet == begin)
			{
				//daca e primit in ordine se scrie in fisier si 
				// se muta fereastra la urmatorul packet neprimit 
				begin++;
				write (fd, tmp, strlen(tmp));
				while (element_at(buffer, begin) != NULL)
				{
					write (fd, element_at(buffer, begin)->char_pos,element_at(buffer, begin)->length);
					begin ++;
					remove_at(&buffer, begin - 1);
				}
				end = begin + window_size;
			}
			else
			{
				//daca nu e primit in ordine e stocat in buffer
				put_at(&buffer, packet, *r);
			}
				fs -= strlen (tmp);
		}
		free(r);
		}
	}
	close (fd);
}
int main(int argc,char** argv)
{
	init(HOST,PORT);
	tabel = tabelcrc(CRCCCITT);
	receive_header(argc, argv);
	receive_content ();
	return 0;
}

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include "lib.h"
#include "crc.h"

#define HOST "127.0.0.1"
#define PORT 10000

int window_size, delay;
int fd;
int file_size;
word *tabel;

/* trimite header format din numele si marimea fisierului
	si marimea ferestrei calculate"
*/
void send_header (char* filename)
{
	msg t, *r;
	struct stat buf;
	if (stat (filename, &buf) < 0)
	{
		perror ("Stat failed");
		return;
	}
	
	fd = open(filename, O_RDONLY);

	if (fd < 0) 
	{
		perror("Couldn't open file");
		return;
	}

	t.type = -1;
	file_size = (int) buf.st_size;
	sprintf(t.payload,"%s\n%d\n%d\n",filename,(int)buf.st_size, window_size);
	t.len = strlen(t.payload)+1;
	compute_crc(&t, tabel);	
	send_message(&t);
	while ((r = receive_message_timeout(delay + 5)) == NULL)
	{
		send_message(&t);
	}
	window_size = atoi (r->payload);
}

/* trimite continutul fisierului */
void send_content () 
{
	msg s,*r;
	int begin = 0, end = window_size, i = 0, eof = 0;
	int p, j;
	_buffer packet_buffer = make_buffer(window_size), ack_buffer = make_buffer(window_size);
	for (j = 0; j < packet_buffer.max; j++)
	{
		packet_buffer.elements[j] = NULL;
		ack_buffer.elements[j] = NULL;
	}
	
	while (begin != end)
	{
		while (i < end)
		{
			memset (&s, 0, sizeof(s));

			s.type = sprintf (s.payload, "%d\n", i);
			s.len = read (fd, s.payload+s.type, 1300);
			
			if (s.len < 1300)
			{
				eof = 1;
				end = i + 1;
			}
			
			s.len = 1400;
			compute_crc (&s, tabel);// calculare crc pentru fiecare pachet si 
							//stocarea lui in payload
			send_message(&s); // trimitere mesaj
			put_at (&packet_buffer, i, s); // retinere in buffer
			i++;

			/* daca intre timp este transmis vreun ack se prelucreaza */
			r = receive_message_timeout (2);
			if (r != NULL)
			{
				sscanf (r->payload, "%d", &p);
				//printf("Received ack %d\n", p);

				if (p == begin)
				{
					begin++;
					remove_at(&packet_buffer, begin - 1);
					while (size (&ack_buffer) != 0 && element_at(ack_buffer, begin) != NULL)
					{
						begin++;
						remove_at (&ack_buffer, begin - 1);
						remove_at (&packet_buffer, begin -1);
					}
				}
				else
				{
					put_at (&ack_buffer, p, *r);
				}
			}
			if (!eof)
				end = begin + window_size;
			free(r);	
	
		}
	
		// se prelucreaza ackurile care nu au 
		// ajuns in timp ce fereastrea era transmisa
		if (begin != end)
		{
			r = receive_message_timeout (delay + 5); //se asteapta ack 
			while (r != NULL)
			{
				sscanf (r->payload, "%d", &p);

				if (p == begin)
				{
					// daca ackul primit e in ordine se scoate packetul asociat din buffer
					//	si se deplaseaza fereastra pana la urmatorul ack neprimit
					begin++;
					remove_at(&packet_buffer, begin - 1);
					while (size (&ack_buffer) != 0 && element_at(ack_buffer, begin) != NULL)
					{
						begin++;
						remove_at (&ack_buffer, begin - 1);
						remove_at (&packet_buffer, begin -1);
					}
				}
				else
				{
					put_at (&ack_buffer, p, *r); //daca ackul nu e in ordine se retine in buffer
				}
				free(r);
				r = receive_message_timeout (10);
			}
			if (size (&packet_buffer) > 0)
			{
				//daca la final nu s-au primit toate ackurile inseamna
				// ca fereastra nu s-a deplasat si urmatorul packet 
				// trebuie retrimis
				send_message(element_at(packet_buffer, begin)->pack); 
			}
		
		}
		if (!eof)
			end = begin + window_size;
	
	}
}

void send_file(char* filename)
{
	send_header (filename);
	send_content ();
	close(fd);
}

int main(int argc,char** argv){
  	init(HOST,PORT);
	if (argc<2)
	{
		printf("Usage %s filename\n",argv[0]);
		return -1;
	}
	//calcul marime fereastra dupa bandwidth si delay
	window_size = (atoi (argv[1] + 6) * 1000000 * atoi(argv[2] + 6) * 0.01) / 1400;
	delay = atoi (argv[2] + 6);
	tabel = tabelcrc (CRCCCITT);

	send_file(argv[5]);
  return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <unistd.h> 
#include <fcntl.h>

#define BUFLEN 1025

void error(char *msg)
{
    perror(msg);
    exit(0);
}

int main(int argc, char *argv[])
{
	int sockfd, sockfd2, n;
	struct sockaddr_in serv_addr, serv_addr2, cli_addr;
	int fdmax, newsocketfd;
	fd_set read_fds, tmp_fds;
	socklen_t clilen;

	FD_ZERO (&read_fds);
	FD_ZERO (&tmp_fds);

	char buffer[BUFLEN];
	if (argc < 5)
	{
		fprintf(stderr,"Usage %s nume_client server_address server_port port\n", argv[0]);
		exit(0);
	}
	
	memset(buffer, 0 , BUFLEN);
	sprintf (buffer, "Info : %s %s %s", argv[1], argv[4], argv[2]);
	
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	sockfd2 = socket (AF_INET, SOCK_STREAM, 0);

	if (sockfd < 0 && sockfd2 < 0) 
		error("ERROR opening socket");

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(atoi(argv[3]));
	inet_aton(argv[2], &serv_addr.sin_addr);
	
	memset ((char *) &serv_addr2, 0, sizeof (serv_addr2));
	serv_addr2.sin_family = AF_INET;
	serv_addr2.sin_addr.s_addr = INADDR_ANY;
	serv_addr2.sin_port = htons ((int)atoi (argv[4]));


	if (connect(sockfd,(struct sockaddr*) &serv_addr,sizeof(serv_addr)) < 0) 
		error("ERROR connecting");

	n = send (sockfd, buffer, strlen(buffer), 0);
	if (n < 0)
		error ("ERROR writing info to socket");

	if (bind (sockfd2, (struct sockaddr *) &serv_addr2, sizeof (struct sockaddr)) < 0)
        {
                perror ("Error on binding");
                exit (1);
        }

	listen (sockfd2, 20);
	
	FD_SET (sockfd2, &read_fds);
	FD_SET (0, &read_fds);
	FD_SET (sockfd, &read_fds);

	fdmax = sockfd2;
	if (sockfd > fdmax)
		fdmax = sockfd;

	while (1)
	{
		tmp_fds = read_fds; 
		if (select(fdmax + 1, &tmp_fds, NULL, NULL, NULL) == -1) 
			error("ERROR in select");
		fflush (stdin);	
		for(int i = 0; i <= fdmax; i++) 
		{
			if (FD_ISSET(i, &tmp_fds)) 
			{
				if (i == sockfd2) 
				{
					// a venit ceva pe socketul de ascultare = o noua conexiune
					// actiunea serverului: accept()
					clilen = sizeof(cli_addr);
					if ((newsocketfd = accept(sockfd2, (struct sockaddr *)&cli_addr, &clilen)) == -1) 
					{
						error("ERROR in accept");
					} 
					else 
					{
						//adaug noul socket intors de accept() la multimea descriptorilor de citire
						FD_SET(newsocketfd, &read_fds);
						if (newsocketfd > fdmax) 
						{ 
							fdmax = newsocketfd;
						}
					}
					printf("Noua conexiune de la %s, port %d, socket_client %d\n ", inet_ntoa(cli_addr.sin_addr), ntohs(cli_addr.sin_port), newsocketfd);
				}
				else 
				{
					// am primit date pe unul din socketii cu care vorbesc cu clientii
					//actiunea serverului: recv()
					memset(buffer, 0, BUFLEN);
					if (i != 0 && (n = recv(i, buffer, sizeof(buffer), 0)) <= 0) 
					{
						if (n == 0) 
						{
							printf("server: socket %d hung up\n", i);
						}
						else 
						{
							error("ERROR in recv");
						}
						close(i);
						FD_CLR(i, &read_fds); // scoatem din multimea de citire socketul pe care
					}
					else 
					{
						if (i != 0)
						{
							char command[100];
							sscanf (buffer, "%s", command);
							if (strcmp (command, "message") == 0)
							{
								printf ("%s", strstr(buffer, "message") + 8);
							}
							if (strcmp (command, "server_quit") == 0)
							{
								FD_CLR (sockfd, &read_fds);
							}
							if (strcmp (command, "getfile") == 0)
							{
								char fileName[100];
								char msg[BUFLEN];
								memset (msg, 0, BUFLEN);
								sscanf (buffer, "getfile %s", fileName);
								int file = open (fileName, O_RDONLY);
								int l = sprintf (msg, "sendedfile %s : %d\n\n", fileName, file);
								read (file, msg + l, BUFLEN - l);
								msg[BUFLEN] = '\0';
								send (i, msg, strlen(msg), 0);
								memset (buffer, 0, BUFLEN);
							}
							if (strcmp (command, "sendedfile") == 0)
							{
								int file, localFile;
								char fileName[100];
								buffer[BUFLEN] = '\0';
								sscanf (buffer, "sendedfile %s : %d\n\n", fileName, &file);
								localFile = open (fileName,O_WRONLY|O_CREAT|O_TRUNC,0644);
								char *text = strstr (buffer, "\n\n") + 2;
								write (localFile, text, strlen (text));
								memset (buffer, 0, BUFLEN);
								sprintf (buffer, "more from %d to %d\n\n", file, localFile);
								buffer[BUFLEN] = '\0';
								send (i, buffer, strlen (buffer), 0);						
							}
							if (strcmp (command, "more") == 0)
							{
								int from, to;
								char msg[BUFLEN];
								memset (msg, 0, BUFLEN);
								sscanf (buffer, "more from %d to %d\n\n", &from, &to);
								int l = sprintf (msg, "sendedmore from %d to %d\r\n", from, to);
								read (from, msg + l, BUFLEN - l);
								msg[BUFLEN] = '\0';
								send (i, msg, strlen (msg), 0);
							}
							if (strcmp (command, "sendedmore") == 0)
							{
								int from, to;
								bool ok = false;
								char msg[BUFLEN];
								memset (msg, 0, BUFLEN);
								buffer[BUFLEN] = '\0';
								sscanf (buffer, "sendedmore from %d to %d\r\n", &from, &to);
								char *text = strstr (buffer, "\r\n") + 2;

								write (to, text, strlen (text));
								if (strlen (text) == 0)
								{
									printf ("File received\n");
									ok = true;
									sprintf (msg, "receivedAll from %d\r\n", from);
								}
								else
									sprintf (msg, "more from %d to %d\n\n", from, to);
								send (i, msg, strlen (msg), 0);
								if (ok)
								{
									FD_CLR (i, &read_fds);
									close (i);
								}
							}
							if (strcmp (command, "receivedAll") == 0)
							{
								int from;
								sscanf (buffer, "receivedAll from %d", &from);
								close (from);
							}

						}	
						if (i == 0)
						{
							char newBuffer[100];
							fflush (stdin);
							//memset (newBuffer, 0, 100);
							char command[100];
							read (i, newBuffer, 100);
							sscanf (newBuffer, "%s %*s", command);
							//trimitere comanda catre server de listclients
							if (strcmp (command, "listclients") == 0)
							{
								send (sockfd, newBuffer, strlen (newBuffer), 0);
								memset (buffer, 0, BUFLEN);
								n = recv (sockfd, buffer, sizeof(buffer), 0);
								printf ("%s", buffer);
							}
							//trimitere comanda infoclient catre server
							if (strcmp (command, "infoclient") == 0)
							{
								send (sockfd, newBuffer, strlen (newBuffer), 0);
								memset (buffer, 0, BUFLEN);
								n = recv (sockfd, buffer, sizeof (buffer), 0);
								printf ("%s", buffer);
							}
							//interogare server informatii client si trimitere mesaj cartre client
							if (strcmp (command, "message") == 0)
							{
								char client[100];
								char *message;
								int tempPort;
								int sockfdTemp = socket (AF_INET, SOCK_STREAM, 0);

								sscanf (newBuffer, "%*s %s %*s", client);
								struct sockaddr_in temp_serv;
								message = strstr (newBuffer, client) + strlen (client) + 1;
								char tempCommand[100];

								sprintf (tempCommand, "infoclient %s", client);
								send (sockfd, tempCommand, strlen (tempCommand), 0);
								memset (buffer, 0, BUFLEN);
								recv (sockfd, buffer, sizeof (buffer), 0);
								if (strstr (buffer, "The client you requested does not exist") != NULL)
									printf ("%s", buffer);
								else
								{

									sscanf (buffer, "Name %*s : Port %d : %*s", &tempPort);
									temp_serv.sin_family = AF_INET;
									temp_serv.sin_port = htons(tempPort);
									inet_aton(argv[2], &temp_serv.sin_addr);
									connect(sockfdTemp, (struct sockaddr*) &temp_serv,sizeof(temp_serv));
									memset (buffer, 0, BUFLEN);
									sprintf (buffer, "message %s: %s", argv[1], message);
									char *term= strstr (buffer, "\n");
									* (term + 1) = '\0';

									send (sockfdTemp, buffer, strlen (buffer), 0);
									close (sockfdTemp);
								}
							}
							//trimitere nume fisier catre server daca acesta exista
							if (strcmp (command, "sharefile") == 0)
							{
								char fileName[100];
								sscanf (newBuffer, "sharefile %s", fileName);
								if (open (fileName, O_RDONLY) == -1)
								{
									printf ("Error : The file you want to share does not exits or cannot be opened\n");
								}
								else
								{
									send (sockfd, newBuffer, strlen (newBuffer), 0);
								}
							}
							// retragere fisier din lista de fisiere shareuite
							if (strcmp (command, "unsharefile") == 0)
							{
								send (sockfd, newBuffer, strlen (newBuffer), 0);
								memset (buffer, 0, BUFLEN);
								recv (sockfd, buffer, sizeof (buffer), 0);
								printf ("%s\n", buffer);
							}
							// retragere lista fisiere unui client
							if (strcmp (command, "getshare") == 0)
							{
								send (sockfd, newBuffer, strlen (newBuffer), 0);
								memset (buffer, 0, BUFLEN);
								recv (sockfd, buffer, sizeof (buffer), 0);
								printf ("%s", buffer);
							}
							// cerere fisier
							if (strcmp (command, "getfile") == 0)
							{
								char client[100];
								char fileName[100];
								int tempPort;
								int sockfdTemp = socket (AF_INET, SOCK_STREAM, 0);

								sscanf (newBuffer, "%*s %s %s", client, fileName);
								struct sockaddr_in temp_serv;
								char tempCommand[100];

								sprintf (tempCommand, "infoclient %s", client);
								send (sockfd, tempCommand, strlen (tempCommand), 0);
								memset (buffer, 0, BUFLEN);
								recv (sockfd, buffer, sizeof (buffer), 0);

								if (strstr (buffer, "The client you requested") != NULL)
								{
									printf ("%s", buffer);
								}
								else
								{
									sscanf (buffer, "Name %*s : Port %d : %*s", &tempPort);
									memset (tempCommand, 0, 100);
									sprintf (tempCommand, "getshare %s", client);
									send (sockfd, tempCommand, strlen (tempCommand), 0);
									memset (buffer, 0, BUFLEN);	
									recv (sockfd, buffer, sizeof (buffer), 0);
									if (strstr (buffer, fileName) != NULL)
									{
										temp_serv.sin_family = AF_INET;
										temp_serv.sin_port = htons(tempPort);
										inet_aton(argv[2], &temp_serv.sin_addr);
										connect(sockfdTemp, (struct sockaddr*) &temp_serv,sizeof(temp_serv));
										FD_SET (sockfdTemp, &read_fds);
										if (sockfdTemp > fdmax)
											fdmax = sockfdTemp;
										memset (buffer, 0, BUFLEN);
										sprintf (buffer, "getfile %s", fileName);
										send (sockfdTemp, buffer, sizeof (buffer), 0);
									}
									else
									{
										printf ("The file you requested does not exist in sharelist\n");
									}
								}
								
							}
							//trimitere mesaj de inchidere catre server si inchidere client
							if (strcmp (command, "quit") == 0)
							{
								memset (buffer, 0, BUFLEN);
								sprintf (buffer, "Iquit");
								send (sockfd, buffer, strlen (buffer), 0);
								for (int j = 0; j <= fdmax; j++)
								{
									if (FD_ISSET (j, &read_fds) && j != sockfd)
									{
										FD_CLR (j, &read_fds);
										close (j);
									}
								}
								FD_CLR (sockfd, &read_fds);
							}
						}
					}
				}
			}
		}
		if (!FD_ISSET (sockfd, &read_fds))
			break;
		fflush (stdin);
		fflush (stdout);
	}
	close(sockfd);
	close(sockfd2);
	return 0;
}



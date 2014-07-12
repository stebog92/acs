#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <time.h>
#include <map>
#include <vector>
#include <string>

using namespace std;

#define BUFLEN 256
typedef struct {
	char name[30];
	int fd;
	int port;
	time_t startConnection;
	vector <string> files;
	char address[100];
} client;
void error (char *msg)
{
	perror (msg);
	exit(1);
}

int main (int argc, char** argv)
{
	int socketfd, fdmax, newsocketfd, port;
	struct sockaddr_in serv_addr, cli_addr;
	fd_set read_fds;
	fd_set tmp_fds;
	vector <client> clients;
	char buffer[BUFLEN];
	socklen_t clilen;
	bool isUnique;
	int n;
	time_t start, end;
	map <int, struct sockaddr_in> itoSock;
	
	if (argc < 2)
	{
		fprintf (stderr, "Usage : %s port\n", argv[0]);
		exit (1);
	}

	FD_ZERO (&read_fds);
	FD_ZERO (&tmp_fds);
	
	socketfd = socket (AF_INET, SOCK_STREAM, 0);
	
	if (socketfd < 0)
	{
		perror ("Error opening socket");
		exit (1);
	}
	
	port = atoi (argv[1]);
	
	memset ((char *) &serv_addr, 0, sizeof (serv_addr));
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons (port);
	
	if (bind (socketfd, (struct sockaddr *) &serv_addr, sizeof (struct sockaddr)) < 0)
	{
		perror ("Error on binding");
		exit (1);
	}
	
	listen (socketfd, 20);
	
	FD_SET (socketfd, &read_fds);
	FD_SET (0, &read_fds);
	fdmax = socketfd;
// main loop
	while (1) 
	{
		tmp_fds = read_fds; 
		if (select(fdmax + 1, &tmp_fds, NULL, NULL, NULL) == -1) 
			error("ERROR in select");
	
		for(int i = 0; i <= fdmax; i++) 
		{
			if (FD_ISSET(i, &tmp_fds)) 
			{
				if (i == socketfd) 
				{
					// a venit ceva pe socketul de ascultare = o noua conexiune
					// actiunea serverului: accept()
					clilen = sizeof(cli_addr);
					if ((newsocketfd = accept(socketfd, (struct sockaddr *)&cli_addr, &clilen)) == -1) 
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
					printf("Noua conexiune de la %s, port %d, socket_client %d\n", inet_ntoa(cli_addr.sin_addr), ntohs(cli_addr.sin_port), newsocketfd);
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
						for (unsigned int x = 0; x < clients.size(); x++)
							if (clients[x].fd == i)
							{
								clients.erase(clients.begin() + x);
								break;
							}
					}
					else 
					{
						if (i == 0)
						{
								
							scanf ("%s", buffer);
							// inchidere toate conexiunile si terminare server
							if (strncmp (buffer, "quit", strlen (buffer)) == 0)
							{
								FD_CLR (socketfd, &read_fds);
								char message[] = "server_quit";
								for (int j = 0; j <= fdmax; j++)
									if (FD_ISSET (j, &read_fds))
										send (j, message, strlen (message), 0);
								
							}
							// afisare informatii clienti
							if (strncmp (buffer, "stats", strlen (buffer)) == 0)
							{
								for (unsigned int j = 0; j < clients.size(); j++)
								{
									printf ("Client %s : Port %d : Address : %s\n", clients[j].name, clients[j].port, clients[j].address);
									printf ("%s's file are: \n", clients[j].name);
									for (unsigned int l = 0; l < clients[j].files.size(); l++)
									{
										printf ("%s\n", clients[j].files[l].c_str());
									}
								}
							}
						}
						else
						{
							// introducere in lista de clienti 
							if (strstr (buffer, "Info") != NULL)
							{
								client c;
								c.fd = i;
								sscanf (buffer, "Info : %s %d %s", c.name, &c.port, c.address);
								time (&start);
								c.startConnection = start;
								isUnique = true;
								for (unsigned int j = 0; j < clients.size(); j++)
									if (strcmp (clients[j].name , c.name) == 0)
										isUnique = false;
								if (isUnique)
									clients.push_back (c);
								else
								{
									printf ("Client rejected : Exista deja un client cu acelasi nume !!!\n");
									close (i);
									FD_CLR(i, &read_fds);
								}		
								
							}
							// trimitere informatii clienti catre clientul interesat
							if (strstr (buffer, "listclients") != NULL)
							{
								char sendBuffer[BUFLEN];
								int l = 0;
								for (unsigned int j = 0; j < clients.size(); j++)
								{
									l = sprintf (sendBuffer + l, "Name: %s Port :%d\n", clients[j].name, clients[j].port);
								}
								send (i, sendBuffer, strlen (sendBuffer), 0);
							}
							// trimitere informatii despre un anumit client
							if (strstr (buffer, "infoclient") != NULL)
							{
								char client[100];
								char sendBuffer[100];
								sscanf (buffer, "infoclient %s", client);
								bool ok = false;
								for (unsigned int j = 0; j < clients.size(); j++)
								{
									if (strcmp (clients[j].name, client) == 0)
									{
										time (&end);
										sprintf (sendBuffer, "Name %s : Port %d : Connected %f s ago\n", clients[j].name, clients[j].port, difftime(end, clients[j].startConnection));
										ok = true;
										break;
									}
								}
								if (ok)
									send (i, sendBuffer, strlen (sendBuffer), 0);
								else
								{
									sprintf (sendBuffer, "The client you requested does not exist\n");
									send (i, sendBuffer, strlen (sendBuffer), 0);
								}
							}
							// inserare fisier trimis de client in lista de fisiere shareuite
							if (strncmp (buffer, "sharefile", 9) == 0)
							{
								char fileName[100];
								sscanf (buffer, "%*s %s",fileName);
								string file (fileName);
								for (unsigned int x = 0; x < clients.size(); x++)
								{
									if (clients[x].fd == i)
									{
										clients[x].files.push_back (file);
										break;
									}
								}
							}
							// scoatere fisier din lista de fisiere shareuite
							if (strncmp (buffer, "unsharefile", 11) == 0)
							{
								char fileName[100];
								bool ok = false;
								sscanf (buffer,"%*s %s",fileName);
								string file (fileName);
								for (unsigned int x = 0; x < clients.size(); x++)
								{
									if (clients[x].fd == i)
									{
										for (unsigned int j = 0; j < clients[x].files.size(); j++)
										{
											if (clients[x].files[j].compare (fileName) == 0)
											{
												ok = true;
												clients[x].files.erase(clients[x].files.begin() + j);
												break;
											}
										}
										break;
									}
								}
								memset (buffer, 0, BUFLEN);
								if (!ok)
									sprintf (buffer, "File does not exist in the sharelist\n");
								else
									sprintf (buffer, "File unshared successfully\n");
								send (i, buffer, strlen (buffer), 0);
							}
							// trimitere lista de fisiere shareuite a unui client
							if (strstr (buffer, "getshare") != NULL)
							{
								char client[100];
								bool ok = false;
								sscanf (buffer, "%*s %s", client);
								memset (buffer, 0, BUFLEN);
								for (unsigned int x = 0; x < clients.size(); x++)
								{
									if (strcmp (clients[x].name, client) == 0)
									{
										ok = true;
										int p = sprintf (buffer, "%s's files are :\n", client);
										for (unsigned int l = 0; l < clients[x].files.size(); l++)
											p += sprintf (buffer + p, "%s\n", clients[x].files[l].c_str());
										break;
									}
								}
								if (!ok)
									sprintf (buffer, "The client you requested does not exist\n");
								send (i, buffer, strlen (buffer), 0);	
							}
							// scoatere client din baza de date si inchidere conexiune
							if (strstr (buffer, "Iquit") != NULL)
							{
								FD_CLR (i, &read_fds);
								close (i);
								for (unsigned int x = 0; x < clients.size(); x++)
									if (clients[x].fd == i)
									{
										printf ("Client %s has quit\n", clients[x].name);
										clients.erase(clients.begin() + x);
										break;
									}
							}
						}
					}
				}
			}
		}
		if (!FD_ISSET (socketfd, &read_fds))
			break;
		//fflush (stdin);
	}	
	close (socketfd);
	return 0;
}

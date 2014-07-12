#include <stdio.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include <fcntl.h>
#include <queue>
#include <algorithm>
#include "helpers.h"

#define DRUMAX 10000

int vecini[10];
int topologie[10][10];
int dist[10], prev[10], inQueue[10];
int LSet[10];
typedef struct {
	int prev;
	int node;
	int dist;
	int inQueue;
} util;


bool myComp (util x, util y)
{
	return dist[x.node] < dist[y.node];
}
// Functie care itereaza prin sir de caractere si
	//se deplaseaza un nr de spatii albe
void next (char *mes, int &x, int nr)
{
	while (nr)
	{
		if (*(mes + x) == ' ')
			nr --;
		x++;
	}
}
// algoritm pentru calcularea rutelor de la nodul actual la
// celelalte modificand astfel si tabela de rutare

void Dijkstra (int s, int (*tab_rutare)[10][2])
{
	//printf("Begin Dijkstra pentru nodul %d\n", s);
	util temp;
	std::vector<util> q;
	for (int i = 0; i < 10; i++)
	{
		dist[i] = DRUMAX;
		prev[i] = -1;
		temp.node = i;
		inQueue[i] = 1;
		q.push_back(temp);
	}
	dist[s] = 0;
	sort (q.begin(), q.end(), myComp);

	while (!q.empty())
	{
		util x = q[0];
		q.erase (q.begin());
		inQueue[x.node] = 0;
		if (dist[x.node] == DRUMAX)
		{
			break;
		}
		for (int i = 0; i < 10; i++)
		{
			if (topologie[x.node][i] != DRUMAX && topologie[i][x.node] != DRUMAX && inQueue[i] == 1)
			{
				int alt = dist[x.node] + topologie[x.node][i];
				if (alt < dist[i])
				{
					dist[i] = alt;
					if (x.node == s)
					{
						prev[i] = i;
					}
					else
					{
						prev[i] = prev[x.node];
					}
				}
			}
		}
		sort (q.begin(), q.end(), myComp);

	}
	// modificare tabela de rutare
	for (int i = 0; i < 10; i++)
	{
		(*tab_rutare)[i][0] = dist[i];
		(*tab_rutare)[i][1] = prev[i];
	}	
}
					
int main (int argc, char ** argv)
{			

	int pipeout = atoi(argv[1]);
	int pipein = atoi(argv[2]);
	int nod_id = atoi(argv[3]); //procesul curent participa la simulare numai dupa ce nodul cu id-ul lui este adaugat in topologie 
	int timp =-1 ;
	int gata = FALSE;
	msg mesaj, my_message;
	int cit, k, nr_vec;
	int tip_ev, ruter1, ruter2;
	int nr_secv = 0;
	int stch = 0;
	msg LSADatabase[10];
	int cost, vec, move;
	std::queue<msg> queues[2];
	
	//nu modificati numele, modalitatea de alocare si initializare a tabelei de rutare - se foloseste la mesajele de tip 8/10, deja implementate si la logare
	int tab_rutare [KIDS][2]; //tab_rutare[k][0] reprezinta costul drumului minim de la ruterul curent (nod_id) la ruterul k 
								//tab_rutare[k][1] reprezinta next_hop pe drumul minim de la ruterul curent (nod_id) la ruterul k 
								
	for (k = 0; k < KIDS; k++) {
		tab_rutare[k][0] = 10000;  // drum =DRUMAX daca ruterul k nu e in retea sau informatiile despre el nu au ajuns la ruterul curent
		tab_rutare[k][1] = -1; //in cadrul protocolului(pe care il veti implementa), next_hop =-1 inseamna ca ruterul k nu e (inca) cunoscut de ruterul nod_id (vezi mai sus)
	}
																
	printf ("Nod %d, pid %u alive & kicking\n", nod_id, getpid());

	if (nod_id == 0) { //sunt deja in topologie
		timp = -1; //la momentul 0 are loc primul eveniment
		mesaj.type = 5; //finish procesare mesaje timp -1
		mesaj.sender = nod_id;
		write (pipeout, &mesaj, sizeof(msg)); 
		printf ("TRIMIS Timp %d, Nod %d, msg tip 5 - terminare procesare mesaje vechi din coada\n", timp, nod_id);
	
	}
	
	// initializare LSADatabase si topologie
	for (int i = 0; i < 10; i++)
		LSADatabase[i].nr_secv = -1;
	
	for (int i = 0; i < 10; i++)
		for (int j = 0; j < 10; j++)
			topologie[i][j] = DRUMAX;

	while (!gata) {
		cit = read(pipein, &mesaj, sizeof(msg));
		
		if (cit <= 0) {
			printf ("Adio, lume cruda. Timp %d, Nod %d, msg tip %d cit %d\n", timp, nod_id, mesaj.type, cit);
			exit (-1);
		}
		
		switch (mesaj.type) {
			
			//1,2,3,4 sunt mesaje din protocolul link state; 
			//actiunea imediata corecta la primirea unui pachet de tip 1,2,3,4 este buffer-area (punerea in coada /coada new daca sunt 2 cozi - vezi enunt)
			//mesajele din coada new se vor procesa atunci cand ea devine coada old (cand am intrat in urmatorul pas de timp)
			case 1:
				//printf ("Timp %d, Nod %d, msg tip 1 - LSA\n", timp, nod_id);
				queues[stch].push(mesaj);
				break;
				
			case 2:
				//printf ("Timp %d, Nod %d, msg tip 2 - Database Request\n", timp, nod_id);
				queues[stch].push(mesaj);
				break;
				
			case 3:
				//printf ("Timp %d, Nod %d, msg tip 3 - Database Reply\n", timp, nod_id);
				queues[stch].push(mesaj);
				break;
				
			case 4: 
				//printf ("Timp %d, Nod %d, msg tip 4 - pachet de date (de rutat)\n", timp, nod_id);
				queues[stch].push(mesaj);
				break; 
			
			case 6://complet in ceea ce priveste partea cu mesajele de control 
					//puteti inlocui conditia de coada goala, ca sa corespunda cu implementarea personala
				  //aveti de implementat procesarea mesajelor ce tin de protocolul de rutare
				{
				timp++;
				// schimbare cozi intre OLD si NEW
				stch = 1 - stch;
				//printf ("Timp %d, Nod %d, msg tip 6 - incepe procesarea mesajelor puse din coada la timpul anterior (%d)\n", timp, nod_id, timp-1);
				
				//daca NU mai am de procesat mesaje venite la timpul anterior
				//(dar mai pot fi mesaje venite in acest moment de timp, pe care le procesez la t+1) 
				//trimit mesaj terminare procesare pentru acest pas (tip 5)
				//altfel, procesez mesajele venite la timpul anterior si apoi trimit mesaj de tip 5
				while (!queues[1 - stch].empty()) 
				{
					//	procesez tote mesajele din coada old 
					//	(sau toate mesajele primite inainte de inceperea timpului curent - marcata de mesaj de tip 6)
					//	la acest pas/timp NU se vor procesa mesaje venite DUPA inceperea timpului curent
//cand trimiteti mesaje de tip 4 nu uitati sa setati (inclusiv) campurile, necesare pt logare:  mesaj.timp, mesaj.creator, mesaj.nr_secv, mesaj.sender, mesaj.next_hop
					//la tip 4 - creator este sursa initiala a pachetului rutat
					//printf ("Exista %d mesaje in nodul %d pentru timpul %d\n", queues[1 - stch].size(), nod_id, timp - 1);
					
					msg current = queues[1 - stch].front();
					queues[1 - stch].pop();

					// mesaj de tip 1
					if (current.type == 1)
					{
						if (LSet[current.creator] == 0)
						{
							LSet[current.creator] = 1;
							LSADatabase[current.creator].nr_secv = -1;
						}
						if (LSADatabase[current.creator].nr_secv < current.nr_secv)
						{
							int modified[10];
							if (current.len < LSADatabase[current.creator].len)
							{
								for (int i = 0; i < 10; i++)
								{
									topologie[i][current.creator] = DRUMAX;
									topologie[current.creator][i] = DRUMAX;
								}
							}

							LSADatabase[current.creator] = current;
							move = 0;
							int tempVec = current.len;
							while (tempVec--)
							{
								sscanf (current.payload + move, "%d %d ", &vec, &cost);
								next (current.payload, move, 2);
								// actualizare legatura intre cele 2 noduri in topologie daca timpul primului
								// nod e mai mare sau egal cu cel al vecinului (deja am verificat daca e mai mare
								// ca el prin numarul de secventa )
								if (current.timp >= LSADatabase[vec].timp)
								{
									//printf ("Actualizare top intre %d si %d pentru %d cu cost %d\n", current.creator, vec, nod_id, cost);
									topologie[current.creator][vec] = cost;
									topologie[vec][current.creator] = cost;
								}
							}
							// propagare LSA si vecinilor

							my_message = current;
							my_message.sender = nod_id;
							for (int i = 0; i < 10; i++)
							{
								if (topologie[nod_id][i] != DRUMAX && i != current.sender)
								{
									my_message.next_hop = i;
									write (pipeout, &my_message, sizeof(msg));
								}
							}

						}
					}
					// Mesaj de tip 2
					if (current.type == 2)
					{	
						sscanf (current.payload, "%*d %*d %d", &cost);
						// Trimitere Database Reply
						for (int i = 0; i < 10; i++)
						{
							if (LSet[i] == 1)
							{
								my_message = LSADatabase[i];
								my_message.type = 3;
								my_message.next_hop = current.sender;
								my_message.sender = nod_id;
							//	my_message.timp = timp;
								write (pipeout, &my_message, sizeof (msg));
							}
						}
						// creare LSA nou cu informatii de la newR
						move = 0;
						my_message.type = 1;
						my_message.timp = timp;
						my_message.creator = nod_id;
						my_message.sender = nod_id;
						my_message.nr_secv = nr_secv++;
						my_message.next_hop = current.sender;
						my_message.len = 0;

						for (int i = 0; i < 10; i++)
						{
							if (topologie[nod_id][i] != DRUMAX)
							{
								int l =  sprintf (my_message.payload + move, "%d %d ", i, topologie[nod_id][i]);
								move += l;
								my_message.len++;
							}
						}
						sprintf (my_message.payload + move, "%d %d", current.sender, cost);
						my_message.len++;

						// trimitere LSA lui newR
						write (pipeout, &my_message, sizeof (msg));
						
						// trimitere LSA vecinilor
						for (int i = 0; i < 10; i++)
						{
							if (topologie[nod_id][i] != DRUMAX)
							{
								my_message.next_hop = i;
								write (pipeout, &my_message, sizeof (msg));
							}
						}
					//	printf ("Actualizare top intre %d si %d pentru %d cu cost %d\n", current.sender, nod_id, nod_id, cost);
						topologie[current.sender][nod_id] = topologie[nod_id][current.sender] = cost;
					}
					// mesaj de tip 3
					if (current.type == 3)
					{
						// idem mesaje de tip 1 fara propagare insa
						if (LSet[current.creator] == 0)
						{
							LSet[current.creator] = 1;
							LSADatabase[current.creator].nr_secv = -1;
						}
						if (LSADatabase[current.creator].nr_secv < current.nr_secv)
						{
							int modified[10];
							if (current.len < LSADatabase[current.creator].len)
							{
								for (int i = 0; i < 10; i++)
								{
									topologie[i][current.creator] = DRUMAX;
									topologie[current.creator][i] = DRUMAX;
								}
							}
							LSADatabase[current.creator] = current;
							move = 0;
							int tempVec = current.len;
							while (tempVec--)
							{
								sscanf (current.payload + move, "%d %d ", &vec, &cost);
								next (current.payload, move, 2);
								if (current.timp >= LSADatabase[vec].timp)
								{
									//printf ("Actualizare top intre %d si %d pentru nod %d cu cost %d\n", current.creator, vec, nod_id, cost);
									topologie[current.creator][vec] = cost;
									topologie[vec][current.creator] = cost;
								}
							}
						
						}
					}
					// mesaj de tip 4
					if (current.type == 4)
					{
						int target;
						sscanf (current.payload, "%d", &target);
						// Daca destinatia finala e nodul curent atunci 
						// afisare mesaj la consola
						if (target == nod_id)
							printf ("Packet arrived at destination : Total Cost %d\n", current.len);
						else
						{
							// Daca destinatia finala nu e ruterul curent atunci 
							// se trimite pachetul catre nodul urmator conform 
							// tabelei de rutare
							current.next_hop = tab_rutare[target][1];
							if (current.next_hop != -1)
							{
								current.len += topologie[nod_id][tab_rutare[target][1]];
								printf("Trimitere catre %d\n", current.next_hop);
								write (pipeout, &current, sizeof(msg));
							}
							else
								printf ("Messsage dropped - there is no route to the destination!\n");
						}
					}
				}
					// calculare rute cele mai scurte
					Dijkstra (nod_id, &tab_rutare);	
					// acum coada e goala trimit mesaj de tip 5
					mesaj.type = 5; 
					mesaj.sender = nod_id;
					write (pipeout, &mesaj, sizeof(msg)); 
				}
				break;
			
			case 7: //complet in ceea ce priveste partea cu mesajele de control
					//aveti de implementat tratarea evenimentelor si trimiterea mesajelor ce tin de protocolul de rutare
					//in campul payload al mesajului de tip 7 e linia de fisier (%s) corespunzatoare respectivului eveniment
					//vezi multiproc.c, liniile 88-115 (trimitere mes tip 7) si liniile 184-194 (parsare fisiere evenimente)
					 
					//rutere direct implicate in evenimente, care vor primi mesaje de tip 7 de la simulatorul central:
					//eveniment tip 1: ruterul nou adaugat la retea  (ev.d1  - vezi liniile indicate)
					//eveniment tip 2: capetele noului link (ev.d1 si ev.d2)
					//eveniment tip 3: capetele linkului suprimat (ev.d1 si ev.d2)
					//evenimet tip 4:  ruterul sursa al pachetului (ev.d1)
				{
					if (mesaj.join == TRUE) 
					{
						timp = mesaj.timp;
						printf ("Nod %d, msg tip eveniment - voi adera la topologie la pasul %d\n", nod_id, timp+1); 
					}
					else
						printf ("Timp %d, Nod %d, msg tip 7 - eveniment\n", timp+1, nod_id);
					//acest tip de mesaj (7) se proceseaza imediat - nu se pune in nicio coada (vezi enunt)
					sscanf (mesaj.payload, "%d", &tip_ev);
					//eveniment de tip 1
					if (tip_ev == 1) 
					{
						sscanf (mesaj.payload, "%*d %*d %d", &nr_vec);
						next (mesaj.payload, move, 3);
						int vec, cost;
						// trimitere mesaje de tip 2 catre noii vecini
						while (nr_vec--)
						{
							sscanf (mesaj.payload + move, "%d %d ", &vec, &cost);
							next (mesaj.payload, move, 2);
							my_message.type = 2;
							my_message.sender = nod_id;
							my_message.creator = nod_id;
							my_message.nr_secv = nr_secv++;
							my_message.next_hop = vec;
							my_message.timp = timp + 1;
							sprintf (my_message.payload, "%d %d %d", nod_id, vec, cost);
							write (pipeout, &my_message, sizeof (msg));
						}
					}
					// eveniment de tip 2
					if (tip_ev == 2)
					{
						sscanf (mesaj.payload,"%*d %d %d %d", &ruter1, &ruter2, &cost);
	
						// trimitere mesaj Database Request la celalalt capat al linkului
						my_message.type = 2;
						my_message.sender = nod_id;
						my_message.timp = timp + 1;
						my_message.creator = nod_id;
						if (nod_id == ruter1)
						{
							my_message.next_hop = ruter2;
							sprintf (my_message.payload, "%d %d %d", nod_id, ruter2, cost);
						}
						else
						{
							my_message.next_hop = ruter1;
							sprintf (my_message.payload, "%d %d %d", nod_id, ruter1, cost);
						}
						write (pipeout, &my_message, sizeof(msg));
						topologie[ruter1][ruter2] = topologie[ruter2][ruter1] = cost;
					}
					// eveniment de tip 3
					if (tip_ev == 3)
					{
						move = 0;
						sscanf (mesaj.payload, "%*d %d %d", &ruter1, &ruter2);
						// stergere legatura intre ruter1 si ruter2
						topologie[ruter1][ruter2] = DRUMAX;
						topologie[ruter2][ruter1] = DRUMAX;
						// construire pachet lsa
						printf ("Stergere legatura intre %d si %d \n", ruter1, ruter2);
						my_message.type = 1;
						my_message.creator = nod_id;
						my_message.sender = nod_id;
						my_message.timp = timp + 1;
						my_message.nr_secv = nr_secv++;
						memset (my_message.payload, 0, 1400);
						my_message.len = 0;
						for (int i = 0; i < 10; i++)
						{
							if (topologie[nod_id][i] != DRUMAX)
							{
								int l =  sprintf (my_message.payload + move, "%d %d ", i, topologie[nod_id][i]);
								move += l;
								my_message.len++;
							}
						}
						for (int i = 0; i < 10 ; i++)
						{
							if (topologie[nod_id][i] != DRUMAX)
							{
								my_message.next_hop = i;
								write (pipeout, &my_message, sizeof(msg));
							}
						}
						LSADatabase[nod_id] = my_message;
						Dijkstra (nod_id, &tab_rutare);

					}
					if (tip_ev == 4)
					{
						sscanf (mesaj.payload, "%*d %d %d", &ruter1, &ruter2);
						if (ruter1 == nod_id)
						{
							if (tab_rutare[ruter2][1] != -1)
							{
								printf("Nod %d Trimitere pachet de date catre %d\n", nod_id, ruter2);
								my_message.type = 4;
								memset(my_message.payload, 0, 1400);
								my_message.sender = nod_id;
								my_message.creator = nod_id;
								my_message.nr_secv = nr_secv++;
								my_message.timp = timp + 1;
								my_message.next_hop = tab_rutare[ruter2][1];
								my_message.len = topologie[nod_id][tab_rutare[ruter2][1]];
								sprintf (my_message.payload, "%d", ruter2);
								write (pipeout, &my_message, sizeof(msg));
							}
							else
								printf ("Nu exista deocamdata legatura catre destinatie\n");
						}
					}
				}
				break;
			
			case 8: //complet implementat - nu modificati! (exceptie afisari on/off)
				{
				//printf ("Timp %d, Nod %d, msg tip 8 - cerere tabela de rutare\n", timp+1, nod_id);
				mesaj.type = 10;  //trimitere tabela de rutare
				mesaj.sender = nod_id;
				memcpy (mesaj.payload, &tab_rutare, sizeof (tab_rutare));
				//Observati ca acest tip de mesaj (8) se proceseaza imediat - nu se pune in nicio coada (vezi enunt)
				write (pipeout, &mesaj, sizeof(msg)); 
				}
				break;
				
			case 9: //complet implementat - nu modificati! (exceptie afisari on/off)
				{
				//Aici poate sa apara timp -1 la unele "noduri"
				//E ok, e vorba de procesele care nu reprezentau rutere in retea, deci nu au de unde sa ia valoarea corecta de timp
				//Alternativa ar fi fost ca procesele neparticipante la simularea propriu-zisa sa ramana blocate intr-un apel de read()
				printf ("Timp %d, Nod %d, msg tip 9 - terminare simulare\n", timp, nod_id);
				gata = TRUE;
				}
				break;
				

			default:
				printf ("\nEROARE: Timp %d, Nod %d, msg tip %d - NU PROCESEZ ACEST TIP DE MESAJ\n", timp, nod_id, mesaj.type);
				exit (-1);
		}			
	}

return 0;

}

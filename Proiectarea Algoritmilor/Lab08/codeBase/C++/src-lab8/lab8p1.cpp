#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstring>

#include "GraphAdjMat.h"

void transitive_closure(GraphAdjMat& graph)
{
  /* TODO: Update-ati graful folosind algoritmul Floyd-Warshall pentru a
   *  contine inchiderea sa tranzitiva.
   *
   * Pentru a accesa muchiile grafului, folositi get_edge(a, b), iar pentru
   * a adauga muchii in graf, folositi set_edge(a, b, cost).
   *
   * Mai multe informatii gasiti in documentatie. */
	//for (int i = 0; i < graph.get_n(); i++)
	//	for (int j = 0; j < graph.get_n(); j++)
	//		if (graph.get_edge(i,j) != graph.NONE)
	//			graph.set_detour(i,j,j);

	for (int i = 0; i < graph.get_n(); i++)
	{
		for (int j = 0; j < graph.get_n(); j++)
		{
			for (int k = 0; k < graph.get_n(); k++)
			{
				if (graph.get_edge(i, k) != graph.NONE && graph.get_edge(k, j) != graph.NONE && i != k && k != j && i != j)
				{
					if (graph.get_edge(i, j) == graph.NONE || graph.get_edge(i, j) > graph.get_edge(i, k) + graph.get_edge(k, j))
					{
						graph.set_edge(i, j, graph.get_edge(i, k) + graph.get_edge(k, j));
						graph.set_detour (i, j, k);
						//graph.set_detour (j, i, graph.get_detour(j,k));
					}
				}
			}
			
		}
	}
	
}

void print_routing_table(GraphAdjMat& graph)
{
  /* TODO: Din graful pe care ati aplicat un algoritm un algoritm care sa
   * calculeze drumul de lungime optima intre oricare doua noduri, precum si
   * urmatorul nod, afisati tabela de rutate pentru fiecare nod in parte in
   * formatul din enunt:
   *
   * DESTINATIA | NEXT HOP | TOTAL COST */
	for (int i = 0; i < graph.get_n(); i++)
	{
		std::cout << "Nodul " << i << " :" << std::endl;
		for (int j = 0; j < graph.get_n(); j++)
		{
			if (i != j)
			{
				std::cout << j << " | " << graph.get_detour (i, j) << " | " << graph.get_edge(i, j) << std::endl;
			}
		}
	}
	
}

void unweightedTasks() {
  /* Deschidem fisierul de intrare si citim numarul de noduri din graf. */
  std::ifstream in("src-lab8/GraphUnweighted.in");
  int n;
  in >> n;

  /* Cream un graf cu n noduri si apoi incarcam muchiile din fisier. */
  GraphAdjMat graph(n);
  in >> graph;
  in.close();

  /* Afisam graful initial. */
  std::cout << "Initial graph:" << std::endl << graph << std::endl;

  /* Efectuam inchiderea tranzitiva pe graf si apoi afisam rezultatul. */
  transitive_closure(graph);
  std::cout << "Transitive closure:" << std::endl << graph << std::endl;

  /* Pe graful cu inchiderea tranzitiva calculata, printam tabela de rutare. */
  std::cout << "Graph routing table:" << std::endl;
  print_routing_table(graph);
}

void dijkstra(GraphAdjMat& graph, int source, int dest)
{
  /* TODO: Calculati drumul optim de la sursa la destinatie.
   * ATENTIE! Trebuie ca de asemenea sa setati detour-urile corect pentru a
   * putea apoi sa va folositi de rezultate:
   *
   * Daca drumul (source,y) vine de la (source,x) U (x,y), atunci detour pentru
   * (source,y) TREBUIE sa fie x! */

  /* Cream si initailizam un vector de distante intre nodul sursa si toate
   * celelalte noduri. */
  int dmin[graph.get_n()];
  int parent[graph.get_n()];
  bool inQueue[graph.get_n()];
  int min;
  int elem;
  for (unsigned int i = 0; i < graph.get_n(); ++i) {
    if (i == source) {
      dmin[i] = 0;
      parent[i] = GraphAdjMat::NONE;
    } else {
      dmin[i] = graph.get_edge(source, i);
      parent[i] = source;
    }
    inQueue[i] = true;
	     
  }

  /* De maxim (N - 1) ori, alegem un nod cu distanta minima si update-am
   * parintele sau. */
  inQueue[source] = false;
  for (unsigned int i = 0; i < graph.get_n() - 1 ; ++i) {
    /* TODO: rezolvarea aici. */
	min = 1000;
	for (int j = 0; j < graph.get_n(); j++)
		if (min > dmin[j] && dmin[j] >= 0 && inQueue[j])
		{
			min = dmin[j];
			elem = j;
		}
	if (inQueue[elem] == false)
		for(int j = 0; j < graph.get_n(); j++)
			if (inQueue[j] == true)
			{
				elem = j;
				break;
			}
	inQueue[elem] = false;
	for (int u = 0; u < graph.get_n(); u++)
		if (graph.get_edge(elem, u) != graph.NONE)
		{
			if (inQueue[u] && dmin[u] > dmin[elem] + graph.get_edge(elem, u) || dmin[u] == graph.NONE)
			{
				dmin[u]	= dmin[elem] + graph.get_edge(elem, u);
				parent[u] = elem;
				graph.set_edge(source, u, dmin[u]);
				graph.set_detour(source, u, elem);
			}
		}

  }
		
}

GraphAdjMat::Path minimum_cycle(GraphAdjMat& graph, int u, int v)
{
  /* Stergem toate drumurile din graph, pentru ca nu mai sunt de actualitate.
   * (Dijkstra va recalcula drumurile). */
  graph.clear_paths();

  /* Stergem muchia directa dintre U si V. */
  int backup = graph.get_edge(u,v);
  graph.erase_edge(u, v);

  /* Rulam Dijkstra pentru a gasi cea mai buna alternativa intre U si V. */
  dijkstra(graph, v, u);
  GraphAdjMat::Path returnValue = graph.path(v, u);

  /* Adaugam muchia inapoi (atat in graf cat si in ciclu). */
  graph.set_edge(u, v, backup);
  if (returnValue.size()) {
    returnValue.push_back(std::pair< std::pair<int,int>, int>(
            std::pair<int, int>(u, v), backup));
  }

  /* Intoarcem ciclul de cost minim depistat intre U si V. */
  graph.clear_paths();
  return returnValue;
}

std::vector<GraphAdjMat::Path> get_min_cycles_cover(GraphAdjMat & graph)
{
  std::vector<GraphAdjMat::Path> returnValue;
  /* TODO: Folositi functia de ciclu minim pentru a obtine un set de cicluri de
   * cost minim care sa acopere muhciile grafului.
   * Puteti sa tineti cont in parcursul rezolvarii de urmatoarele hint-uri:
   *
   * a) Orice muchie din graf poate face parte din 0, 1 sau mai multe cicluri.
   * Dintre ciclurile din care face muchia parte, exista unul singur de cost
   * minim.
   *
   * b) Oricare ar fi doua cicluri diferite in graf, exista cel putin o muchie
   * in fiecare dintre ele care sa nu faca parte din celalalt ciclu.
   */

  /* Vom tine o matrice booleana din care sa reiasa daca o muchie a fost sau nu
   * folosita pana acum intr-un ciclu. */
  bool used[graph.get_n()][graph.get_n()];
  memset(used, 0x00, graph.get_n() * graph.get_n());
  graph.clear_paths();

  /* TODO: rezolvarea aici. */
	std::vector<Edge> edges = graph.get_edges();
	int am = edges.size();
	while (am)
	{
		for (int i = 0; i < edges.size(); i++)
		{
			if (used[edges[i].u][edges[i].v] == false)
			{
				GraphAdjMat::Path p = minimum_cycle (graph, edges[i].u, edges[i].v);
				for (int j = 0; j < p.size(); j++)
				{
					int a = p[j].first.first;
					int b = p[j].first.second;
					used[a][b] = true;
					used[b][a] = true;
					am --;
				}
			}
		}
	}
	
	

  return returnValue;
}

void weightedTasks() {
  /* Deschidem fisierul de intrare si citim numarul de noduri din graf. */
  std::ifstream in("src-lab8/GraphWeighted.in");
  int n;
  in >> n;

  /* Cream un graf cu n noduri si apoi incarcam muchiile din fisier. */
  GraphAdjMat graph(n);
  in >> graph;
  in.close();

  /* Afisam graful initial. */
  std::cout << "Initial graph:" << std::endl << graph << std::endl;

  /* Afisam la intamplare doua drumuri optime din graf. */
  if (graph.get_n() >= 7) {
    dijkstra(graph, 0, 6);
    std::cout << "Best path between 0 and 6 is: " << graph.path(0, 6)
        << std::endl;
	graph.clear_paths();
    dijkstra(graph, 2, 7);
    std::cout << "Best path between 2 and 7 is: " << graph.path(2, 7)
        << std::endl;
  }

  /* Calculam un set de cicluri minime care sa acopere muchiile grafului. */
  //std::vector<GraphAdjMat::Path> cover = get_min_cycles_cover(graph);
  std::cout << "A set of minimum cycles that covers the graph's edges is:"
      << std::endl;
  //for (unsigned int i = 0; i < cover.size(); ++i) {
  //  std::cout << cover[i] << std::endl;
  //}
}

int main()
{
  /* Punctele (a) si (c) se rezolva pe graful cu costuri uniforme egale cu 1. */
  unweightedTasks();

  /* Punctele (b) si (d) se rezolva pe graful cu costuri diferite, dar pozitive. */
  weightedTasks();

  return 0;
}


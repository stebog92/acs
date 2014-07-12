#include <iostream>
#include <vector>
#include <queue>
#include <fstream>
#include <cstring>
#include <algorithm>

#include "FlowGraph.h"

#define NONE -1

std::vector<unsigned int> bfs(FlowGraph& graph,
                              unsigned int source,
                              unsigned int sink)
{
  /* Ne vom folosi de vectorul de parinti pentru a spune daca un nod a fost
   * adaugat sau nu in coada. */
  std::vector<unsigned int> parent(graph.size(), NONE);

  std::deque<unsigned int> q;
  q.push_back(source);

  /* TODO: Rulati BFS pentru a determina un drum intre sursa si destinatie. */
	while (!q.empty())
	{
		unsigned int u = q.front();
		q.pop_front();
		int *vec = graph[u];
		for (int v = 0; v < graph.size (); v++)
			if (vec[v] && parent[v] == NONE && !graph.is_saturated(u, v))
			{
				parent[v] = u;
				q.push_back (v);
			}
	}
					
  /* Daca nu s-a atins destinatia, atunci nu mai exista drumuri de crestere. */
  if (parent[sink] == NONE) {
    return std::vector<unsigned int>();
  }

  /* TODO: Reconstituim drumul de la destinatie spre sursa. */
  std::vector<unsigned int> returnValue;
	while (sink != source)
	{
		returnValue.push_back(sink);
		sink = parent[sink];
	}
	returnValue.push_back (source);
  return returnValue;
}

unsigned int saturate_path(FlowGraph& graph,
                           std::vector<unsigned int>& path)
{
	int maxim = 1000;
  /* TODO: Determinam fluxul maxim prin drum. */
	for (int i = path.size() - 1; i >= 1; i--)
		maxim = std::min (graph[path[i]][path[i - 1]], maxim);
	for (int i = path.size() - 1; i >= 1; i--)
		graph[path[i]][path[i - 1]] -= maxim;
	return maxim;
	
  /* TODO: Si il saturam in graf. */

  /* TODO: Raportam fluxul cu care am saturat graful. */
  return 0;
}


unsigned int maximum_flow(FlowGraph& graph,
                          unsigned int source,
                          unsigned int sink) {
  unsigned int returnValue = 0;

  /* TODO: Stergeti linia de mai jos atunci cand rezolvati. */

  /* Vom incerca in mod repetat sa determinam drumuri de crestere folosind
   * BFS si sa le saturam pana cand nu mai putem determina un astfel de drum in
   * graf. */
  while (true) {
    /* TODO: Determina drum de crestere. */
	std::vector <unsigned int> path = bfs (graph, source, sink);
	if (path.size() == 0)
		return returnValue;
	else
		returnValue += saturate_path (graph, path);

    /* TODO: Daca nu exista drum de crestere, atunci intoarce fluxul maxim gasit. */

    /* TODO: Altfel satureaza drumul. */
  }

  return returnValue;
}

void min_cut(FlowGraph& graph,
             std::vector<unsigned int>& source_set,
             std::vector<unsigned int>& sink_set,
             unsigned int source)
{
  /* Facem o parcurgere BFS din nodul sursa si punem nodurile atinse de
   * parcurgere in source_set. Toate celelalte noduri se vor afla in
   * sink_set. */
  std::vector<bool> in_queue(graph.size(), false);
  std::deque<unsigned int> q;
  q.push_back(source);
  in_queue[source] = true;

  /* TODO: Rulam BFS din sursa si marcam nodurile atinse. */

  /* TODO: Traversam inca odata si impartim nodurile intre cele doua multimi. */
}

void disjoint_paths(FlowGraph& graph,
                    unsigned int source,
                    unsigned int sink)
{
  /* Caclculam fluxul prin graf. */
  unsigned int nr_paths = maximum_flow(graph, source, sink);
  std::cout << "There are " << nr_paths << " disjoint paths in the graph from "
      << source << " to " << sink << "." << std::endl;

  for (unsigned int i = 0; i < nr_paths; ++i) {
    /* TODO: Extragem drumuri mergand doar pe muchii complet saturate, pe care
     * apoi le desaturam. */
  }
  std::cout << std::endl;
}

void approximateAndPrint(FlowGraph& graph)
{
  /* TODO: Cititi in main despre cum s-a reprezentat graful problemei. */

  /* TODO: Rulam flux in graf. */

  /* TODO: Si apoi printam matricea in functie de ce s-a saturat efectiv. */
}

int main()
{
  /* Deschidem fisierul de intrare si citim un graf din el. */
  std::ifstream in("src-lab10/Graph.in");
  FlowGraph graph;
  in >> graph;
  in.close();

  unsigned int source = 0;
  unsigned int sink = 5;

  /* Calculam si afisam fluxul maxim de date care poate fi suportat de retea
   * intre doua noduri oarecare: de exemplu, 0 si 5. */
  unsigned int flow = maximum_flow(graph, source, sink);
  std::cout << "The maximum flow in the graph between nodes 0 and 5 is: "
      << flow << "." << std::endl;

  /* Calculam si afisam o taietura minimala a grafului. */
  std::vector<unsigned int> source_set;
  std::vector<unsigned int> sink_set;
  min_cut(graph, source_set, sink_set, source);
  std::cout << "The minimum cut associated with the flow yields:" << std::endl;
  std::cout << "Set of the source: {";
  for (unsigned int i = 0; i < source_set.size(); ++i) {
    std::cout << (i == 0 ? " " : ", ") << source_set[i];
  }
  std::cout << " }" << std::endl;
  std::cout << "Set of the sink: {";
  for (unsigned int i = 0; i < sink_set.size(); ++i) {
    std::cout << (i == 0 ? " " : ", ") << sink_set[i];
  }
  std::cout << " }" << std::endl << std::endl;

  /* Deschidem un fisier din care citim topologia originala a grafului, */
  std::ifstream topo("src-lab10/GraphBinary.in");
  FlowGraph graphTopo;
  topo >> graphTopo;
  in.close();

  /* Printam un numar maximal de drumuri disjuncte intre aceleasi doua noduri
   * din graf. */
  disjoint_paths(graphTopo, source, sink);

  /* Deschidem un fisier din care sa incarcam un graf pentru o matrice. */
  std::ifstream matrix("src-lab10/GraphMatrix.in");
  FlowGraph graphMatrix;

  /* Graful incarcat din matricea N x N din fisier va arata astfel:
   * (a) Exista un nod sursa, cu numarul 0.
   * (b) Exista cate un nod pentru fiecare linie, numerotate cu 1, 2, ... N.
   *      Capacitatea de la 0 la nodurile liniilor este egala cu partea
   *      intreaga a sumei pe linia respectiva + 1.
   * (c) Exista cate un nod pentru fiecare coloana, numerotate cu N + 1, N + 2,
   *      ... 2 * N.
   *      Capacitatea de la fiecare nod pentru linie la fiecare nod pentru
   *      coloana este egala cu partea intreaga a respectivei celule + 1.
   * (d) Exista un nod destinatie, cu numarul 2 * N - 1.
   *      Capacitatea de la un nod pentru coaloana la nodul destinatie este
   *      egala cu partea intreaga a sumei pe coloana respectiva + 1.
   *
   * Se poate observa usor ca o aproximare valida este egala cu fluxul care
   * trece prin muchiile care reprezinta celulele din matrice in cazul saturarii
   * maxime a retelei de transport.
   */
  loadMatrixGraph(matrix, graphMatrix);
  matrix.close();

  /* Aproximam graful si printam matricea aproximata. */
  approximateAndPrint(graphMatrix);

  return 0;
}


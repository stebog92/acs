#include <iostream>
#include <fstream>
#include <algorithm>
#include <cmath>
#include <vector>

#include "GraphAdjMat.h"
#include "DisjointSets.h"
#define INF 9999

std::vector<Edge> prim(GraphAdjMat& graph, int seed)
{
  std::vector<Edge> returnValue;

  /* Initial, nici unul dintre noduri in afara de frunza nu apartine de
   * arbore. */
  std::vector<bool> in_tree(graph.get_n(), false);
  //in_tree[seed] = true;

  /* Cream un vector de distante si il initializam cu distantele fata de seed. */
  int dist[graph.get_n()];
  int parent[graph.get_n()];
	int s = 0;
  for (unsigned int i = 0; i < graph.get_n(); ++i) {
    if (i != seed) {
      dist[i] = INF;
      parent[i] = seed;
    } else {
      dist[i] = 0;
      parent[i] = GraphAdjMat::NONE;
    }
	s ++;
  }

  /* TODO: Puneti in vectorul returnValue acele muchii care formeaza APM in urma
   * rularii algoritmului lui Prim din nodul sursa seed. */
	while (s)
	{
		int min = 10000;
		int poz;
		for (int i = 0; i < graph.get_n(); i++)
		{
			if (dist[i] < min && in_tree[i] == false)
			{
				min = dist[i];
				poz = i;
			}
		}
		in_tree[poz] = true;
		if (poz != seed)
			returnValue.push_back (Edge (poz, parent[poz], dist[poz]));
		for (int v = 0; v < graph.get_n();v++)
			if (graph.get_edge(poz, v) != graph.NONE)
			{
				if (!in_tree[v] && graph.get_edge(poz, v) < dist[v])
				{
					dist[v] = graph.get_edge(poz, v);
					parent[v] = poz;
				}
			}
		s--;
	}

  return returnValue;
}
bool myComp (Edge a, Edge b)
{
	return a.cost < b.cost;
}
std::vector<Edge> kruskall(GraphAdjMat& graph)
{
  std::vector<Edge> returnValue;

  /* Presupunem initial ca fiecare nod in graf este in propriul sau arbore. */
  DisjointSets tree_set(graph.get_n());
  std::vector<Edge> edges = graph.get_edges();
  
sort (edges.begin(), edges.end(), myComp);
  /* TODO: Puneti in vectorul returnValue acele muchii care formeaza APM in urma
   * rularii algoritmului lui Kruskall. */

	for (int i = 0; i < edges.size(); i++)
	{
		if (!tree_set.same_set (edges[i].u, edges[i].v))
		{
			tree_set.merge_sets_of (edges[i].u, edges[i].v);
			returnValue.push_back (edges[i]);
		}
	}
			

  return returnValue;
}

int main()
{
  /* Deschidem un fisier si citim din el un graf neorientat. */
  std::ifstream in("src-lab9/Graph.in");
  int n;
  in >> n;
  GraphAdjMat graph(n);
  in >> graph;

  std::cout << "Initial graph: " << std::endl << graph << std::endl;
  std::vector<Edge> edges;

  /* Calculam muchiile pe care le alege algoritmul lui Prim. */
  edges = prim(graph, 0);
  std::cout << "Algoritmul lui Prim construieste pornind la nodul 0"
      << " arborele format din urmatoarele muchii:" << std::endl;
  for (unsigned int i = 0; i < edges.size(); ++i) {
    std::cout << edges[i] << std::endl;
  }

  /* Calculam muchiile pe care le alege algoritmul lui Kruskall. */
  edges = kruskall(graph);
  std::cout << "Algoritmul lui Kruskall construieste o padure de arbori"
      << " formata din urmatoarele muchii:" << std::endl;
  for (unsigned int i = 0; i < edges.size(); ++i) {
    std::cout << edges[i] << std::endl;
  }

  return 0;
}


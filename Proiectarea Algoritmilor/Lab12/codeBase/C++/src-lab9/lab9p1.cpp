#include <iostream>
#include <fstream>
#include <algorithm>
#include <cmath>
#include <vector>

#include "GraphAdjMat.h"
#include "DisjointSets.h"

std::vector<Edge> prim(GraphAdjMat& graph, int seed)
{
  std::vector<Edge> returnValue;

  /* Initial, nici unul dintre noduri in afara de frunza nu apartine de
   * arbore. */
  std::vector<bool> in_tree(graph.get_n(), false);
  in_tree[seed] = true;

  /* Cream un vector de distante si il initializam cu distantele fata de seed. */
  int dist[graph.get_n()];
  int parent[graph.get_n()];

  for (unsigned int i = 0; i < graph.get_n(); ++i) {
    if (i != seed) {
      dist[i] = graph.get_edge(seed, i);
      parent[i] = seed;
    } else {
      dist[i] = 0;
      parent[i] = GraphAdjMat::NONE;
    }
  }

  /* De maxim (N - 1) ori incercam sa alegem un nod care se afla la distanta
   * minima fata de orice nod din arbore si sa il adaugam la arbore. */
  for (unsigned int i = 0; i < graph.get_n() - 1; ++i) {
    /* Gasim urmatorul nod care se afla la distanta minima fata de arbore. */
    int nodmin = GraphAdjMat::NONE;
    for (int j = 0; j < graph.get_n(); ++j) {
      if (dist[j] != GraphAdjMat::NONE && parent[j] != GraphAdjMat::NONE) {
        if (nodmin == GraphAdjMat::NONE || dist[nodmin] > dist[j]) {
          nodmin = j;
        }
      }
    }

    /* Daca nodul nu exista, ne oprim. */
    if (nodmin == GraphAdjMat::NONE) {
      break;
    }

    /* Altfel adaugam muchia (parent[nodmin], nodmin) la arbore si relaxam in
     * continuare muchiile care pleaca din nodmin. */
    returnValue.push_back(Edge(parent[nodmin],
                               nodmin,
                               graph.get_edge(parent[nodmin], nodmin)));
    parent[nodmin] = GraphAdjMat::NONE;

    for (int j = 0; j < graph.get_n(); ++j) {
      if (graph.get_edge(nodmin, j) != GraphAdjMat::NONE &&
          parent[j] != GraphAdjMat::NONE &&
          (dist[j] == GraphAdjMat::NONE ||
           dist[j] > graph.get_edge(nodmin, j))) {
        dist[j] = graph.get_edge(nodmin, j);
        parent[j] = nodmin;
      }
    }
  }

  return returnValue;
}

std::vector<Edge> kruskall(GraphAdjMat& graph)
{
  std::vector<Edge> returnValue;

  /* Presupunem initial ca fiecare nod in graf este in propriul sau arbore. */
  DisjointSets tree_set(graph.get_n());

  /* Vom sorta crescator dupa cost muchiile grafului. */
  std::vector<Edge> edges = graph.get_edges();
  std::sort(edges.begin(), edges.end());

  /* Cat timp arborele nu este gata si mai sunt muchii. */
  unsigned int i = 0;
  while (tree_set.number_of_disjoint_sets() > 1 && i < edges.size()) {
    /* Daca capetele muchiei sunt in arbori diferiti, foloseste muchia pentru a
     * uni arborii, altfel arunc-o. */
    if (!tree_set.same_set(edges[i].u, edges[i].v)) {
      returnValue.push_back(edges[i]);
      tree_set.merge_sets_of(edges[i].u, edges[i].v);
    }

    ++i;
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


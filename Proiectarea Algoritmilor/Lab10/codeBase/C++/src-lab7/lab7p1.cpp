#include <iostream>
#include <fstream>
#include <vector>

enum Color {
  WHITE,
  GRAY,
  BLACK
};

struct Node {
  unsigned int color;
  unsigned int component;
};

/* Matricea de adiacenta globala. */
namespace graph {

std::vector<std::vector<bool> > edge;
unsigned int size;
std::vector<Node> nodes;

};

void clearColor()
{
  for (unsigned int i = 0; i < graph::size; ++i) {
    graph::nodes[i].color = WHITE;
  }
}

void dfs(unsigned int node,
         bool directed,
         bool transposed,
         unsigned int component) {
  if (graph::nodes[node].color == WHITE) {
    /* Daca nodul nu a mai fost vizitat niciodata, atunci il facem gri. */
    graph::nodes[node].color = GRAY;
    graph::nodes[node].component = component;

    /* Il expandam corespunzator. */
    for (unsigned int i = 0; i < graph::size; ++i) {
      /* Tinem cont de cazul in care graful ar putea sa nu fie oritentat. */
      if (graph::edge[node][i] ||
          (directed == false && graph::edge[i][node])) {
        dfs(i, directed, component);
      }
    }

    /* Si incheiem si expansiunea. */
    graph::nodes[node].color = BLACK;
  } else {
    /* Nothing for now. */
  }
}

void paintComponents(std::vector<std::vector<unsigned int> >& components)
{
  clearColor();
  unsigned int component = 0;
  for (unsigned int i = 0; i < graph::size; ++i) {
    if (graph::nodes[i].color == WHITE) {
      dfs(i, false, false, component);
      component++;
    }
  }

  /* Populam vectorul de componente conexe cu nodurile din ele. */
  components = std::vector<std::vector<unsigned int> >(
      component, std::vector<unsigned int>());
  for (unsigned int i = 0; i < graph::size; ++i) {
    components[graph::nodes[i].component].push_back(i);
  }
}

void stronglyConnected(std::vector<std::vector<unsigned int> >& components)
{
  clearColor();
  unsigned int component = 0;
  for (unsigned int i = 0; i < graph::size; ++i) {
    if (graph::nodes[i].color == WHITE) {
      /* Facem o prima parcurgere pentru a depisa componenta nodului. */
      dfs(i, true, false, component);
      /* Apoi facem o parcurgere pe graful transpus din acelasi nod si le
       * intersectam. */
      clearColor();
      dfs(i, true, true, component);
    }
  }
}

/** Functie care incarca un graf dintr-un fisier daca se da numele fisierului.
 * Atentie, fisierul cu graful trebuie sa aiba sintaxa corecta. */
bool loadGraph(const char* fileName)
{
  std::ifstream in(fileName);
  if (!in.good()) {
    std::cout << "Error! Could not open graph file '" << fileName << "'."
        << std::endl;
    return false;
  }

  in >> graph::size;
  graph::edge = std::vector<std::vector<bool> >(
      graph::size, std::vector<bool>(graph::size, false));
  graph::nodes = std::vector<Node>(graph::size, Node());
  unsigned int edgeCount;
  in >> edgeCount;
  for (unsigned int i = 0; i < edgeCount; ++i) {
    unsigned int a, b;
    in >> a >> b;
    graph::edge[a][b] = true;
  }

  if (!in.good()) {
    std::cout << "Error. Bad graph file syntax." << std::endl;
    return false;
  }

  in.close();
  return true;
}

int main()
{
  /* Deschide fisierul de intrare si citeste graful. */
  if (!loadGraph("src-lab7/Graph.txt")) {
    return 0;
  }

  /* Calculam si afisam componentele conexe. */
  std::vector<std::vector<unsigned int> > components;
  paintComponents(components);
  std::cout << std::endl << "Componentele conexe ale grafului neorientat sunt: "
      << std::endl;
  for (unsigned int i = 0; i < components.size(); ++i) {
    std::cout << "Componenta " << i << ":";
    for (unsigned int j = 0; j < components[i].size(); ++j) {
      std::cout << " " << components[i][j];
    }
    std::cout << std::endl;
  }
  std::cout << std::endl;

  return 0;
}

#include <iostream>
#include <fstream>
#include <algorithm>
#include <cassert>
#include <set>
#include <queue>
#include <vector>

#include "Node.h"

class NodeComparator {
 public:
  /* Determina algoritmul folosit. */
  enum Algorithm {
    BestFirst,
    AStar,
    AStarOptimized
  };

  NodeComparator(Algorithm algorithm, const Node* solution_node)
      : solution_node_(solution_node),
      algorithm_(algorithm) { }

  int f(const Node* node) const {
    /* f(n) = g(n) + h(n) */
    /* g(n) = numarul de mutari din pozitia initiala */
    int f = node->distance();
    switch(algorithm_) {
      case BestFirst:
        /* TODO: Definiti functia f pentru BestFirst. */
	f = node->out_of_place (*solution_node_);
        //return 0;
      case AStar:
        /* TODO: Definiti functia f pentru o euristica A* admisibila simpla. */
        return 0;
      case AStarOptimized:
        /* TODO: Definiti functia f pentru o euristica A* mai apropiata de f*. */
        return 0;
    }
    return f;
  }

  bool operator() (const Node* node1, const Node* node2) const {
    return f(node1) > f(node2);
  }

 private:
  const Node* solution_node_;
  const Algorithm algorithm_;
};

bool is_explored(const std::vector<Node*>& closed, const Node& node) {
  /* TODO: Verificati daca nodul node este sau nu in multimea closed.
     ATENTIE la distinctia dintre stare si nod! */
	for (int i = 0; i < closed.size(); i++)
	{
		if (closed[i]->distance() == node.distance() && closed[i]->out_of_place(node) == 0)
			return true;
	}
	return false;
}

int Node::N;

int main() {
  /* Deschidem un fisier si citim din el configuratia initiala si finala. */
  std::ifstream in("src-lab11/Puzzle.in");

  /* Dimensiunea puzzel-ului este setata global, per clasa. */
  in >> Node::N;
  Node* initial_node = new Node();
  Node* solution_node = new Node();
  in >> *initial_node >> *solution_node;

  std::cout << *initial_node << std::endl;
  std::cout << *solution_node << std::endl;

  /* Pentru nodurile in curs de explorare, trebuie sa folosim o coada de
   * prioritati. */
  std::priority_queue<Node*, std::vector<Node*>, NodeComparator> open(
      NodeComparator(NodeComparator::BestFirst, solution_node));

  /* Initial doar nodul de start este in curs de explorare. */
  initial_node->set_distance(0);
  initial_node->set_parent(NULL);
  open.push(initial_node);

  /* Pentru nodurile care au fost deja expandate. */
  std::vector<Node*> closed;

  do {
    /* Daca multimea Open de noduri este goala, atunci se termina explorarea. */
    if (open.empty()) {
      std::cout << "Nu a fost gasita nicio solutie." << std::endl;
      break;
    }

    Node* next = open.top(); 
    open.pop();

    /* TODO: Verifica daca nodurl "next" este solutie. */
	if (next->has_same_state(*solution_node))
		break;

    /* TODO: Daca nodul nu a fost explorat, expandati-l. */
	if (!is_explored (closed, *next))
	{
		closed.push_back (next);
		std::vector<Node*> vec;
		next->expand (vec);
		for (int i = 0; i < vec.size(); i++)
		{
			vec[i]->set_distance(next->distance() + 1);
			vec[i]->set_parent(next);
			open.push (vec[i]);
		}
	}
  } while (true);
	
  /* Eliberare memoria folosită de noduri. */
  while (!open.empty()) {
    delete open.top();
    open.pop();
  }

  for (std::vector<Node*>::iterator it = closed.begin(); it != closed.end();
       ++it) {
    delete *it;
  }

  delete solution_node;

  return 0;
}

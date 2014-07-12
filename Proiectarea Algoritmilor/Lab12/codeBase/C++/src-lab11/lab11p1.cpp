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
    switch(algorithm_) {
      case BestFirst:
        /* g(n) = 0 */
        return node->approx_distance(*solution_node_);
      case AStar:
        /* h(n) = numarul de piese care nu se afla in pozitia corecta */
        return node->distance() + node->out_of_place(*solution_node_);
      case AStarOptimized:
        /* h(n) = suma distantelor intre pozitiile pieselor si pozitia corecta */
        return node->distance() + node->approx_distance(*solution_node_);
    }
    return 0;
  }

  bool operator() (const Node* node1, const Node* node2) const {
    return f(node1) > f(node2);
  }

 private:
  const Node* solution_node_;
  const Algorithm algorithm_;
};

bool is_explored(const std::vector<Node*>& closed, const Node& node) {
  for (std::vector<Node*>::const_iterator it = closed.begin();
       it != closed.end();
       ++it) {
    if (node.has_same_state(**it)) {
      return true;
    }
  }
  return false;
}

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

  /* Pentru nodurile in curs de explorare, implementate ca o coada de
   * prioritati. */
  std::priority_queue<Node*, std::vector<Node*>, NodeComparator> open(
      NodeComparator(NodeComparator::AStarOptimized, solution_node));

  /* Initial doar nodul de start este in curs de explorare. */
  initial_node->set_distance(0);
  initial_node->set_parent(NULL);
  open.push(initial_node);

  /* Pentru nodurile care au fost deja expandate. */
  std::vector<Node*> closed;

  do {
    if (open.empty()) {
      std::cout << "Nu a fost gasita nicio solutie." << std::endl;
      break;
    }

    Node* next = open.top(); 
    open.pop();

    if (next->has_same_state(*solution_node)) {
      std::cout << "A fost gasita solutia:" << std::endl;
      next->print_path();
      delete next;
      break;
    }

    /* Se exploreaza doar nodurile care nu au fost explorate deja. */
    if (!is_explored(closed, *next)) {
      /* Explorarea nodului este finalizata. */
      closed.push_back(next);

      /* Lista vecinilor. */
      std::vector<Node*> expand_next;
      next->expand(expand_next);
      for (std::vector<Node*>::iterator it = expand_next.begin();
           it != expand_next.end();
           ++it) {
        open.push(*it);
      }
    } else {
      delete next;
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

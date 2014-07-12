#ifndef _NODE_H_
#define _NODE_H_

#include <iostream>
#include <vector>

#define ABS(a) ((a) < 0 ? (-(a)) : (a))

class Node {
 public:
  static int N;

  Node() : distance_(0), parent_(NULL) { }

  friend std::ostream& operator<< (std::ostream& out, const Node& node);
  friend std::istream& operator>> (std::istream& in, Node& node);

  /* Verifica data 2 noduri reprezinta aceeasi stare. */
  bool has_same_state(const Node& other) const;

  /* Distanta de la starea initiala. */
  int distance() const { return distance_; }
  void set_distance(int distance) { distance_ = distance; }

  /* Nodul prin care s-a ajuns in aceasta stare. */
  const Node* parent() const { return parent_; }
  void set_parent(const Node* parent) { parent_ = parent; }

  /* Intoarce numarul de piese care n use afla la locul lor. */
  int out_of_place(const Node& other) const;

  /* Intoarce estimarea distantei dintre 2 noduri ca suma a diferentei
   * pozitiilor. */
  int approx_distance(const Node& other) const;

  /* Lista vecinilor. */
  void expand(std::vector<Node*>& expanded) const;

  /* Afiseaza toti parintii nodului, in ordinea parcurgerii. */
  void print_path() const;

 private:
  /* Returneaza locul in care se afla cifra x in grid. */
  std::pair<int, int> get_position(int x) const;

  /* Creaza un nou nod din nodul curent interschimband continutul celor 2
   * pozitii. */
  Node* create_node(int i, int j, int next_i, int next_j) const;

  /* Helper pentru afisare. Intoarce numarul de mutari. */ 
  static int print_partial_path(const Node* node);

  /* Starea pe care o reprezinta nodul. */
  std::vector<std::vector<int> > grid_;

  /* Distanta de la nodul sursa in explorare. */
  int distance_;

  /* Nodul parinte in explorare. NULL pentru nodul sursa. */
  const Node* parent_;
};

/* I/O operators. */
std::ostream& operator<< (std::ostream& out, const Node& node);
std::istream& operator>> (std::istream& in, Node& node);

#endif  /* _NODE_H_ */

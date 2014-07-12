#include <iostream>
#include <algorithm>
#include <fstream>
#include <map>
#include <queue>
#include <string>
#include <cstring>
#include <vector>

#define MIN(a, b) ((a) < (b) ? (a) : (b))

enum Color {
  WHITE,
  GRAY,
  BLACK
};

struct Node {
  Color color;
  unsigned int finishTime;
  std::string subjectName;
  unsigned int inDegree;

  std::vector<Node*> neigh;

  Node(std::string subjectName) : subjectName(subjectName), inDegree(0) { }
};

bool compareNodePointersByFinishTime(const Node* left, const Node* right)
{
  return left->finishTime > right->finishTime;
}

typedef std::vector<Node*> Graph;

std::map<std::string, unsigned int> subjectToInt;

void dfs(Node* node, unsigned int& time)
{
  /* Daca nodul nu este alb, nu il expandam. */
  if (node->color != WHITE) {
    return;
  }

  /* Facem nodul gri si incepem sa-l expandam. */
  node->color = GRAY;
  time++;
  for (unsigned int i = 0; i < node->neigh.size(); ++i) {
    dfs(node->neigh[i], time);
  }

  /* Notam timpul la care s-a terminat expandarea nodului curent. */
  time++;
  node->finishTime = time;
}

void topologicalSorting(Graph& graph)
{
  /* Facem toate nodurile albe. */
  for (unsigned int i = 0; i < graph.size(); ++i) {
    graph[i]->color = WHITE;
  }

  /* Pornim DFS din noduri. */
  unsigned int time = 0;
  for (unsigned int i = 0; i < graph.size(); ++i) {
    if (graph[i]->color == WHITE) {
      dfs(graph[i], time);
    }
  }

  /* Si acum vom sorta descrescator dupa timpul de finish pentru a obtine o
   * ordonare topologica. */
  std::sort(graph.begin(), graph.end(), compareNodePointersByFinishTime);
}

void planYears(Graph& graph,
               std::vector<std::vector<std::string> >& yearlyPlanning)
{
  std::queue<Node*> q;

  /* Intai facem o parcugere si punem intr-o coada toate nodurile care au
   * indegree-ul 0. */
  for (unsigned int i = 0; i < graph.size(); ++i) {
    if (graph[i]->inDegree == 0) {
      q.push(graph[i]);
    }
  }

  /* Cat timp mai sunt in coada materii care pot fi facute, incearca sa le
   * planifici. */
  while (!q.empty()) {
    /* Vom planifica maxim 5 materii, in limita materiilor ce pot fi facute in
     * acest an (nu trebuie ca in acelasi semestru sa avem materii care
     * depind una de alta. */
    std::vector<std::string> year;
    unsigned int yearSubjectCount = MIN(q.size(), 5);

    /* Le planificam efectiv, si daca cumva prin parcurgerea lor si alte materii
     * devin disponibile, atunci le adaugam si pe acelea in coada pentru viitor.
     */
    for (unsigned int i = 0; i < yearSubjectCount; ++i) {
      Node* node = q.front();
      year.push_back(node->subjectName);
      /* Scadem gradul intern al materiilor care depindeau de aceasta materie cu
       * o unitate. */
      for (unsigned int j = 0; j < node->neigh.size(); ++j) {
        node->neigh[j]->inDegree--;
        /* Daca materia a ajuns independenta, o punem in coada. */
        if (node->neigh[j]->inDegree == 0) {
          q.push(node->neigh[j]);
        }
      }
      q.pop();
    }

    /* Si adaugam anul curent in planificarea anuala. */
    yearlyPlanning.push_back(year);
  }
}

int main()
{
  /* Declaram un graf. */
  Graph graph;

  /* Citim un graf din fisierul de intrare. */
  std::ifstream in("src-lab6/Materii.txt");
  unsigned int edgeCount;
  in >> edgeCount;

  std::string subjectA, separator, subjectB;
  for (unsigned int i = 0; i < edgeCount; ++i) {
    in >> subjectA >> separator >> subjectB;

    /* Daca n-am mai vazut niciodata subjectA, atunci creaza-i un cod unic. */
    if (subjectToInt.count(subjectA) == 0) {
      unsigned int code = subjectToInt.size();
      subjectToInt[subjectA] = code;
      graph.push_back(new Node(subjectA));
    }

    /* La fel facem si cu subjectB. */
    if (subjectToInt.count(subjectB) == 0) {
      unsigned int code = subjectToInt.size();
      subjectToInt[subjectB] = code;
      graph.push_back(new Node(subjectB));
    }

    /* Adaugam subjectB ca vecin al lui subjectA. */
    graph[subjectToInt[subjectA]]->neigh.push_back(
        graph[subjectToInt[subjectB]]);
    graph[subjectToInt[subjectB]]->inDegree++;
  }

  topologicalSorting(graph);

  std::cout << "O posibila sortare topologica este: " << std::endl;
  for (unsigned int i = 0; i < graph.size(); ++i) {
    std::cout << "\t" << graph[i]->subjectName << std::endl;
  }

  /* Facem impartirea pe ani. */
  std::vector<std::vector<std::string> > yearlyPlanning;
  planYears(graph, yearlyPlanning);

  std::cout << std::endl << "Iar o impartire pe ani ar putea fi urmatoarea: "
      << std::endl;
  for (unsigned int i = 0; i < yearlyPlanning.size(); ++i) {
    std::cout << "Anul " << (i + 1) << std::endl;
    std::vector<std::string>& subjects = yearlyPlanning[i];
    for (unsigned int j = 0; j < subjects.size(); ++j) {
      std::cout << "\t" << subjects[j] << std::endl;
    }
  }

  return 0;
}


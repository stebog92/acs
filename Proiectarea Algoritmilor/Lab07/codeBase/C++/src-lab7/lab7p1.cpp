#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

enum Color {
  WHITE,
  GRAY,
  BLACK
};

struct Node {
  unsigned int color;
  unsigned int component;
	bool onStack;
	bool art;
	std::vector<unsigned int> copii;
	int time;
	bool art2;
	int parent;
  /* TODO: Adaugat aici tot ce vi se pare util. */
};

/* Matricea de adiacenta globala. */
namespace graph {

/* In C++, variabilele dintr-un namespace se pot accesa prefixand numele
 * namespace-ului numelui variabilelelor (asta ca sa nu fie globale si sa intre
 * in conflict cu alte variabile exportate). De exemplu:
 *
 * graph::size
 */
std::vector<std::vector<bool> > edge;
unsigned int size;
std::vector<Node> nodes;

};
int t;

/* Functie care face toate nodurile albe. */
void clearColor()
{
  for (unsigned int i = 0; i < graph::size; ++i) {
    graph::nodes[i].color = WHITE;
  }
}

void dfs(unsigned int node, std::vector<unsigned int> &component, bool trans
         /* TODO: Adaugati orice parametri vi se par utili/relevanti. */)
{
  /* TODO: Implementati un DFS (obs: Puteti implementa unul generic pe care sa
   * il refolositi la toate subpunctele. Va trebui insa sa dati parametri
   * speciali care sa tine cont daca se face DFS pe graful
   * neorientat/transpus) */
	if (trans == false)
	{
		graph::nodes[node].color = GRAY;
		graph::nodes[node].onStack = true;
		//component.push_back (node);
		for (int i = 0; i < graph::nodes.size(); i++)
		{
			if (graph::nodes[i].color == WHITE && graph::edge[node][i] == true)
			{
				dfs (i, component, false);
			}

		}
		component.push_back (node);
		graph::nodes[node].color = BLACK;
	}
	else
	{
		graph::nodes[node].color = GRAY;
		//std::cout << node << " ";
	/*	for (int i = 0; i < component.size(); i++)
		{
			if (component[i] == node)
				component.erase(component.begin() + i);
		}
	*/	for (int i = 0; i < graph::nodes.size(); i++)
		{
			if (graph::nodes[i].color == WHITE && graph::edge[i][node] == true)
			{
				dfs (i, component, true);
			}
		}
		for (int i = 0; i < component.size(); i++)
		{
			if (component[i] == node)
				component.erase(component.begin() + i);
		}
		std::cout << node << " ";

		graph::nodes[node].color = BLACK;
	}
}

void paintComponents(std::vector<std::vector<unsigned int> >& components)
{
  /* TODO 1: Implementati aici detectarea componentelor din graful reunit cu graful
   * transpus (altfel spus, graful daca ar fi fost neorientat).
   *
   * Trebuie ca in vectorul de components sa puneti cate un vector pentru
   * fiecare componenta conexa detectata, acel vector continand ID-urile
   * nodurilor din componenta. */
	int k = 0;
	clearColor();
	for (int i = 0; i < graph::nodes.size(); i++)
	{
		if (graph::nodes[i].color == WHITE)
		{
			std::vector<unsigned int> temp;
			dfs (i, temp, false);
			components.push_back (temp);
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
std::vector<int> low;
void dfs2 (int node)
{
	graph::nodes[node].time = t;
	graph::nodes[node].art = true;
	//graph::nodes[node].color = GRAY;
	t++;
	low[node] = t - 1;
	for (int i = 0; i < graph::size; i++)
	{
		if (graph::edge[node][i] == true) //&& graph::nodes[i].color == WHITE)
	//	{
			if (!graph::nodes[i].art)
			{	
				graph::nodes[i].parent = node;
				graph::nodes[node].copii.push_back(i);
				dfs2 (i);
				if (low[i] >= graph::nodes[node].time)
					std::cout << node << " ";
				low[node] = std::min (low[i], low[node]);
			}
			else
			if (graph::nodes[node].parent != i)
				low[node] = std::min (low[i], low[node]);
	//	}
	}
	/*if (node == 0 && graph::nodes[node].copii.size() >= 2)
		std::cout << node << " ";
	
	else
	{
		for (int j = 0; j < graph::nodes[node].copii.size(); j++)
		{
			if (low[graph::nodes[node].copii[j]] >= graph::nodes[node].time)
			{
				std::cout << node << " ";
				break;
			}
		}
	}
	//graph::nodes[node].art = true;
	//graph::nodes[node].color = BLACK;
	*/
}

void dfs3 (int node)
{
	graph::nodes[node].time = t;
	graph::nodes[node].color = GRAY;
	t++;
	low[node] = t - 1;
	
	for (int i = 0; i < graph::size; i++)
	{
		if (graph::nodes[i].color == WHITE && graph::edge[node][i])
		{
		bool ok = true;
		for (int j = 0; j < graph::size; j++)
			if (graph::edge[node][j])
			{
				ok = false;
				break;
			}
		if (ok)
		{
			if (!graph::nodes[i].art2)
			{
				dfs3(i);
				low[node] = std::min (low[node], low[i]);
				if (low[i] > graph::nodes[node].time)
				{
					std::cout << node << " " << i << std::endl;
				}
			}
			else
				low[node] = std::min(low[node], low[i]);
		}
		}
	}
	graph::nodes[node].art2 = true;
	graph::nodes[node].color = BLACK;
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

  /* TODO 2: Calculati si afisati componentele tare conexe. */
	std::vector<unsigned int> s;
	clearColor();
	for (int i = 0; i < graph::size; i++)
		graph::nodes[i].onStack = false;
	while (s.size() != graph::size)
	{
		for (int i = 0; i < graph::nodes.size(); i++)
		{
			if (graph::nodes[i].color == WHITE &&  !graph::nodes[i].onStack)
			{
				dfs (i, s, false);
			}
		}
	}
	clearColor();
	int k = 0;
	while (!s.empty())
	{
		int x = s.back();
		s.pop_back();
		std::cout << "Componenta Tare Conexe  "<< k++ << " : " ;
		dfs (x, s, true);
		std::cout << "\n";
	}
	

  /* TODO 3: Calculati si afisati nodurile critice. */
	low = std::vector<int> (graph::size, -1);
	std::cout << "Puncte Critice : " ;
	clearColor();
	for (int i = 0; i < graph::size; i++)
		graph::nodes[i].art = false;
	for (int i = 0; i < graph::size; i++)
	{
		if (!graph::nodes[i].art)
		{
			//clearColor();
			graph::nodes[i].parent = -1;
			dfs2 (i);
		}
	}
	std :: cout << "\n";
		

  /* TODO 4: Calculati si afisati puntile. */
	t = 0;
	std::cout << "Punti : " ;
	clearColor();
	low = std::vector<int>(graph::size, -1);
	for (int i = 0; i < graph::size; i++)
	{
		graph::nodes[i].art2 = false;
	}
	for(int i = 0; i < graph::size; i++)
	{
		if (!graph::nodes[i].art2)
		{
			dfs3(i);
		}
	}

  /* TODO 5: Calculati si afisati o strategie de pavare. */

  return 0;
}

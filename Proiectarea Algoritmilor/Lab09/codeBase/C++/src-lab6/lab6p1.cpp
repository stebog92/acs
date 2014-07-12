#include <iostream>
#include <fstream>
#include <queue>

#include "Maze.h"

typedef std::pair<int, int> Coord;
const Coord NOWHERE(-1, -1);

const int dirLin[4] = { -1, 0, 1, 0 };
const int dirCol[4] = { 0, 1, 0, -1 };

void find_exit(Maze& maze, Coord source)
{
  /* Pentru a reconstitui drumul, vom folosi o matrice de parinti. */
  Coord parent[maze.get_height()][maze.get_width()];
  for (unsigned int i = 0; i < maze.get_height(); ++i) {
    for (unsigned int j = 0; j < maze.get_width(); ++j) {
      parent[i][j] = NOWHERE;
    }
  }

  /* Vom folosi BFS pentru a determina drumul optim. Cream o coada pe care o
   * intializam cu prima celula. */
  std::queue<Coord> q;
  q.push(source);
  parent[source.first][source.second] = source;

  while (!q.empty()) {
    Coord pos = q.front();
    q.pop();

    for (unsigned int dir = 0; dir < 4; ++dir) {
      Coord newPos(pos.first + dirLin[dir], pos.second + dirCol[dir]);
      /* Verificam ca nou pozitie pe care o incercam este o pozitie valida de pe
       * harta in care se poate muta. */
      if (maze.is_walkable(newPos)) {
        if (maze.is_exit_point(newPos)) {
          parent[newPos.first][newPos.second] = pos;
          /* Tiparim solutia si iesim. */
          do {
            maze.mark_solution_step(newPos);
            newPos = parent[newPos.first][newPos.second];
          } while (newPos != source);
          maze.mark_solution_step(source);
          return;
        } else if (parent[newPos.first][newPos.second] == NOWHERE) {
          /* Nu adaugam noua pozitie in coada decat daca nu se afla deja. */
          parent[newPos.first][newPos.second] = pos;
          q.push(newPos);
        }
      }
    }
  }
}

int main()
{
  /* Citim o harta din fisierul de intrare. */
  std::ifstream in("src-lab6/Labirint.txt");
  Maze maze;
  unsigned int lineTrudy, columnTrudy;
  in >> maze >> lineTrudy >> columnTrudy;

  /* Calculam pe ea drumul din labirint. */
  find_exit(maze, Coord(lineTrudy, columnTrudy));

  /* Si afisam drumul final. */
  std::cout << "Labirintul cu drumul marcat spre iesire este: " << std::endl
      << maze;

  return 0;
}


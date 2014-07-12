#include <iostream>
#include <fstream>
#include <ctime>
#include <cstdlib>
#include <algorithm>

#include "Maze.h"

const int vi[4] = { -1, 0, 1, 0 };
const int vj[4] = { 0, 1, 0, -1 };

void dig_out_maze(Maze& maze, int line, int column) {
  int directions[4] = { 0, 1, 2, 3 };

  /* Amestecam directiile din vector. */
  std::random_shuffle(directions, directions + 4);

  for (unsigned int dir = 0; dir < 4; ++dir) {
    int newline = line + vi[dir];
    int newcolumn = column + vj[dir];
    if (newline >= 0 && newcolumn >= 0 &&
        newline < maze.get_height() && newcolumn < maze.get_width() &&
        !maze.is_walkable(newline, newcolumn) &&
        !maze.is_corner(newline, newcolumn)) {
      maze.mark_solution_step(newline, newcolumn);
      dig_out_maze(maze, newline, newcolumn);
    }
  }
}

void dig_out_maze(Maze& maze)
{
  /* Initializam un generator de numere aleatoare. */
  srand(time(NULL));

  /* Oservatie: Indiferent de la ce algoritm porniti cu ideea, urmariti sa nu
   * stricati "colturile" din labirint.
   *
   *    ####@ @## #
   *    ##     @@ #
   *    #####@    #
   *    ###########
   *
   * Zidurile notate cu "@" nu trebuie daramate, ca sa nu creati camere 2x2 din
   * coridoare. Am vrea ca in final sa obtinem un labirint "sculptat" aleator,
   * cu coridoare de latime 1 si fara camere patratice.
   */

  /* Fie o celula random de pe perimetru de la care sa incepem, de exemplu
   * (0, 1). */
  dig_out_maze(maze, 0, 1);
}

int main()
{
  int width, height;

  /* Citeste dimensiunile labirintului si creaza o parcela de pamant de
   * dimensiunile respective. */
  std::cin >> width >> height;
  Maze maze(width, height);

  /* Sapa un labirint din respectiva parcela de pamant. */
  dig_out_maze(maze);

  /* Si afiseaza-l. */
  std::cout << "The resulting maze is:" << std::endl << maze;
  return 0;
}


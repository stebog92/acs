#include <iostream>
#include <fstream>
#include <ctime>
#include <cstdlib>
#include <algorithm>

#include "Maze.h"

const int vi[4] = { -1, 0, 1, 0 };
const int vj[4] = { 0, 1, 0, -1 };

void dig_out_maze(Maze& maze, int line, int column) {
  /* TODO: Sapati galerii prin maze astfel incat:
   * (a) orice celula dintr-o galerie sa aiba acces la exterior.
   * (b) galeriile sa fie "inguste" (adica sa nu se formeze camere de 2x2).
   */
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


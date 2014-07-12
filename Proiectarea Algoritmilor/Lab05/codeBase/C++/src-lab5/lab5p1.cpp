#include <iostream>
#include <fstream>
#include <vector>

#include "XOBoard.h"

#define INF 8

#define OPPONENT(x) ((x) == XOBoard::PlayerX ? \
                     XOBoard::PlayerO : XOBoard::PlayerX)

/** Un exemplu de functie de gandire care foloseste rezultatele furnizate de
 * o abordare negaMax exhaustiva. */
XOBoard negaMaxThink(XOBoard::Player player, XOBoard board)
{
  /* TODO: Fa o miscare si returneaza tabla dupa aceasta miscare. Aceasta
   * functie de AI trebuie sa respecte acest format pentru ca este data in
   * constructorul jocului, dar puteti apela aici o functie scrisa de voi. */

  /* TODO: Stergeti linia de mai jos dupa ce rezolvati. */
  return board;
}

int main()
{
  /** Cream un engine de joc si specificam ca am vrea ca AI-ul sa gandeasca
   * cu functia data ca parametru (o functie de tipul:
   *       XOBoard ComputerAI(XOBoard::Player, XOBoard)).
   *
   * Engine-ul de joc va apela intern aceasta functie atunci cand pune
   * calculatorul sa gandeasca. Parametrii au urmatoarea semnificatie:
   *
   * player = jucatorul care trebuie sa mute in continuare (identitatea
   *          calculatorului care gandeste cu aceasta functie).
   *           Valorile posibile sunt { XOBoard::PlayerX, XOBoard::PlayerO }
   *
   * board = tabla pe care o vede jucatorul care trebuie sa mute in
   *         continuare.
   */
  XOGame game(negaMaxThink);

  /** Lansam un joc intre un om (joaca cu X) si un calculator (joaca cu O) 
   * Puteti lansa si computerVShuman, humanVShuman, dar si computerVScomputer.
   * Mai multe detalii in documentatie.
   * (codeBase/C++/doc/html/index.html -> classes)
   */
  game.humanVScomputer();

  return 0;
}


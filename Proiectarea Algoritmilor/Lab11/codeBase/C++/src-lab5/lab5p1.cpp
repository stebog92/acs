#include <iostream>
#include <fstream>
#include <vector>

#include "XOBoard.h"

#define INF 8

#define OPPONENT(x) ((x) == XOBoard::PlayerX ? \
                     XOBoard::PlayerO : XOBoard::PlayerX)

/** Puteti folosi functia negaMax pentru a implementa un AI pe baza de negaMAX.
 *
 * Functia primeste ca parametri:
 * 
 * player = Jucatorul care trebuie sa mute in continuare (identitatea
 *          calculatorului care gandeste cu aceasta functie).
 *           Valorile posibile sunt { XOBoard::PlayerX, XOBoard::PlayerO }
 *
 * board = Tabla pe care o vede jucatorul care trebuie sa mute in continuare.
 *
 * alpha = Inseamna ca player a gasit deja o cale prin care pot sa termin
 *         jocul cu un scor cel putin egal cu alpha.
 *
 * beta = Inseamna ca OPPONENT(player) a gasit o cale prin care sa-l forteze pe
 *        player sa termine jocul cu un scor cel mult egal cu beta (cu alte
 *        cuvinte daca player gaseste o modalitate sa castige mai mult de beta,
 *        cel mai probabil analizeaza un scenariu nerealist in care a presupus
 *        ca OPPONENT(player) a fost prost la un moment dat si a facut o
 *        greseala.
 */
std::pair<int, XOBoard> negaMax(XOBoard::Player player,
                                XOBoard board,
                                int alpha,
                                int beta)
{
  /* Daca s-a terminat jocul, scorul este cel raportat. */
  if (board.game_over()) {
    int myScore = board.get_score(player) - board.get_score(OPPONENT(player));
    return std::pair<int, XOBoard>(myScore, board);
  }

  /* Generam lista de expansiuni ale tablei (toate mutarile viitoare). */
  std::vector<XOBoard> expansions;
  for (unsigned int i = 0; i < 3; ++i) {
    for (unsigned int j = 0; j < 3; ++j) {
      if (board.get(i, j) == '_') {
        board.put(player, i, j);
        expansions.push_back(board);
        board.erase(i, j);
      }
    }
  }

  /* Verificam care este mutarea cea mai inteleapta. */
  XOBoard nextMove;
  for (unsigned int i = 0; i < expansions.size(); ++i) {
    /* Fiindca urmatorul nivel de negaMax este privit din partea oponentului,
     * cand apelez functia trebuie sa neg pe alfa si sa i-l servesc drept
     * beta pentru ca asta inseamna ca il "avertizez" ca nu sunt fraier si ca
     * deja stiu un mod prin care el nu poate sa faca mai mult decat -alpha.
     *
     * Pe de alta parte, desi eu il limitez pe el superior, din punct de vedere
     * inferior nu am nici un motiv sa-l limitez, asa ca ii voi servi un alpha
     * egal cu -INF (nu stiu cat de prost poate el sa joace, n-am cum sa-mi dau
     * seama).
     */

    /* Acum ne gandim cum s-ar descurca el in situatia asta. */
    std::pair<int, XOBoard> outcome = negaMax(
        OPPONENT(player), expansions[i], -INF, -alpha);

    /* Vedem ce miscare a reusit sa scoata el in conditiile date. */
    int myScore = -outcome.first;

    /* Analizam jocul din perspectiva taierii alfa-beta. */
    if (myScore > beta) {
      /* Inseamna ca asta e un scenariu in care el ar fi facut o greseala. Noi
       * stim ca el nu e prost, asa ca din moment ce a gasit deja mai sus in
       * arbore o modalitate prin care sa ma faca sa termin jocul cu cel mult
       * beta, n-o sa joace in asa fel incat sa ma puna pe mine in situatia
       * asta de acum. Aplicam deci, taierea beta. */
      return std::pair<int, XOBoard>(beta, nextMove);
    } else if (myScore > alpha) {
      /* Inseamna ca tocmai am gasit o miscare prin care eu sa castig la sigur
       * mai mult decat stiam inainte ca pot sa castig (daca vreti, un fel de
       * plan "la sigur" mai bun).
       */
      alpha = myScore;
      nextMove = expansions[i];
    }
  }

  /* Raportam mutarea aleasa ca fiind cea mai buna. */
  return std::pair<int, XOBoard>(alpha, nextMove);
}

/** Un exemplu de functie de gandire care foloseste rezultatele furnizate de
 * o abordare negaMax exhaustiva. */
XOBoard negaMaxThink(XOBoard::Player player, XOBoard board)
{
  /* La inceput, consideram alpha -8 si beta 8. Cu alte cuvinte, daca tabla ar
   * fi plina de marcajele oponentului, el ar castiga 3 linii, 3 coloane si 2
   * diagonale (deci eu n-am cum sa fac mai rau de -8). Similar, din ce stiu eu
   * pana acum, oponentul n-are cum sa ma forteze sa joc in vreun fel, deci
   * consider ca as putea sa castig, ipotetic, pana la 8 (beta).
   */
  return (negaMax(player, board, -INF, +INF)).second;
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


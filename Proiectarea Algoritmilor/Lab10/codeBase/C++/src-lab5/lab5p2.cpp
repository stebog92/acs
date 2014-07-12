#include <iostream>

#include "Nim.h"

#define UNDEF -1

/* In cazul acestui joc, scorurile sunt binare: 0/1 sau WIN/LOSE. Cu alte
 * cuvinte, nu exista un scor anume care sa fie castigat, ci doar intreg jocul.
 * Astfel, algoritmul de taiere alfa-beta se reduce la un caz particular:
 *
 * Daca oponentul pierde, atunci mutarea respectiva duce sigur la castig si nu
 * mai are sens sa expandam in continuare arborele (putem taia toti subarborii
 * care urmeaza in expansiune).
 *
 * In continuare am dat o implementare de tipul nega-max care include si
 * prunning-ul alfa-beta.
 */
int negaMax(NimGameConf& game, int depth) {
  /* Daca s-a terminat jocul, atunci scorul este 0. */
  if (game.gameOver()) {
    return 0;
  }

  /* Alegem o configuratie in care celalalt sa aiba solutie de pierdere. */
  for (unsigned int i = 3; i <= game.size(); ++i) {
    if (game[i] > 0) {
      /* Daca se poate sparge gramada asta, atunci incercam sa o spargem. */
      for (unsigned int j = 1; j <= i/2; ++j) {
        /* Aflam scorul pe care l-ar obtine inamicul dupa aceasta spargere. */
        game.split(i, j, i - j);
        int opponentScore = negaMax(game, depth + 1);
        /* Daca el obtine 0, inseamna ca noi am castigat si ne putem opri. */
        if (opponentScore == 0) {
          /* Optimizarea alfa-beta: deoarece am gasit o miscare prin care
           * jucatorul advers va pierde cu siguranta, nu mai are sens sa cautam
           * mai departe.
           *
           * Mai mult, daca adancimea este 0, nu mai facem "undo" miscarii,
           * pentru ca de fapt asta este miscarea pe care o efectueaza AI-ul.
           * Daca in schimb adancimea este > 0, atunci trebuie sa ii facem
           * "undo" pentru ca noi predam inapoi tabla de joc pe care sa se afle
           * doar urmatoarea mutare, nu mai multe.
           */
          if (depth) {
            game.unsplit(i, j, i - j);
          }
          return 1;
        } else {
          /* Altfel, retragem miscarea si incercam cu altceva. */
          game.unsplit(i, j, i - j);
        }
      }
    }
  }

  /* Inseamna ca nu am gasit strategie sigura de castig.  */
  if (depth == 0) {
    /* Daca adancimea este 0, inseamna ca trebuie sa lasam tabla de joc cu o
     * mutare efectuata pe ea. Fiindca nu am gasit strategie sigura de castig,
     * putem alege orice mutare (prima posibila), pentru ca oricum toate duc
     * sigur la pierdere. */
    for (unsigned int i = 3; i <= game.size(); ++i) {
      if (game[i] > 0) {
        game.split(i, 1, i - 1);
        return 0;
      }
    }
    std::cerr << "Trouble!" << std::endl;
  } else {
    /* Adancimea nefiind 0, nu trebuie sa facem o miscare. Pur si simplu
     * raportam ca pe ramura asta de joc se pierde intotdeauna. */
    return 0;
  }
}

void computerPlayer(NimGameConf& game) {
  std::cout << game << "Computer starts thinking... ";
  negaMax(game, 0);
  std::cout << "done." << std::endl;
}

void humanPlayer(NimGameConf& game) {
  std::cout << game << "Alege o gramada de impartit, precum si dimensiunile"
      << " in care vrei sa spargi gramada: ";
  int heap, a, b;
  do {
    /* Citim o miscare de la consola. */
    std::cin >> heap >> a >> b;
    if (game[heap] == 0) {
      /* Verificam sa existe o gramada de atatea pietricele. */
      std::cout << "Error! Nu exista nici o gramada de " << heap
          << " pietricele. Try again: ";
    } else if (heap != a + b || a <= 0 || b <= 0) {
      /* Verificam ca miscarea sa fie valida. */
      std::cout << "Error! O gramada de " << heap << " pietricele nu se "
          << "poate imparti in " << a << " + " << b << " pietricele."
          << std::endl << "Try again: ";
    } else if (heap >= 0 && heap <= 2) {
      /* Alta verificare de miscare valida. */
      std::cout << "Error! Nu se poate imparti o gramada de " << heap
          << " pietricele in gramezi mai mici. Try again: ";
    } else {
      /* Efectuam miscarea si iesim din functie. */
      game.split(heap, a, b);
      return;
    }
  } while(1);
}

int main()
{
  /* Citim un numar initial de pietricele si pornim un joc. */
  int n;
  std::cout << "Game size: ";
  std::cin >> n;
  NimGameConf game(n);

  do {
    /* First player takes a move. */
    computerPlayer(game);
    std::cout << std::endl;
    if (game.gameOver()) {
      std::cout << std::endl << "Player 1 wins!" << std::endl;
      break;
    }

    /* Second player takes a move. */
    humanPlayer(game);
    std::cout << std::endl;
    if (game.gameOver()) {
      std::cout << std::endl << "Player 2 wins!" << std::endl;
      break;
    }
  } while(1);

  return 0;
}


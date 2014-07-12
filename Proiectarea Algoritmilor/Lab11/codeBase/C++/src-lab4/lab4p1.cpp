#include <iostream>
#include <fstream>
#include <cstdio>
#include <cstring>
#include <queue>
#include <sys/time.h>
#include <map>

#include "VectorIO.h"
#include "PairIO.h"
#include "MapIO.h"

#include "Rebus.h"

/* Vom modela o tabela de variable ca fiind un map de la o coordonata (i, j)
 * la o multime de cuvinte care ar putea ipotetic sa fie introduse in Rebus
 * incepand cu prima litera de la (i, j), fie in pozitie orizontala (spre
 * dreapta), fie in pozitie verticala (spre in jos). */
typedef std::pair<int, int> Position;
typedef std::map<Position, std::vector<std::string> > Domains;

/* Numarul de intrari in recursivitate (pentru evaluarea performantelor). */
unsigned int recursions = 0;

/* Cuvintele disponibile in dictionar (ca vector de stringuri). */
std::vector<std::string> vocabular;

/* Obiectul Rebus in sine. Are doar cateva metode ajutatoare gata scrise,
 * verificati documentatia pentru detalii. */
Rebus rebus;

/* Functie care umple cele doua tabele de restrictii, orizontala sau verticala
 * cu cuvinte care ar putea, ipotetic, sa fie completate in Rebus. */
void build_word_sets(Domains& horizontal, Domains& vertical)
{
  /* Si completam. */
  for (unsigned int i = 0; i < rebus.rows; ++i) {
    for (unsigned int j = 0; j < rebus.columns; ++j) {
      if (rebus.is_empty(i, j) &&
          (j == 0 || rebus.is_empty(i, j - 1) == false)) {
        /* Completam orizontal. */
        int span = 0;
        while (j + span < rebus.columns && rebus.is_empty(i, j + span)) {
          ++span;
        }

        /* Adaugam toate cuvintele de span litere aici. */
        for (unsigned int index = 0; index < vocabular.size(); ++index) {
          if (vocabular[index].length() == span) {
            horizontal[Position(i, j)].push_back(vocabular[index]);
          }
        }
      }
      if (rebus.is_empty(i, j) &&
          (i == 0 || rebus.is_empty(i - 1, j) == false)) {
        /* Completam vertical. */
        int span = 0;
        while (i + span < rebus.rows && rebus.is_empty(i + span, j)) {
          ++span;
        }

        /* Adaugam toate cuvintele de span litere aici. */
        for (unsigned int index = 0; index < vocabular.size(); ++index) {
          if (vocabular[index].length() == span) {
            vertical[Position(i, j)].push_back(vocabular[index]);
          }
        }
      }
    }
  }
}

/* Functie care verifica daca avem blocare. */
bool verifica_inainte(int row,
                      int col,
                      std::string& s,
                      Domains& horizontal,
                      Domains& vertical,
                      std::vector<std::string>& prefixes) {
  /* Reducem domeniul orizontal la s. */
  horizontal[Position(row, col)].clear();
  horizontal[Position(row, col)].push_back(s);

  /* Parcurgem prefixele nou modificate si stergem din domeniul vertical tot ce
   * nu se potriveste cu prefixele. */
  for (unsigned int i = 0; i < s.length(); ++i) {
    /* Selectam domeniile vizate, le golim, si punem inapoi doar variantele care
     * au prefix matching. */
    unsigned int prefixLength = prefixes[col + i].length();
    Position verticalBegin(row - prefixLength + 1, col + i);
    std::vector<std::string> verticalDomain = vertical[verticalBegin];
    vertical[verticalBegin].clear();

    /* Parcurgem vechea lista si adaugam inapoi doar ceea ce se potriveste cu
     * prefixul. */
    for (unsigned int word = 0; word < verticalDomain.size(); ++word) {
      if (prefixes[col + i] == verticalDomain[word].substr(0, prefixLength)) {
        vertical[verticalBegin].push_back(verticalDomain[word]);
      }
    }

    /* Daca cumva domeniul este gol, atunci intoarce false. */
    if (vertical[verticalBegin].empty()) {
      return false;
    }
  }
  return true;
}

/* Functie care propaga constrangerile dupa completarea unui string s orizontal
 * incepand de la pozitia (row, col) din rebus. */
bool propagate_constraints(Domains& horizontal, Domains& vertical)
{
  /* Cel mai simplu de implementat este AC1, deoarece ne lipseste o reprezentare
   * determinista a domeniilor care se intersecteaza, iar cautarea suplimentara
   * ar aduce un cost de performanta destul de mare. Se poate implementa,
   * desigur, si AC3.
   *
   * Predictia partiala sau completa se poate obtine modificand aceasta
   * functie (eliminand bucla do-while). Diferenta dintre ele nu este sesizabila
   * deoarece multimea de variabile din problema este bipartita si nu exista
   * restrictii intre doua cuvinte orizontale sau intre doua cuvinte verticale,
   * astfel incat este impropriu sa distingem intre cele doua tipuri de
   * predictie. Intr-o alta reprezentare a problemei, pot aparea diferente intre
   * cele doua tipuri de implementari.
   */
  bool changed;
  do {
    changed = false;
    /* Consideram toate domeniile, doua cate doua. */
    Domains::iterator ith;
    Domains::iterator itv;
    for (ith = horizontal.begin(); ith != horizontal.end(); ++ith) {
      for (itv = vertical.begin(); itv != vertical.end(); ++itv) {
        /* Verificam daca cele doua domenii fac overlap. Domeniile sunt sigur
         * nevide. */
        Position ph = ith->first;
        Position pv = itv->first;
        unsigned int spanh = ith->second[0].length();
        unsigned int spanv = itv->second[0].length();
        if (pv.first <= ph.first && ph.first <= pv.first + spanv &&
            ph.second <= pv.second && pv.second <= ph.second + spanh) {
          int indexh = pv.second - ph.second;
          int indexv = ph.first - pv.first;

          /* Si acum facem purge reciproc la domenii. */
          std::vector<std::string> newHorizontal;
          for (unsigned int h = 0; h < ith->second.size(); ++h) {
            /* Cautam sa vedem daca exista vreun cuvant vertical care sa aiba pe
             * pozitia indexv litera pe care o are cuvantul acesta orizontal pe
             * pozitia indexh. */
            bool matches = false;
            for (unsigned int v = 0; v < itv->second.size() && !matches; ++v) {
              if (ith->second[h][indexh] == itv->second[v][indexv]) {
                matches = true;
              }
            }

            /* Iar daca exista, il adaugam in noul domeniu. */
            if (matches) {
              newHorizontal.push_back(ith->second[h]);
            } else {
              changed = true;
            }
          }

          std::vector<std::string> newVertical;
          for (unsigned int v = 0; v < itv->second.size(); ++v) {
            /* Cautam sa vedem daca exista vreun cuvant orizontal care sa aiba
             * pe pozitia indexh litera pe care o are cuvantul acesta orizontal
             * pe pozitia indexv. */
            bool matches = false;
            for (unsigned int h = 0; h < ith->second.size() && !matches; ++h) {
              if (ith->second[h][indexh] == itv->second[v][indexv]) {
                matches = true;
              }
            }

            /* Iar daca exista, il adaugam in noul domeniu. */
            if (matches) {
              newVertical.push_back(itv->second[v]);
            } else {
              changed = true;
            }
          }

          /* In definitiv, daca vreo unul dintre cele doua domenii a ajuns vid,
           * atunci inseamna ca ne oprim cu "false", altfel actualizam
           * domeniile. */
          if (newHorizontal.empty() || newVertical.empty()) {
            return false;
          } else {
            ith->second = newHorizontal;
            itv->second = newVertical;
          }
        }
      }
    }
  } while (changed);
  return true;
}

void backtracking(
    int row,
    int col,
    Domains& horizontal,
    Domains& vertical,
    std::vector<std::string>& verticalPrefixes)
{
  static bool found_solution = false;

  /* Marcam faptul ca am mai efectuat o intrare in recursivitate. */
  recursions++;

  /* Daca e solutie, afiseaz-o si iesi. */
  if (rebus.is_done()){
    std::cout << rebus;
    found_solution = true;
    return;
  } else if (row == rebus.rows) {
    return;
  }

  if (rebus.is_empty(row, col)) {
    /* Daca la (row, col) nu este completat, atunci incercam sa completam
     * orizontal unul dintre stringurile din domeniu pentru (row, col). */
    std::vector<std::string>& possibilities = horizontal[Position(row, col)];
    for (unsigned int i = 0; i < possibilities.size(); ++i) {
      std::string& s = possibilities[i];

      /* Completeaza aceasta varianta. */
      rebus.putString(row, col, s);

      /* Modifica prefixele verticale. */
      std::vector<std::string> newVerticalPrefixes = verticalPrefixes;
      for (unsigned int j = 0; j < s.length(); ++j) {
        newVerticalPrefixes[col + j] += std::string(1, s[j]);
      }

      /* Doar daca verificarea s-a incheiat cu succes. */
      Domains newHorizontal = horizontal;
      Domains newVertical = vertical;
      if (verifica_inainte(row, col, s, newHorizontal, newVertical,
                           newVerticalPrefixes)) {
        /* Apeleaza intai propagarea constrangerilor. */
        if (propagate_constraints(newHorizontal, newVertical)) {
          backtracking(row + (col + 1) / rebus.columns,
                       (col + 1) % rebus.columns,
                       newHorizontal,
                       newVertical,
                       newVerticalPrefixes);
        }
      }

      /* Retrage-ti miscarea. */
      rebus.eraseString(row, col);
    }
  } else {
    /* Cand treci peste un '*', sterge prefixul pentru coloana respectiva. */
    if (rebus.get(row, col) == '*') {
      verticalPrefixes[col] = std::string();
    }
    /* Daca e completat deja, treci mai departe. */
    backtracking(row + (col + 1) / rebus.columns,
                 (col + 1) % rebus.columns,
                 horizontal,
                 vertical,
                 verticalPrefixes);
  }
}

int main()
{
  /* Declaram si citim un rebus. */
  std::ifstream rebus_file("src-lab4/Puzzle.rebus");
  rebus_file >> rebus;
  rebus_file.close();

  /* Citim dictionarul. */
  std::ifstream vocabulary_file("src-lab4/Vocabular.txt");
  vocabulary_file >> vocabular;
  vocabulary_file.close();

  /* Inregistram timpul de inceput. */
  timeval start_time;
  gettimeofday(&start_time, NULL);

  Domains horizontal, vertical;
  std::vector<std::string> prefixes(rebus.columns, std::string());
  build_word_sets(horizontal, vertical);
  backtracking(0, 0, horizontal, vertical, prefixes);

  /* Inregistram timpul final. */
  timeval end_time;
  gettimeofday(&end_time, NULL);

  /* Afisam diferenta. */
  int nsec = end_time.tv_sec-start_time.tv_sec;  
  int nmsec = (end_time.tv_usec-start_time.tv_usec)/(double)1000;
  if (nmsec < 0 && nsec){
    nsec--;
    nmsec += 1000;
  }
  std::cout << "Total recursive calls: " << recursions << std::endl
      << "Total time is: " << nsec << " sec " << nmsec << " msec"
      << std::endl;
  return 0;
}


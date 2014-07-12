#include <iostream>
#include <vector>
#include <string>
#include <cstring>

#include "VectorIO.h"

void greedy_format(unsigned int l, std::vector<std::string>& v)
{
  std::cout << "Greedy format:" << std::endl;
  unsigned int cost = 0;

  /* TODO: Calculati costul formatarii Greedy si afisati textul formatat
   * folosind aceasta strategie. Afisati cate "l" caractere pe o linie, urmate de
   * un caracter "|" inainte de terminatorul de linie. */
	int spaceleft = l, i, j;
	std::cout << v[i];
	spaceleft -= v[i].length();
	for (i = 1; i < v.size(); i++)
	{
		if (v[i].length() + 1 > spaceleft)
		{
			for (j = 0; j < spaceleft; j++)
				std::cout << " ";
			std::cout << "|\n";
			cost += spaceleft;
			spaceleft = l - v[i].length();
			std::cout << v[i];
		}
		else
		{
			spaceleft = spaceleft - v[i].length() - 1;
				std::cout <<" "<< v[i];
		}
	}	
	cost += spaceleft;
  /* Afisam costul. */
  std::cout << "TOTAL COST: " << cost << std::endl;
}

void tex_format(unsigned int l, std::vector<std::string>& v)
{
  std::cout << "Tex format:" << std::endl;

  /* TODO: Calculati costul formatarii Tex si afisati textul formatat
   * folosind aceasta strategie. Afisati cate "l" caractere pe o linie, urmate de
   * un caracter "|" inainte de terminatorul de linie. */

  /* Afisam costul total. */
  std::cout << "TOTAL COST: " << 0 << std::endl;
}

int main()
{
  /* Declaram si citim lungimea unei linii si un vector de cuvinte. */
  unsigned int l;
  std::vector<std::string> word;
  std::cin >> l >> word;

  /* Verificam sa nu avem cuvinte mai lungi de o linie. */
  for (unsigned int i = 0; i < word.size(); ++i) {
    if (word[i].length() > l) {
      std::cerr << "Imposibil. Anumite cuvinte sunt mai lungi de o linie!"
          << std::endl;
      return 0;
    }
  }

  /* Afisam impartirea greedy. Se vor afisa L caractere pe o linie, urmate de un
   * caracter pipe ('|') la final. */
  greedy_format(l, word);
  std::cout << std::endl;

  /* Afisam impartirea folosind functia de cost din TeX. Se vor afisa L
   * caractere pe o linie, urmate de un caracter pipe ('|') la final.*/
  //tex_format(l, word);

  return 0;
}


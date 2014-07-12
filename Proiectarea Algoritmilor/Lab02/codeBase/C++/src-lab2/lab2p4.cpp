#include <iostream>
#include <vector>
#include <algorithm>

#include "VectorIO.h"
#include "PairIO.h"

#define UNDEF -1

/* Un material este o pereche (greutate, valoare). */
typedef std::pair<int, int> Mobila;
int a[100];
int val_max(int t, std::vector<Mobila>& v)
{
  /* TODO: Caclulati valoarea maxima transportabila de catre camionul de
   * capacitate t. */
	int i, j;
	for (i = 0; i < v.size(); i++)
	{
		for (j = t - v[i].first; j >= 0; --j)
		{
			if (a[j + v[i].first] < a[j] + v[i].second)
				a[j + v[i].first] =  a[j] + v[i].second;
		}
		
	}
	return a[t];

  return 0;
}

int main()
{
  /* Declaram capacitatea camionului si un vector care sa retina tipurile de
   * mobila sub forma de perechi (greutate, valoare) si citim datele de intrare.
   */
  int t;
  std::vector<Mobila> v;
  std::cin >> t >> v;

  /* Afisam valoarea maxima transportabila de catre camion. */
  std::cout << "Valoarea maxima a mobilierului transportabil: "
            << val_max(t, v) << std::endl;

  return 0;
}


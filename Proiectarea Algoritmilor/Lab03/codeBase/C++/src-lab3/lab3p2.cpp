#include <iostream>
#include <vector>
#include <cstring>

#include "VectorIO.h"
#include "PairIO.h"
#define INF 10000000

/* Vom considera o matrice ca o pereche de intregi (nr_linii, nr_coloane). */
typedef std::pair<int, int> Matrice;

int min_multiplications(std::vector<Matrice>& v)
{
  /* Tiparim efectiv lantul parantezat si intoarcem numarul minim de operatii de
   * inmultire elementare. */
	
	int m[10][10];
	int s[10][10];
	int i, l, j, k, q;
	for (i = 0; i < v.size(); i++)
		m[i][i] = 0;
	for (i = 0; i < v.size() - 1; i++)
		m[i][i+1] = v[i].first*v[i].second*v[i+1].second;
	for (l = 3; l <= v.size(); l++)
	{
		for (i = 0; i < v.size() - l + 1; ++i)
		{
			j = i + l - 1;

			m[i][j] = INF;
			for (k = i; k < j; k++)
			{
				if (i == 0)
					q = m[i][k] + m[k + 1][j] + v[i].first*v[k].second*v[j].second;
				else
					q = m[i][k] + m[k + 1][j] + v[i - 1].second*v[k].second*v[j].second;
				if (q < m[i][j])
				{
					m[i][j] = q;
					s[i][j] = k;
				}
				
			}
			
		}
	}
	return m[0][v.size() -1];
}

int main()
{
  /* Declaram si citim un vector de matrice de la consola. */
  std::vector<Matrice> v;
  std::cin >> v;

  /* Verificam intai ca lantul de matrice chiar se poate inmulti. */
  for (unsigned int i = 0; i < v.size() - 1; ++i) {
    if (v[i].second != v[i + 1].first) {
      std::cerr << "Fail! Nu se pot inmulti matricele " << i << " si "
          << (i + 1) << " de dimensiuni: " << v[i] << ", respectiv "
          << v[i + 1] << std::endl;
      return 0;
    }
  }

  /* Afisam numarul minim de operatii. */
  std::cout << "Numarul minim de operatii de inmultire elementare este: "
      << min_multiplications(v) << std::endl;

  return 0;
}


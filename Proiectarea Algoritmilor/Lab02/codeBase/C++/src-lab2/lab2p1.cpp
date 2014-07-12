#include <iostream>
#include <vector>
#include <algorithm>

#include "VectorIO.h"
#include "PairIO.h"

/* Un material este o pereche (greutate, valoare). */
typedef std::pair<int, double> Material;
bool myComp (std::pair<int, double> x, std::pair<int, double> y)
{
	return (x.second/x.first) < (y.second/y.first);
}

double val_max(int t, std::vector<Material>& v)
{
	double s = 0;
	int i;
	sort (v.begin(), v.end(), myComp);
	for (i = v.size() - 1; i >= 0 && t >= v[i].first; --i)
	{
		s += v[i].second;
		t -= v[i].first;
	}
	if (t > 0)
		s += t * (v[i].second/v[i].first);
  return s;
}

int main()
{
  /* Declaram capacitatea camionului si un vector care sa retina tipurile
   * de material sub forma de perechi (greuate, valoare) si citim datele
   * de intrare.
   */
  int t;
  std::vector<Material> v;
  std::cin >> t >> v;

  /* Afisam valoarea maxima transportabila de catre camion. */
  std::cout << "Valoarea maxima a unui transport: "
      << val_max(t, v) << std::endl;

  return 0;
}


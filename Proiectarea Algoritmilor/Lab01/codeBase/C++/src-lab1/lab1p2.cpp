#include <iostream>

#include "VectorIO.h"

int find_kth_smallest(std::vector<int>& v, int k)
{
  /* TODO: functie care returneaza al k-lea element in ordine crescatoare din
   * vectorul primit ca parametru. */
	std::vector<int> greater;
	std::vector<int> less;
	int pivot = v[0];
	int i;
	for (i = 1; i < v.size(); i++)
		if (v[i] >= pivot)
			greater.push_back (v[i]);
		else
			less.push_back (v[i]);
	if (less.size() == k)
		return pivot;
	else
		if (less.size() > k)
			return find_kth_smallest(less, k);
		else
			return find_kth_smallest(greater, k - less.size() - 1);
  return 0;
}

int main()
{
  /* Declaram si citim un vector de numere intregi de la tastatura. */
  std::vector<int> v;
  std::cin >> v;

  /* Afisam elementul care se afla pe pozitia mediana in vectorul sortat. */
  std::cout << "Elementul median din multime este: "
            << find_kth_smallest(v, 6) << std::endl;

	return 0;
}


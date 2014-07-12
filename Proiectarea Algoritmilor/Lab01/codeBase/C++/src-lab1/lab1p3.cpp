#include <iostream>

#include "VectorIO.h"

#define MAX(a,b) ((a)<(b)?(b):(a))

int bsum = -int(2e9);


int max_sum_subsequence(std::vector<int>& v)
{
  /* Functie care intoarce suma maxima a unei subsecvente din vectorul primit
   * ca parametru folosind o abordare de tipul Divide & Impera (se impune o
   * complexitate din clasa O(N) sau O(N log N)). */
	if (v.size() == 1)
	{
		bsum = MAX(bsum, v[0]);
		return bsum;
	}

	std::vector<int> v1(v.begin(), v.begin() + v.size()/2);
	std::vector<int> v2(v.begin() + v.size()/2, v.end());
	max_sum_subsequence(v1);
	max_sum_subsequence(v2);
	
	int pre = -int(1e9);
	int suf = -int(1e9);
	int s = 0;
	int i;
	for (i = v1.size() - 1; i >= 0; --i) {
		s += v1[i];
		pre = MAX (pre, s);
	}
	s = 0;
	for (i = 0; i < v2.size(); i++) {
		s += v2[i];
		suf = MAX (suf, s);
	}
	bsum = MAX (suf + pre, bsum);
	return bsum;	
		
}

int main()
{
  /* Declaram si citim un vector de numere intregi. */
  std::vector<int> v;
  std::cin >> v;

	/* Afisam rezultatul. */
	std::cout << "Subsecventa de suma maxima din vector are suma = "
            << max_sum_subsequence(v) << std::endl;

	return 0;
}


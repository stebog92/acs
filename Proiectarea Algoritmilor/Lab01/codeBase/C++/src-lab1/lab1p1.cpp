#include <iostream>
#include <stdint.h>

uint64_t karatsuba(uint64_t x, uint64_t y, unsigned char exp)
{
  /* TODO: Implementati metoda de inmultire a doua numere folosind algoritmul
   * Karatsuba, scriind cele doua numere in baza (2^exp) si efectuand
   * inmultirile conform enuntului din laborator.
   *
   * TODO(pe hartie): Cate inmultiri elementare se efectueaza pentru algoritmul
   * de inmultire Karatsuba? Se stie ca pentru a imulti doua numere a cate K
   * biti fiecare, este nevoie de K^2 inmultiri elementare.
   */
	if (exp == 1)
		return x*y;
	uint64_t xl = (x >> exp);
	uint64_t xr = x & ((1 << (exp )) - 1);
	uint64_t yl = (y >> exp);
	uint64_t yr = y & ((1 << (exp )) - 1);
	
	uint64_t P1 = karatsuba (xl, yl, exp/2);
	uint64_t P2 = karatsuba (xr, yr, exp/2);
	uint64_t P3 = karatsuba (xl + xr, yl + yr, exp/2);
	return P1 * (1 << (exp*2)) + (P3 - P2 - P1) * (1 << exp) + P2;

  return 0;
}

int main()
{
  /* Citim doua numere fara semn, de 32 biti, pe care le vom inmulti. */
  uint32_t n1, n2;
  std::cin >> n1 >> n2;

  /* Afisam rezultatul inmultirii Karatasuba. */
  uint64_t prod_classic = ((uint64_t) n1) * ((uint64_t) n2);
  uint64_t prod_karatsuba = karatsuba(n1, n2, 16);

  if (prod_classic == prod_karatsuba) {
    std::cout << "OK!" << std::endl << "Produsul celor doua numere este: "
              << prod_classic << std::endl;
  } else {
    std::cout << "FAIL!" << std::endl << "Produsul celor doua numere este: "
              << prod_classic << std::endl << "iar Karatsuba a calculat: "
              << prod_karatsuba << std::endl;
  }

  return 0;
}


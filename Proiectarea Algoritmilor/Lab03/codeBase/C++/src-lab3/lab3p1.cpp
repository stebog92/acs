#include <iostream>
#include <string>
#include <vector>
#include <cstring>

#include "BooleanExpression.h"
int t[10][10];
int f[10][10];

int count_modes(const std::vector<BooleanExpression::Lexem>& expr)
{
  /* TODO: Numarati modurile in care se pot pune paranteze paranteze in
   * expresie astfel incat sa se obtina rezultatul "true".
   *
   * OBS: Este obligatoriu sa puneti in expresie un numar de paranteze egal
   * cu numarul de operatori prezenti (adica parantezarea sa fie completa).
   *
   * OBS: Asa cum scrie si in documentatie, Lexem este un tip de enumerare ce
   * poate avea valorile: 
   *
   * BooleanExpression::True, 
   * BooleanExpression::False, 
   * BooleanExpression::Or, 
   * BooleanExpression::Xor, 
   * BooleanExpression::And 
   *
   * (sunt constante numerice, valoare reala nu este importanta).
   */
	BooleanExpression::Lexem a[10][10];
	std::vector<BooleanExpression::Lexem> operands;
	int i, j, k, l;

	for (i = 0; i < expr.size(); i+=2)
		operands.push_back(expr[i]);

	for (i = 0; i < operands.size(); i ++)
	{	
		if (operands[i] == BooleanExpression::True)
		{
			t[i][i] = 1;
			f[i][i] = 0;
		}
		else
		{
			t[i][i] = 0;
			f[i][i] = 1;
		}
	}
	for (l = 2; l <= operands.size(); l++)
	{
		for (i = 0; i < operands.size() - l + 1; i ++)
		{
			j = i + l - 1;
			for (k = i; k < j; k++)
			{
				if (expr[k*2+1] == BooleanExpression::And)
					t[i][j] += t[i][k]*t[k+1][j];
				if (expr[k*2+1] == BooleanExpression::Or)
					t[i][j] += (t[i][k] + f[i][k])*(t[k+1][j] + f[k+1][j]) - f[i][k]*f[k+1][j];
				if (expr[k*2+1] == BooleanExpression::Xor)
					t[i][j] += t[i][k]*f[k+1][j] + f[i][k]*t[k+1][j];
				
				if (expr[k*2+1] == BooleanExpression::And)
					f[i][j] += (t[i][k] + f[i][k])*(t[k+1][j] + f[k+1][j]) - t[i][k]*t[k+1][j];
				if (expr[k*2+1] == BooleanExpression::Or)
					f[i][j] += f[i][k]*f[k+1][j];
				if (expr[k*2+1] == BooleanExpression::Xor)
					f[i][j] += t[i][k]*t[k+1][j] + f[i][k]*f[k+1][j];
			
			}
		}
	}
  return t[0][operands.size() - 1];
}

int main()
{
  /* Citim si interpretam o expresie de la tastatura */
  BooleanExpression booleanExpression;
  std::cin >> booleanExpression;

  /* Daca este corecta gramatical, afisam rezultatul */
  if (booleanExpression.is_valid()){
    std::cout << "Numarul de moduri in care se poate obtine rezultatul "
        << "\"true\" este: \n\t"
        << count_modes(booleanExpression.to_vector()) << "\n";
  }

  return 0;
}


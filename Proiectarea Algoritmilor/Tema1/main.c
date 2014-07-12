#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char *line;

typedef struct {
	int freq;
	char s[100];
} wordFreq;

typedef struct {
	int size;
	wordFreq words[6000];
} dictio;

typedef struct {
	long int totalFreq;
	long int totalDist;
	int words;
	char string[100];
	int len;
} util;

dictio dict[40];
util sol[100][100];
int max_size;

/* intoarce distanta de editare
 * dintre 2 siruri de caractere
 */
int editDistance (char *s1, char *s2)
{
	/* intoarce 0 daca sirurile sunt identice */
	if (strcmp (s1, s2) == 0)
		return 0;

	int distMat[strlen (s1) + 1][strlen (s2) + 1], i, j;

	for (i = 0; i <= strlen (s1); i++)
		distMat[i][0] = i;
	for (i = 0; i <= strlen (s2); i++)
		distMat[0][i] = i;

	for (i = 0; i < strlen (s1); i++)
		for (j = 0; j < strlen (s2); j++)
		{
			/* Daca ultimele 2 caractere sunt egale inseamna 
			 * ca nu e nicio diferenta iar distanta de editare
			 * va fi aceeasi cu aceea neluand in considerare 
			 * cele 2 caractere, altfel inseamna ca e o diferenta
			 * si distanta de editare creste cu 1;
			 */
			distMat[i + 1][j + 1] = distMat[i][j] + (s1[i] == s2[j] ? 0 : 1);

			/* O alta posibilitate ar fi sa stergem ultimul caracter din sirul s1
			 * si sa il transformam in  s2 cu distanta de editare d(s1, s2 + ch2) + 1;
			 */ 
			if (distMat [i + 1][j + 1] > distMat[i][j + 1] + 1)
				distMat [i + 1][j + 1] = distMat[i][j + 1] + 1;
			/* Analog pentru celalalt sir; Costul final trebuie ales minim */
			if (distMat [i + 1][j + 1] > distMat[i + 1][j] + 1)
				distMat [i + 1][j + 1] = distMat[i + 1][j] + 1;
		}
	return distMat[strlen(s1)][strlen(s2)];
}

/* Functia trim sterge toate spatiile albe
 * dintr-un sir de caractere
 */
char* trim (char *s)
{
	char *newLine = malloc (100 * sizeof (char));
	char *p = s, *r = newLine;
	while (*p)
	{
		if (!isspace (*p))
			*(r++)= *p;
		p++;
	}
	free(s);
	return newLine;
}


util compareStrings (int begin, int end)
{
	int i, up, down, size;
	char temp[100];

	util new;
	/* initializare campuri structura */
	new.totalFreq = 0;
	new.totalDist = 100;
	new.words = 1;
	new.len = strlen (line);
	memset (new.string, 32, 100);


	if (end - begin >= 18)
		return new;
	/* Construire subsir din sirul initial */
	strncpy (temp, line + begin, end - begin + 1);
	temp[end - begin + 1] = '\0';
	

	if ((up = strlen(temp) + 2) > max_size)
		up = max_size;
	if ((down = strlen (temp) - 1) < 1)
		down = 1;

	/* comparare intre sir si cuvintele din dictionar
	 * si retinere cel mai bun in functie de distanta de
	 * editare si de frecventa
	 */
	for (size = down; size <= up; size++)
	{
		for (i = 0; i < dict[size].size; i++)
		{
			int dist = editDistance(dict[size].words[i].s, temp);
			if (dist < new.totalDist || 
				dist == new.totalDist && new.totalFreq < dict[size].words[i].freq)
				{
					new.totalDist = dist;
					new.totalFreq = dict[size].words[i].freq;
					strcpy (new.string, dict[size].words[i].s);
				}
			if (new.totalDist == 0)
				break;
		}
		if (new.totalDist == 0)
			break;
	}
	return new;
}

/* Citire dictionar si retinere in hash */
void citire_dictionar (FILE *f) 
{
	char temp[100];
	int freq, len, sz;
	while (fscanf (f, "%s %d", temp, &freq) != EOF)
	{
		len = strlen(temp);
		if (len > max_size)
			max_size = len;
		sz = dict[len].size;
		memcpy (dict[len].words[sz].s, temp, 100);
		dict[len].words[sz].freq = freq;
		dict[len].size++;
	}
}

/* Calculeaza corectia prin programare dinamica */
util compute (int length)
{
	long int totalDist, totalFreq, len, l, begin, end, k, words;
	char string[100];

	/* Pentru un sir de lungime l intre indicii begin si end 
	 * se verifica daca este deja in dictionar sau o variatie 
	 * a lui cu o distanta de editare mai mica decat lungimea sa;
	 * apoi se incearca reconstructia lui din siruri mai mici 
	 * corecte ortografic ce se afla intre begin - k si k + 1 - end;
	 */
	for (l = 1; l <= length; l++)
	{
		for (begin = 0; begin < length - l + 1; begin++)
		{
			end = begin + l - 1;
			sol[begin][end] = compareStrings (begin, end);
			/* Daca distanta de editare e 0 nu se mai 
			 * nu se mai aplica modificari pe cuvant
			 * deoarece e corect ortografic
			 */

			if (sol[begin][end].totalDist != 0)
			{
				for (k = begin; k < end; k++)
				{
					words = sol[begin][k].words + sol[k + 1][end].words;
					totalFreq = sol[begin][k].totalFreq + sol[k + 1][end].totalFreq;
					totalDist = sol[begin][k].totalDist + sol[k + 1][end].totalDist;
					strcpy (string, sol[begin][k].string);
					len = strlen(string);
					string [len] = ' ';
					strcpy (string + len + 1, sol[k + 1][end].string);
		
					if (totalDist < sol[begin][end].totalDist || 
					totalDist == sol[begin][end].totalDist && sol[begin][end].words > words ||
					totalDist == sol[begin][end].totalDist && sol[begin][end].words == words 
					&& sol[begin][end].totalFreq < totalFreq ||
					totalDist == sol[begin][end].totalDist && sol[begin][end].words == words &&
					sol [begin][end].totalFreq == totalFreq && strcmp (sol[begin][end].string, string) > 0)
					{
						sol[begin][end].totalDist = totalDist;
						sol[begin][end].totalFreq = totalFreq;
						memcpy (sol[begin][end].string, string, 100 * sizeof (char));
						sol[begin][end].words = words;
					}
				}
			}
		}
	}
	return sol[0][length - 1];

}
	
			


		
int main () {

int len = 100, i,j;
/* Deschidere fisier-dictionar */
FILE *f = fopen ("dict.txt", "r");
line = malloc (100 * sizeof (char));

/* Citire sir de cuvine */
getline (&line, &len, stdin);

/* Stergere spatii albe din propozitie */
line = trim (line);

/* Citire dictionar si retinere in hash 
 * ce are cheie=lungime_cuvant si 
 * valoare=struct (cuvant, frecventa)
 */
citire_dictionar(f);

/* Corectare sir */
util new = compute (strlen (line));

/* Afisare solutie */
printf ("%s\n", new.string);

free(line);
return 0;
}

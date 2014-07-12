#include <stdio.h>
#include <vector>
#include <math.h>
#include <stdlib.h>
#include <queue>
#include <map>

using namespace std;
typedef struct
{
	int val;
	map<int, double> ngb;
	bool ok;	
} pxl;
double PI = 4.0*atan(1);
double INF = 99999999;
vector<pxl> image;
vector<int> maskF;
vector<int> maskB;
double sf = 0, nrf = 0, sb = 0, nrb = 0;
double ub, uf, sigmaf, sigmab;


int width, height, max_value, treshold, lambda;

/* bfs - pleaca din source (fg) si retine minimul muchiei fiecare cale
	si parintele*/
double bfs (int s, int t, bool ok)
{
	double flow = 0;
	queue<int> queue;
	vector<int> parent(image.size() + 2, -1);
	vector<double> m(image.size() + 2);
	for (int i = 0; i < image.size(); i++)
	{
		m[i] = image[i].ngb[s];
	}
	m[s] = INF;
	parent[s] = -2;
	queue.push (s);
	while (!queue.empty())
	{
		int u = queue.front();
		queue.pop();
		for (map <int, double>::iterator v = image[u].ngb.begin(); v != image[u].ngb.end(); v++)
		{
			if ((*v).second > (double)0 && parent[(*v).first] == -1)
			{
				parent[(*v).first] = u;
				m[(*v).first] = min (m[u], (*v).second);
				/*decid daca face parte din fg sau din bg*/
				if (ok)
					image[(*v).first].ok = true;
				if ((*v).first != t)
				{
					queue.push ((*v).first);
				}
				else
				{
					double mint = m[t];
					while (t != s)
					{
						image[parent[t]].ngb[t] -= mint;
						t = parent[t];
					}
					flow += mint;
				}
				
			}
		}
	}
	return flow;
}
		
double edmonds_karp (int s, int t)
{
	double f = 0;
	double m;
	/*saturez caile cu 2 muchii - caile de la source la sink ce contin
		un singur pixel*/
	for (int i = 0; i < height; i++)
		for (int j = 0; j < width; j++)
		{
			double minim = min (image[width * height].ngb[i * width + j], image[width * height + 1].ngb[i * width + j]);
			image[width * height].ngb[i * width + j] -= minim;
			image[i * width + j].ngb[height * width + 1] -= minim;
			f += minim;
		}
	/*execut bfs cat timp mai exista cai nesaturate */
	while (1)
	{
		m = bfs (s,t, false);
		if (m != (double)0)
		{
			f += m;
		}
		else
			break;
	}
	bfs (s, t, true);
	return f;
	
}
int main ()
{
	
	FILE* img = fopen ("imagine.pgm", "r");
	FILE* maskFG = fopen ("mask_fg.pgm", "r");
	FILE* maskBG = fopen ("mask_bg.pgm", "r");
	FILE* param = fopen ("parametri.txt", "r");

	fscanf (img, "P2\n%d %d\n%d", &width, &height, &max_value);
	fscanf (param, "%d %d", &lambda, &treshold);
	fscanf (maskFG, "P2\n%*d%*d\n%*d");
	fscanf (maskBG, "P2\n%*d%*d\n%*d");
	image.resize (width * height + 2);
	maskF.resize (width * height + 2, 0);
	maskB.resize (width * height + 2, 0);

	for (int i = 0; i < height; i++)
	{

		int fg, bg;
		for (int j = 0; j < width; j++)
		{
			pxl temp;
			temp.ok = false;
			fscanf (img, "%d", &(temp.val));
			image [i * width + j] = temp;
			fscanf (maskFG, "%d", &fg);
			maskF [i * width + j] = fg;
			if (fg > 0)
			{
				sf += temp.val;
				nrf++;
			}
			fscanf (maskBG, "%d", &bg);
			maskB [i * width + j] = bg;
			if (bg > 0)
			{
				sb += temp.val;
				nrb++;
			}
		}
	}
	/*calcul miu-f si miu-b*/
	uf = sf / nrf;
	ub = sb / nrb;

	/*calcul sigma-f si sigma-b*/
	double sumsigmaf = 0, sumsigmab = 0;
	for (int i = 0; i < height; i++)
	{
		for (int j = 0; j < width; j++)
		{
			if (maskF[i * width + j] > 0)
			{
				sumsigmaf += (uf  - image[i * width + j].val)*(uf  - image[i * width + j].val);
			}
			if (maskB[i * width + j] > 0)
			{
				sumsigmab += (ub  - image[i * width + j].val)*(ub  - image[i * width + j].val);

			}
		}
	}

	sigmaf = sqrt (sumsigmaf/nrf);
	sigmab = sqrt (sumsigmab/nrb);

	/*constructia grafului*/
	for (int i = 0; i < height; i++)
	{
		for (int j = 0; j < width; j++)
		{
			if (i - 1 >= 0)
			{
				if (abs (image[(i - 1) * width + j].val - image[i * width + j].val) <= treshold)
				{
					image[i * width + j].ngb.insert (pair<int, double> ((i - 1) * width + j, lambda));
				}
				else
				{
					image[i * width + j].ngb.insert (pair<int, double> ((i - 1) * width + j, 0));
				}
			}
			
			if (j - 1 >= 0)
			{
				if (abs (image[i * width + j].val - image[i * width + j - 1].val) <= treshold)
				{
					image[i * width + j].ngb.insert (pair<int, double> (i * width + j - 1, lambda));
				}
				else
				{
					image[i * width + j].ngb.insert (pair<int, double> (i * width + j - 1, 0));
				}
			}
	
			if (i + 1 < height)
			{
				if (abs (image[(i + 1) * width + j].val - image[i * width + j].val) <= treshold)
				{
					image[i * width + j].ngb.insert (pair<int, double> ((i + 1) * width + j, lambda));
				}
				else
				{
					image[i * width + j].ngb.insert (pair<int, double> ((i + 1) * width + j, 0));
				}
			}
			
			if (j + 1 < width)
			{
				if (abs (image[i * width + j].val - image[i * width + j + 1].val) <= treshold)
				{
					image[i * width + j].ngb.insert (pair<int, double> (i * width + j + 1, lambda));
				}
				else
				{
					image[i * width + j].ngb.insert (pair<int, double> (i * width + j + 1, 0));
				}
			}
			/* adaugare source si sink pentru fiecare pixel in lista de vecini cu
				capacitatea data de functia unara*/
			double fu_source = 0.5 * ((double)(image[i * width + j].val - uf)/sigmaf) * ((double)(image[i * width + j].val - uf)/sigmaf) + 
						log (sqrt (2 * PI * sigmaf * sigmaf));
			fu_source = min (fu_source, (double)10);
			image[i * width + j].ngb.insert (pair<int, double> (height * width,  fu_source));
			image[height*width].ngb.insert (pair<int, double> (i * width + j, fu_source));
			
			double fu_dest = 0.5 * ((double)(image[i * width + j].val - ub)/sigmab) * ((double)(image[i * width + j].val - ub)/sigmab) + 
						log (sqrt (2 * PI * sigmab * sigmab));
			fu_dest = min (fu_dest, (double)10);
			image[i * width + j].ngb.insert (pair<int, double> (height*width + 1,  fu_dest));
			image[height*width + 1].ngb.insert (pair<int, double> (i * width + j, fu_dest));
		}
	}

	/*executie algoritm pentru taietura minima*/
	double max_flow = edmonds_karp(height*width, height * width + 1);

	/*afisare imagine segmentata in fisier*/
	FILE* res = fopen ("segment.pgm", "w");
	fprintf (res, "P2\r\n%d %d\r\n%d\r\n", width, height, 255);
	for (int i = 0; i < height; i++)
	{
		for (int j = 0; j < width; j++)
		{
			if (image[i * width + j].ok)
			{
				fprintf (res, "%d \r\n", 0);
			}
			else
			{
				fprintf (res, "%d \r\n", 255);
			}
		}
	}

	printf ("%f\n", max_flow);
	return 0;
}

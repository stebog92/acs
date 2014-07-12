Elemente de grafica pe calculator - Tema 1
	Fotbal Static
	Ciocan Mihai 334CA

Cerinta :

Tema cere implementarea unui joc de fotbal static.

Utilizare :
Programul se ruleaza normal utilizand Visual Studio 2010, nu necesita parametrii de intrare suplimentari.


Input Tastatura:

Jucatorii se rotesc din tastele marcate cu sageti (stanga, dreapta) iar iar propulsarea balonului de catre un jucator se realizeaza prin sageata
up.

Interfata grafica:

Jocul presupune desenarea unui teren de fotbal dimensional, in interiorul caruia se afla jucatorii in pozitii aleatoare fara posibilitatea de translatare. Sunt cate 4 jucatori de fiecare echipa, in culori diferite (portocaliu si albastru). In cazul
unui gol, se mareste scorul echipei marcatoare cu o linie de culoare corespunzatoare,in stanga sus, respectiv dreapta sus.

Implementare:

Tema a fost implementata in Visual Studio 2010, pe Windows 7, utilizand frameworkul oferit la laborator.

Schema generala :

Terenul de joc este construit in functia de initializare a WorldDrawer2d::init () dintr-un dreptunghi de culoare verde, margini rosii (create exclusiv din triunghiuri) si liniile de marcaj de culoare alba.

Jucatorii si mingea, sunt obiecte create de functia createCircle prin rotirea unei multimi de triunghiuri (rotirea punctelor initiale si apoi constructia triunghiului).

Rotirea jucatorilor se realizeaza apasand tastele cu sageti (stanga sau dreapta). Se realizeaza in jurul punctului de origine al cercului, simultan rotindu-se si mingea (tot in jurul punctului de origine al jucatorului). Actionarea tastelor de directie, rotesc numai jucatorul care detine mingea. 

Propulsarea balonului de catre jucator se realizeaza apasand tasta up, traiectoria sa fiind determinata de directia originile celor 2 cercuri (jucator si minge). 

Detectarea coliziunilor se realizeaza in functie de limitele stanga dreapta sus jos ale terenului.
In cazul in care mingea atinge manta, ea ricoseaza din aceasta sub acelasi unghi fata de normala (modificand x sau y cu negativul sau).In cazul in care mingea intra in poarta (se afla pe abscisa intre barele celor 2 porti, iar pe ordonata depasesc limitele superioare sau inferioare ale terenului) se mareste scorul echipei marcatoare (cu metoda increase_score) prin plasarea unui dreptunghi in plus.
Mingea revine echipei care a primit gol, la un jucator aleator ales. La fiecare iteratie din animatie se verifica daca mingea se afla in la o distanta mai mica decat raza cercului unui jucator. Daca acest lucru se intampla mingea se opreste apartinand jucatorului respectiv.

Daca o echipa a marcat 3 goluri in poarta adversa, pe ecran apare,timp de 2 secunde, un fundal albastru, jocul reluandu-se de la scorul de 0 - 0.

La inceputul unui joc nou, mingea este oferita unui jucator albastru ales aleator.


Continutul arhivei :

main.cpp : sursa principala a aplicatiei
Transform2d.cpp : fisier ce contine functiile de rotatie, translatie, scalare a obiectelor.
Support2d.cpp : fisier ce contine definirea obiectelor, sistemelor de coordonate, etc;
Readme




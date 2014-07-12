Elemente de grafica pe calculator - Tema 2
	Rubik
	Ciocan Mihai 334CA

Cerinta :
Implementa?i un joc care va permite rezolvarea cubului lui Rubik.
Utilizare :

Programul se ruleaza normal utilizand Visual Studio 2010, nu necesita parametrii de intrare suplimentari.


Input Tastatura:

Cubul se poate roti la dreapta sau la stanga apasand tasta z, iar apoi sagetile stanga sau dreapta. Se poate roti in jos sau in sus apasand tasta x iar apoi folosind sagetile up sau down.
De asemenea se poate roti o coloana folosind tasta s (si sagetile up sau down), sau o linie folosind tasta a (si sagetile left sau right). Liniile sau coloanele care se pot roti sunt evidentiate prin schimbarea culorii sale. De asemena tasta SPACE duce jocul in diferite moduri, cum ar fi amestecarea cubului, sau rezolvarea lui.

Interfata grafica:

Cubul Rubik este format din 27 de cuburi de aceleasi dimensiuni, cu diferite culori in functie de pozitia lor. Cuburile sunt formate prin functia makeCube, iar culorile pe fiecare din fete sunt stocate intr-un vector din obiect. Cuburile sunt despartite de un spatiu pentru o mai buna vizualizare a cubului si a culorilor acesuia. La selectarea unei linii, sau a unei coloane (sau a intregului cub), culorile respective se schimba treptat oferind un efect de glow. Daca dorim sa selectam o linie se apasa tasta a, apoi sagetile up - down pentru linia dorita si left - right pentru rotire. Analog se intampla pentru linii. 

Implementare:

Tema a fost implementata in Visual Studio 2010, pe Windows 7, utilizand frameworkul oferit la laborator.

Schema generala :

Cubul este initial rezolvat. Pe ecran apare mesajul Scramble the cube! care indeamna utilizator-ul sa modifice starea actuala a cubului. Dupa ce culorile cubului s-au amestecat si apasarea tastei SPACE se trece in modul rezolvare pe ecran aparand mesajul "Solve the cube!". Daca utilizatorul rezolva cubul (nu e necesar sa se afle in aceeasi configuratie ca la inceput) pe ecran apare scorul ce se compune din numarul de mutari facute pana la solutie. Scorul e afisat sub forma unor cuburi grupate pe un plan dimensional. La apasare SPACE jocul se reia. 

Continutul arhivei :

main.cpp : sursa principala a aplicatiei
Transform3d.cpp : fisier ce contine functiile de rotatie, translatie, scalare a obiectelor.
Support3d.cpp : fisier ce contine definirea obiectelor, sistemelor de coordonate, etc;
Readme




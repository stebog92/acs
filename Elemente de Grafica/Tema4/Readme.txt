Elemente de grafica pe calculator - Imperiul Contraataca  
Ciocan Mihai 334CA

Cerinta :
Implementarea unui joc, in care utilizatorul trebuie sa manevreze o nava, sa ocoleasca obstacolele sau sa le distruga.

Utilizare :
Nava se deplaseaza cu ajutorul tastelor W, A, S, D doar atunci cand camera se afla in modul Camera_nava; de asemenea nava poate distruge meteoritii facand click pe acestia.

Nava si meteoritii

Nava este incarcata dintr-un fisier object file format, fisierul fiind preluat de pe siteurile mentionate in pdf. Am construit o clasa Spaceship care in constructor citeste fisierul si se obtine un mesh, ce se foloseste in functia Draw ce randeaza nava. In jurul ei am plasat o sfera (shield), fiind desenata cu ajutorul gluSolidSphere si glMaterialfv cu GL_AMBIENT_AND_DIFFUSE. La fiecare coliziune cu un meteorit se distruge o portiune din shield (10%) si devine mai transparent (pana cand dispare de tot).
Pe locul navei am asezat si o lumina de tip spotlight cu deschidere de 45 de grade, ce e indreptata in fata navei. De asemenea am mai asezat o lumina de tip spotlight in dreapta navei, pentru a sublinia detaliile navei.

Meteoritii sunt de fapt creati cu glutSolidDodecahedron(), si se deplaseaza spre dreapta spre nava. Meteoritii apar random in fata navei. In cazul in care utilizatorul apasa pe unul, meteoritul se inroseste si apoi dispare (la release) fiind distrus de nava.

Detectarea coliziunilor

Detectarea coliziunilor se realizeaza, urmand sfatul din enunt, folosind Bounding Spheres. La fiecare coliziune scade mai intai starea scutului iar apoi (cand scutul ajunge la 0) se distruge nava.

Camerele - am folosit 2 camere, una se afla in lateral fiind indreptata perpedicular pe directia meteoritilor iar cealalta se afla in spatele navei fiind un fel de third person camera.

Implementare:

Tema a fost implementata in Visual Studio 2010, pe Windows 7.





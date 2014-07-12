Elemente de grafica pe calculator - Labyrinth 
Ciocan Mihai 334CA

Cerinta :
Implementarea unui joc, in care personajul principal controlat de catre utilizator sa parcurga un labirint si sa gaseasca un portal.

Utilizare :
Personajul este controlat folosind configuratia clasica de butoane pentru un joc fps si anume tasta W pentru deplasare inainte, tasta S pentru deplasare inapoi, tasta A pentru deplasare in stanga si tasta D pentru deplasare in dreapta. De asemenea mouse-ul permite miscarea camerei. Miscarea camerei presupune rotirea pe axa OX si axa OY a camerei. Utilizatorul poate schimba tipul camerei apasand tasta C, tinand cont ca la inceperea jocului tipul camerei este First-Person. Apasare tastei C itereaza prin celelalte tipuri de camera, ajungandu-se din nou la First-Person.

Jocul presupune gasirea portalului reprezentat printr-o sfera rosie. Intrarea in portal aduce playerul in pozitia de inceput.

Labirintul

Peretii labirintului sunt formati din cuburi de aceeasi dimensiune. Pe langa functia glutSolidCube am folosit si glutWireCube pentru a delimita cuburile intre ele. Peretii sunt construiti strat peste strat (2 foruri) avand inaltimea de 2 cuburi.

Detectarea coliziunilor

Detectarea coliziunilor se realizeaza, urmand sfatul din enunt, folosind Bounding Spheres. Practic la fiecare deplasare( W, A, S, D) execut miscarea si apoi verific daca distanta dintre sfera playerului si sfera peretilor (a cubului respectiv din perete) este mai mica decat suma razelor sferelor. In caz afirmativ, execut o miscare in partea opusa, astfel obiectul stand pe loc.

Analog se verifica daca player-ul a ajuns la portal.


Miscarea camerelor si a playerului

Miscarea camerei utilizand mouseul se realizeaza cu ajutorul glutPassiveMotionFunc(mouse_motion) unde mouse_motion are ca parametrii x si y mouse-ului. Principiul e urmatorul: daca x curent e mai mic decat cel vechi (pe care il retin) inseamna rotire la stanga, altfel rotire la dreapta. Analog si pentru y (rotire pe OX in sus sau in jos);

FirstPerson
Pentru ca rotirea (la stanga sau la dreapta) sa se realizeze natural, camera se aduce in pozitia initiala (adica unghiul pe care il face forward cu planul sa fie 0), se realizeaza rotirea dupa OY iar apoi se aduce in pozitia din care am plecat (se roteste dupa OX).

ThirdPerson
Se realizeaza la fel ca la FirstPerson si in plus se translateaza camera in fata (in locul FirstPerson), se realizeaza modificarile, si se muta camera in pozitia de unde s-a plecat.


Top Down
Top Down este similar cu ThirdPerson doar ca unghiul de rotire pe axa OX este mai mare.

 

Implementare:

Tema a fost implementata in Visual Studio 2010, pe Windows 7.
Schema generala :

Continutul arhivei :

main.cpp : sursa principala a aplicatiei
camera.cpp
Vector3D.h
Readme





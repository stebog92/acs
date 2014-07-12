// EGC
// Laborator 7
//-------------------------------------------------
// ESC    - iesire din program
//-------------------------------------------------

// INCLUDES
//-------------------------------------------------
#include <stdlib.h>
#include <freeglut.h>
#include <stdio.h>
#include <stdarg.h>
#include <string.h>
#include <vector>

#include "Camera.h"
#include "Object3D.h"
#include "Plane.h"
#include "Light.h"
#include "Cube.h"
#include "Vector3D.h"
#include "Vector4D.h"
#include "Meteorite.h"
#include "SpaceShip.h"

// tasta escape
#define ESC	27
#define CAMERA_DINAMICA 1
#define CAMERA_NAVA 2

float *Vector3D::arr;
float *Vector4D::arr;

// VARIABILE
//-------------------------------------------------
// numarul de obiecte
int objectCount;
// obiectul selectat
int selectedIndex = -1;
float x_offset = 0.0;
SpaceShip spaceship;
// camera
Camera *camera_front,*camera_top, *camera_aux;
// vector de obiecte 3D
Cube *objects;
//Meteorite *meteorites;
std::vector<Meteorite> meteorites;
// planul de baza
Plane *ground;
// lumina omni
Light *light_o;
// lumina spot
Light *light_s;
// z meteorites
GLfloat *z_positions, *y_positions;
//lumina spaceship
Light *light_spaceship;
Light *space_ship_enlightened;
// meteorite de distrus;
int remove_meteorite = -1;
int camera_type = CAMERA_DINAMICA;
int posneg []= {-1, 1};
std::vector<Vector3D> stars;

// variabila pentru animatie
GLfloat spin=0.0;

// variabile necesare pentru listele de afisare
#define GROUND	1
int drawLists = 1;
int decisionVar = 0;

// variabila folosita pentru a determina daca listele de afisare trebuiesc recalculate
int recomputeLists = 0;

// identificatori ferestre
// TODO : adaugati identificatori si pentru celelalte ferestre din scena
int fereastraStanga=-1,fereastraDreapta=-1,mainWindow;

// pe ce obiect a fost executat pick
int obiect = -1;

bool collision(Vector3D p1, Vector3D p2, double r1, double r2) {
	double d = sqrt(((p1.x - p2.x)*(p1.x - p2.x)) + ((p1.y - p2.y)*(p1.y - p2.y)) + ((p1.z - p2.z)*(p1.z - p2.z)));
	if (d - r1 - r2 < 0.01)
		return true;
	return false;
}

bool testCollision() {
	for (int i = 0; i < meteorites.size(); i++) {
		if (meteorites[i].destroy_effect && collision(meteorites[i].translation, spaceship.translation, 2, 1)) {
			spaceship.isHit();
			meteorites[i].destroy_effect = false;
		}
	}
	return false;
}

// FUNCTII
//-------------------------------------------------

// functie de initializare a setarilor ce tin de contextul OpenGL asociat ferestrei
void init(void)
{
	
	// Construieste listele de display
	glNewList(GROUND,GL_COMPILE);
	//ground->Draw();
	glEndList();

	// pregatim o scena noua in opengl
	glClearColor(0.0, 0.0, 0.0, 0.0);	// stergem tot
	glEnable(GL_DEPTH_TEST);			// activam verificarea distantei fata de camera (a adancimii)
	glShadeModel(GL_SMOOTH);			// mod de desenare SMOOTH
	glEnable(GL_LIGHTING);				// activam iluminarea
	glEnable(GL_NORMALIZE);				// activam normalizarea normalelor
}

// functie de initializare a scenei 3D
void initScene(void)
{
	// initialize vector arrays
	Vector3D::arr = new float[3];
	Vector4D::arr = new float[4];

	// initializam camera pentru vedere top
	camera_top = new Camera();

	camera_top->SetPosition(new Vector3D(11,4,4));
	camera_top->SetForwardVector(new Vector3D(-1,-0.1,0));
	camera_top->SetRightVector(new Vector3D(0,0,1));
	camera_top->SetUpVector(new Vector3D(0,1,0));

	// initializam camera pentru vedere front ( cea default )
	camera_front = new Camera();
	camera_front->SetPosition(new Vector3D(0.0, 0.0, 30));
	//initializare pozitii meteoriti z
	z_positions = new GLfloat[3];
	z_positions[0] = 1.0;
	z_positions[1] = 3.0;
	z_positions[2] = 5.0;
	z_positions[3] = 7.0;
	z_positions[4] = 9.0;

	//initializare pozitii meteoriti y
	y_positions = new GLfloat[3];
	y_positions[0] = 1.0;
	y_positions[1] = 3.0;
	y_positions[2] = 5.0;
	y_positions[3] = -1.0;
	y_positions[4] = -3.0;


	// vrem 3 obiecte
	objectCount = 1;
	// initializam vectorul de 3 Object3D
	objects = new Cube[objectCount];
	spaceship = SpaceShip();
	spaceship.SetPosition(new Vector3D(2,1,6));
	spaceship.SetRotation(new Vector3D(0,90,0));

	// initializam o noua lumina spot
	light_spaceship = new Light();
	light_spaceship->SetLightType(IlluminationType::Spot);
	light_spaceship->SetPosition(new Vector3D(2, 1, 6));
	light_spaceship->SetDirection(Vector3D(-1,0,0));
	space_ship_enlightened = new Light();
	space_ship_enlightened->SetLightType(IlluminationType::Spot);
	space_ship_enlightened->SetDirection(Vector3D(0,0,1));
	space_ship_enlightened->SetPosition(new Vector3D(2,1,-2));

	for (int i = 0; i < 200; i++) {
		int x = rand() % 30;
		int y = rand() % 30;
		int z = rand() % 2;
		int t = rand() % 2;
		stars.push_back(Vector3D(posneg[z] * x, posneg[t] * y, -10));
		//glTranslatef(posneg[z] * x, posneg[t] * y, -10);
		//glColor3f(1,1,1);
		//glutSolidSphere(0.1, 10, 10);
	}

}

// functie pentru output text
void output(GLfloat x, GLfloat y, char *format,...)
{
	va_list args;

	char buffer[1024],*p;

	va_start(args,format);

	vsprintf(buffer, format, args);

	va_end(args);

	glPushMatrix();
	
	glTranslatef(x,y,-15);

	//glRotatef(180,0,1,0);

	glScalef(0.0050, 0.0050, 0.0); /* 0.1 to 0.001 as required */

	for (p = buffer; *p; p++)
		glutStrokeCharacter(GLUT_STROKE_MONO_ROMAN, *p);

	glPopMatrix();
}

void output_spaceship(GLfloat x, GLfloat y, char *format,...)
{
	va_list args;

	char buffer[1024],*p;

	va_start(args,format);

	vsprintf(buffer, format, args);

	va_end(args);

	glPushMatrix();
	glRotatef (90, 0.0, 1.0, 0.0);
	glTranslatef(0,x,y);

	//glRotatef(180,0,1,0);

	glScalef(0.0050, 0.0050, 0.0); /* 0.1 to 0.001 as required */

	for (p = buffer; *p; p++)
		glutStrokeCharacter(GLUT_STROKE_MONO_ROMAN, *p);

	glPopMatrix();
}

// AFISARE SCENA
//-------------------------------------------------

// functie de desenare a scenei 3D
void drawScene(void)
{

	if (camera_type == CAMERA_DINAMICA) {
		glColor3f(1.0,1.0,1.0);
		glDisable(GL_LIGHTING);
		glLineWidth(1.0);
		output(-25,16,"Spaceship Shield: %d", spaceship.shield);
		output(-25,14,"Spaceship Life: %d", spaceship.health);
		if (spaceship.health == 0) {
			glColor3f(1.0,0.0,0.0);
			output(-25,12,"Spaceship is destroyed! Trust me!");
		}
		for (int i = 0; i < 200; i++) {
			glTranslatef(stars[i].x, stars[i].y, stars[i].z);
			glColor3f(1,1,1);
			glutSolidSphere(0.1, 10, 10);
			glTranslatef(-stars[i].x, -stars[i].y, -stars[i].z);
		}
		glEnable(GL_LIGHTING);
	} else {
		glColor3f(1.0,1.0,1.0);
		glDisable(GL_LIGHTING);
		glLineWidth(1.0);
		output_spaceship(8,-17,"Spaceship Shield: %d %", spaceship.shield);
		output_spaceship(6,-17,"Spaceship Life: %d %", spaceship.health);
		if (spaceship.health == 0) {
			glColor3f(1.0,0.0,0.0);
			output_spaceship(4,-17,"Spaceship is destroyed! Trust me!");
		}
		for (int i = 0; i < 200; i++) {
			glTranslatef(stars[i].z - 25, stars[i].y, stars[i].x);
			glColor3f(1,1,1);
			glutSolidSphere(0.1, 10, 10);
			glTranslatef(-stars[i].z + 25, -stars[i].y, -stars[i].x);
		}
		glEnable(GL_LIGHTING);
	}
	if (obiect >= 0 && obiect < meteorites.size()) {
		meteorites[obiect].Select();
		glEnable(GL_LINE_SMOOTH);
		glMaterialfv(GL_FRONT_AND_BACK,GL_AMBIENT_AND_DIFFUSE,(Vector4D(0,1,0,1)).Array());
		glLineWidth(3);
		glPushMatrix();
		glBegin(GL_LINES);
		glVertex3f(spaceship.translation.x, spaceship.translation.y, spaceship.translation.z); // origin of the line
		glVertex3f(meteorites[obiect].translation.x, meteorites[obiect].translation.y, meteorites[obiect].translation.z); // ending point of the line
		glEnd( );
		glPopMatrix();
	}

	// vizualizare observator in functie de fereastra

	if(glutGetWindow() == fereastraDreapta)
	{
		camera_top->Render();
	}

	if(glutGetWindow() == fereastraStanga)
	{
		camera_front->Render();
	}

	light_spaceship->Render();
	space_ship_enlightened->Render();
	
	// desenare obiecte
	for ( int i = 0 ; i < meteorites.size() ; i ++ )
	{
		if (meteorites[i].xPositionLessThan(11.0)) {
			
			meteorites[i].MoveRight(0.025);
			glPushName(i);
			meteorites[i].Draw();
			glPopName();
			meteorites[i].Draw();
		} else {
			meteorites.erase(meteorites.begin() + i);
		}
		
		//spaceship.Draw();
	}
	spaceship.Draw();
	testCollision();
	
	//meteorites.erase(meteorites.begin() + obiect);
	light_spaceship->Disable();

	// double buffering
	// glutSwapBuffers();

	// redeseneaza scena
	// glutPostRedisplay();
}

// functie de desenare (se apeleaza cat de repede poate placa video)
// se va folosi aceeasi functie de display pentru toate subferestrele ( se puteau folosi si functii distincte 
// pentru fiecare subfereastra )
void display(void)
{
	// stergere ecran
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	
	if(glutGetWindow() == mainWindow)
		return;

	// Reconstruieste listele de display pentru ambele subferestre
	if(recomputeLists)
	{
		glNewList(GROUND,GL_COMPILE);
		ground->Draw();
		glEndList();

		recomputeLists--;
	}

	// TODO : implementati mecanismul de transparenta folosind ALPHA TESTING / BLENDING
	// First Pass - alpha test
	glEnable(GL_ALPHA_TEST);
	glAlphaFunc(GL_EQUAL, GL_ONE);
	drawScene();
	
	// Second Pass - blending
	glAlphaFunc(GL_LESS, GL_ONE);
	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_DST_ALPHA);
	glDepthMask(GL_FALSE);
	drawScene();
	
	glDisable(GL_BLEND);
	glDepthMask(GL_TRUE);

	// double buffering
	glutSwapBuffers();

	// redeseneaza scena
	glutPostRedisplay();
		
}

// PICKING
//-------------------------------------------------

// functia care proceseaza hitrecordurile pentru a vedea daca s-a click pe un obiect din scena
void processhits (GLint hits, GLuint buffer[])
{
   int i;
   GLuint names, *ptr, minZ,*ptrNames, numberOfNames;

   // pointer la inceputul bufferului ce contine hit recordurile
   ptr = (GLuint *) buffer;
   // se doreste selectarea obiectului cel mai aproape de observator
   minZ = 0xffffffff;
   for (i = 0; i < hits; i++) 
   {
      // numarul de nume numele asociate din stiva de nume
      names = *ptr;
	  ptr++;
	  // Z-ul asociat hitului - se retine 
	  if (*ptr < minZ) {
		  numberOfNames = names;
		  minZ = *ptr;
		  // primul nume asociat obiectului
		  ptrNames = ptr+2;
	  }
	  
	  // salt la urmatorul hitrecord
	  ptr += names+2;
  }

  // identificatorul asociat obiectului
  ptr = ptrNames;
  
  obiect = *ptr;
     
}

// functie ce realizeaza picking la pozitia la care s-a dat click cu mouse-ul
void pick(int x, int y)
{
	// buffer de selectie
	GLuint buffer[1024];

	// numar hituri
	GLint nhits;

	// coordonate viewport curent
	GLint	viewport[4];

	// se obtin coordonatele viewportului curent
	glGetIntegerv(GL_VIEWPORT, viewport);
	// se initializeaza si se seteaza bufferul de selectie
	memset(buffer,0x0,1024);
	glSelectBuffer(1024, buffer);
	
	// intrarea in modul de selectie
	glRenderMode(GL_SELECT);

	// salvare matrice de proiectie curenta
	glMatrixMode(GL_PROJECTION);
	glPushMatrix();
	glLoadIdentity();

	// se va randa doar intr-o zona din jurul cursorului mouseului de [1,1]
	glGetIntegerv(GL_VIEWPORT,viewport);
	gluPickMatrix(x,viewport[3]-y,1.0f,1.0f,viewport);

	gluPerspective(45,(viewport[2]-viewport[0])/(GLfloat) (viewport[3]-viewport[1]),0.1,1000);
	glMatrixMode(GL_MODELVIEW);

	// se "deseneaza" scena : de fapt nu se va desena nimic in framebuffer ci se va folosi bufferul de selectie
	drawScene();

	// restaurare matrice de proiectie initiala
	glMatrixMode(GL_PROJECTION);						
	glPopMatrix();				

	glMatrixMode(GL_MODELVIEW);
	// restaurarea modului de randare uzual si obtinerea numarului de hituri
	nhits=glRenderMode(GL_RENDER);	
	
	// procesare hituri
	if(nhits != 0)
		processhits(nhits,buffer);
	else
		obiect=-1;

				
}

// handler pentru tastatura
void keyboard(unsigned char key , int x, int y)
{
	switch (key)
	{
		// la escape se iese din program
	case ESC : exit(0);
		// afisare obiecte wireframe
	case 'q': case 'Q':
		for ( int i = 0 ; i < objectCount ; i ++ )
			objects[i].Wireframe = true;
		
		break;
		// afisare obiecte solid
	case 'z': case 'Z':
		for ( int i = 0 ; i < objectCount ; i ++ )
			objects[i].Wireframe = false;
		
		break;
		// cu [ si ] se selecteaza urmatorul obiect sau predecesorul
	case ']' : 
		// deselectam vechiul obiect
		objects[selectedIndex].Deselect();
		// ciclam prin obiecte, daca am ajuns la 0, luam ultimul obiect din vector
		selectedIndex = (selectedIndex > 0) ? selectedIndex - 1 : objectCount - 1;
		// selectam obiectul nou
		objects[selectedIndex].Select();
		break;
	case '[' : 
		// deselectam vechiul obiect
		objects[selectedIndex].Deselect();
		// ciclam prin obiecte, daca am ajuns la ultimul obiect, luam obiectul de la adresa 0
		selectedIndex = (selectedIndex < objectCount - 1) ? selectedIndex + 1 : 0;
		// selectam obiectul nou
		objects[selectedIndex].Select();
		break;
		// cu < si > se modifica nivelul de detaliu al terenului
	case ',' : ground->SetLevelOfDetail(ground->GetLevelOfDetail()*2); 
			if(drawLists)
				recomputeLists = 2;
			
			break;
	case '.' : ground->SetLevelOfDetail(ground->GetLevelOfDetail()/2); 
			if(drawLists)
				recomputeLists = 2;
			
			break;
		// cu W A S D Q E observatorul se misca prin scena
	case 'w' : case 'W' : 
		//camera_top->MoveForward(0.1); camera_front->MoveForward(0.1);
		if (camera_type == CAMERA_NAVA) {
			spaceship.MoveUp(0.1);
			light_spaceship->SetPosition(&spaceship.translation);
		}
		break;
	case 's' : case 'S' : 
		//camera_top->MoveBackward(0.1); camera_front->MoveBackward(0.1);
		if (camera_type == CAMERA_NAVA) {
			spaceship.MoveDown(0.1);
			light_spaceship->SetPosition(&spaceship.translation);
		}
		break;
	case 'a' : case 'A' : 
		//camera_top->MoveRight(0.1); camera_front->MoveRight(0.1);break;
		if (camera_type == CAMERA_NAVA) {
			spaceship.MoveLeft(0.1);
			light_spaceship->SetPosition(&spaceship.translation);
		}
		break;
	case 'd' : case 'D' : 
		//camera_top->MoveLeft(0.1); camera_front->MoveLeft(0.1);break;
		if (camera_type == CAMERA_NAVA) {
			spaceship.MoveRight(0.1);
			light_spaceship->SetPosition(&spaceship.translation);
		}
		break;
		// Activare/Dezactivare liste de display
	case 'x': case 'X':
			drawLists=(drawLists-1)*(-1);
			recomputeLists = 2;
			break;
	case 'c':
			camera_type++;
			if (camera_type == 3) {
				camera_type = 1;
			}
			camera_aux = camera_front;
			camera_front = camera_top;
			camera_top = camera_aux;
			break;
	default: break;
	}
}

// handler taste speciale
void keyboard(int key , int x, int y)
{
	// incercam sa obtinem un pointer la obiectul selectat
	Object3D *selected;
	// daca nu exista un astfel de obiect
	if( selectedIndex >= 0 && selectedIndex < objectCount )
		selected = &objects[selectedIndex];
	else
		// se iese din functie
		return;

	// cu stanga/dreapta/sus/jos se misca obiectul curent
	switch (key)
	{
	case GLUT_KEY_RIGHT : 
		selected->SetPosition(&(selected->GetPosition() + Vector3D(0.2,0,0))); break;
	case GLUT_KEY_LEFT : 
		selected->SetPosition(&(selected->GetPosition() + Vector3D(-0.2,0,0))); break;
	case GLUT_KEY_DOWN : 
		selected->SetPosition(&(selected->GetPosition() + Vector3D(0,-0.2,0))); break;
	case GLUT_KEY_UP : 
		selected->SetPosition(&(selected->GetPosition() + Vector3D(0,0.2,0))); break;
	}
}

// Functia de idle a GLUT - folosita pentru animatia de rotatie a cuburilor
void spinDisplay()
{
	spin=spin+1;
	if(spin>360.0)
		spin= spin -360.0;
		
}

void movement() {
	x_offset += 0.1;
}

void addMeteoriteDecision() {
	decisionVar += 1;
	if (decisionVar == 35) {
		decisionVar = 0;
		Meteorite new_meteorite = Meteorite();
		int y_random = rand() % 5;
		int z_random = rand() % 5;
		new_meteorite.SetPosition(new Vector3D (-10.0, y_positions[y_random], z_positions[z_random]));
		new_meteorite.SetDiffuse(new Vector4D(0.0,0.0,1.0,1));
		meteorites.push_back(new_meteorite);
	}
}

// Callback pentru a procesa inputul de mouse
void mouse(int buton, int stare, int x, int y)
{
	switch(buton)
	{
	// Am apasat click stanga : porneste animatia si realizeaza picking
	case GLUT_LEFT_BUTTON:
		if(stare == GLUT_DOWN)
		{
			// in aceasta variabila se va intoarce obiectul la care s-a executat pick
			obiect = -1;

			pick(x,y);

			//glutIdleFunc(spinDisplay);
		}
		if(stare == GLUT_UP) {
			if (obiect != -1) {
				meteorites.erase(meteorites.begin() + obiect);
				obiect = -1;
			}
		}
		break;
	case GLUT_RIGHT_BUTTON:
		if(stare== GLUT_DOWN)
			glutIdleFunc(NULL);
		break;
	}
}

// Functie pentru redimensionarea subferestrei dreapta
void reshapeDreapta(int w, int h)
{
	glViewport(0,0, (GLsizei) w, (GLsizei) h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45, (float)w/h, 1.0, 40.0); 
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	// Initializeaza contextul OpenGL asociat ferestrei
	init();

}

// Functie pentru redimensionarea subferestrei stanga
void reshapeStanga(int w, int h)
{
	glViewport(0,0, (GLsizei) w, (GLsizei) h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45, (float)w/h, 1.0, 60.0); 

	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	// Initializeaza contextul OpenGL asociat ferestrei
	init();
}

// functie de proiectie
void reshape(int w, int h)
{
	// Main Window
	glViewport(0,0, (GLsizei) w, (GLsizei) h);
	// calculare aspect ratio ( Width/ Height )
	GLfloat aspect = (GLfloat) w / (GLfloat) h;

	// intram in modul proiectie
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	// incarcam matrice de perspectiva 
	gluPerspective(45, aspect, 1.0, 60);

	// Initializeaza contextul OpenGL asociat ferestrei
	init();

	// Fereastra aplicatiei a fost redimensionata : trebuie sa recream subferestrele

	//if(fereastraDreapta != -1 && camera_type == CAMERA_NAVA)
		//glutDestroyWindow(fereastraDreapta);
	if(fereastraStanga != -1)
		glutDestroyWindow(fereastraStanga);

	// Creeaza fereastra stanga
	//if (camera_type == CAMERA_DINAMICA) {
		fereastraStanga = glutCreateSubWindow(mainWindow,0,0,w,h);

		glutDisplayFunc(display);
		glutReshapeFunc(reshapeStanga);
		glutMouseFunc(mouse);
		glutKeyboardFunc(keyboard);
		glutSpecialFunc(keyboard);
	//}

	// Creeaza fereastra dreapta
	//if (camera_type == CAMERA_NAVA) {
	/*	fereastraDreapta = glutCreateSubWindow(mainWindow,w,0,w,h);

		glutDisplayFunc(display);
		glutReshapeFunc(reshapeDreapta);
		glutMouseFunc(mouse);
		glutKeyboardFunc(keyboard);
		glutSpecialFunc(keyboard);
	}*/
}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE|GLUT_RGB);
	int w = 1200, h= 1000;
	glutInitWindowSize(w,h);
	glutInitWindowPosition(0,0);
	
	// Main window
	mainWindow = glutCreateWindow("Lab7");

	glutDisplayFunc(display);
	glutIdleFunc(movement);
	glutIdleFunc(addMeteoriteDecision);
	glutKeyboardFunc(keyboard);
	glutReshapeFunc(reshape);
	glutSpecialFunc(keyboard);
	glutMouseFunc(mouse);

	// Initializeaza scena 3D
	initScene();

	glutMainLoop();
	return 0;
}
#pragma once

#include <freeglut.h>
#include "Object3D.h"
#include "Camera.h"
class SpaceShip : public Object3D
{
public:
	SpaceShip(void);
	~SpaceShip(void);
	void Draw();
	typedef struct Vertex {
		float x, y, z;
	} Vertex;

	typedef struct Face {
		Face(void) : nverts(0), verts(0) {};
		int nverts;
		Vertex **verts;
		float normal[3];
	} Face;

	typedef struct Mesh {
		Mesh(void) : nverts(0), verts(0), nfaces(0), faces(0) {};
		int nverts;
		Vertex *verts;
		int nfaces;
		Face *faces;
	} Mesh;
	Mesh* mesh;
	int shield;
	int health;
	
public:
	// Functie pentru a seta latura meteoritului
	void SetLatura(GLfloat latura);
	// Seteaza culoare difuza ( atentie , la testul alfa se foloseste componenta A din culoarea difuza !!!_
	void SetDiffuse(Vector4D *color);
	void MoveRight ( GLfloat Distance );
	void MoveLeft (GLfloat Distance);
	void MoveUp ( GLfloat Distance );
	void MoveDown (GLfloat Distance);
	bool xPositionLessThan(GLfloat x);
	Mesh* ReadOffFile(const char *filename);
	void isHit();

private:
	// latura meteoritului
	GLfloat latura;
	// culoare difuza
	Vector4D diffuse;
	// culoare ambientala
	Vector4D ambient;
	// culoare speculara
	Vector4D specular;
};


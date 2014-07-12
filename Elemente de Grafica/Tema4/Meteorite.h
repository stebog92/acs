#pragma once

#include <freeglut.h>
#include "Object3D.h"
#include "Camera.h"
class Meteorite :  public Object3D
{
public:
	Meteorite(void);

	void Draw();
	
	public:
	// Functie pentru a seta latura meteoritului
	void SetLatura(GLfloat latura);
	// Seteaza culoare difuza ( atentie , la testul alfa se foloseste componenta A din culoarea difuza !!!_
	void SetDiffuse(Vector4D *color);
	void MoveRight ( GLfloat Distance );
	bool xPositionLessThan(GLfloat x);
	bool destroy_effect;
	
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


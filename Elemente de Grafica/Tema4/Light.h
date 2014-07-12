// EGC
// Laborator 7
//-------------------------------------------------

#ifndef LIGHT_H
#define LIGHT_H

#include <freeglut.h>
#include "Object3D.h"
#include "Camera.h"

// tipul de iluminare
enum IlluminationType
{
	Ideal, // lumina omnidirectionala
	Spot   // lumina directionala
};

/*
	Light
	Clasa pentru desenarea si activarea unei lumini
*/

// derivata din object3D
class Light : public Object3D
{

// VARIABILE STATICE
//-------------------------------------------------
private:
	static int baseId;

// VARIABILE
//-------------------------------------------------
public:
	// tipul luminii - nu este folosit, inca
	IlluminationType LightType;

private:
	// id-ul asignat. pleaca din 0 si este folosit pentru GL_LIGHT0 + id
	int id;
	// lumina difuza
	Vector4D diffuse;
	// lumina ambientala
	Vector4D ambient;
	// lumina speculara
	Vector4D specular;
	Vector3D direction;

	// pentru spot :

// FUNCTII
//-------------------------------------------------
public:
	// constructor fara parametri
	Light();

	// plaseaza lumina in scena si o activeaza
	void Render();
	// dezactiveaza lumina
	void Disable();
	// seteaza tipul de lumina
	void SetLightType(IlluminationType LightType);
	void SetDirection(Vector3D v);

};

#endif
#include "Meteorite.h"


Meteorite::Meteorite(void) : Object3D()
{
	// valori default
	diffuse = Vector4D(1,1,1,1);
	ambient = Vector4D(0,0,0,0);
	specular = Vector4D(1,1,1,1);
	color = Vector3D(1,1,1);
	scale = Vector3D(0.75,0.75,0.75);
	destroy_effect = true;
	
	// default , nu este wireframe
	Wireframe = false;

	latura = 1.0;
}

void Meteorite::SetLatura(GLfloat _latura)
{
	latura = _latura;
}

void Meteorite::MoveRight ( GLfloat Distance )
{
	translation = translation + Vector3D(Distance , 0.0 ,0.0);
}

void Meteorite::SetDiffuse(Vector4D *color)
{
	diffuse = *color;
}

bool Meteorite::xPositionLessThan(GLfloat x) {
	return (translation.x < x);
}

// DRAW
// Suprascriem prin polimorfism functia de desenare a clasei parinte 
// pentru a avea propria functie de desenare
//-------------------------------------------------
void Meteorite::Draw ()
{
	
	// daca nu este vizibil, nu-l desenam
	if(!Visible)
	return;

	glPushMatrix();

	// translatie
	glTranslatef( translation.x , translation.y , translation.z );

	// rotatie
	glRotatef( rotation.x , 1.0 , 0.0 , 0.0 );
	glRotatef( rotation.y , 0.0 , 1.0 , 0.0 );
	glRotatef( rotation.z , 0.0 , 0.0 , 1.0 );

	// scalare
	glScalef( scale.x , scale.y , scale.z);

	// setari de material :
	// daca nu este selectat
	if( !selected )
	{
		// culoare normala
		glColor3f(color.x,color.y,color.z);
		glMaterialfv(GL_FRONT_AND_BACK,GL_AMBIENT_AND_DIFFUSE,(Vector4D(diffuse.x,diffuse.y,diffuse.z,diffuse.a)).Array());
	}
	else
	{
		// culoarea atunci cand obiectul este selectat
		glColor3f(SelectedColor.x, SelectedColor.y, SelectedColor.z);
		glMaterialfv(GL_FRONT_AND_BACK,GL_AMBIENT_AND_DIFFUSE,(Vector4D(SelectedColor.x,SelectedColor.y,SelectedColor.z,1)).Array());
	}
	// culoare speculara, default
	glMaterialfv(GL_FRONT_AND_BACK,GL_SPECULAR,(Vector4D(0.1,0.1,0.1,1)).Array());

	// daca este wireframe
	if( Wireframe )
	{
		glutWireDodecahedron();
	}
	// daca nu este wireframe
	else
	{
		glutSolidDodecahedron();
	}
	glPopMatrix();
}

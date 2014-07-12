// EGC
// Laborator 7
//-------------------------------------------------

#include "camera.h"
#include "math.h"


// constructor
Camera::Camera()
{
	//initializare cu valorile standard OpenGL
	Position = Vector3D (0.0, 0.0,	12);
	ForwardVector = Vector3D( 0.0, 0.0, -1.0);
	RightVector = Vector3D (1.0, 0.0, 0.0);
	UpVector = Vector3D (0.0, 1.0, 0.0);
}

void Camera::RotateX (GLfloat Angle)
{
}

void Camera::RotateY (GLfloat Angle)
{
}

void Camera::RotateZ (GLfloat Angle)
{
}

// rotire fata de centru, la o distanta generica 5
void Camera::RotateXCenter (GLfloat Angle)
{
}

// rotire fata de centru, la o distanta generica 5
void Camera::RotateYCenter (GLfloat Angle)
{
	float distance = 5;
	MoveForward(distance);
	RotateY(Angle);
	MoveBackward(distance);
}

// rotire fata de centru, la o distanta generica 5
void Camera::RotateZCenter (GLfloat Angle)
{
	float distance = 5;
	MoveForward(distance);
	RotateZ(Angle);
	MoveBackward(distance);
}

// plasare observator in scena
void Camera::Render( void )
{
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	// punctul catre care se uita camera
	Vector3D ViewPoint = Position + ForwardVector;

	// stim vectorul UpVector, folosim LookAt
	gluLookAt(	Position.x,Position.y,Position.z,
				ViewPoint.x,ViewPoint.y,ViewPoint.z,
				UpVector.x,UpVector.y,UpVector.z);
}

// miscari simple, pe toate axele

void Camera::MoveForward( GLfloat Distance )
{
	Position = Position + (ForwardVector * Distance);
}

void Camera::MoveBackward( GLfloat Distance )
{
	Position = Position + (ForwardVector * -Distance);
}

void Camera::MoveRight ( GLfloat Distance )
{
	Position = Position + ( RightVector * -Distance);
}

void Camera::MoveLeft ( GLfloat Distance )
{
	Position = Position + ( RightVector * Distance);
}

void Camera::MoveUpward( GLfloat Distance )
{
	Position = Position + ( UpVector * Distance );
}

void Camera::MoveDownward( GLfloat Distance )
{
	Position = Position + ( UpVector * -Distance );
}

// seteaza pozitie
void Camera::SetPosition(Vector3D *value)
{
	Position = *value;
}

// seteaza forward vector
void Camera::SetForwardVector(Vector3D *value)
{
	ForwardVector = *value;
}

// seteaza right vector
void Camera::SetRightVector(Vector3D *value)
{
	RightVector = *value;
}

// seteaza up vector
void Camera::SetUpVector(Vector3D *value)
{
	UpVector = *value;
}
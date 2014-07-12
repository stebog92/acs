//-----------------------------------------------------------------------------------------------
//					LAB 4
//
//	Fisiere de interes: main.cpp 
//
//	Happy coding.
//----------------------------------------------------------------------------------------------
#pragma once
#include <cstdlib>
#include "dependinte\freeglut.h"
#include <iostream>
#include <cmath>

float fov=90;

//var globale
class float3{
public:
	float3(){x=y=z=0;}
	float3(float x, float y, float z){
		this->x=x;
		this->y=y;
		this->z=z;
	}
	~float3(){};
	void equalscrossproduct(float3 a, float3 b){
		x=a.y*b.z-a.z*b.y;
		y=a.z*b.x-a.x*b.z;
		z=a.x*b.y-a.y*b.x;
	}
	void equalsdifference(float3 a, float3 b){
		x=a.x-b.x;
		y=a.y-b.y;
		z=a.z-b.z;
	}
	void normalize(){
		float dim=sqrt(x*x+y*y+z*z);
		if(dim==0) return;
		x/=dim;
		y/=dim;
		z/=dim;
	}
	float x,y,z;
};
float3 culoare[3][3];
bool wireframe;


void setView(float3 eye, float3 center, float3 up){
	
	float3 forward;
	forward.equalsdifference(center, eye);
	forward.normalize();
	up.normalize();
	
	up.normalize(); 
	float3 right;
	right.equalscrossproduct(forward, up);

	float3 newup;
	newup.equalscrossproduct(right, forward);

	float m[16];
	m[0] = right.x;
	m[1] = newup.x;
	m[2] = -forward.x;
	m[3] = 0;
	m[4] = right.y;
	m[5] = newup.y; 
	m[6] = -forward.y; 
	m[7] = 0;
	m[8] = right.z;
	m[9] = newup.z;
	m[10] = -forward.z;
	m[11] = 0;
	m[12] = 0;
	m[13] = 0;
	m[14] = 0;
	m[15] = 1;

	glLoadMatrixf(m);
	glTranslatef(-eye.x, -eye.y, -eye.z);


}
void setProjection(float fovy,float aspect,float znear, float zfar){
	
	float f = 1.0f / tan((fovy/2) * 3.1415 / 180);
	float m[16];

	m[0]=f / aspect;
	m[1] = 0;
	m[2] = 0;
	m[3] = 0;
	m[4] = 0;
	m[5] = f; 
	m[6] = 0; 
	m[7] = 0;
	m[8] = 0;
	m[9] = 0;
	m[10] = (zfar+znear)/(znear-zfar); 
	m[11] = -1;
	m[12] = 0;
	m[13] = 0;
	m[14] = 2*zfar*znear/(znear-zfar);
	m[15] = 0;

	glLoadMatrixf(m);
	
}



void init(void)
{
	//background negru
	glClearColor(0.0, 0.0, 0.0, 0.0);
	glEnable(GL_DEPTH_TEST);

	//
	for(int i=0;i<3;i++)
		for(int j=0;j<3;j++){
			culoare[i][j].x=125.0f/6.0f*(i*3+j)/255.0f;
			culoare[i][j].y=125.0f/255.0f;
			culoare[i][j].z=125.0f/6.0f*(i+j*3)/255.0f;
		}

}

void displayFunctionCallback(void)
{

	//clear framebuffer de culoare
	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);


	//Desenare matrice de cuburi
	for(int i=0;i<3;i++)
		for(int j=0;j<3;j++)
		{
			glPushMatrix();
			
			//Pozitia initiala a obiectului
			glTranslatef(3.0f-(i*3),2.0f-(j*2.5f),5.0f);

	
			//culoare
			glColor3f(culoare[i][j].x,culoare[i][j].y,culoare[i][j].z);

			//desenare
			switch(i*3+j){
				case 0:
						if(wireframe) glutWireCube(1);
						else glutSolidCube(1);
					break;
				case 1:
						if(wireframe) glutWireCone(1,1,20,20);
						else glutSolidCone(1,1,20,20);
					break;
				case 2:
						if(wireframe) glutWireCylinder(1,1,20,20);
						else glutSolidCylinder(1,1,20,20);
					break;
				case 3:
						if(wireframe) glutWireRhombicDodecahedron();
						else glutSolidRhombicDodecahedron();
					break;
				case 4:
						if(wireframe) glutWireTeapot(1);
						else glutSolidTeapot(1);
					break;
				case 5:
						if(wireframe) glutWireSphere(1,20,20);
						else glutSolidSphere(1,20,20);
					break;
				case 6:
						if(wireframe) glutWireDodecahedron();
						else glutSolidDodecahedron();
					break;
				case 7:
						if(wireframe) glutWireTorus(0.5,1,20,20);
						else glutSolidTorus(0.5,1,20,20);
					break;
				case 8:
						if(wireframe) glutWireTetrahedron();
						else glutSolidTetrahedron();
					break;
				default:
					break;
			}
					
			glPopMatrix();
		
		}

	glutSwapBuffers();
}

void idleFunctionCallback()
{
	glutPostRedisplay();
}


void reshapeMyCallback(int w, int h){
	//viewport
	glViewport(0,0, w, h);

	//proiectie
	float aspect = (float) w / (float) h;
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	setProjection(fov,aspect,0.1f,60.0f);

	//vizualizare
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	setView(float3(0,1,-2),float3(0,1,1), float3(0,1,0));
}
void reshapeFunctionCallback(int w, int h)
{
	//viewport
	glViewport(0,0, w, h);

	//proiectie
	float aspect = (float) w / (float) h;
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(fov, aspect, 0.1f, 60.0f);

	//vizualizare
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	gluLookAt(0.0, 1.0, -2.0,0.0, 1.0, 1.0, 0.0, 1.0, 0.0);  
}

void mouseFunctionCallback(int buton, int stare, int x, int y)
{
	switch(buton)
	{
	case GLUT_LEFT_BUTTON:
		if(stare == GLUT_DOWN)
			glutIdleFunc(idleFunctionCallback);
		break;
	case GLUT_RIGHT_BUTTON:
		if(stare== GLUT_DOWN)
			glutIdleFunc(NULL);
		break;
	}
}

void keyboardFunctionCallback(unsigned char key, int x, int y){
	switch(key){
		case 32:
			wireframe=!wireframe;
			break;
		case 27:
			exit(0);
			break;
		case '.':
			
			break;
		case ',':
			
			break;
		case '1':
			glutReshapeFunc(reshapeFunctionCallback);	//original
			break;
		case '2':
			glutReshapeFunc(reshapeMyCallback);			// cu functiile noastre
			break;
		default:
			break;
	}
	//reshape on key
	glutReshapeWindow(glutGet(GLUT_WINDOW_WIDTH),glutGet(GLUT_WINDOW_HEIGHT));
}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE|GLUT_RGB|GLUT_DEPTH);
	glutInitWindowSize(640,480);
	glutInitWindowPosition(100,100);
	glutCreateWindow("Lab 4");
	
	//callbacks
	glutDisplayFunc(displayFunctionCallback);
	glutReshapeFunc(reshapeFunctionCallback);
	glutMouseFunc(mouseFunctionCallback);
	glutKeyboardFunc(keyboardFunctionCallback);
	glutIdleFunc(idleFunctionCallback);
	
	//init
	init();

	//run
	glutMainLoop();
	return 0;
}
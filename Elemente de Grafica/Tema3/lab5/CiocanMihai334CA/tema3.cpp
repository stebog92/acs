//include librarii de opengl, glut si glu
#pragma comment(lib, "opengl32.lib")
#pragma comment(lib, "glu32.lib")
#pragma comment(lib, "glut32.lib")

//includes
#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <sstream>
#include <string>
#include <vector>

//glut and glew
#include "glut.h"

//ground
#include "ground.h"

//camera
#include "camera.h"

#define PI 3.14159265
using namespace std;

//cam
Camera camera;

//cuburi
float angle=0;
int x, y, camera_mode, move_camera_it = 60;
GLfloat person_angle;

bool init = true, change_camera = false, move_camera_back = false, initial_pos2 = false, initialize_walls = false;
float count_OX_Down = 0, count_OX_Down_TP;
Vector3D center_poz_global, player;
vector <Vector3D> walls;
Vector3D redBalloon, initialPosition;

bool collision(Vector3D p1, Vector3D p2, double r1, double r2) {
	double d = sqrt(((p1.x - p2.x)*(p1.x - p2.x)) + ((p1.y - p2.y)*(p1.y - p2.y)) + ((p1.z - p2.z)*(p1.z - p2.z)));
	if (d - r1 - r2 < 0.01)
		return true;
	return false;
}

bool testCollision() {
	for (int i = 0; i < walls.size(); i++) {
		if (collision(walls[i], player, 1, 1)) {
			return true;
		}
	}
	return false;
}

bool reachedRedPoint() {
	return collision (player, redBalloon, 1, 1);
}

void drawBitmapText(char *string,float x,float y,float z) 
{  
	char *c;
	glColor3f (1, 0, 0);
	glRasterPos3f(x, y,z);
	for (c=string; *c != '\0'; c++) 
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, *c);
	}
}

//functie afisare
void display(){
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	//setup view
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	camera.render();
	
	if (init) {
		camera.translate_Right(15);
		initialPosition = camera.position;
		init = false;
		redBalloon  = Vector3D (-3, 1, -18);
	}

	glPushMatrix();
		glTranslatef (-3, 0.5, -18);
		glColor3f (1, 0, 0);
		glutSolidSphere(1, 50, 50);
		glPopMatrix();
	//your code here
	glPushMatrix();
	if (camera_mode == 1) {
		Vector3D center_poz;
		center_poz = camera.position + camera.forward * 6;
		glTranslatef(center_poz.x, center_poz.y, center_poz.z);
		player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
		glColor3f(0.0, 1.0, 0.0);
		glRotatef(180 + person_angle, 0.0, 1.0, 0.0);
		glutSolidCone(0.5, 1, 50, 50);
		if (change_camera) {
			camera.rotateFPS_OX (0.6);
			camera.translate_Forward(-6);
			change_camera = false;
		}
	} else if (camera_mode == 2) {
		if (!initial_pos2) {
			camera.translate_Forward(6);
			camera.rotateFPS_OX(-0.6);
			camera.rotateFPS_OX(-count_OX_Down);
			count_OX_Down = 0;
			initial_pos2 = true;
		}
		Vector3D center_poz;
		center_poz = camera.position + camera.forward * 20;
		glTranslatef(center_poz.x, center_poz.y, center_poz.z);
		player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
		glColor3f(0.0, 1.0, 0.0);
		glRotatef(180 + person_angle, 0.0, 1.0, 0.0);
		glutSolidCone(0.5, 1, 50, 50);
		if (change_camera) {
			camera.rotateFPS_OX(1.5);
			camera.translate_Forward(-20);
			change_camera = false;
		}
	} else if (camera_mode == 0 && change_camera) {
		camera.translate_Forward(20);
		camera.rotateFPS_OX(-1.5);
		change_camera = false;
	}
	glPopMatrix();

	if (camera_mode == 2) {
		drawBitmapText ("Find the Red Gate!", player.x + 5, player.y + 3, player.z - 6);
	}

	//ground
	draw_ground(34, 40, 2, 2, -2);
	int xw, yw, zw;
	glPushMatrix();
	glColor3f(0,0,0);
	glTranslatef(-18, -1, -21);
	xw = -18;
	yw = -1;
	zw = -21;
	for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 17; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(2, 0, 0);
			glutSolidCube (1.99);
			glColor3f(0, 0, 0);
			glutWireCube(2);
			xw += 2;
			if (!initialize_walls) {
				walls.push_back (Vector3D(xw, yw, zw));
			}
		}
		xw -= 34;
		yw += 2;
		glTranslatef(-34, 2, 0);
	}
	glTranslatef(12, -4, 0);
	xw += 12;
	yw -= 4;
	for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 2; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(0, 0, 2);
			glutSolidCube (1.99);
			glColor3f(0, 0, 0);
			glutWireCube(2);
			zw += 2;
			if (!initialize_walls) {
				walls.push_back (Vector3D(xw, yw, zw));
			}
		}
		yw += 2;
		zw -= 4;
		glTranslatef(0, 2, -4);
	}
	glTranslatef(6, -4, 0);
	xw += 6;
	yw -= 4;
	for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 14; i++) {

			glColor3f(0.52,0.807,0.98);
			glTranslatef(0, 0, 2);

			zw += 2;
			if (i != 6 && i != 7 && i != 8 && i != 9 && i != 10) {
				glutSolidCube (1.99);
				glColor3f(0, 0, 0);
				glutWireCube(2);
				if (!initialize_walls) {
					walls.push_back (Vector3D(xw, yw, zw));
				}
			}
		}
		yw += 2;
		zw -= 28;
		glTranslatef(0, 2, -28);
	}

	glTranslatef(6, -4, 16);
	yw -= 4;
	xw += 6;
	zw += 16;
	for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 10; i++) {

			glColor3f(0.52,0.807,0.98);
			glTranslatef(0, 0, 2);
			glutSolidCube (1.99);
			glColor3f(0, 0, 0);
			glutWireCube(2);
			zw += 2;
			if (!initialize_walls) {
				walls.push_back (Vector3D(xw, yw, zw));
			}
		}
		zw -= 20;
		yw += 2;
		glTranslatef(0, 2, -20);
	}

	glTranslatef(6, -4, -10);
	xw += 6;
	zw -= 10;
	yw -= 4;
	for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 17; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(0, 0, 2);
			zw += 2;
			if (i != 6 && i != 7 && i != 8 && i != 9) {
				glutSolidCube (1.99);
				glColor3f(0, 0, 0);
				glutWireCube(2);
				if (!initialize_walls) {
					walls.push_back (Vector3D(xw, yw, zw));
				}
			}
		}
		zw -= 34;
		yw += 2;
		glTranslatef(0, 2, -34);
	}

	glTranslatef(2, -4, 0);
	xw += 2;
	yw -= 4;
	for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 3; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(-2, 0, 0);
			glutSolidCube (1.99);
			glColor3f(0, 0, 0);
			glutWireCube(2);
			xw -= 2;
			if (!initialize_walls) {
				walls.push_back (Vector3D(xw, yw, zw));
			}
		}
		xw += 6;
		yw += 2;
		glTranslatef(6, 2 ,0);
	}

	glTranslatef(-2, -4, 6);
	xw -= 2;
	yw -= 4;
	zw += 6;
	for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 12; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(-2, 0, 0);
			glutSolidCube (1.99);
			glColor3f(0, 0, 0);
			glutWireCube(2);
			xw -= 2;
			if (!initialize_walls) {
				walls.push_back (Vector3D(xw, yw, zw));
			}
		}
		xw += 24;
		yw += 2;
		glTranslatef(24, 2, 0);
	}

	glTranslatef(-6, -4, 6);
	xw -= 6;
	yw -= 4;
	zw += 6;
	for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 6; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(-2, 0, 0);
			glutSolidCube (1.99);
			glColor3f(0, 0, 0);
			glutWireCube(2);
			xw -= 2;
			if (!initialize_walls) {
				walls.push_back (Vector3D(xw, yw, zw));
			}
		}
		xw += 12;
		yw += 2;
		glTranslatef(12, 2, 0);
	}

	glTranslatef(-4, -4, 12);
	xw -= 4;
	yw -= 4;
	zw += 12;
	for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 7; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(-2, 0, 0);
			xw -= 2;
			if (i != 6) {
				glutSolidCube (1.99);
				glColor3f(0, 0, 0);
				glutWireCube(2);
				if (!initialize_walls) {
					walls.push_back (Vector3D(xw, yw, zw));
				}
			}
		}
		xw += 14;
		yw += 2;
		glTranslatef(14, 2, 0);
	}

	glTranslatef(10, -4, 6);
	xw +=  10;
	yw -= 4;
	zw += 6;
	for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 8; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(-2, 0, 0);
			xw -= 2;
			if (i != 2) {
				glutSolidCube (1.99);
				glColor3f(0, 0, 0);
				glutWireCube(2);
				if (!initialize_walls) {
					walls.push_back (Vector3D(xw, yw, zw));
				}
			}
		}
		xw += 16;
		yw += 2;
		glTranslatef(16, 2, 0);
	}

	glTranslatef(-24, -4, 2);
	xw -= 24;
	yw -= 4;
	zw += 2;
	for (int j = 0; j < 2; j++) {
		for (int i = 0; i < 16; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(0, 0, -2);
			zw -= 2;
			if (i != 12) {
				glutSolidCube(1.99);
				glColor3f(0,0,0);
				glutWireCube(2);
				if (!initialize_walls) {
					walls.push_back (Vector3D(xw, yw, zw));
				}
			}
		}
		yw += 2;
		zw += 32;
		glTranslatef(0, 2, 32);
	}

	glTranslatef(6, -4, -12);
	xw += 6;
	yw -= 4;
	zw -= 12;
	for (int j = 0; j < 2; j++) {	
		for (int i = 0; i < 3; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(0, 0, -2);
			glutSolidCube (1.99);
			glColor3f(0, 0, 0);
			glutWireCube(2);
			zw -= 2;
			if (!initialize_walls) {
				walls.push_back (Vector3D(xw, yw, zw));
			}
		}
		yw += 2;
		zw += 6;
		glTranslatef(0, 2, 6);
	}

	glTranslatef(-12, -4, 16);
	xw -= 12;
	yw -= 4;
	zw += 16;
	for (int j = 0; j < 2; j++) {	
		for (int i = 0; i < 20; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(0, 0, -2);
			glutSolidCube (1.99);
			glColor3f(0, 0, 0);
			glutWireCube(2);
			zw -= 2;
			if (!initialize_walls) {
				walls.push_back (Vector3D(xw, yw, zw));
			}
		}
		yw += 2;
		zw += 40;
		glTranslatef(0, 2, 40);
	}

	glTranslatef(0, -4, 0);
	yw -= 4;
	for (int j = 0; j < 2; j++) {	
		for (int i = 0; i < 17; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(2, 0, 0);
			glutSolidCube (1.99);
			glColor3f(0, 0, 0);
			glutWireCube(2);
			xw += 2;
			if (!initialize_walls) {
				walls.push_back (Vector3D(xw, yw, zw));
			}
		}
		yw += 2;
		xw -= 34;
		glTranslatef(-34, 2, 0);
	}

	glTranslatef(36, -4, 0);
	xw += 36;
	yw -= 4;
	for (int j = 0; j < 2; j++) {	
		for (int i = 0; i < 20; i++) {
			glColor3f(0.52,0.807,0.98);
			glTranslatef(0, 0, -2);
			glutSolidCube (1.99);
			glColor3f(0, 0, 0);
			glutWireCube(2);
			zw -= 2;
			if (!initialize_walls) {
				walls.push_back (Vector3D(xw, yw, zw));
			}
		}
		yw += 2;
		zw += 40;
		glTranslatef(0, 2, 40);
	}
	glPopMatrix();
	initialize_walls = true;

	//swap buffers
	glutSwapBuffers();
}

void reshape(int width, int height){
	//set viewport
	glViewport(0,0,width,height);

	//set proiectie
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45,(float)width/(float)height,0.2,100);
}

void idle();

void keyboard(unsigned char ch, int x, int y){
	switch(ch){
		case 27:	//esc
			exit(0);
			break;

		case 'w':
			if (camera_mode == 0) {
				camera.rotateFPS_OX (-count_OX_Down);
				camera.translate_Forward(0.5);
				camera.rotateFPS_OX (count_OX_Down);
				Vector3D center_poz = camera.position;
				player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
				if (testCollision()) {
					camera.rotateFPS_OX (-count_OX_Down);
					camera.translate_Forward(-0.5);
					camera.rotateFPS_OX (count_OX_Down);
				}
				
			} else if (camera_mode == 2) {
					camera.rotateFPS_OX(-1.5);
					camera.translate_Forward(0.5);
					camera.rotateFPS_OX(1.5);

					Vector3D center_poz = camera.position + camera.forward * 20;
					player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
					if (testCollision()) {
						camera.rotateFPS_OX(-1.5);
						camera.translate_Forward(-0.5);
						camera.rotateFPS_OX(1.5);
					}
			} else {
				camera.rotateFPS_OX (-count_OX_Down);
				camera.rotateFPS_OX (-0.6);
				camera.translate_Forward(0.5);
				camera.rotateFPS_OX (0.6);
				camera.rotateFPS_OX (count_OX_Down);
				Vector3D center_poz = camera.position + camera.forward * 6;
				player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
				if (testCollision()) {
					camera.rotateFPS_OX (-count_OX_Down);
					camera.rotateFPS_OX (-0.6);
					camera.translate_Forward(-0.5);
					camera.rotateFPS_OX (0.6);
					camera.rotateFPS_OX (count_OX_Down);
				}
			}
			break;
		case 'a':
			camera.translate_Right(-0.5);
			if (camera_mode == 2) {
				Vector3D center_poz = camera.position + camera.forward * 20;
				player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
				if (testCollision()) {
					camera.translate_Right(0.5);
				}
			} else if (camera_mode == 0) {
				Vector3D center_poz = camera.position;
				player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
				if (testCollision()) {
					camera.translate_Right(0.5);
				}
			} else {
				Vector3D center_poz = camera.position + camera.forward * 6;
				player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
				if (testCollision()) {
					camera.rotateFPS_OX (-count_OX_Down);
					camera.rotateFPS_OX (-0.6);
					camera.translate_Right(0.5);
					camera.rotateFPS_OX (0.6);
					camera.rotateFPS_OX (count_OX_Down);
				}
			}
			break;
		case 's':
			if (camera_mode == 0) {
				camera.rotateFPS_OX (-count_OX_Down);
				camera.translate_Forward(-0.5);
				camera.rotateFPS_OX (count_OX_Down);
				Vector3D center_poz = camera.position;
				player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
				if (testCollision()) {
					camera.rotateFPS_OX (-count_OX_Down);
					camera.translate_Forward(0.5);
					camera.rotateFPS_OX (count_OX_Down);
				}
			} else if (camera_mode == 2) {
				camera.rotateFPS_OX(-1.5);
				camera.translate_Forward(-0.5);
				camera.rotateFPS_OX(1.5);

				Vector3D center_poz = camera.position + camera.forward * 20;
				player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
				if (testCollision()) {
					camera.rotateFPS_OX(-1.5);
					camera.translate_Forward(0.5);
					camera.rotateFPS_OX(1.5);
				}
			} else {
				camera.rotateFPS_OX (-count_OX_Down);
				camera.rotateFPS_OX (-0.6);
				camera.translate_Forward(-0.5);
				camera.rotateFPS_OX (0.6);
				camera.rotateFPS_OX (count_OX_Down);
				Vector3D center_poz = camera.position + camera.forward * 6;
				player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
				if (testCollision()) {
					camera.rotateFPS_OX (-count_OX_Down);
					camera.rotateFPS_OX (-0.6);
					camera.translate_Forward(0.5);
					camera.rotateFPS_OX (0.6);
					camera.rotateFPS_OX (count_OX_Down);
				}
			}
			break;
		case 'd':
			camera.translate_Right(0.5);
			if (camera_mode == 2) {
				Vector3D center_poz = camera.position + camera.forward * 20;
				player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
				if (testCollision()) {
					camera.translate_Right(-0.5);
				}
			} else if (camera_mode == 0) {
				Vector3D center_poz = camera.position;
				player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
				if (testCollision()) {
					camera.translate_Right(-0.5);
				}
			} else {
				Vector3D center_poz = camera.position + camera.forward * 6;
				player = Vector3D(center_poz.x, center_poz.y, center_poz.z);
				if (testCollision()) {
					camera.rotateFPS_OX (-count_OX_Down);
					camera.rotateFPS_OX (-0.6);
					camera.translate_Right(-0.5);
					camera.rotateFPS_OX (0.6);
					camera.rotateFPS_OX (count_OX_Down);
				}
			}
			break;
		case 'q':
			camera.rotateTPS_OY(-0.1,10);
			break;
		case 'e':
			camera.rotateTPS_OY(0.1,10);
			break;
		case 'z':
			camera.rotateFPS_OY(-0.1);
			break;
		case 'x':
			camera.rotateFPS_OY(0.1);
			break;
		case 'c':
			//camera.translate_Up(0.5);
			if (camera_mode == 2) {
				camera_mode = 0;
			}
			else {
				camera_mode ++;
			}
			if (camera_mode == 2) {
				initial_pos2 = false;
			}
			change_camera = true;
			break;
		case 'v':
			camera.translate_Up(-0.5);
			break;
		case 'r':
			camera.rotateFPS_OX(0.1);
			break;
		case 't':
			camera.rotateFPS_OX(-0.1);
		case 'y':
			camera.rotateFPS_OZ(0.1);
			break;
		case 'u':
			camera.rotateFPS_OZ(-0.1);
			break;
		default:
			break;
	}

	if (reachedRedPoint()) {
		camera.position = initialPosition;
		if (camera_mode == 1) {
			camera.translate_Forward(-6);
		} else if (camera_mode == 2) {
			camera.translate_Forward(-20);
		}
	}

}

void mouse_motion (int xx, int yy) {

	bool ok = false;
	if (x == 0) {
		x = xx;
	}
	if (y == 0) {
		y = yy;
	}

	if (yy < y) {
		if (camera_mode == 0) {
			if (count_OX_Down > -1.5) {
				camera.rotateFPS_OX(-0.05);
				count_OX_Down -= 0.05;
			}
		} else if (camera_mode == 1) {
			if (count_OX_Down > -1.5) {
				camera.translate_Forward(6);
				camera.rotateFPS_OX(-0.05);
				camera.translate_Forward(-6);
				count_OX_Down -= 0.05;
			}
		}
		y = yy;
	} else if (yy > y) {
		if (camera_mode == 0) {
			if (count_OX_Down < 1.5) {
				camera.rotateFPS_OX(0.05);
				count_OX_Down += 0.05;
			}
		} else if (camera_mode == 1) {
			if (count_OX_Down < 1.5) {
				camera.translate_Forward(6);
				camera.rotateFPS_OX(0.05);
				camera.translate_Forward(-6);
				count_OX_Down += 0.05;
			}
		}
		y = yy;
	}

	if (xx < x) {
		if (camera_mode == 0) {
			camera.rotateFPS_OX (-count_OX_Down);
			camera.rotateFPS_OY(-0.04);
			camera.rotateFPS_OX (count_OX_Down);
		} else if (camera_mode == 1) {
			camera.translate_Forward(6);
			camera.rotateFPS_OX(-0.6);
			camera.rotateFPS_OX (-count_OX_Down);
			camera.rotateFPS_OY(-0.04);
			camera.rotateFPS_OX (count_OX_Down);
			camera.rotateFPS_OX(0.6);
			camera.translate_Forward(-6);
		} else if (camera_mode == 2) {
			camera.translate_Forward(20);
			camera.rotateFPS_OX(-1.5);
			camera.rotateFPS_OY(-0.04);
			camera.rotateFPS_OX(1.5);
			camera.translate_Forward(-20);
		}

		person_angle += 2.291;
		x = xx;
	} else if (xx > x) {
		if (camera_mode == 0) {
			camera.rotateFPS_OX (-count_OX_Down);
			camera.rotateFPS_OY(0.04);
			camera.rotateFPS_OX (count_OX_Down);
		} else if (camera_mode == 1) {
			camera.translate_Forward(6);
			camera.rotateFPS_OX(-0.6);
			camera.rotateFPS_OX (-count_OX_Down);
			camera.rotateFPS_OY(0.04);
			camera.rotateFPS_OX (count_OX_Down);
			camera.rotateFPS_OX(0.6);
			camera.translate_Forward(-6);
		} else if (camera_mode == 2) {
			camera.translate_Forward(20);
			camera.rotateFPS_OX(-1.5);
			camera.rotateFPS_OY(0.04);
			camera.rotateFPS_OX(1.5);
			camera.translate_Forward(-20);
		}
		person_angle -= 2.291;
		x = xx;
	}

	if (person_angle > 360) {
		person_angle -= 360;
	}
	else if (person_angle < -360) {
		person_angle += 360;
	}
}


//idle
void idle(){
	angle = angle+0.01;
	if(angle >360) angle = angle-360;
	glutPostRedisplay();
}



int main(int argc, char *argv[]){

	//init glut
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_DEPTH);

	//init window
	glutInitWindowSize(800,600);
	glutInitWindowPosition(200,0);
	glutCreateWindow("Labyrinth");

	//callbacks
	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
	glutKeyboardFunc(keyboard);
	glutPassiveMotionFunc(mouse_motion);
	glutIdleFunc(idle);


	//z test on
	glEnable(GL_DEPTH_TEST);

	//set background
	glClearColor(0.2,0.2,0.2,1.0);

	//init camera
	camera.init();

	//loop
	glutMainLoop();

	return 0;
}
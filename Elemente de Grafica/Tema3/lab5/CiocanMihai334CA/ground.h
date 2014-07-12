#pragma once
#include <cmath>

void draw_ground(int dimx, int dimy, int tessx, int tessy, float height){

	float sx = (float)dimx;
	float sy = (float)dimy;
	float tx = (float)tessx;
	float ty = (float)tessy;
	if(tx==0) tx=1;
	if(ty==0) ty=1;

	float cx = sx/tx; 
	float cy = sy/ty; 
	float dx = sx/cx;
	float dy = sy/cy;

	int sizex = (int)floor(cx);
	int sizey = (int)floor(cy);

	glPushMatrix();
	glTranslatef(-dimx/2.0f, height, -20);
	glBegin(GL_QUADS);
	for(int i=0;i<sizex;i++){
		for(int j=0;j<sizey;j++){

			if((i+j)%2==0) glColor3f( 1,1,1);
			if((i+j)%2==1) glColor3f( 0,0,0);
			
			glVertex3f( i*dx, 0, j*dy);
			glVertex3f( (i+1)*dx, 0, j*dy);
			glVertex3f( (i+1)*dx, 0, (j+1)*dy);
			glVertex3f( i*dx, 0, (j+1)*dy);
		}
	}
	glEnd();
	glPopMatrix();
}
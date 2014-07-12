#include "WorldDrawer3d.h"

CoordinateSystem3d WorldDrawer3d::cs_basis;
std::vector<CoordinateSystem3d*> WorldDrawer3d::cs_used;

float colors[12][3];
float dif = 0.0f;
bool inc = true; 
char* text;

void WorldDrawer3d::idleCallbackFunction(){
	//call client function
	onIdle();
	//redisplay
	glutPostRedisplay();
}

void WorldDrawer3d::reshapeCallbackFunction(int w, int h){
	glViewport(0,0, w, h);

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	float aspect = (float)w/(float)h;
	gluPerspective(90.0f, aspect, 0.1f, 3000.0f);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	gluLookAt(20.0, 20.0, 20.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);  //looking at xoy
}

void WorldDrawer3d::solve(char *txt) {
	text = txt;
}

void drawBitmapText(char *string,float x,float y,float z) 
{  
	char *c;
	glRasterPos3f(x, y,z);
	for (c=string; *c != '\0'; c++) 
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, *c);
	}
}

void WorldDrawer3d::displayCallbackFunction(){
	
	//Render objects
	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	glColor3f(1,0,0);
	drawBitmapText(text, 10.0, 20.0, 0.0);
	if (inc) {
		dif += 0.005f;
	}
	else {
		dif -= 0.005f;
	}

	if (abs(dif - 1.0f) < 0.00001f) {
		inc = false;
	}
	else if (dif < 0.00f) {
		inc = true;
	}

	glLineWidth(1);
	//draw basis coord system
	/*if(cs_basis.draw_axis){
		glBegin(GL_LINES);
			glColor3f(0,1,1);
			glVertex3f(cs_basis.axisup.x,-cs_basis.axisup.y,cs_basis.axisup.z);
			glVertex3f(cs_basis.axisup.x,cs_basis.axisup.y,cs_basis.axisup.z);
			glColor3f(1,0,1);
			glVertex3f(-cs_basis.axisright.x,cs_basis.axisright.y,cs_basis.axisright.z);
			glVertex3f(cs_basis.axisright.x,cs_basis.axisright.y,cs_basis.axisright.z);
			glColor3f(1,1,0);
			glVertex3f(cs_basis.axisfwd.x,cs_basis.axisfwd.y,-cs_basis.axisfwd.z);
			glVertex3f(cs_basis.axisfwd.x,cs_basis.axisfwd.y,cs_basis.axisfwd.z);
		glEnd();
		glPointSize(5);
		glBegin(GL_POINTS);
			glColor3f(0,1,1);	glVertex3f(cs_basis.axisup.x,cs_basis.axisup.y,cs_basis.axisup.z);
			glColor3f(1,0,1);	glVertex3f(cs_basis.axisright.x,cs_basis.axisright.y,cs_basis.axisright.z);
			glColor3f(1,1,0);	glVertex3f(cs_basis.axisfwd.x,cs_basis.axisfwd.y,cs_basis.axisfwd.z);
		glEnd();

	}*/

	//draw objects in cs_basis
	for(unsigned int j=0;j<cs_basis.objects.size();j++){
		//set object color
		glColor3f(cs_basis.objects[j]->colorx,cs_basis.objects[j]->colory,cs_basis.objects[j]->colorz);

		//get data
		std::vector<Point3d> points = cs_basis.objects[j]->points;
		std::vector<int> topology = cs_basis.objects[j]->topology;
		
		//draw
		if(topology.size()<2){
			std::cout<<"Folositi triunghiuri, dimensiune minima topologie =3"<<std::endl;
			continue;
		}

		//obiectul
		glBegin(GL_TRIANGLES);
		for(unsigned int k=0;k<topology.size();k++){
			int index=topology[k];
			glVertex3f(points[index].x, points[index].y,points[index].z);
		}
		glEnd();
		
		//axele obiectului
		glLineWidth(2);
		if(cs_basis.objects[j]->draw_axis){
			/*glBegin(GL_LINES);
				glColor3f(0,1,0);
				glVertex3f(cs_basis.objects[j]->axiscenter.x,cs_basis.objects[j]->axiscenter.y,cs_basis.objects[j]->axiscenter.z);
				glVertex3f(cs_basis.objects[j]->axisup.x,cs_basis.objects[j]->axisup.y,cs_basis.objects[j]->axisup.z);
				glColor3f(1,0,0);
				glVertex3f(cs_basis.objects[j]->axiscenter.x,cs_basis.objects[j]->axiscenter.y,cs_basis.objects[j]->axiscenter.z);
				glVertex3f(cs_basis.objects[j]->axisright.x,cs_basis.objects[j]->axisright.y,cs_basis.objects[j]->axisright.z);
				glColor3f(0,0,1);
				glVertex3f(cs_basis.objects[j]->axiscenter.x,cs_basis.objects[j]->axiscenter.y,cs_basis.objects[j]->axiscenter.z);
				glVertex3f(cs_basis.objects[j]->axisfwd.x,cs_basis.objects[j]->axisfwd.y,cs_basis.objects[j]->axisfwd.z);
			glEnd();*/
		}
	}

	//draw each used coord system
	for(unsigned int i=0;i<cs_used.size();i++){
		//draw used coord system
		glLineWidth(1);
		if(cs_used[i]->draw_axis){
			/*glBegin(GL_LINES);
				glColor3f(0,1,0);
				glVertex3f(cs_used[i]->axiscenter.x,cs_used[i]->axiscenter.y,cs_used[i]->axiscenter.z);
				glVertex3f(cs_used[i]->axisup.x,cs_used[i]->axisup.y,cs_used[i]->axisup.z);
				glColor3f(1,0,0);
				glVertex3f(cs_used[i]->axiscenter.x,cs_used[i]->axiscenter.y,cs_used[i]->axiscenter.z);
				glVertex3f(cs_used[i]->axisright.x,cs_used[i]->axisright.y,cs_used[i]->axisright.z);
				glColor3f(0,0,1);
				glVertex3f(cs_used[i]->axiscenter.x,cs_used[i]->axiscenter.y,cs_used[i]->axiscenter.z);
				glVertex3f(cs_used[i]->axisfwd.x,cs_used[i]->axisfwd.y,cs_used[i]->axisfwd.z);
			glEnd();*/
		}

		//draw objects
		for(unsigned int j=0;j<cs_used[i]->objects.size();j++){
			//set object color
			glColor3f(cs_used[i]->objects[j]->colorx,cs_used[i]->objects[j]->colory,cs_used[i]->objects[j]->colorz);

			//get data
			std::vector<Point3d> points = cs_used[i]->objects[j]->points;
			std::vector<int> topology = cs_used[i]->objects[j]->topology;
			
			//draw
			if(topology.size()<2){
				std::cout<<"Folositi triunghiuri, dimensiune minima topologie =3"<<std::endl;
				continue;
			}

			int color = 0;
			//obiectul
			glBegin(GL_TRIANGLES);
				for(unsigned int k=0;k<topology.size();k++){

					if (cs_used[i]->objects[j]->glow) {
						if (k % 6 == 0) {
							float c1, c2, c3;
							c1 = colors[cs_used[i]->objects[j]->sideColors[color]][0] + dif;
							if (c1 > 1.0f) {
								c1 = 1.0f;
							}
							c2 = colors[cs_used[i]->objects[j]->sideColors[color]][1] + dif;
							if (c2 > 1.0f) {
								c2 = 1.0f;
							}
							c3 = colors[cs_used[i]->objects[j]->sideColors[color]][2] + dif;
							if (c3 > 1.0f) {
								c3 = 1.0f;
							}
							glColor3f(c1, c2, c3);
							color ++;
						}
					}
					else
					{
						if (k % 6 == 0) {
							glColor3f(colors[cs_used[i]->objects[j]->sideColors[color]][0],
								colors[cs_used[i]->objects[j]->sideColors[color]][1],
								colors[cs_used[i]->objects[j]->sideColors[color]][2]);
							color ++;
						}
					}
					int index=topology[k];
					glVertex3f(points[index].x, points[index].y,points[index].z);
				}
			glEnd();
			
			//axele obiectului
			glLineWidth(2);
			if(cs_used[i]->objects[j]->draw_axis){
				glBegin(GL_LINES);
					glColor3f(0,1,0);
					glVertex3f(cs_used[i]->objects[j]->axiscenter.x,cs_used[i]->objects[j]->axiscenter.y,cs_used[i]->objects[j]->axiscenter.z);
					glVertex3f(cs_used[i]->objects[j]->axisup.x,cs_used[i]->objects[j]->axisup.y,cs_used[i]->objects[j]->axisup.z);
					glColor3f(1,0,0);
					glVertex3f(cs_used[i]->objects[j]->axiscenter.x,cs_used[i]->objects[j]->axiscenter.y,cs_used[i]->objects[j]->axiscenter.z);
					glVertex3f(cs_used[i]->objects[j]->axisright.x,cs_used[i]->objects[j]->axisright.y,cs_used[i]->objects[j]->axisright.z);
					glColor3f(0,0,1);
					glVertex3f(cs_used[i]->objects[j]->axiscenter.x,cs_used[i]->objects[j]->axiscenter.y,cs_used[i]->objects[j]->axiscenter.z);
					glVertex3f(cs_used[i]->objects[j]->axisfwd.x,cs_used[i]->objects[j]->axisfwd.y,cs_used[i]->objects[j]->axisfwd.z);
				glEnd();
			}
		}
	}

	//swap buffers
	glutSwapBuffers();
}
void WorldDrawer3d::keyboardCallbackFunction(unsigned char key, int posx, int posy){
	if(key==KEY_ESC) glutExit();
	//call client function
	onKey(key);
}
void WorldDrawer3d::keyboardSpecialCallbackFunction(int key, int posx, int posy){
	//call client function
	onKey(key);
}

WorldDrawer3d::WorldDrawer3d(int argc, char **argv, int windowWidth, int windowHeight, int windowStartX, int windowStartY, std::string windowName){
	//init
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE|GLUT_RGB|GLUT_DEPTH);
	glutInitWindowSize(windowWidth,windowHeight);
	glutInitWindowPosition(windowStartX,windowStartY);
	glutCreateWindow(windowName.c_str());
	
	//bind funcs
	glutDisplayFunc(displayCallbackFunction);
	glutReshapeFunc(reshapeCallbackFunction);
	glutIdleFunc(idleCallbackFunction);
	glutKeyboardFunc(keyboardCallbackFunction);
	glutSpecialFunc(keyboardSpecialCallbackFunction);
	glutMotionFunc(motionCallback);
	glutMouseFunc(mouseCallbackFunction);

	glClearColor(0.4f,0.5f,1,1);

	//zbuff
	glEnable(GL_DEPTH_TEST);
}
void WorldDrawer3d::run(){
	colors[YELLOW][0] = 1;
	colors[YELLOW][1] = 0.84;
	colors[YELLOW][2] = 0;
	colors[GREEN][0] = 0;
	colors[GREEN][1] = 1;
	colors[GREEN][2] = 0;
	colors[WHITE][0] = 1;
	colors[WHITE][1] = 1;
	colors[WHITE][2] = 1;
	colors[BLUE][0] = 0;
	colors[BLUE][1] = 0;
	colors[BLUE][2] = 1;
	colors[RED][0] = 1;
	colors[RED][1] = 0;
	colors[RED][2] = 0;
	colors[ORANGE][0] = 1;
	colors[ORANGE][1] = 0.56f;
	colors[ORANGE][2] = 0;
	colors[BLACK][0] = 0;
	colors[BLACK][1] = 0;
	colors[BLACK][2] = 0;
	colors[BRIGHT_BLUE][0] = 0.11f;
	colors[BRIGHT_BLUE][1] = 0.56f;
	colors[BRIGHT_BLUE][2] = 1.0f;
	colors[BRIGHT_GREEN][0] = 0.0f;
	colors[BRIGHT_GREEN][1] = 1.0f;
	colors[BRIGHT_GREEN][2] = 0.5f;
	colors[BRIGHT_RED][0] = 1;
	colors[BRIGHT_RED][1] = 0.25;
	colors[BRIGHT_RED][2] = 0;
	colors[BRIGHT_ORANGE][0] = 1;
	colors[BRIGHT_ORANGE][1] = 0.64f;
	colors[BRIGHT_ORANGE][2] = 0;
	colors[BRIGHT_YELLOW][0] = 1;
	colors[BRIGHT_YELLOW][1] = 1;
	colors[BRIGHT_YELLOW][2] = 1;

	dif = 0.0f;

	glutMainLoop();
}
WorldDrawer3d::~WorldDrawer3d(){
}
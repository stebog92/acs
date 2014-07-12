
#include "WorldDrawer3d.h"
bool WorldDrawer3d::animation=true;


//used global vars
CoordinateSystem3d *cs1;
Object3d *o, *rubik[3][3][3], *rubik_copy[3][3][3];
bool initialized = false, rotate_left = false, 
	rotate_right = false, glow = false, rotate_line = false, rotate_row = false;
bool rotate_up, rotate_down, possible = true, solve_m = false, solved, printscore = true, move_all = false;
int xi, yi, lastx, lasty, line, row, moves;
int count_line[3], count_row[3];
bool  line_changed[3];
Point3d origin;
float rotation = 0.0f, rotationx = 0.0f;


Object3d* makeCube(float size) {

	std::vector<Point3d> points;
	std::vector<int> topology;
	points.push_back(Point3d(size,size,size));
	points.push_back(Point3d(size,size,-size));
	points.push_back(Point3d(-size,size,-size));
	points.push_back(Point3d(-size,size,size));
	points.push_back(Point3d(size,-size,size));
	points.push_back(Point3d(size,-size,-size));
	points.push_back(Point3d(-size,-size,-size));
	points.push_back(Point3d(-size,-size,size));
	topology.push_back(0);topology.push_back(1);topology.push_back(2);	//top
	topology.push_back(2);topology.push_back(3);topology.push_back(0);
	topology.push_back(6);topology.push_back(5);topology.push_back(4);	//bottom
	topology.push_back(7);topology.push_back(4);topology.push_back(6);
	topology.push_back(2);topology.push_back(3);topology.push_back(6);	//left
	topology.push_back(7);topology.push_back(3);topology.push_back(6);
	topology.push_back(0);topology.push_back(1);topology.push_back(5);	//right
	topology.push_back(0);topology.push_back(5);topology.push_back(4);
	topology.push_back(0);topology.push_back(3);topology.push_back(4);	//front
	topology.push_back(7);topology.push_back(3);topology.push_back(4);
	topology.push_back(5);topology.push_back(1);topology.push_back(2);	//back
	topology.push_back(6);topology.push_back(2);topology.push_back(5);
	return new Object3d(points,topology);
}

//add
void WorldDrawer3d::init(){
	//creeaza 2 sistem de coordonate client

	cs1 = new CoordinateSystem3d();
	cs_used.push_back(cs1);
	line_changed[0] = line_changed[1] = line_changed[2] = false;
	origin = Point3d(0.0, 0.0, 0.0);
	WorldDrawer3d::solve("Scramble the cube!");
	float size = 4.0f, dist = 5.0f;
	for (int i = -1; i < 2; i++) {
		for (int j = -1; j < 2; j++) {
			for (int k = -1; k < 2; k++) {
				Object3d *o = makeCube(size);
				int* colors = new int[6];
				if (i == 1) {
					colors[2] = BLACK;
					colors[3] = RED;
				}
				else if(i == -1) {
					colors[2] = ORANGE;
					colors[3] = BLACK;
				}
				else {
					colors[2] = BLACK;
					colors[3] = BLACK;
				}
				if (j == -1) {
					colors[1] = YELLOW;
					colors[0] = BLACK;
				}
				else if (j == 1) {
					colors[0] = WHITE;
					colors[1] = BLACK;
				}
				else {
					colors[0] = BLACK;
					colors[1] = BLACK;
				}
				if (k == 1) {
					colors[4] = GREEN;
					colors[5] = BLACK;
				}
				else if (k == -1) {
					colors[4] = BLACK;
					colors[5] = BLUE;
				}
				else {
					colors[4] = BLACK;
					colors[5] = BLACK;
				}
				o->setSideColor(colors, 0);
				o->translate(i * (size + dist), j * (size + dist), k * (size + dist));
				cs1->objectAdd(o);
				rubik[i + 1][j + 1][k + 1] = o;
				rubik_copy[i+1][j+1][k +1] = o;
			}
		}
	}

}


void WorldDrawer3d::onIdle(){	//per frame
	static int iteration=1;
	static int poz=0;
	static int count=0;
	static float dif = -1.0f;
	static int up = true;
	if(animation){
		if (!solved) {
			if (initialized) {
				/*if (xi > 700 && xi < 800) {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							for (int k = 0; k < 3; k++) {
								rubik[i][j][k]->rotateYRelativeToPoint(origin,0.785398165f);
								rubik[i][j][k]->rotateXRelativeToPoint(origin,(lasty - yi) * 0.01f);
								rubik[i][j][k]->rotateYRelativeToPoint(origin,-0.785398165f);
							}
						}
					}
					rotationx += (lasty - yi) * 0.01f;
				}
				else if (yi > 100 && yi < 200) {*/
				cs1->rotateYSelf((xi - lastx) * 0.01f);
				rotation += (xi - lastx) * 0.01f;
				//}

				xi = lastx;
				yi = lasty;
			}
			if (rotate_left && rotate_line) {
				if (!move_all) {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							rubik[i][line][j]->rotateYRelativeToPoint(origin, 0.785398165f);
							rubik[i][line][j]->rotateXRelativeToPoint(origin, -rotationx);
							rubik[i][line][j]->rotateYRelativeToPoint(origin, 0.0785398165f);
							rubik[i][line][j]->rotateXRelativeToPoint(origin, rotationx);
							rubik[i][line][j]->rotateYRelativeToPoint(origin, -0.785398165f);
						}
					}
					count_line[line]++;
					possible = false;
					if (count_line[line] == 20) {
						count_line[line] = 0;
						rotate_left = false;
						possible = true;
					}

					if (count_line[line] == 0) {
						Object3d* aux1 = rubik[2][line][0];
						Object3d* aux2 = rubik[1][line][0];
						rubik[2][line][0] = rubik[0][line][0];
						rubik[1][line][0] = rubik[0][line][1];
						rubik[0][line][0] = rubik[0][line][2];
						rubik[0][line][1] = rubik[1][line][2];
						rubik[0][line][2] = rubik[2][line][2];
						rubik[1][line][2] = rubik[2][line][1];
						rubik[2][line][2] = aux1;
						rubik[2][line][1] = aux2;
						if (line == 1) {
							for (int i = 0; i < 3; i++) {
								Object3d* aux1 = rubik_copy[2][i][0];
								Object3d* aux2 = rubik_copy[1][i][0];
								rubik_copy[2][i][0] = rubik_copy[0][i][0];
								rubik_copy[1][i][0] = rubik_copy[0][i][1];
								rubik_copy[0][i][0] = rubik_copy[0][i][2];
								rubik_copy[0][i][1] = rubik_copy[1][i][2];
								rubik_copy[0][i][2] = rubik_copy[2][i][2];
								rubik_copy[1][i][2] = rubik_copy[2][i][1];
								rubik_copy[2][i][2] = aux1;
								rubik_copy[2][i][1] = aux2;
							}
						}
					}
				} else {
					count_line[line]++;
					possible = false;
					if (count_line[line] == 20) {
						count_line[line] = 0;
						rotate_left = false;
						possible = true;
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							for (int k = 0; k < 3; k++) {
								rubik[i][k][j]->rotateYRelativeToPoint(origin, 0.0785398165f);
							}
						}
					}
				}
			}
			if (rotate_right && rotate_line) {
				if (!move_all) {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							rubik[i][line][j]->rotateYRelativeToPoint(origin, 0.785398165f);
							rubik[i][line][j]->rotateXRelativeToPoint(origin, -rotationx);
							rubik[i][line][j]->rotateYRelativeToPoint(origin, -0.0785398165f);
							rubik[i][line][j]->rotateXRelativeToPoint(origin, rotationx);
							rubik[i][line][j]->rotateYRelativeToPoint(origin, -0.785398165f);
						}
					}
					possible = false;
					count_line[line]++;
					if (count_line[line] == 20) {
						count_line[line] = 0;
						rotate_right = false;
						possible = true;
					}

					if (count_line[line] == 0) {
						Object3d* aux1 = rubik[0][line][0];
						Object3d* aux2 = rubik[1][line][0];
						rubik[0][line][0] = rubik[2][line][0];
						rubik[1][line][0] = rubik[2][line][1];
						rubik[2][line][0] = rubik[2][line][2];
						rubik[2][line][1] = rubik[1][line][2];
						rubik[2][line][2] = rubik[0][line][2];
						rubik[1][line][2] = rubik[0][line][1];
						rubik[0][line][2] = aux1;
						rubik[0][line][1] = aux2;
						if (line == 1) {
							for (int i = 0; i < 3; i++) {
								Object3d* aux1 = rubik_copy[0][i][0];
								Object3d* aux2 = rubik_copy[1][i][0];
								rubik_copy[0][i][0] = rubik_copy[2][i][0];
								rubik_copy[1][i][0] = rubik_copy[2][i][1];
								rubik_copy[2][i][0] = rubik_copy[2][i][2];
								rubik_copy[2][i][1] = rubik_copy[1][i][2];
								rubik_copy[2][i][2] = rubik_copy[0][i][2];
								rubik_copy[1][i][2] = rubik_copy[0][i][1];
								rubik_copy[0][i][2] = aux1;
								rubik_copy[0][i][1] = aux2;
							}
						}
					}
				} else {
					count_line[line]++;
					possible = false;
					if (count_line[line] == 20) {
						count_line[line] = 0;
						rotate_right = false;
						possible = true;
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							for (int k = 0; k < 3; k++) {
								rubik[i][k][j]->rotateYRelativeToPoint(origin, -0.0785398165f);
							}
						}
					}
				}
			}

			if (rotate_up && rotate_row) {
				if (!move_all) {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							rubik[row][i][j]->rotateYRelativeToPoint(origin, 0.785398165f);
							rubik[row][i][j]->rotateXRelativeToPoint(origin, -rotationx);
							rubik[row][i][j]->rotateYRelativeToPoint(origin, -0.785398165f);
							rubik[row][i][j]->rotateYRelativeToPoint(origin, -rotation);
							rubik[row][i][j]->rotateXRelativeToPoint(origin, 0.0785398165f);
							rubik[row][i][j]->rotateYRelativeToPoint(origin, rotation);
							rubik[row][i][j]->rotateYRelativeToPoint(origin, 0.785398165f);
							rubik[row][i][j]->rotateXRelativeToPoint(origin, rotationx);
							rubik[row][i][j]->rotateYRelativeToPoint(origin, -0.785398165f);
						}
					}
					possible = false;
					count_row[row] ++;
					if (count_row[row] == 20) {
						count_row[row] = 0;
						possible = true;
						rotate_up = false;
					}

					if (count_row[row] == 0) {
						Object3d* aux1 = rubik[row][2][0];
						Object3d* aux2 = rubik[row][1][0];
						rubik[row][2][0] = rubik[row][0][0];
						rubik[row][1][0] = rubik[row][0][1];
						rubik[row][0][0] = rubik[row][0][2];
						rubik[row][0][1] = rubik[row][1][2];
						rubik[row][0][2] = rubik[row][2][2];
						rubik[row][1][2] = rubik[row][2][1];
						rubik[row][2][2] = aux1;
						rubik[row][2][1] = aux2;

						if (row == 1) {
							for (int i = 0; i < 3; i++) {
								Object3d* aux1 = rubik_copy[i][2][0];
								Object3d* aux2 = rubik_copy[i][1][0];
								rubik_copy[i][2][0] = rubik_copy[i][0][0];
								rubik_copy[i][1][0] = rubik_copy[i][0][1];
								rubik_copy[i][0][0] = rubik_copy[i][0][2];
								rubik_copy[i][0][1] = rubik_copy[i][1][2];
								rubik_copy[i][0][2] = rubik_copy[i][2][2];
								rubik_copy[i][1][2] = rubik_copy[i][2][1];
								rubik_copy[i][2][2] = aux1;
								rubik_copy[i][2][1] = aux2;
							}
						}
					}
				} else {
					count_row[row]++;
					possible = false;
					if (count_row[row] == 20) {
						count_row[row] = 0;
						rotate_up = false;
						possible = true;
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							for (int k = 0; k < 3; k++) {
								rubik[k][i][j]->rotateXRelativeToPoint(origin, -0.0785398165f);
							}
						}
					}
				}
			}
			if (rotate_down && rotate_row) {
				if (!move_all) {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							rubik[row][i][j]->rotateYRelativeToPoint(origin, 0.785398165f);
							rubik[row][i][j]->rotateXRelativeToPoint(origin, -rotationx);
							rubik[row][i][j]->rotateYRelativeToPoint(origin, -0.785398165f);
							rubik[row][i][j]->rotateYRelativeToPoint(origin, -rotation);
							rubik[row][i][j]->rotateXRelativeToPoint(origin, -0.0785398165f);
							rubik[row][i][j]->rotateYRelativeToPoint(origin, rotation);
							rubik[row][i][j]->rotateYRelativeToPoint(origin, 0.785398165f);
							rubik[row][i][j]->rotateXRelativeToPoint(origin, rotationx);
							rubik[row][i][j]->rotateYRelativeToPoint(origin, -0.785398165f);
						}
					}
					possible = false;
					count_row[row] ++;
					if (count_row[row] == 20) {
						count_row[row] = 0;
						possible = true;
						rotate_down = false;
					}

					if (count_row[row] == 0) {
						Object3d* aux1 = rubik[row][0][0];
						Object3d* aux2 = rubik[row][1][0];
						rubik[row][0][0] = rubik[row][2][0];
						rubik[row][1][0] = rubik[row][2][1];
						rubik[row][2][0] = rubik[row][2][2];
						rubik[row][2][1] = rubik[row][1][2];
						rubik[row][2][2] = rubik[row][0][2];
						rubik[row][1][2] = rubik[row][0][1];
						rubik[row][0][2] = aux1;
						rubik[row][0][1] = aux2;
						if (row == 1) {
							for (int i = 0; i < 3; i++) {
								Object3d* aux1 = rubik_copy[i][0][0];
								Object3d* aux2 = rubik_copy[i][1][0];
								rubik_copy[i][0][0] = rubik_copy[i][2][0];
								rubik_copy[i][1][0] = rubik_copy[i][2][1];
								rubik_copy[i][2][0] = rubik_copy[i][2][2];
								rubik_copy[i][2][1] = rubik_copy[i][1][2];
								rubik_copy[i][2][2] = rubik_copy[i][0][2];
								rubik_copy[i][1][2] = rubik_copy[i][0][1];
								rubik_copy[i][0][2] = aux1;
								rubik_copy[i][0][1] = aux2;
							}
						}
					}
				} else {
					count_row[row]++;
					possible = false;
					if (count_row[row] == 20) {
						count_row[row] = 0;
						rotate_down = false;
						possible = true;
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							for (int k = 0; k < 3; k++) {
								rubik[k][i][j]->rotateXRelativeToPoint(origin, 0.0785398165f);
							}
						}
					}
				}
			}

			if (solve_m) {
				solved = true;
				printscore = true;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						for (int k = 0; k < 3; k++) {
							if (rubik[i][j][k] != rubik_copy[i][j][k]) {
								solved = false;
							}
						}
					}
				}
			}
		} else if (printscore){
			printscore = false;
			WorldDrawer3d::solve("Score");
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 3; k++) {
						cs1->objectRemove(rubik[i][j][k]);
					}
				}
			}
			
			
			float di = -18, dj = 20;
			int *colors = new int[6];
			colors[0] = RED;
			colors[1] = YELLOW;
			colors[2] = GREEN;
			colors[3] = BLUE;
			colors[4] = YELLOW;
			colors[5] = ORANGE;
			for (int i = 0; i < 9 && moves; i++) {
				dj -= 7;
				di = -30;
				for (int j = 0; j < 9 && moves; j++) {
					di += 6;
					Object3d *o = makeCube(2.0f);
					o->setSideColor(colors,0);
					o->translate(di, dj, 0.0f);
					cs1->objectAdd(o);
					moves --;
				}
			}
			cs1->rotateYSelf(-0.785398165f);

			cs1->rotateYSelf(0.785398165f);
			cs1->rotateXSelf(-0.5f);
			cs1->rotateYSelf(-0.785398165f);
			
					
		}
		iteration++;
		
	}
}

void WorldDrawer3d::onKey(unsigned char key){
	switch(key){
		case KEY_UP:
			if (!move_all && possible && rotate_line && line < 2) {
				line++;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						for (int k = 0; k < 3; k++) {
							rubik[i][k][j]->glow = false;
						}
					}
				}
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						rubik[i][line][j]->glow = true;
					}
				}
			}
			else if (possible && rotate_row) {
				rotate_up = true;
				rotate_down = false;
				moves++;
				if (move_all) {
					for (int i = 0; i < 3; i++) {
						Object3d* aux1 = rubik[i][2][0];
						Object3d* aux2 = rubik[i][1][0];
						rubik[i][2][0] = rubik[i][0][0];
						rubik[i][1][0] = rubik[i][0][1];
						rubik[i][0][0] = rubik[i][0][2];
						rubik[i][0][1] = rubik[i][1][2];
						rubik[i][0][2] = rubik[i][2][2];
						rubik[i][1][2] = rubik[i][2][1];
						rubik[i][2][2] = aux1;
						rubik[i][2][1] = aux2;
					}
				}
			}
			break;
		case KEY_DOWN:
			if (!move_all && rotate_line && line > 0) {
				line--;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						for (int k = 0; k < 3; k++) {
							rubik[i][k][j]->glow = false;
						}
					}
				}
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						rubik[i][line][j]->glow = true;
						rubik[i][line + 1][j]->glow = false;
					}
				}
			}
			else if (possible && rotate_row) {
				rotate_down = true;
				rotate_up = false;
				moves++;
				if (move_all) {
					for (int i = 0; i < 3; i++) {
						Object3d* aux1 = rubik[i][0][0];
						Object3d* aux2 = rubik[i][1][0];
						rubik[i][0][0] = rubik[i][2][0];
						rubik[i][1][0] = rubik[i][2][1];
						rubik[i][2][0] = rubik[i][2][2];
						rubik[i][2][1] = rubik[i][1][2];
						rubik[i][2][2] = rubik[i][0][2];
						rubik[i][1][2] = rubik[i][0][1];
						rubik[i][0][2] = aux1;
						rubik[i][0][1] = aux2;
					}
				}
			}
			break;
		case KEY_LEFT:
			if (rotate_line) {
				rotate_left = true;
				moves++;
				if (move_all) {
					for (int i = 0; i < 3; i++) {
						Object3d* aux1 = rubik[2][i][0];
						Object3d* aux2 = rubik[1][i][0];
						rubik[2][i][0] = rubik[0][i][0];
						rubik[1][i][0] = rubik[0][i][1];
						rubik[0][i][0] = rubik[0][i][2];
						rubik[0][i][1] = rubik[1][i][2];
						rubik[0][i][2] = rubik[2][i][2];
						rubik[1][i][2] = rubik[2][i][1];
						rubik[2][i][2] = aux1;
						rubik[2][i][1] = aux2;
					}
				}
			}
			else if (!move_all && possible && row > 0) {
				row --;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						for (int k = 0; k < 3; k++) {
							rubik[i][k][j]->glow = false;
						}
					}
				}
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						rubik[row][i][j]->glow = true;
						rubik[row+1][i][j]->glow = false;
					}
				}
			}
			break;
		case KEY_RIGHT:
			if (rotate_line) {
				moves++;
				rotate_right = true;
				if (move_all) {
					for (int i = 0; i < 3; i++) {
						Object3d* aux1 = rubik[0][i][0];
						Object3d* aux2 = rubik[1][i][0];
						rubik[0][i][0] = rubik[2][i][0];
						rubik[1][i][0] = rubik[2][i][1];
						rubik[2][i][0] = rubik[2][i][2];
						rubik[2][i][1] = rubik[1][i][2];
						rubik[2][i][2] = rubik[0][i][2];
						rubik[1][i][2] = rubik[0][i][1];
						rubik[0][i][2] = aux1;
						rubik[0][i][1] = aux2;
					}
				}
			}
			else if (!move_all && possible && rotate_row && row < 2) {
				row ++;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						for (int k = 0; k < 3; k++) {
							rubik[i][k][j]->glow = false;
						}
					}
				}
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						rubik[row][i][j]->glow = true;
						rubik[row-1][i][j]->glow = false;
					}
				}
			}
			break;
		case KEY_SPACE:
			if (solved) {
				WorldDrawer3d::solve("Scramble the cube!");
				solved = false;
				solve_m = false;
				cs1->objects.clear();
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						for (int k = 0; k < 3; k++) {
							cs1->objectAdd(rubik[i][j][k]);
						}
					}
				}
			}
			else {
				WorldDrawer3d::solve("Solve the cube!");
				solve_m = true;
			}
			moves = 0;
			break;
		case 97: 
			move_all = false;
			if (possible) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						for (int k = 0; k < 3; k++) {
							rubik[i][k][j]->glow = false;
						}
					}
				}
				rotate_line = true;
				rotate_row = false;
				line = 0;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						rubik[i][line][j]->glow = true;
					}
				}
			}
			break;
		case 115 :
			move_all = false;
			if (possible) {
				rotate_row = true;
				rotate_line = false;
				row = 0;
				count_row[row] = 0;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						for (int k = 0; k < 3; k++) {
							rubik[i][k][j]->glow = false;
						}
					}
				}
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						rubik[row][i][j]->glow = true;
					}
				}
			}
			break;
		case 122:
			move_all = true;
			rotate_line = true;
			rotate_row = false;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 3; k++) {
						rubik[k][i][j]->glow = true;
					}
				}
			}
			break;
			case 120:
			move_all = true;
			rotate_row = true;
			rotate_line = false;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 3; k++) {
						rubik[k][i][j]->glow = true;
					}
				}
			}
			break;
		default:
			break;
	}
}

void WorldDrawer3d::motionCallback(int x, int y) {
	lastx = x;
	lasty = y;
}

void WorldDrawer3d::mouseCallbackFunction(int button, int state, int x, int y) {
	if(state == GLUT_DOWN) {
		if (!initialized) {
			lastx = xi = x;
			lasty = yi = y;
			initialized = true;
		}
	}
	else if (state == GLUT_UP) {
		initialized = false;
	}
}


int main(int argc, char** argv){
	WorldDrawer3d wd3d(argc,argv,800,800,100,0,std::string("Rubik"));
	wd3d.init();
	wd3d.run();
	return 0;
}
#include "WorldDrawer2d.h"
#include <time.h>
bool WorldDrawer2d::animation=true;
bool game_new = false;


CoordinateSystem2d *cs1;
Object2d *o1, *teren, *background, *margins, *ball, *player_with_ball, *new_game_background;
std::vector<Object2d*> blue_team;
std::vector<Object2d*> orange_team;
std::vector<Point2d> blue_team_translation;
std::vector<Point2d> orange_team_translation;
std::vector<Point2d> o_score_points, b_score_points;
std::vector<int> o_score_topology, b_score_topology;
std::vector<Object2d*> objects_to_remove;
bool rotate = true;

float ball_transl_x = 0, ball_transl_y = 0, ball_radius, right_edge = 13,
	left_edge = -13, upper_edge = 16, bottom_edge = -16, upper_net_edge = 18, bottom_net_edge = -18, left_net_edge = -5, right_net_edge = 5,
	player_x, player_y;
float ball_x, ball_y;
int blue_score = 0, orange_score = 0;

void init_ball (int team) {
	Object2d *player;
	time_t t;
	time(&t);
	srand((unsigned int)t);
	float x, y;
	if (team == 0) {
		player = blue_team[(0 + rand()) % blue_team.size()];
		player->getCenter(&x, &y);
		ball->translate(-ball_x, -ball_y);
		ball->translate(x, y);
		ball->translate(0, 2);
		player_with_ball = player;
	}
	else {
		player = orange_team[(0 + rand()) % orange_team.size()];
		player->getCenter(&x, &y);
		ball->translate(-ball_x, -ball_y);
		ball->translate(x, y);
		ball->translate(0, -2);
		player_with_ball = player;
	}
	rotate = true;
}

void new_game() {
	std::vector<Point2d> points_background;
	std::vector<int> topology_background;
	points_background.push_back(Point2d(-20, -20));
	points_background.push_back(Point2d(20, -20));
	points_background.push_back(Point2d(20, 20));
	points_background.push_back(Point2d(-20, 20));

	topology_background.push_back(0);
	topology_background.push_back(1);
	topology_background.push_back(2);
	topology_background.push_back(0);
	topology_background.push_back(3);
	topology_background.push_back(2);
	new_game_background = new Object2d(points_background, topology_background);
	new_game_background->setcolor(0, 1, 1);
	game_new = true;
	orange_score = 0;
	blue_score = 0;
	WorldDrawer2d::animation = true;
	cs1->objectAddAtFront(new_game_background);
	for (int i = 0; i < objects_to_remove.size(); i++) {
		cs1->objectRemove(objects_to_remove[i]);
	}
}

void increase_score(int x) {
	if (x == 1)
	{
		if (blue_score == 1) {
			b_score_points.push_back(Point2d(14, 17));
			b_score_points.push_back(Point2d(15, 17));
			b_score_points.push_back(Point2d(14, 19));
			b_score_points.push_back(Point2d(15, 19));
			b_score_topology.push_back(0);
			b_score_topology.push_back(1);
			b_score_topology.push_back(2);
			b_score_topology.push_back(3);
			b_score_topology.push_back(2);
			b_score_topology.push_back(1);
			Object2d* one = new Object2d(b_score_points, b_score_topology);
			one->setcolor(0, 0, 1);
			objects_to_remove.push_back(one);
			cs1->objectAddAtFront(one);
		}
		else {
			std::vector<int> top;
			std::vector<Point2d> points;
			float dist = 0;
			if (blue_score == 2)
				dist = 2;
			else
				dist = 4;
			for (int i = 0; i < 4; i++){
				points.push_back(Point2d (b_score_points[i].x + dist, b_score_points[i].y));
			}
			top.push_back(0);
			top.push_back(1);
			top.push_back(2);
			top.push_back(3);
			top.push_back(2);
			top.push_back(1);
			Object2d* one = new Object2d(points, top);
			one->setcolor(0, 0, 1);
			cs1->objectAddAtFront(one);
			objects_to_remove.push_back(one);
		}
	}else {
		if (orange_score == 1) {
			o_score_points.push_back(Point2d(-14, 17));
			o_score_points.push_back(Point2d(-15, 17));
			o_score_points.push_back(Point2d(-14, 19));
			o_score_points.push_back(Point2d(-15, 19));
			o_score_topology.push_back(0);
			o_score_topology.push_back(1);
			o_score_topology.push_back(2);
			o_score_topology.push_back(3);
			o_score_topology.push_back(2);
			o_score_topology.push_back(1);
			Object2d* one = new Object2d(o_score_points, o_score_topology);
			one->setcolor(1, 0.54, 0);
			cs1->objectAddAtFront(one);
			objects_to_remove.push_back(one);
		} else {
			std::vector<int> top;
			std::vector<Point2d> points;
			float dist = 0;
			if (orange_score == 2)
				dist = 2;
			else
				dist = 4;
			for (int i = 0; i < 4; i++){
				points.push_back(Point2d (o_score_points[i].x - dist, o_score_points[i].y));
			}
			top.push_back(0);
			top.push_back(1);
			top.push_back(2);
			top.push_back(3);
			top.push_back(2);
			top.push_back(1);
			Object2d* one = new Object2d(points, top);
			one->setcolor(1, 0.54, 0);
			cs1->objectAddAtFront(one);
			objects_to_remove.push_back(one);
		}
	}
	WorldDrawer2d::animation = false;
}

void place_ball(Object2d *player) {
	player_with_ball = player;
	float x, y, dist_x, dist_y;
	player->getCenter(&x, &y);
	rotate = true;
	/*dist_x = x - ball_x;
	dist_y = y - ball_y;
	if (dist_x >= 0)
		dist_x = -2;
	else
		dist_x = 2;
	if (dist_y >= 0)
		dist_y = -2;
	else
		dist_y = 2;
	ball->translate(-ball_x, -ball_y);
	ball->translate(x, y);
	ball->translate(dist_y, dist_y);*/
	WorldDrawer2d::animation = false;
}

Object2d* createCircle(float raza, float r, float g, float b) {
	std::vector<Point2d> points_circle;
	std::vector<int> topology_circle;
	float x = raza * cos(1.4);
	float y = raza * sin(1.4);

	points_circle.push_back(Point2d(0, 0));
	points_circle.push_back(Point2d(-x, -y));
	points_circle.push_back(Point2d(x, -y));
	topology_circle.push_back(0);
	topology_circle.push_back(1);
	topology_circle.push_back(2);
	int k = 2;
	for (int i = 0; i < 30; i++) {
	Point2d a = points_circle[k-2];
	a.rotateRelativeToPoint(Point2d(0,0), 0.3);
	Point2d b = points_circle[k-1];
	b.rotateRelativeToPoint(Point2d(0,0), 0.3);
	Point2d c = points_circle[k];
	c.rotateRelativeToPoint(Point2d(0,0), 0.3);
	k += 3;

	points_circle.push_back(a);
	points_circle.push_back(b);
	points_circle.push_back(c);
	topology_circle.push_back(k-2);
	topology_circle.push_back(k-1);
	topology_circle.push_back(k);
	}

	Object2d *o = new Object2d(points_circle, topology_circle);
	o->setcolor(r, g, b);
	return o;
}

void WorldDrawer2d::init(){
	//creeaza 2 sistem de coordonate client
	cs1 = new CoordinateSystem2d();
	cs1->draw_axis = false;
	cs_used.push_back(cs1);

	//background
	std::vector<Point2d> points_background;
	std::vector<int> topology_background;

	points_background.push_back(Point2d(-20, -20));
	points_background.push_back(Point2d(20, -20));
	points_background.push_back(Point2d(20, 20));
	points_background.push_back(Point2d(-20, 20));

	topology_background.push_back(0);
	topology_background.push_back(1);
	topology_background.push_back(2);
	topology_background.push_back(0);
	topology_background.push_back(3);
	topology_background.push_back(2);
	background = new Object2d(points_background, topology_background);
	background->setcolor(0.7f, 1, 0.5f);

	//teren
	std::vector<Point2d> points_teren;
	std::vector<int> topology_teren;
	points_teren.push_back(Point2d(-13, -16));
	points_teren.push_back(Point2d(13, -16));
	points_teren.push_back(Point2d(13, 16));
	points_teren.push_back(Point2d(-13, 16));
	topology_teren.push_back(0);
	topology_teren.push_back(1);
	topology_teren.push_back(2);
	topology_teren.push_back(0);
	topology_teren.push_back(3);
	topology_teren.push_back(2);
	teren = new Object2d(points_teren, topology_teren);
	teren->setcolor(0, 0.5f, 0.0);

	//margins
	std::vector<Point2d> points_margins;
	std::vector<int> topology_margins;

	points_margins.push_back(Point2d(-13, -16));   //0
	points_margins.push_back(Point2d(-13.5f, -16));//1
	points_margins.push_back(Point2d(-13, 16));    //2
	points_margins.push_back(Point2d(-13.5f, 16)); //3
	points_margins.push_back(Point2d(-5, 16));     //4
	points_margins.push_back(Point2d(-5, 16.5f));  //5
	points_margins.push_back(Point2d(-13.5f, 16.5f));//6
	points_margins.push_back(Point2d(-5.5f, 16.5f)); //7
	points_margins.push_back(Point2d(-5, 16.5f));   //8
	points_margins.push_back(Point2d(-5, 18));       //9
	points_margins.push_back(Point2d(-5.5f, 18));    //10
	points_margins.push_back(Point2d(-5.5f, 18.5f)); //11
	points_margins.push_back(Point2d(5.5f, 18.5f));     //12
	points_margins.push_back(Point2d(5.5f, 18));		 //13
	points_margins.push_back(Point2d(-5, -16));		 //14
	points_margins.push_back(Point2d(-5, -16.5f));	 //15
	points_margins.push_back(Point2d(-13.5f, -16.5f));//16
	points_margins.push_back(Point2d(-5.5f, -16.5f)); //17
	points_margins.push_back(Point2d(-5, -18));		  //18
	points_margins.push_back(Point2d(-5.5f, -18));	  //19
	points_margins.push_back(Point2d(-5.5f, -18.5f)); //20
	points_margins.push_back(Point2d(5.5f, -18));		  //21
	points_margins.push_back(Point2d(5.5f, -18.5f));	  //22
	points_margins.push_back(Point2d(5, 18));//23
	points_margins.push_back(Point2d(5, 16.5f));//24
	points_margins.push_back(Point2d(5.5f, 16.5f));//25
	points_margins.push_back(Point2d(5, 16));//26
	points_margins.push_back(Point2d(13.5f, 16));//27
	points_margins.push_back(Point2d(13.5f, 16.5f));//28
	points_margins.push_back(Point2d(13, 16));//29
	points_margins.push_back(Point2d(13, -16));//30
	points_margins.push_back(Point2d(13.5f, -16));//31
	points_margins.push_back(Point2d(13.5f, -16.5f));//32
	points_margins.push_back(Point2d(5, -16.5f));//33
	points_margins.push_back(Point2d(5, -16));//34
	points_margins.push_back(Point2d(5.5f, -16.5f));//35
	points_margins.push_back(Point2d(5, -18.5f));//36

		
	topology_margins.push_back(0);
	topology_margins.push_back(1);
	topology_margins.push_back(2);
	topology_margins.push_back(3);
	topology_margins.push_back(1);
	topology_margins.push_back(2);
	topology_margins.push_back(3);
	topology_margins.push_back(4);
	topology_margins.push_back(5);
	topology_margins.push_back(6);
	topology_margins.push_back(3);
	topology_margins.push_back(5);
	topology_margins.push_back(7);
	topology_margins.push_back(8);
	topology_margins.push_back(9);
	topology_margins.push_back(10);
	topology_margins.push_back(9);
	topology_margins.push_back(7);
	topology_margins.push_back(10);
	topology_margins.push_back(11);
	topology_margins.push_back(12);
	topology_margins.push_back(10);
	topology_margins.push_back(13);
	topology_margins.push_back(12);
	topology_margins.push_back(14);
	topology_margins.push_back(15);
	topology_margins.push_back(16);
	topology_margins.push_back(1);
	topology_margins.push_back(16);
	topology_margins.push_back(14);
	topology_margins.push_back(17);
	topology_margins.push_back(15);
	topology_margins.push_back(18);
	topology_margins.push_back(19);
	topology_margins.push_back(17);
	topology_margins.push_back(18);
	topology_margins.push_back(21);
	topology_margins.push_back(22);
	topology_margins.push_back(20);
	topology_margins.push_back(19);
	topology_margins.push_back(20);
	topology_margins.push_back(21);
	topology_margins.push_back(13);
	topology_margins.push_back(23);
	topology_margins.push_back(24);
	topology_margins.push_back(25);
	topology_margins.push_back(13);
	topology_margins.push_back(24);
	topology_margins.push_back(26);
	topology_margins.push_back(27);
	topology_margins.push_back(28);
	topology_margins.push_back(24);
	topology_margins.push_back(26);
	topology_margins.push_back(28);
	topology_margins.push_back(29);
	topology_margins.push_back(30);
	topology_margins.push_back(31);
	topology_margins.push_back(27);
	topology_margins.push_back(29);
	topology_margins.push_back(31);
	topology_margins.push_back(31);
	topology_margins.push_back(32);
	topology_margins.push_back(33);
	topology_margins.push_back(34);
	topology_margins.push_back(33);
	topology_margins.push_back(31);
	topology_margins.push_back(35);
	topology_margins.push_back(33);
	topology_margins.push_back(22);
	topology_margins.push_back(36);
	topology_margins.push_back(34);
	topology_margins.push_back(22);

	std::vector<Point2d> points_lines;
	std::vector<int> topology_lines;

	points_lines.push_back(Point2d(-5, -16));
	points_lines.push_back(Point2d(-5, -16.5));
	points_lines.push_back(Point2d(5, -16));
	points_lines.push_back(Point2d(5, -16.5));
	topology_lines.push_back(0);
	topology_lines.push_back(1);
	topology_lines.push_back(2);
	topology_lines.push_back(3);
	topology_lines.push_back(2);
	topology_lines.push_back(1);

	Object2d* line = new Object2d(points_lines, topology_lines);
	line->setcolor(1,1,1);

	std::vector<Point2d> points_lines2;
	std::vector<int> topology_lines2;

	points_lines2.push_back(Point2d(-5, 16));
	points_lines2.push_back(Point2d(-5, 16.5));
	points_lines2.push_back(Point2d(5, 16));
	points_lines2.push_back(Point2d(5, 16.5));
	topology_lines2.push_back(0);
	topology_lines2.push_back(1);
	topology_lines2.push_back(2);
	topology_lines2.push_back(3);
	topology_lines2.push_back(2);
	topology_lines2.push_back(1);

	Object2d* line2 = new Object2d(points_lines2, topology_lines2);
	line2->setcolor(1,1,1);

	std::vector<Point2d> points_lines3;
	std::vector<int> topology_lines3;

	points_lines3.push_back(Point2d(-13, -0.25));
	points_lines3.push_back(Point2d(-13, 0.25));
	points_lines3.push_back(Point2d(13, -0.25));
	points_lines3.push_back(Point2d(13, 0.25));
	topology_lines3.push_back(0);
	topology_lines3.push_back(1);
	topology_lines3.push_back(2);
	topology_lines3.push_back(3);
	topology_lines3.push_back(2);
	topology_lines3.push_back(1);

	Object2d* line3 = new Object2d(points_lines3, topology_lines3);
	line3->setcolor(1,1,1);



	
	for (int i = 0; i < 4; i++) {
		blue_team.push_back(createCircle(1.5f, 0, 0, 1));
		orange_team.push_back(createCircle(1.5f, 1, 0.54, 0));
	}

	blue_team_translation.push_back(Point2d(-8, -8));
	blue_team_translation.push_back(Point2d(3, 5));
	blue_team_translation.push_back(Point2d(-7, 8));
	blue_team_translation.push_back(Point2d(6, -9));

	orange_team_translation.push_back(Point2d(-7, 0));
	orange_team_translation.push_back(Point2d(6, 11));
	orange_team_translation.push_back(Point2d(9, -2));
	orange_team_translation.push_back(Point2d(0, -7));

	margins = new Object2d(points_margins, topology_margins);
	margins->setcolor(1, 0, 0);
	ball_radius = 0.7;
	ball = createCircle(ball_radius, 1, 1, 1);
	ball->setcolor(1, 1, 1);
	cs1->objectAdd(ball);

	for (int i = 0; i < orange_team.size();i++)
	{
		cs1->objectAdd(orange_team[i]);
		cs1->objectTranslate(orange_team[i], orange_team_translation[i].x, orange_team_translation[i].y);
	}
	
	for (int i = 0; i < blue_team.size();i++)
	{
		cs1->objectAdd(blue_team[i]);
		cs1->objectTranslate(blue_team[i], blue_team_translation[i].x, blue_team_translation[i].y);
	}
	init_ball(0);
	cs1->objectAdd(margins);
	cs1->objectAdd(line);
	cs1->objectAdd(line2);
	cs1->objectAdd(line3);
	cs1->objectAdd(teren);
	cs1->objectAdd(background);
	animation = false;



}
void WorldDrawer2d::onIdle(){	//per frame
	static int iteration=1;
	bool goal = false;
	//static bool o1dir=true;
	//static bool o2dir=true;
	//static bool o3dir=true;
	//static bool o3dir2=true;
	if(animation){
		iteration++;
		ball->getCenter(&ball_x, &ball_y);
		for (int i = 0; i < 4; i++) {
			Object2d *player = blue_team[i];
			if (player != player_with_ball)
			{
				player->getCenter(&player_x, &player_y);
				if ((ball_x + ball_radius >= player_x - 1 && ball_x - ball_radius <= player_x + 1)  && (ball_y - ball_radius <= player_y + 1  && ball_y + ball_radius >= player_y - 1)) {
					place_ball(player);
					iteration = 1;
					return;
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			Object2d *player = orange_team[i];
			if (player != player_with_ball)
			{
				player->getCenter(&player_x, &player_y);
				if ((ball_x + ball_radius >= player_x - 1 && ball_x - ball_radius <= player_x + 1)  && (ball_y - ball_radius <= player_y + 1  && ball_y + ball_radius >= player_y - 1)) {
					place_ball(player);
					iteration = 1;
					return;
				}
			}
		}
		if (!game_new && ball_x + ball_radius <= right_net_edge + 0.5f && ball_x - ball_radius >= left_net_edge - 0.5f && ball_y - ball_radius > 16) {
			orange_score ++;
			increase_score(2);
			if (orange_score < 3)
			init_ball(0);
			goal = true;
			
		}else if (!game_new && ball_x + ball_radius <= right_net_edge + 0.5f && ball_x - ball_radius >= left_net_edge - 0.5f && ball_y + ball_radius < -16) {
			blue_score ++;
			increase_score(1);
			if (blue_score < 3)
				init_ball(1);
			goal = true;
		}

		if (orange_score == 3 || blue_score == 3) {
			new_game();
			ball_transl_x = 0, ball_transl_y = 0;
			iteration = 1;
			return;
		}

		if (goal) {
			goal = false;
			return;
		}

		if ((ball_y - ball_radius > -16 && ball_y + ball_radius < 16) && (ball_x + ball_radius >= right_edge || ball_x - ball_radius <= left_edge)) {
			ball_transl_x = -ball_transl_x;
		}
		else if ((ball_y - ball_radius <= bottom_edge || ball_y + ball_radius >= upper_edge) && ((ball_x < right_net_edge && ball_x + ball_radius >= right_net_edge) || (ball_x > left_net_edge && ball_x - ball_radius <= left_net_edge))) {
			ball_transl_x = -ball_transl_x;
		}
		if ((ball_x - ball_radius > 4 || ball_x + ball_radius < -4) && (ball_y + ball_radius > upper_edge || ball_y - ball_radius < bottom_edge)) {
			ball_transl_y = -ball_transl_y;
		}
		else if ((ball_x + ball_radius <= 5 && ball_x - ball_radius >= -5) && (ball_y + ball_radius >= upper_net_edge || ball_y - ball_radius <= bottom_net_edge)){
			ball_transl_y = -ball_transl_y;
		}
		ball->translate(ball_transl_x, ball_transl_y);
		if (iteration == 8)
			player_with_ball = NULL;

		if (game_new && iteration == 340) {
			animation = false;
			iteration = 1;
			init_ball(0);
			game_new = false;
			cs1->objectRemove(new_game_background);
		}

	}
}

void WorldDrawer2d::onKey(unsigned char key){
	float x, y, dist, ball_dist = 0.1;
	for (int i = 0; i < blue_team.size(); i++) {
		if (player_with_ball == blue_team[i]) {
			x = blue_team_translation[i].x;
			y = blue_team_translation[i].y;
			break;
		}
		if (player_with_ball == orange_team[i]) {
			x = orange_team_translation[i].x;
			y = orange_team_translation[i].y;
			break;
		}
	}
	ball->getCenter(&ball_x, &ball_y);
	switch(key){
		case KEY_UP:
			if (animation == false) {
				player_with_ball->getCenter(&x, &y);
				animation = true;
				dist = sqrt((ball_x - x)*(ball_x - x) + (ball_y - y)*(ball_y - y));
				ball_transl_x =	(ball_dist * (ball_x - x))/dist;
				ball_transl_y =	(ball_dist * (ball_y - y))/dist;
				rotate = false;
			}
			break;
		case KEY_DOWN:
			break;
		case KEY_LEFT:
			
			if (rotate) {
				//player_with_ball->getCenter(&x, &y);
				ball->rotateRelativeToPoint(Point2d(x, y), 0.15);
				//player_with_ball->rotateSelf(0.15);
				player_with_ball->rotateRelativeToPoint(Point2d(x, y), 0.15);
			}
			break;
		case KEY_RIGHT:
			if (rotate) {
				//player_with_ball->getCenter(&x, &y);
				ball->rotateRelativeToPoint(Point2d(x, y), -0.15f);
				//player_with_ball->rotateSelf(-0.15f);
				player_with_ball->rotateRelativeToPoint(Point2d(x, y), -0.15f);
			}
			break;
		case KEY_SPACE:
				//animation=!animation;
			break;
		default:
			break;
	}
}


int main(int argc, char** argv){
	WorldDrawer2d wd2d(argc,argv,800,700,200,0,std::string("Tema 1"));
	wd2d.init();
	wd2d.run();
	return 0;
}
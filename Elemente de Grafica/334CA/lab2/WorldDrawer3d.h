#pragma once
#include "HeadersAndDefines.h"
#include "Support3d.h"

enum COLORS {
	YELLOW,
	GREEN, 
	WHITE, 
	BLUE,
	RED,
	ORANGE,
	BLACK,
	BRIGHT_YELLOW,
	BRIGHT_GREEN,
	BRIGHT_BLUE,
	BRIGHT_RED,
	BRIGHT_ORANGE,
};

class WorldDrawer3d{
	public:
		//implemented in worldDrawer2d_gl .. not for lab1
		WorldDrawer3d(int argc, char **argv, int windowWidth, int windowHeight, int windowStartX, int windowStartY, std::string windowName);
		~WorldDrawer3d();
		static void run();	
		static void displayCallbackFunction();
		static void reshapeCallbackFunction(int width, int height);
		static void idleCallbackFunction();
		static void keyboardCallbackFunction(unsigned char key, int x, int y);
		static void keyboardSpecialCallbackFunction(int key, int x, int y);
		static void mouseCallbackFunction(int button, int state, int x, int y);
		static void motionCallback(int x, int y);
		static void solve(char text[]);


		//implemented in worldDrawer2d_logic .. for lab1
		static void init();
		static void onIdle();
		static void onKey(unsigned char key);

	public:
		static bool animation;
		static CoordinateSystem3d cs_basis;
		static std::vector<CoordinateSystem3d*> cs_used;
};
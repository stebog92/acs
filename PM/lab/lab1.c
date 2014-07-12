/************************************************************************ *********
 * Proiectarea cu Microprocesoare
 * Laboratorul 1
 * Digital I/O
 *********************************************************************************/

#include <avr\io.h>
#include <avr\interrupt.h>
#define F_CPU 16000000
#include <util/delay.h>

#include <string.h>

#include "lcd.h"

/* Generare secvente aprindere leduri */
void task1()
{
	/*	TODO */
	/* 	Setare directii porturi folosite	*/
	
	while(1) {
		
		/*	Generare secvente pentru PB1 si PB0	*/
		
	
	}
}

/* Afisare mesaj simplu */
void task3(char * mesaj)
{	
	/* TODO */
	/* 	Initializare LCD	*/	
	/*	Afisare mesaj	*/
	
	
	while(1)
	{
		LCD_writeInstruction(0x18); // shiftare stanga
		_delay_ms(250);
	}
		
	
	
	
}

void task4()
{
	/*	TODO */
	/* afisare mesaj */
	
	while(1) {
		/*	Actiuni butoane*/
		
	
	}
	
}

/* 	Miscare caracter custom pe ecran	*/
void task5()
{
	/*	TODO */
}

/*	Afisare caracter custom 	*/
void printCustomChar()
{
	/*	TODO */
	/*	Scriere octeti caracter custom in  CGRAM */
	
	/*	Setare adresa DDRAM si afisare caracter */
	
}


int main()
{
	task1();
	//task3("Hello Hardware World!");
	task4();
	task5();
	while(1);
	
	return 0;
}

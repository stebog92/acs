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

/* Afisare mesaj simplu */
void print_message (char *mesaj) {	
	/* 	Initializare LCD	*/	
	LCD_init();
	
	/*	Afisare mesaj	*/
	//LCD_printn("kfjsdkfshkf", 12);
	LCD_print(mesaj);
	
	while (1) {
		LCD_writeInstruction(0x18); // shiftare stanga
		_delay_ms(250);
	}
}

void task4() {
	unsigned int sw;
	
	LCD_init();
	
	/* afisare mesaj */
	LCD_print2("Alegeti:", "Buzz-PB0 Char-PB1");
	
	while (1) {
		/*	Actiuni butoane  */
		DDRB &= ~((1 << PB0) | (1 << PB1));
		DDRD |= (1 << PD5);
		PORTB |= (1 << PB0) | (1 << PB1);
		sw = ((PINB & (1 << PB0)) == 0);
		
		if(sw == 1) {
			PORTD |= (1 << PD5);
			_delay_ms(4);
		}
		else {
			LCD_print2("","");
		}
	}
}

/*	Afisare caracter custom  */
void printCustomChar() {
	/*	Scriere octeti caracter custom in  CGRAM */
	
	/*	Setare adresa DDRAM si afisare caracter */
}

int main() {
	//task4();
	
	print_message("Hello World!");
	
	printCustomChar();
	
	while(1);
	
	return 0;
}
#include <avr/io.h>
#include <avr/interrupt.h>
#define F_CPU 16000000
#include <util/delay.h>
#include <string.h>
#include "lcd.h"

int main()
{
	LCD_init();
	LCD_print("Hello world");
	
	return 0;
}

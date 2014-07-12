/************************************************************************ *********
 * Proiectarea cu Microprocesoare
 * Laboratorul 1
 * Digital I/O
 *********************************************************************************/
#define F_CPU 16000000
#include <avr\io.h>
#include <avr\interrupt.h>
#include <util/delay.h>
/************************************************************************ 
 * DEFINE-uri pentru parametrizarea modulului LCD
 ************************************************************************/
#define RS_HIGH()				LcdCMD_PORT |= _BV(LcdRS)
#define RS_LOW()				LcdCMD_PORT &= ~_BV(LcdRS)
 
#define RW_HIGH()				LcdCMD_PORT |= _BV(LcdRW)
#define RW_LOW()				LcdCMD_PORT &= ~_BV(LcdRW)
 
#define ENABLE()				LcdCMD_PORT |= _BV(LcdE)
#define DISABLE()				LcdCMD_PORT &= ~_BV(LcdE)
 
#define LcdDATA_DDR		DDRC			// Portul pe care conectam firele de date la LCD-ul
#define LcdDATA_PORT		PORTC
#define LcdDATA_PIN		PINC

#define LcdCMD_DDR		DDRA			// Portul pe care conectam firele de comenzi la LCD
#define LcdCMD_PORT		PORTA
#define LcdCMD_PIN		PINA

#define LcdD4			 PC3				// Pin-ul pentru firul de date D4 de pe LCD
#define LcdD5			 PC4				// Pin-ul pentru firul de date D5 de pe LCD
#define LcdD6			 PC5				// Pin-ul pentru firul de date D6 de pe LCD
#define LcdD7			 PC6				// Pin-ul pentru firul de date D7 de pe LCD

#define LcdRS			 PA7		    // Pinul pentru selectare operatie (LCD)
#define LcdRW			 PA6				// Pinul pentru Read/ Write (LCD)
#define LcdE			 PA5				// Pinul de Enable (LCD)

#define LCD_INSTR_4wire 		0x28 	// 4 fire date, font 5x8
#define LCD_INSTR_display 		0x0C 	// Display On, Cursor On, Blinking On ( 1 Display Cursor Blink )
#define LCD_INSTR_clearDisplay  	0x01 	// Clear Display
#define LCD_INSTR_returnHome		0x02 	// Return Cursor and LCD to Home Position
#define LCD_INSTR_nextLine 		0xC0 	// Return Cursor and LCD to Home Position
#define LCD_INSTR_gotoCGRAM		0x40	// go to Character Generator RAM

/*************
 * API LCD   *
 *************/
void LCD_init();                // Initializare modul LCD.Trebuie apelata inainte de a se face orice operatie cu LCD-ul
void LCD_writeInstruction(unsigned char _instruction);	// Trimite o instructiune catre lcd (vezi datasheet)
void LCD_writeData(unsigned char _data);		// Trimite date catre LCD pentru afisare
void LCD_write(unsigned char _byte);			// trimite un bute catre LCD la modul general (nu conteaza daca e instructiune sau date)
void LCD_waitNotBusy();					// Asteptam pana cand lcd-ul devine disponibil pt o noua comanda
void LCD_waitInstructions(unsigned char _instructions);	// Asteapta un numar de cicli de ceas.
void LCD_print(char* _msg);				// Afiseaza imformatia pe LCD (doar 1 linie, primele 16 caractere)
void LCD_print2(char* _msg1, char* _msg2);		// Afisare pe 2 lini pe LCD
void LCD_printDecimal2u(unsigned int _n);		// Afisare numar in baza 10 pe LCD
void LCD_printHexa(unsigned int _n);			// Afisare numar in baza 16 pe LCD


int main()
{
    /* TODO */LCD_init();
	LCD_print("Hello world");
	return 0;
}

/*****************************
 * IMPLEMENTARE API LCD.     *
 *****************************/

void LCD_init()
{
	LcdDATA_DDR |=  (1<<LcdD4)|(1<<LcdD5)|(1<<LcdD6)|(1<<LcdD7);	// setam pinii de date ca pini de iesire
	LcdCMD_DDR  |=  (1<<LcdRS)|(1<<LcdRW)|(1<<LcdE);				// setam pinii de comenzi ca pini de iesire

	LCD_waitNotBusy();

	LcdCMD_PORT   &= ~(1<<LcdRS);									// Setam linia RS pe low
	LcdCMD_PORT   &= ~(1<<LcdRW);									// Setam linia RW pe low (acum suntem in modul de trimis instructiuni)
	LcdDATA_PORT  &= ~(1<<LcdD4)&~(1<<LcdD6)&~(1<<LcdD7); 		// Specificam ca vrem 4 fire de date, prima comanda (LcdD5 activ, restul nu)
	LcdDATA_PORT  |=  (1<<LcdD5);									// setam pinii de comenzi ca pini de iesire

	LcdCMD_PORT |=  (1<<LcdE);					// Setam linia E(nable) pe high; aceasta ii specifica LCD-ului sa preia datele
	LCD_waitInstructions(6);					// Asteptam o perioada de timp T
	LcdCMD_PORT &= ~(1<<LcdE );				// Setam linia E(nable) pe low; transferul s-a terminat

	LCD_writeInstruction(LCD_INSTR_4wire); 		// Incarcam comanda: 4 bit data, 2 lines, 5x8 font
	LCD_writeInstruction(LCD_INSTR_display); 	// Display On, Cursor On, Blinking On
	LCD_writeInstruction(0x06);					// Increment, no shift
	LCD_writeInstruction(0x01);					// Clear Display


}

void LCD_writeInstruction(unsigned char _instruction)
{
	/* TODO */					
	LCD_waitNotBusy();// asteptam ca LCD-ul sa fie liber sa primeasca comenzile	
	RS_LOW();// setam pinul RS pe low (low=instructiuni, high=date)
	RW_LOW();// setam pinul RW pe low (suntem in modul de comenzi acum)
	LCD_write(_instruction);// apelam procedura ce trimite byte-ul pe firele de date
}


void LCD_writeData(unsigned char _data)
{
        /* TODO */
	// similar cu LCD_writeInstruction
	LCD_waitNotBusy();
	RS_HIGH();
	RW_LOW();
	LCD_write(_data);

}

void LCD_write(unsigned char _byte)
{
        /* TODO */
	LcdDATA_PORT	 = 0;// scriem cei mai semnificativi 4 biti pe firele de date
	LcdDATA_PORT |= ((_byte&0x80)>>7)<<LcdD7;
	LcdDATA_PORT |= ((_byte&0x40)>>6)<<LcdD6;
	LcdDATA_PORT |= ((_byte&0x20)>>5)<<LcdD5;
	LcdDATA_PORT |= ((_byte&0x10)>>4)<<LcdD4;


	ENABLE();// Setam Pinul E pe high
	_delay_ms(200);// Asteptam o perioada de timp T
	DISABLE();// Setam Pinul E pe low
	_delay_ms(200);// Asteptam o perioada de timp T

	LcdDATA_PORT	 = 0;// scriem cei mai semnificativi 4 biti pe firele de date
	LcdDATA_PORT |= ((_byte&0x8)>>3)<<LcdD7;
	LcdDATA_PORT |= ((_byte&0x4)>>2)<<LcdD6;
	LcdDATA_PORT |= ((_byte&0x2)>>1)<<LcdD5;
	LcdDATA_PORT |= ((_byte&0x1))<<LcdD4;

	ENABLE();// Setam Pinul E pe high
	_delay_ms(200);// Asteptam o perioada de timp T
	DISABLE();// Setam Pinul E pe low
}

void LCD_waitNotBusy()
{
	unsigned char _loop = 1;

	while (_loop)
	{
		LcdDATA_DDR &= ~(1<<LcdD4 | 1<<LcdD5 | 1<<LcdD6 | 1<<LcdD7);	// Setam pinii de date de la LCD pe in pt a citi busy flag
		LcdDATA_PORT &= ~(1<<LcdD4 | 1<<LcdD5 | 1<<LcdD6 | 1<<LcdD7); 	// Dezactivam pullup resistor pentru pinii de in

		LcdCMD_PORT &= ~(1<<LcdE);					// Setam pin-ul e pe low; ar trebui sa fie deja low, doar ne asiguram
		LcdCMD_PORT &= ~(1<<LcdRS);					// Setam pinul RS pe low
		LcdCMD_PORT |=  (1<<LcdRW);					// Setam pinul RW pe high (acum suntem in modul de interogare busy/adr)

		LcdCMD_PORT |= (1<<LcdE);						// Setam Pinul E pe high
		LCD_waitInstructions(6);						// Asteptam o perioada de timp T
		_loop = LcdDATA_PIN & (1<<LcdD7);				// Citim busy flag-ul
		LcdCMD_PORT &= ~(1<<LcdE);						// Setam Pinul E pe low

		LCD_waitInstructions(6);						// Asteptam o perioada de timp T

		LcdCMD_PORT |= (1<<LcdE);						// Setam Pinul E pe high
		LCD_waitInstructions(6);						// Asteptam o perioada de timp T
		LcdCMD_PORT &= ~(1<<LcdE);						// Setam Pinul E pe low

		LcdDATA_DDR |= (1<<LcdD4 | 1<<LcdD5 | 1<<LcdD6 | 1<<LcdD7); // Setam Portul de LCD ca port de iesire la loc
	}
}

void LCD_waitInstructions(unsigned char _instructions)
{
	while (_instructions--)
		;
}


void LCD_printDecimal2u(unsigned int _n)
{
	unsigned char tmp=0;

	// Extragem sutele
	while(_n>=100)
		_n-=100;

	while(_n>=10){
		tmp++;
		_n-=10;
	}

	LCD_writeData(tmp+'0');
	LCD_writeData(_n+'0');
}

void LCD_printHexa(unsigned int _n)
{
	unsigned char _tmp = _n>>4;
	if (_tmp>9)
		_tmp += 'A'-10;
	else
		_tmp += '0';
	LCD_writeData( _tmp );
	_tmp = _n & 0x0F;
	if (_tmp>9)
		_tmp += 'A'-10;
	else
		_tmp += '0';
	LCD_writeData( _tmp );
}


void LCD_print(char* _msg)
{
	unsigned char i=0;
	for( ; _msg[i]!=0 && i<16; i++)
		LCD_writeData( _msg[i] );
}

void LCD_print2(char* _msg1, char* _msg2)
{
	LCD_writeInstruction(LCD_INSTR_clearDisplay);
	LCD_print(_msg1);
	LCD_writeInstruction(LCD_INSTR_nextLine);
	LCD_print(_msg2);
}

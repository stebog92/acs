//#ifndef __LCD_H
//#define __LCD_H

#include <avr\io.h>
#include <avr\interrupt.h>

/************************************************************************ 
 * DEFINE-uri pentru parametrizarea modulului LCD
 ************************************************************************/
#define LcdDATA_DDR		DDRC			// Portul pe care conectam firele de date la LCD-ul
#define LcdDATA_PORT	PORTC
#define LcdDATA_PIN		PINC

#define LcdCMD_DDR		DDRC			// Portul pe care conectam firele de comenzi la LCD
#define LcdCMD_PORT		PORTC
#define LcdCMD_PIN		PINC

#define LcdD4			 PC3				// Pin-ul pentru firul de date D4 de pe LCD
#define LcdD5			 PC4				// Pin-ul pentru firul de date D5 de pe LCD
#define LcdD6			 PC5				// Pin-ul pentru firul de date D6 de pe LCD
#define LcdD7			 PC6				// Pin-ul pentru firul de date D7 de pe LCD

#define LcdRS			 PC0			    // Pinul pentru selectare operatie (LCD)
#define LcdRW			 PC1				// Pinul pentru Read/ Write (LCD)
#define LcdE			 PC2				// Pinul de Enable (LCD)

#define LCD_INSTR_4wire 			0x28 	// 4 fire date, font 5x8
#define LCD_INSTR_display 			0x0f 	// Display On, Cursor On, Blinking On ( 1 Display Cursor Blink )
#define LCD_INSTR_incnoshift		0x06	// Auto-Increment, No Shift on write
#define LCD_INSTR_clearDisplay  	0x01 	// Clear Display
#define LCD_INSTR_returnHome		0x02 	// Return Cursor and LCD to Home Position
#define LCD_INSTR_nextLine 			0xC0 	// Return Cursor and LCD to Home Position
#define LCD_INSTR_gotoCGRAM			0x40	// go to Character Generator RAM


#define RS_HIGH()				do { LcdCMD_PORT |= _BV(LcdRS); } while(0)
#define RS_LOW()				do { LcdCMD_PORT &= ~_BV(LcdRS); } while(0)
 
#define RW_HIGH()				do { LcdCMD_PORT |= _BV(LcdRW); } while(0)
#define RW_LOW()				do { LcdCMD_PORT &= ~_BV(LcdRW); } while(0)
 
#define ENABLE()				do { LcdCMD_PORT |= _BV(LcdE); } while(0)
#define DISABLE()				do { LcdCMD_PORT &= ~_BV(LcdE); } while(0)
#define E_DELAY()				do { __asm__ __volatile__( "nop\n\
										nop\n\
										nop\n\
										rjmp 1f\n\
										1:" );  } while(0)


/*************
 * API LCD   *
 *************/
/* Initializare modul LCD. Trebuie apelata inainte de a se face orice operatie cu LCD-ul */
void LCD_init();                
/* Trimite o instructiune catre lcd (vezi datasheet) */
void LCD_writeInstruction(unsigned char _instruction);	
/* Trimite date catre LCD pentru afisare	*/
void LCD_writeData(unsigned char _data);		
/* Trimite un byte catre LCD la modul general (nu conteaza daca e instructiune sau date) */
void LCD_write(unsigned char _byte);
/* Asteptam pana cand lcd-ul devine disponibil pt o noua comanda	*/			
void LCD_waitNotBusy();					
/* Asteapta un numar de cicli de ceas	*/
void LCD_waitInstructions(unsigned char _instructions);	
/* Afiseaza imformatia pe LCD (doar 1 linie, primele 16 caractere)	*/
void LCD_print(char* _msg);				
/* Afiseaza imformatia pe LCD (mai multe caractere, poate trece pe alta linie)	*/
void LCD_printn(char* _msg, uint8_t n);			
/* Afisare pe 2 lini pe LCD	*/
void LCD_print2(char* _msg1, char* _msg2);		
/* Afisare numar in baza 10 pe LCD	*/
void LCD_printDecimal2u(unsigned int _n);		
/* Afisare numar in baza 16 pe LCD	*/
void LCD_printHexa(unsigned int _n);			


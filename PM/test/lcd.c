#include<avr\io.h>
#define F_CPU 16e6
#include <util/delay.h>
 
#define RS 0 //PD6
#define EN 2 //PD7
#define databits PORTC //lower nibble
#define LINE1 cmd(0x80)
#define LINE2 cmd(0xc0)
 
void port_init()
{
    DDRC = 0xff;
    DDRA = (1 << RS)|(1 << EN);
}
 
void LCD_STROBE(void)
{
    PORTA |= (1 << EN);
    _delay_us(1);
    PORTA &= ~(1 << EN);
}
 
void data(unsigned char c)
{
    PORTA |= (1 << RS);
    _delay_us(50);
    databits = (c >> 4);
    LCD_STROBE();
    databits = (c);
    LCD_STROBE();
}
 
void cmd(unsigned char c)
{
    PORTA &= ~(1 << RS);
    _delay_us(50);
    databits = (c >> 4);
    LCD_STROBE();
    databits = (c);
    LCD_STROBE();
}
 
void clear(void)
{
    cmd(0x01);
    _delay_ms(5);
}
 
void lcd_init()
{
    _delay_ms(15);
    cmd(0x30);
    _delay_ms(1);
    cmd(0x30);
    _delay_us(100);
    cmd(0x30);
    cmd(0x28);            // Function set (4-bit interface, 2 lines, 5*7Pixels)
    cmd(0x28);            // Function set (4-bit interface, 2 lines, 5*7Pixels)
    cmd(0x0c);            // Make cursorinvisible
    clear();            // Clear screen
    cmd(0x6);            // Set entry Mode(auto increment of cursor)
}
 
void string(char *p)
{
    while(*p) data(*p++);
}
 
int main()
{
    _delay_ms(50);
    port_init();
    lcd_init();
    LINE1;
    string("HELLO WORLD");
    LINE2;
    string("IT IS WORKING:-)");
    while(1);
    return 0;
}
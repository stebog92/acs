all: lab.hex

lab.elf: lab.c lcd.c	
	avr-gcc -mmcu=atmega324p -Wall -o $@ $^ -Os
lab.hex: lab.elf
	avr-objcopy  -j .text -j .data -O ihex  lab.elf lab.hex
	avr-size lab.elf
 
clean:
	rm -rf lab.elf lab.hex

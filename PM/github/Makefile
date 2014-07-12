all: lab1.hex

lab1.elf: lab1.c	
	avr-gcc -mmcu=atmega324pa -Os -Wall  -o lab1.elf lab1.c

lab1.hex: lab1.elf
	avr-objcopy  -j .text -j .data -O ihex  lab1.elf lab1.hex
	avr-size lab1.elf

clean:
	rm -rf lab1.elf lab1.hex
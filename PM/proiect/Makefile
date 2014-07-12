all: main.hex

main.elf: main.c 	
	avr-gcc -mmcu=atmega324p -Wall  -o $@ $^ -Os
main.hex: main.elf
	avr-objcopy  -j .text -j .data -O ihex  main.elf main.hex
	avr-size main.elf
 
clean:
	rm -rf main.elf main.hex

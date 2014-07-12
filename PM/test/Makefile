# AVR-GCC Makefile
PROJECT=lcd
SOURCES=lcd.c
CC=avr-gcc
OBJCOPY=avr-objcopy
MMCU=atmega32

CFLAGS=-mmcu=$(MMCU)

$(PROJECT).hex: $(PROJECT).out
	$(OBJCOPY) -j .text -j .data -O ihex $(PROJECT).out $(PROJECT).hex

$(PROJECT).out: $(SOURCES)
	$(CC) $(CFLAGS) -Os -I./ -o $(PROJECT).out $(SOURCES)

program: $(PROJECT).hex
	avrdude -p m16 -c usbasp -U flash:w:$(PROJECT).hex:i
clean:
	rm -f $(PROJECT).out
	rm -f $(PROJECT).hex
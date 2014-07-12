KDIR = /usr/src/linux-so2

kbuild:
	make -C $(KDIR) M=$(shell pwd)

clean:
	make -C $(KDIR) M=$(shell pwd) clean
	-rm -f *~

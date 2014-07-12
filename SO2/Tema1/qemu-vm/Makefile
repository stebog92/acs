run: boot.iso \
		pipe1.in pipe1.out \
		pipe2.in pipe2.out \
		disk1.img disk2.img \
		tap0 tap1
	qemu-system-i386 -cdrom $< -boot d \
		-serial pipe:pipe1 -serial pipe:pipe2 \
		-hda disk1.img -hdb disk2.img \
		-s \
		-net nic,model=pcnet -net tap,ifname=tap0,script=no,downscript=no \
		-net nic,model=rtl8139 -net tap,ifname=tap1,script=no,downscript=no

boot.iso: build-iso.sh bzImage busybox $(shell find fsimg)
	./build-iso.sh

tap0:
	./create_net.sh $@
tap1:
	./create_net.sh $@

pipe1.in:
	mkfifo $@
pipe1.out:
	mkfifo $@
pipe2.in: pipe1.out
	ln $< $@
pipe2.out: pipe2.in
	ln $< $@

disk1.img:
	qemu-img create -f raw $@ 100M
disk2.img:
	qemu-img create -f raw $@ 100M

gdb:
	gdb vmlinux -x gdb_remote

.PHONY: run clean gdb tap0

clean:
	rm -f boot.iso pipe1.in pipe1.out pipe2.in pipe2.out disk1.img disk2.img

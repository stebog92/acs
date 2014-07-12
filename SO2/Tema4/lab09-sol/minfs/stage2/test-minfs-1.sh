#!/bin/sh

# load module
insmod minfs.ko

# create mount point
mkdir -p /mnt/minfs

# format partition
./mkfs.minfs /dev/sdb

# mount filesystem
mount -t minfs /dev/sdb /mnt/minfs

# list all filesystem files
cd /mnt/minfs
ls -la

# unmount filesystem
cd ..
umount /mnt/minfs

# unload module
rmmod minfs

#!/bin/sh

# load module
insmod minfs.ko

# create mount point
mkdir -p /mnt/minfs

# format partition
./mkfs.minfs /dev/sdb

# mount filesystem
mount -t minfs /dev/sdb /mnt/minfs

# show registered filesystems
cat /proc/filesystems

# show mounted filesystems
cat /proc/mounts

# umount filesystem
umount /mnt/minfs

# unload module
rmmod minfs

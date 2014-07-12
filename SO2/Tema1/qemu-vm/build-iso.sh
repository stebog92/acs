#!/bin/bash

set -e

if ! genisoimage --version >/dev/null; then
    echo "can't find genisoimage, please install it"
    exit 1
fi

isolinuxbin=/usr/lib/syslinux/isolinux.bin
if ! [ -f "$isolinuxbin" ]; then
    echo "can't find syslinux, aborting; please install syslinux and / or set \$isolinuxbin"
    exit 1
fi

HERE=`pwd`

if [ -z "$KERNEL" ]; then
    KERNEL=$HERE/linux
fi

if [ -z "$BUSYBOX" ]; then
    BUSYBOX=$HERE/busybox
fi

RAMDISK=`mktemp -d`
RAMDISK_IMAGE=`mktemp`

find_libs ()
{
    TMP=`mktemp`
    sed 's~^.*$~/\0[-.]~' >$TMP
    {
        find /lib -maxdepth 2 -name '*.so*'
        [ -d /usr/lib ] && find  /usr/lib -maxdepth 2 -name '*.so*'
    } |
    egrep -f $TMP
    rm $TMP
}

copy_with_libs()
{
    cp $1 $2
    ldd $1 | tr -s ' ' | tr -d '\t' | cut -f 1 -d ' ' | cut -f1 -d . | grep -v '/' | find_libs | tee -a /tmp/debug | cpio -udpm $RAMDISK
}

cd $RAMDISK
mkdir -p bin sbin dev/pts lib log opt pipe sbin tmp etc
mkdir -p usr/bin usr/lib usr/sbin var/log var/tmp var/run
find -type d | xargs -r chmod 0755
chmod 1777 tmp var/tmp

#if ! [ -f $KERNEL/Kbuild ]; then
#    echo "can't find $KERNEL/KBuild, aborting; please set \$KERNEL"
#    exit 1
#fi
#
#cd $KERNEL
#INSTALL_MOD_PATH=$RAMDISK make modules_install -j3 >/dev/null

# XXX might need perf or gdb

if ! [ -f $BUSYBOX ]; then
    echo "can't find $BUSYBOX, aborting; please set \$BUSYBOX "
    exit 1
fi

echo -e "ld-linux\nld" | find_libs | cpio -updm $RAMDISK

copy_with_libs $BUSYBOX $RAMDISK/bin/
ln -s /bin/busybox $RAMDISK/bin/sh

cp -r $HERE/fsimg/* $RAMDISK

DEVICES=`mktemp`
cat >$DEVICES <<EOF
drwx	/dev
crw-	5,1	/dev/console
crw-	4,0	/dev/tty0
crw-	4,1	/dev/tty1
crw-	4,2	/dev/tty2
crw-	4,3	/dev/tty3
crw-	4,4	/dev/tty4
crw-	4,5	/dev/tty5
crw-	4,64	/dev/ttyS0
crw-	4,65	/dev/ttyS1
crw-	204,8	/dev/ttySCO
brw-	1,0	/dev/ram0
crw-	204,9	/dev/ttySC1
crw-	5,2	/dev/ptmx
crw-	136,0	/dev/pts/0
crw-	1,3	/dev/null
crw-	1,5	/dev/zero
crw-	1,1	/dev/mem
crw-	1,2	/dev/kmem
crw-	1,8	/dev/random
crw-	1,9	/dev/urandom
brw-    8,0	/dev/sda
brw-    8,16	/dev/sdb
EOF

function cpio_dev_head()
{
    case $1 in
	"c") mode=020000 ;;
	"b") mode=060000 ;;
	"d") mode=040000 ;;
    esac
    mode=$[$mode+0600]
    name=$4
    name_len=$(echo "$name" | wc -c)
    printf "07070100000000%08X00000000000000000000000100000000000000000000000000000000%08X%08x%08X00000000%s\0" $mode $2 $3 $name_len $name
    trailer=$[(4-(($name_len+6+13*8)%4))%4]
    for((i=0;i<$trailer;i++)); do echo -ne '\0'; done
}
cat $DEVICES | while read line; do
    cmd=${line:0:1}
    case $cmd in
	"c"|"b")
	    major=`echo $line|tr -s ' '|cut -f2 -d' '| cut -f1 -d,`
	    minor=`echo $line|tr -s ' '|cut -f2 -d' '| cut -f2 -d,`
	    name=`echo $line|tr -s ' '|cut -f3 -d' '`
	    cpio_dev_head $cmd $major $minor ${name:1}
	    ;;
	"d")
	    name=`echo $line|tr -s ' '|cut -f2 -d' '`
	    cpio_dev_head $cmd 0 0 ${name:1}
	    ;;
    esac
done > $RAMDISK_IMAGE
cpio_dev_head d 0 0 "TRAILER!!!" >> $RAMDISK_IMAGE
fs=$(ls -la $RAMDISK_IMAGE|tr -s ' '| cut -f5 -d' ')
trailer=$[(512-($fs%512))%512]
for((i=0;i<$trailer;i++)); do echo -ne '\0'; done >> $RAMDISK_IMAGE
cd $RAMDISK
find . | cpio -A -o -H newc -F $RAMDISK_IMAGE
gzip $RAMDISK_IMAGE
rm -f $DEVICES

cp $isolinuxbin $RAMDISK/isolinux.bin
cat >$RAMDISK/isolinux.cfg <<-EOF
	timeout 3
	default linux
	label linux
	kernel bzImage
	append root=/dev/ram ro noquiet rdinit=/init initrd=ramdisk.gz debug ignore_loglevel
EOF

cd $HERE

genisoimage \
    -f \
    -input-charset utf-8 \
    -o boot.iso \
    -b isolinux.bin \
    -c boot.cat \
    -no-emul-boot \
    -boot-load-size 4 \
    -boot-info-table \
    -graft-points \
    -R \
    bzImage=bzImage \
    ramdisk.gz=$RAMDISK_IMAGE.gz \
    isolinux.cfg=$RAMDISK/isolinux.cfg \
    $RAMDISK/isolinux.bin

rm -f $RAMDISK_IMAGE.gz
rm -rf $RAMDISK

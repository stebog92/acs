#!/bin/bash

set -x

device=$1

[ $device = tap0 ] && addr=172.20.0.1/24
[ $device = tap1 ] && addr=172.30.0.1/24

if ! ip link show dev $device &> /dev/null; then
	sudo ip tuntap add mode tap user $USER dev $device
	sudo ip link set dev $device up
	sudo ip address add dev $device $addr
fi

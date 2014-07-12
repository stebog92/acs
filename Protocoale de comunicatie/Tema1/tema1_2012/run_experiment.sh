#!/bin/bash

SPEED=5
DELAY=10
LOSS=10
CORRUPT=10
WINDOW=0

killall link
killall recv
killall send

rm -f recv_$1

./link speed=$SPEED delay=$DELAY loss=$LOSS corrupt=$CORRUPT&> link.out &
sleep 1
./recv window=$WINDOW &> /dev/null &
sleep 1

echo "Starting transfer"
time ./send speed=$SPEED delay=$DELAY loss=$LOSS corrupt=$CORRUPT $1 
sleep 2

echo "Finished transfer, checking files"
diff $1 recv_$1

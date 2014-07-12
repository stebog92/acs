PROCS=$1
IN=$2
MSG=$3
module load libraries/openmpi-1.6-gcc-4.4.6
time mpirun -np $PROCS ./tema4 $IN $MSG > out4


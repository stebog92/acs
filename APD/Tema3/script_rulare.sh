PROCS=$1
IN=$2
time mpirun -np $PROCS ./tema3 $IN out.pgm


EXE=$1
IN=$3
OUT=$4
TIME=$2
export OMP_SCHEDULE="dynamic"
export OMP_NUM_THREADS=$5
time ./$EXE $TIME $IN $OUT


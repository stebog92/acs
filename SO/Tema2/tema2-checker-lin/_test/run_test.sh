#!/bin/bash
#
# Testing framework - bash version
#
# 2012, Operating Systems
#

# ----------------- General declarations and util functions ------------------ #

MPIRUN=./mpirun
TEST_DIR=_test/bin
DEBUG_=1
max_points=90
tmpfile=/tmp/sompi
logfile="test.log"

EXIT_IF_FAIL=0

DEBUG()
{
    if test "x$DEBUG_" = "x1"; then
	$@ 1>&2
    fi
}

test_do_fail()
{
    points=$1
    printf "failed  [  0/%02d]\n" "$max_points"
    if test "x$EXIT_IF_FAIL" = "x1"; then
	exit 1
    fi
}

test_do_pass()
{
    points=$1
    printf "passed  [%02d/%02d]\n" "$points" "$max_points"
}

basic_test()
{
    printf "%02d) %s" "$test_index" "$description"

    for ((i = 0; i < 56 - ${#description}; i++)); do
	printf "."
    done

    $@ > /dev/null 2>&1
    if test $? -eq 0; then
	test_do_pass "$points"
    else
	test_do_fail "$points"
    fi
}

# ---------------------------------------------------------------------------- #

# ----------------- Init and cleanup tests ----------------------------------- #

# Initializes a test
init_test()
{
    DEBUG echo "test $test_index start"
    > $tmpfile
}

# Cleanups a test
cleanup_test()
{
    pkill -9 $(basename $test_prog) &> /dev/null
    pkill -9 mpirun &> /dev/null

    DEBUG echo "test $test_index end"
}

# Initializes the whole testing environment
# Should be the first test called
init_world()
{
    DEBUG echo "-----------------------------"
    DEBUG echo "start tests on $(date)"
    EXIT_IF_FAIL=1
}

# Cleanups the whole testing environment
# Should be the last test called
cleanup_world()
{
    pkill -9 mpirun
    DEBUG echo "end tests on $(date)"
}
# ---------------------------------------------------------------------------- #

# ----------------- Test Suite ----------------------------------------------- #

mpi_test()
{
    local num_procs=$1
    local test_prog=$2

    init_test

    cmd="$MPIRUN -np $num_procs $test_prog"
    DEBUG echo "running $cmd"

    $cmd >> $tmpfile

    basic_test test $(grep -c success $tmpfile) -eq $num_procs

    cleanup_test
}

test_mpirun_works()
{
    num_procs=5
    
    test_prog=$TEST_DIR/test_mpirun

    init_test

    cmd="$MPIRUN -np $num_procs $test_prog"
    DEBUG echo "running $cmd"

    $cmd >> $tmpfile &

    DEBUG cat $tmpfile

    n=$(ps --no-headers -C $(basename $test_prog) | wc -l)

    DEBUG echo "created $n procs, expected $num_procs"
    basic_test test $n -eq $num_procs

    cleanup_test
}

test_simple()
{
    mpi_test 2 $TEST_DIR/test_simple
}

test_double_init()
{
    mpi_test 2 $TEST_DIR/test_double_init
}

test_double_finalize()
{
    mpi_test 2 $TEST_DIR/test_double_finalize
    DEBUG cat $tmpfile
}

test_call_before_init_after_fin()
{
    mpi_test 2 $TEST_DIR/test_before_after
    DEBUG cat $tmpfile

}

test_send_recv()
{
    mpi_test 2 $TEST_DIR/test_send_recv
    DEBUG cat $tmpfile
}

test_send_recv_array()
{
    mpi_test 2 $TEST_DIR/test_send_recv_array
}

test_ring()
{
    mpi_test 8 $TEST_DIR/test_ring
}

test_matrix_multiply()
{
    mpi_test 6 $TEST_DIR/test_matrix_multiply
}

test_fun_array=(								\
    test_mpirun_works "Test if mpirun creates processes" 10
    test_simple "Test a simple Init and Finalize" 10
    test_double_init "Test double call to MPI_Init" 10
    test_double_finalize "Test double call to MPI_Finalize" 10
    test_call_before_init_after_fin "Test calling functions before init and after finalize" 10
    test_send_recv "Test simple send and receive operations" 10
    test_send_recv_array "Test sending and receiving an array" 10
    test_ring "Send data across a ring of processes" 10
    test_matrix_multiply "Distributed matrix multiplication" 10
)

# ---------------------------------------------------------------------------- #

# ----------------- Run test ------------------------------------------------- #

exec 2>>$logfile

if test $# -ne 1; then
    echo "Usage: $0 <test_number>" 1>&2
    exit 1
fi

test_index=$1

if test $test_index = "init"; then
    init_world
    exit 0
fi

if test $test_index = "cleanup"; then
    cleanup_world
    exit 0
fi

arr_index=$((($test_index - 1) * 3))
last_test=$((${#test_fun_array[@]} / 3))
description=${test_fun_array[$(($arr_index + 1))]}
points=${test_fun_array[$(($arr_index + 2))]}

if test "$test_index" -gt "$last_test" -o "$arr_index" -lt 0; then
    echo "Error: Test index is out range (1 < test_index <= $last_test)." 1>&2
    exit 1
fi

# Run proper function
${test_fun_array[$(($arr_index))]}

exit 0

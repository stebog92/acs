#!/bin/bash

if [[ $QUEUE == "ibm-quad.q" ]]; then
   #./blas-quad quad < /export/home/ncit-cluster/stud/m/mihai.ciocan1801/c-43.mtx >> quad-times
   for file in /export/home/ncit-cluster/stud/m/mihai.ciocan1801/*.mtx; do 
       ./optimized-O2 my-quad < $file >> my-quad-extra-opt_times
   done
elif [[ $QUEUE == "ibm-nehalem.q" ]]; then
   #./blas-nehalem nehalem < /export/home/ncit-cluster/stud/m/mihai.ciocan1801/c-43.mtx >> nehalem-times
   for file in /export/home/ncit-cluster/stud/m/mihai.ciocan1801/*.mtx; do
       ./optimized-O2 my-nehalem < $file >> my-nehalem-extra-opt_times
   done
elif [[ $QUEUE == "ibm-opteron.q" ]]; then
   #./blas-opteron opteron < /export/home/ncit-cluster/stud/m/mihai.ciocan1801/c-43.mtx >> opteron-times
    for file in /export/home/ncit-cluster/stud/m/mihai.ciocan1801/*.mtx; do
       ./optimized-O2 my-opteron < $file >> my-opteron-extra-opt_times
    done
fi

#!/bin/bash

if [[ $QUEUE == "ibm-quad.q" ]]; then
   ./blas-quad < /export/home/ncit-cluster/stud/m/mihai.ciocan1801/bcsstk15.mtx > quad
   ./blas-my < /export/home/ncit-cluster/stud/m/mihai.ciocan1801/bcsstk15.mtx > my-quad
elif [[ $QUEUE == "ibm-nehalem.q" ]]; then
   ./blas-nehalem < /export/home/ncit-cluster/stud/m/mihai.ciocan1801/bcsstk16.mtx > nehalem
   ./blas-my < /export/home/ncit-cluster/stud/m/mihai.ciocan1801/bcsstk16.mtx > my-nehalem

elif [[ $QUEUE == "ibm-opteron.q" ]]; then
   ./blas-opteron < /export/home/ncit-cluster/stud/m/mihai.ciocan1801/bcsstm10.mtx > opteron
   ./blas-my < /export/home/ncit-cluster/stud/m/mihai.ciocan1801/bcsstm10.mtx > my-opteron

fi

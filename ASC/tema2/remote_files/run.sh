#!/bin/bash
#
# Author: Heri
#
# Script de submitere a job-urilor pe fiecare coda, folosind compilatoare diferite
#

mprun.sh --job-name MyTestGcc-O --queue ibm-opteron.q \
	--modules "libraries/atlas-3.10.1-gcc-4.4.6-opteron" \
	--script exec_script.sh --show-qsub --show-script --batch-job
mprun.sh --job-name MyTestGcc-N --queue ibm-nehalem.q \
	--modules "libraries/atlas-3.10.1-gcc-4.4.6-nehalem" \
	--script exec_script.sh --show-qsub --show-script --batch-job
mprun.sh --job-name MyTestGcc-Q --queue ibm-quad.q \
	--modules "libraries/atlas-3.10.1-gcc-4.4.6-quad" \
	--script exec_script.sh --show-qsub --show-script --batch-job


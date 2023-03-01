#!/usr/bin/bash

source setenv.sh
CURR_DIR=${JAVAPROJ}/src/edu/ufp/inf/sd/rmi/_02_calculator
RUNSCRIPTS=${CURR_DIR}/runscripts

function trap_sigInt() {
    #TODO: make sure services are shut
    echo "Terminating script"
    exit 1
}

trap 'trap_sigInt' 2

source ${RUNSCRIPTS}/_1_runpython.sh &
source ${RUNSCRIPTS}/_2_runregistry.sh &
source ${RUNSCRIPTS}/_3_runserver.sh &
    
while [[ 1 ]]; do
    echo "Press 'Y' to run Client!"
    read  input
    case ${input} in
        [Yy]) source ${JAVAPROJ}/src/edu/ufp/inf/sd/rmi/_02_calculator/runscripts/_4_runclient.sh;;
    esac
done

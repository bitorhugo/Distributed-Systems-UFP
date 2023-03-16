#!/usr/bin/bash


source /home/bitor/projects/SD/src/edu/ufp/inf/sd/rmi/_05_observer/runscripts/setenv.sh
source /home/bitor/projects/JCompile/build.sh -n ${JAVAPROJ_NAME} -r ${JAVAPROJ}
CURR_DIR=${JAVAPROJ}/src/edu/ufp/inf/sd/rmi/_04_diglib
RUNSCRIPTS=${CURR_DIR}/runscripts

function trap_sigInt() {
    #make sure services are shut
    echo -e "Terminating Python server"
    kill $(lsof -t -i:8000)
    echo -e "Terminating rmiregistry"
    kill $(lsof -t -i:1099)
    echo -e "Terminating Java server"
    exit 1
}

trap 'trap_sigInt' 2

source ${RUNSCRIPTS}/_1_runpython.sh &
source ${RUNSCRIPTS}/_2_runregistry.sh &

while [[ 1 ]]; do
    source ${RUNSCRIPTS}/_3_runserver.sh 
    sleep infinity
done


# echo "Press 'Y' to run Client!"
#     read  input
#     case ${input} in
#         [Yy]) source ${JAVAPROJ}/src/edu/ufp/inf/sd/rmi/_02_calculator/runscripts/_4_runclient.sh;
#     esac

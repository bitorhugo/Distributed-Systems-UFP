#!/usr/bin/env bash
#@REM ************************************************************************************
#@REM Description: run 
#@REM Author: Rui S. Moreira
#@REM Date: 10/04/2018
#@REM ************************************************************************************
#@REM Script usage: runsetup <role> (where role should be: server / client)
source ./setenv.sh producer

export WORD_1=$1
export WORD_2=$2
export WORD_3=$3

echo ${ABSPATH2CLASSES}
cd ${ABSPATH2CLASSES}
#clear
#pwd
rabbit="/home/bitor/projects/rabbit/src/main/java/edu/ufp/inf/sd/rabbitmqservices/amqp-client-5.17.0.jar"

slf="/home/bitor/projects/rabbit/src/main/java/edu/ufp/inf/sd/rabbitmqservices/slf4j-api-1.7.36.jar"

java -cp ${CLASSPATH}":"${rabbit}":"${slf} \
     ${JAVAPACKAGEROLEPATH}.${PRODUCER_CLASS_PREFIX} ${BROKER_HOST} ${BROKER_PORT} ${BROKER_QUEUE} ${WORD_1} ${WORD_2} ${WORD_3}


cd ${ABSPATH2SRC}/${JAVASCRIPTSPATH}
#pwd

#!/usr/bin/env bash
#REM ************************************************************************************
#REM Description: run 
#REM Author: Rui S. Moreira
#REM Date: 10/04/2018
#REM ************************************************************************************
#REM Script usage: runclient <role> (where role should be: producer / consumer)
source ./setenv.sh consumer

rabbit="/home/bitor/projects/rabbit/src/main/java/edu/ufp/inf/sd/rabbitmqservices/amqp-client-5.17.0.jar"

slf="/home/bitor/projects/rabbit/src/main/java/edu/ufp/inf/sd/rabbitmqservices/slf4j-api-1.7.36.jar"

echo ${ABSPATH2CLASSES}
cd ${ABSPATH2CLASSES}
#clear
#pwd
java -cp ${CLASSPATH}":"${rabbit}":"${slf} \
     ${JAVAPACKAGEROLE}.${CONSUMER_CLASS_PREFIX} ${BROKER_HOST} ${BROKER_PORT} ${BROKER_QUEUE}

echo ${ABSPATH2SRC}/${JAVASCRIPTSPATH}
cd ${ABSPATH2SRC}/${JAVASCRIPTSPATH}
#pwd

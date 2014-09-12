#!/bin/bash

echo $1

javac -d $1 -classpath ".:apache-log4j-2.0.2-bin/log4j-api-2.0.2.jar:apache-log4j-2.0.2-bin/log4j-core-2.0.2.jar" src/*/*.java
cp registry.properties $1
cp message.properties $1
cp log4j2.xml $1
cp run.sh $1

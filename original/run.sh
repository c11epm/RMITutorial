#!/bin/bash
clear
echo "<--- run.sh script started --->"
echo ""
echo "Program output: "
echo ""
echo "----------------------------------------------------"
echo ""
if [ "$1" = "Client" ]; then
	java -classpath ".:/home/c11/c11epm/5dv147/lab1/apache-log4j-2.0.2-bin/log4j-api-2.0.2.jar:/home/c11/c11epm/5dv147/lab1/apache-log4j-2.0.2-bin/log4j-core-2.0.2.jar" src.Client.Client
elif [ "$1" = "Server" ]; then
	#TODO: Fix string parameter in this script
	java -classpath ".:/home/c11/c11epm/5dv147/lab1/apache-log4j-2.0.2-bin/log4j-api-2.0.2.jar:/home/c11/c11epm/5dv147/lab1/apache-log4j-2.0.2-bin/log4j-core-2.0.2.jar" src.Server.Server
elif [ "$1" = "newServer" ]; then
	#TODO: Fix string parameter in this script
	java -classpath ".:/home/c11/c11epm/5dv147/lab1/apache-log4j-2.0.2-bin/log4j-api-2.0.2.jar:/home/c11/c11epm/5dv147/lab1/apache-log4j-2.0.2-bin/log4j-core-2.0.2.jar" src.Server.newServer
else
	echo "Usage ./run.sh {Client|Server [return string]}"
fi
echo ""
echo "<--- run.sh" $1 "ended --->"

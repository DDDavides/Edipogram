#!/bin/bash

echo Halting Consul  

docker stop asw-consul 
docker rm asw-consul 

echo Halting db-enigmi

docker stop db-enigmi
docker rm db-enigmi

echo Halting db-connessioni

docker stop db-connessioni
docker rm db-connessioni

echo Halting Kafka, Zookeeper and channels
docker-compose down
docker-compose rm -f
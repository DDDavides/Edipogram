#!/bin/bash

docker compose build

docker compose up -d --scale enigmi-seguiti=2 --scale enigmi=2

KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

echo Creating Kafka topics for the enigmi e connessioni service...
docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server kafka:9092 \
  --create --if-not-exists --topic enigma-created-service-event-channel --replication-factor 1 --partitions 4

echo Creating topic for connessioni

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server kafka:9092 \
  --create --if-not-exists --topic connessione-created-service-event-channel --replication-factor 1 --partitions 4


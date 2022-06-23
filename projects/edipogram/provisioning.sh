#!/bin/bash

echo Starting Consul in a Docker Container
docker run -d --hostname localhost --name asw-consul --publish 8500:8500 consul

echo Starting db for enigmi in a Docker Container
docker run --name db-enigmi -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=enigmidb -e POSTGRES_USER=postgres -p 5438:5432 -d postgres

echo Starting db for connessioni in a Docker Container
docker run --name db-connessioni -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=connessionidb -e POSTGRES_USER=postgres -p 5439:5432 -d postgres

echo Starting db for enigmiseguiti in a Docker Container
docker run --name db-enigmiseguiti -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=enigmiseguitidb -e POSTGRES_USER=postgres -p 5440:5432 -d postgres

#TODO: eseguire TUTTI i container tramite docker compose
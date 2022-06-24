#!/bin/bash

if [ -z "$1" ]; then
	read -p "Sei sicuro di voler bloccare l'app, questo fermera' l'infrastruttura e tutti i processi java attivi. [y/N]: " \
	resp
else
	resp="$1"
fi

if [ "$resp" == 'y' ] || [ "$resp" == 'Y' ]; then
	./kill-java-processes.sh

	echo Halting edipogram Network
	docker compose down
	docker compose rm -f

fi



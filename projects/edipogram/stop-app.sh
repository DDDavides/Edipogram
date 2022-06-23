#!/bin/bash

# echo "Sei sicuro di voler bloccare l'app? Questo fermera' l'infrastruttura [y/N]"
# read $resp

# if [[ $resp -eq 'y' ] || [ $resp -eq 'Y' ]]; then
# fi

./kill-java-processes.sh

echo Halting edipogram Network
docker compose down
docker compose rm -f

#!/bin/bash

echo Halting edipogram Network
docker compose down -v
docker compose rm -f

#rm -rd ./database/*/*
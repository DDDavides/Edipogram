# Edipogram

Repository del progetto del corso di Architettura dei Sistemi Software di Roma Tre (2022) di Davide Galletti, Davide Gattini e Davide Molitierno.

Questo repository contiene il codice del *progetto Edipogram* 
del corso di [Architettura dei Sistemi Software]

## Software necessari sul proprio PC 

### Per la gestione degli ambienti di esecuzione  

* [VirtualBox](https://www.virtualbox.org/)
* [Vagrant](https://www.vagrantup.com/) 
* [Git](https://git-scm.com/) 
* opzionalmente [Docker](https://www.docker.com/), 
  che però non è strettamente necessario, poiché può essere eseguito nelle macchine virtuali. 

### Per lo sviluppo del software 

Ecco il software opzionale per lo sviluppo del software (non è strettamente necessario, poiché può essere eseguito nelle macchine virtuali):
* [Java SDK](http://www.oracle.com/technetwork/java/javase/) 
* [Gradle](http://gradle.org/) 

## Organizzazione del repository 

Questo repository è organizzato in diverse sezioni (cartelle): 
* [edipogram](projects/) contiene il codice di *Edipogram*, e i relativi script per l'avvio, la chiusura e le varie curl;
* [environments](environments/) contiene il codice per la gestione degli *ambienti distribuiti*, 
  con una sottosezione (sottocartella) per ciascuno degli ambienti distribuiti 
  su cui poter eseguire le applicazioni distribuite sviluppate; 
* [resources](resources/) contiene ulteriori risorse condivise per la gestione degli *ambienti distribuiti*.  
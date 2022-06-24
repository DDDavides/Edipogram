package asw.edipogram.enigmiseguiti.domain;

import asw.edipogram.common.api.event.DomainEvent;
import asw.edipogram.connessioni.api.event.ConnessioneCreatedEvent;
import asw.edipogram.enigmi.api.event.EnigmaCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EnigmiSeguitiEventAdapter {

    private final Logger logger = Logger.getLogger(EnigmiSeguitiEventAdapter.class.toString());
    @Autowired
    private EnigmiSeguitiService enigmiSeguitiService;

    //TODO: Gestisci con polimorfismo
    public void onEvent(DomainEvent event) {
        if (event.getClass().equals(EnigmaCreatedEvent.class)) {
            onEventEnigmaCreated((EnigmaCreatedEvent) event);
        } else if (event.getClass().equals(ConnessioneCreatedEvent.class)) {
            onEventConnessioneCreated((ConnessioneCreatedEvent) event);
        } else {
            //TODO: gestisci il caso in cui non riconosco l'evento
            logger.info("Nessun Evento di dominio");
        }
    }

    public void onEventConnessioneCreated(ConnessioneCreatedEvent cce) {
        logger.info("Evento connessioneCreated: " + cce);
        Connessione connessione = new Connessione(cce.getId(), cce.getUtente(), cce.getTipo());
        logger.info("Aggiungo connessione: " + connessione);
        enigmiSeguitiService.addConnessione(connessione);
        logger.info("Aggiunta riuscita");
        logger.info("Aggiungo gli enigmi -" + connessione.getTipo() + "- seguiti da " + connessione.getUtente());
        enigmiSeguitiService.addEnigmiSeguiti(connessione);
        logger.info("Aggiunta risucita");
    }

    public void onEventEnigmaCreated(EnigmaCreatedEvent ece) {
        logger.info("Evento enigmaCreated: " + ece);
        Enigma enigma = new Enigma(ece.getId(), ece.getAutore(), ece.getTipo(), ece.getTipoSpecifico(), ece.getTitolo(), ece.getTesto().toArray(new String[0]));
        logger.info("Aggiungo enigma: " + enigma);
        enigmiSeguitiService.addEnigma(enigma);
        logger.info("Aggiunta riuscita");
        logger.info("Aggiungo l'enigma " + enigma.getId() + "ad utenti che seguono -" + enigma.getTipo() + "-");
        enigmiSeguitiService.addEnigmiSeguiti(enigma);
        logger.info("Aggiunta risucita");
    }
}
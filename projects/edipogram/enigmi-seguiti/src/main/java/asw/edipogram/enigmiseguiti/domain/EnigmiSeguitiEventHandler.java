package asw.edipogram.enigmiseguiti.domain;

import asw.edipogram.common.api.event.DomainEvent;
import asw.edipogram.connessioni.api.event.ConnessioneCreatedEvent;
import asw.edipogram.enigmi.api.event.EnigmaCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EnigmiSeguitiEventHandler {

    private final Logger logger = Logger.getLogger(EnigmiSeguitiEventHandler.class.toString());
    @Autowired
    private EnigmiSeguitiService enigmiSeguitiService;

    //TODO: Gestisci con polimorfismo
    public void onEvent(DomainEvent event) {
        if (event.getClass().equals(EnigmaCreatedEvent.class)) {
            onEnigmaCreatedEvent((EnigmaCreatedEvent) event);
        } else if (event.getClass().equals(ConnessioneCreatedEvent.class)) {
            onConnessioneCreatedEvent((ConnessioneCreatedEvent) event);
        } else {
            //TODO: gestisci il caso in cui non riconosco l'evento
            logger.info("Nessun Evento di dominio");
        }
    }

    public void onConnessioneCreatedEvent(ConnessioneCreatedEvent cce) {
        logger.info("Evento connessioneCreated: " + cce);
        enigmiSeguitiService.createConnessione(cce.getId(), cce.getUtente(), cce.getTipo());
        logger.info("Aggiunta risucita");
    }

    public void onEnigmaCreatedEvent(EnigmaCreatedEvent ece) {
        logger.info("Evento enigmaCreated: " + ece);
        enigmiSeguitiService.createEnigma(ece.getId(), ece.getAutore(), ece.getTipo(), ece.getTipoSpecifico(), ece.getTitolo(), ece.getTesto().toArray(new String[0]));
        logger.info("Aggiunta risucita");
    }
}
package asw.edipogram.enigmiseguiti.domain;

import asw.edipogram.common.api.event.DomainEvent;
import asw.edipogram.connessioni.api.event.ConnessioneCreatedEvent;
import asw.edipogram.enigmi.api.event.EnigmaCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Component
public class EnigmiSeguitiEventAdapter {

    private final Logger logger = Logger.getLogger(EnigmiSeguitiEventAdapter.class.toString());
    @Autowired
    private EnigmiSeguitiService enigmiSeguitiService;

    public void onEvent(DomainEvent event) {
        if (event.getClass().equals(EnigmaCreatedEvent.class)) {
            EnigmaCreatedEvent ece = (EnigmaCreatedEvent) event;
            enigmaCreated(ece);
        } else if (event.getClass().equals(ConnessioneCreatedEvent.class)) {
            ConnessioneCreatedEvent cce = (ConnessioneCreatedEvent) event;
            connessioneCreated(cce);
        } else {
            //TODO: gestisci il caso in cui non riconosco l'evento
        }
    }

    public void connessioneCreated(ConnessioneCreatedEvent cce) {
        logger.info("Evento connessione: " + cce);
        Connessione connessione = new Connessione(cce.getId(), cce.getUtente(), cce.getTipo());
        enigmiSeguitiService.addConnessione(connessione);
    }

    public void enigmaCreated(EnigmaCreatedEvent ece) {
        Enigma enigma = new Enigma(ece.getId(), ece.getAutore(), ece.getTipo(), ece.getTipoSpecifico(), ece.getTitolo(), ece.getTesto().toArray(new String[0]));
        enigmiSeguitiService.addEnigma(enigma);
    }
}
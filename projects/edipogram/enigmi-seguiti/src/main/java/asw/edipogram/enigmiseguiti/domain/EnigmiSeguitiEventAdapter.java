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
        }
    }

    public void onEventConnessioneCreated(ConnessioneCreatedEvent cce) {
        logger.info("Evento connessione: " + cce);
        Connessione connessione = new Connessione(cce.getId(), cce.getUtente(), cce.getTipo());
        enigmiSeguitiService.addConnessione(connessione);
        enigmiSeguitiService.addEnigmaSeguito(connessione);
    }

    public void onEventEnigmaCreated(EnigmaCreatedEvent ece) {
        Enigma enigma = new Enigma(ece.getId(), ece.getAutore(), ece.getTipo(), ece.getTipoSpecifico(), ece.getTitolo(), ece.getTesto().toArray(new String[0]));
        enigmiSeguitiService.addEnigma(enigma);
        enigmiSeguitiService.addEnigmaSeguito(enigma);
    }
}
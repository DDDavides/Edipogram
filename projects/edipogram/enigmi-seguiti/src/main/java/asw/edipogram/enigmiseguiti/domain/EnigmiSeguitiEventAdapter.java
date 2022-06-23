package asw.edipogram.enigmiseguiti.domain;

import asw.edipogram.common.api.event.DomainEvent;
import asw.edipogram.connessioni.api.event.ConnessioneCreatedEvent;
import asw.edipogram.enigmi.api.event.EnigmaCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class EnigmiSeguitiEventAdapter {

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
        Connessione connessione = new Connessione(cce.getId(), cce.getUtente(), cce.getTipo());
        enigmiSeguitiService.addConnessione(connessione);
    }

    public void enigmaCreated(EnigmaCreatedEvent event) {
        Enigma enigma = new Enigma(event.getId(), event.getAutore(), event.getTipo(), event.getTipoSpecifico(), event.getTitolo(), (String[]) event.getTesto().toArray());
        enigmiSeguitiService.addEnigma(enigma);
    }
}
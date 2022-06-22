package asw.edipogram.connessioni.domain;

import asw.edipogram.common.api.event.DomainEvent;

public interface ConnessioniMessagePublisher {
    public void publish(DomainEvent message);

}

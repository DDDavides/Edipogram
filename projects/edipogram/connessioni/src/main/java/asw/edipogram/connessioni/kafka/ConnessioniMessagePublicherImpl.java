package asw.edipogram.connessioni.kafka;

import asw.edipogram.connessioni.domain.Connessione;
import asw.edipogram.connessioni.domain.ConnessioniMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import asw.edipogram.common.api.event.DomainEvent;
import org.springframework.stereotype.Component;

@Component
public class ConnessioniMessagePublicherImpl implements ConnessioniMessagePublisher {

    //@Value()
    private String channel;

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    @Override
    public void publish(DomainEvent message) {
        template.send(channel, message);
    }
}

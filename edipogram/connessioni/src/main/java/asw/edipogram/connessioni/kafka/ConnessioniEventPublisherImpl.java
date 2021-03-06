package asw.edipogram.connessioni.kafka;

import asw.edipogram.connessioni.domain.ConnessioniEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import asw.edipogram.common.api.event.DomainEvent;
import org.springframework.stereotype.Component;

@Component
public class ConnessioniEventPublisherImpl implements ConnessioniEventPublisher {

    @Value("${asw.edipogram.connessioni.channel.out}")
    private String channel;

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    @Override
    public void publish(DomainEvent message) {
        template.send(channel, message);
    }
}

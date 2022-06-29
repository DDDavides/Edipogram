package asw.edipogram.enigmiseguiti.eventListener;

import asw.edipogram.common.api.event.DomainEvent;
import asw.edipogram.enigmiseguiti.domain.EnigmiSeguitiEventHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EnigmiSeguitiDomainEventListener {
    @Autowired
    private EnigmiSeguitiEventHandler enigmiSeguitiEventHandler;

    @KafkaListener(topics = {"${asw.edipogram.enigmiseguiti.channel.in-enigma-created}", "${asw.edipogram.enigmiseguiti.channel.in-connessione-created}"}, groupId = "${asw.edipogram.enigmiseguiti.groupId}")
    public void listen(ConsumerRecord<String, DomainEvent> record) {
        enigmiSeguitiEventHandler.onEvent(record.value());
    }
}

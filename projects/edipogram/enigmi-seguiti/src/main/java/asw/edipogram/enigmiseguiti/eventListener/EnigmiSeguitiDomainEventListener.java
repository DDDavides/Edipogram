package asw.edipogram.enigmiseguiti.eventListener;

import asw.edipogram.common.api.event.DomainEvent;
import asw.edipogram.enigmi.api.event.EnigmiServiceChannel;
import asw.edipogram.enigmiseguiti.domain.EnigmiSeguitiEventAdapter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EnigmiSeguitiDomainEventListener {

    @Autowired
    private EnigmiSeguitiEventAdapter enigmiSeguitiEventAdapter;

    //TODO: gestisci eccezioni
    @KafkaListener(topics = EnigmiServiceChannel.channel, groupId = "${asw.edipogram.enigmiseguiti.groupId}")
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception{
        enigmiSeguitiEventAdapter.onEvent(record.value());
    }
}

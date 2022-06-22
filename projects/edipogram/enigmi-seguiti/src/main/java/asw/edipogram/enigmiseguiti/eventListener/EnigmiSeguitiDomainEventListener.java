package asw.edipogram.enigmiseguiti.eventListener;

import asw.edipogram.common.api.event.DomainEvent;
import asw.edipogram.enigmi.api.event.EnigmiServiceChannel;
import asw.edipogram.enigmiseguiti.domain.EnigmiSeguitiService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

public class EnigmiSeguitiDomainEventListener {

    @Autowired
    private EnigmiSeguitiService enigmiSeguitiService;

    //TODO: aggiungi in topic il canale delle connessioni created
    @KafkaListener(topics = EnigmiServiceChannel.channel)
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception{
        enigmiSeguitiService.onEvent(record.value());
    }
}

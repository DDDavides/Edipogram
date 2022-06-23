package asw.edipogram.enigmiseguiti.eventListener;

import asw.edipogram.common.api.event.DomainEvent;
import asw.edipogram.enigmiseguiti.domain.EnigmiSeguitiEventAdapter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EnigmiSeguitiDomainEventListener {

    /*@Value("${asw.edipogram.enigmiseguiti.channel.in-enigma-created}")
    private String channel1;
    @Value("${asw.edipogram.enigmiseguiti.channel.in-connessione-created}")
    private String channel2;
    @Value("${asw.edipogram.enigmiseguiti.groupId}")
    private String groupId;*/
    
    @Autowired
    private EnigmiSeguitiEventAdapter enigmiSeguitiEventAdapter;

    //TODO: gestisci eccezioni
    @KafkaListener(topics = {"${asw.edipogram.enigmiseguiti.channel.in-enigma-created}", "${asw.edipogram.enigmiseguiti.channel.in-connessione-created}"}, groupId = "${asw.edipogram.enigmiseguiti.groupId}")
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception{
        enigmiSeguitiEventAdapter.onEvent(record.value());
    }
}

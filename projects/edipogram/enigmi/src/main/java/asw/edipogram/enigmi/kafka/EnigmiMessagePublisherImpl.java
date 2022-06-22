package asw.edipogram.enigmi.kafka;

import asw.edipogram.common.api.event.DomainEvent;
import asw.edipogram.enigmi.domain.EnigmiMessagePublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

@Component
public class EnigmiMessagePublisherImpl implements EnigmiMessagePublisher{

	@Value("${asw.edipogram.enigmi.channel.out}")
    private String channel;

	@Autowired
	private KafkaTemplate<String, DomainEvent> template;

	@Override
	public void publish(DomainEvent event) {
		template.send(channel, event);
	}

}

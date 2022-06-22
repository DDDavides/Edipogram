package asw.edipogram.enigmi.kafka;

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
	private KafkaTemplate<String, String> template;

	@Override
	public void publish(String message) {
		template.send(channel, message);
	}

}

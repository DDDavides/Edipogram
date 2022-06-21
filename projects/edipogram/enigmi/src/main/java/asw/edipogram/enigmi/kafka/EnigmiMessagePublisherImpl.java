package asw.edipogram.enigmi.kafka;

import asw.edipogram.enigmi.domain.EnigmiMessagePublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnigmiMessagePublisherImpl implements EnigmiMessagePublisher{

	@Value("${asw.edipogram.enigmi.channel.out}")
    private String channel;

	@Override
	public void publish(String message) {
		// TODO Auto-generated method stub
		
	}

}

package asw.edipogram.enigmi.domain;

import asw.edipogram.common.api.event.DomainEvent;

public interface EnigmiMessagePublisher {
	
	 public void publish(DomainEvent event);

}

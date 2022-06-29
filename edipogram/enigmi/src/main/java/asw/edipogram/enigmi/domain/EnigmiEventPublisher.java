package asw.edipogram.enigmi.domain;

import asw.edipogram.common.api.event.DomainEvent;

public interface EnigmiEventPublisher {
	
	 public void publish(DomainEvent event);

}

package asw.edipogram.connessioni.domain;

import asw.edipogram.common.api.event.DomainEvent;
import asw.edipogram.connessioni.api.event.ConnessioneCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
public class ConnessioniService {
	@Autowired
	private ConnessioniRepository connessioniRepository;

	@Autowired
	private ConnessioniEventPublisher connessioniEventPublisher;

 	public Connessione createConnessione(String utente, String tipo) {
		Connessione connessione = new Connessione(utente, tipo);
		connessione = connessioniRepository.save(connessione);

		DomainEvent event = new ConnessioneCreatedEvent(connessione.getId(), connessione.getUtente(), connessione.getTipo());
		connessioniEventPublisher.publish(event);

		return connessione;
	}

 	public Connessione getConnessione(Long id) {
		Connessione connessione = connessioniRepository.findById(id).orElse(null);
		return connessione;
	}

 	public Collection<Connessione> getConnessioni() {
		Collection<Connessione> connessioni = connessioniRepository.findAll();
		return connessioni;
	}

	public Collection<Connessione> getConnessioniByUtente(String utente) {
		Collection<Connessione> connessioni = connessioniRepository.findByUtente(utente);
		return connessioni;
	}

}

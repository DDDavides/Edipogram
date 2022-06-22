package asw.edipogram.enigmiseguiti.domain;

import asw.edipogram.common.api.event.DomainEvent;
import asw.edipogram.enigmi.api.event.EnigmiCreatedEvent;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*; 

@Service 
public class EnigmiSeguitiService {

	@Autowired 
	private ConnessioniService connessioniService;

	@Autowired 
	private EnigmiService enigmiService;

	/* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */ 
	public Collection<Enigma> getEnigmiSeguiti(String utente) {
		//TODO: deve accedere alla propria base di dati
		Collection<Enigma> enigmiSeguiti = new TreeSet<>(); 
		Collection<Connessione> connessioni = connessioniService.getConnessioniByUtente(utente); 
		Collection<String> tipiSeguiti = 
			connessioni
				.stream()
				.map(c -> c.getTipo())
				.collect(Collectors.toSet()); 
		if (tipiSeguiti.size()>0) {
			Collection<Enigma> enigmi = enigmiService.getEnigmiByTipi(tipiSeguiti);
			enigmiSeguiti.addAll(enigmi); 
		}
		return enigmiSeguiti; 
	}

	//TODO: aggiungere logica applicativa per quando riceve EnigmaCreatedEvent
	public Long addEnigma() { throw new NotYetImplementedException(); }

	//TODO: aggiungere logica applicativa per ConnessioneCreatedEvent
	public Long addConnessione() { throw new NotYetImplementedException(); }

	public void onEvent(DomainEvent event) {
		if(event.getClass().equals(EnigmiCreatedEvent.class)) {
			EnigmiCreatedEvent ece = (EnigmiCreatedEvent) event;
			enigmaCreated(ece);
		}
		//TODO: elif l'evento è della classe relativa alla creazione di una connessione ed else se l'evento non si conosce
	}

	private void enigmaCreated(EnigmiCreatedEvent event) {
		//TODO: Moli deve cambiare le String[] in array di Stringhe
		//TODO: Moli deve aggiungere l'id all'enigmaCreatedEvent
		Enigma enigma = new Enigma(event.getAutore(), event.getTipo(), event.getTipoSpecifico(), event.getTitolo(), event.getTesto());
		//TODO: aggiungi al db di enigmi
	}

	//TODO: Aggiungi metodo per creare la connessione


}

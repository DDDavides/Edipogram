package asw.edipogram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*; 

@Service 
public class EnigmiSeguitiService {

	@Autowired
	private ConnessioneRepository connessioneRepository;

	@Autowired 
	private EnigmaRepository enigmaRepository;

	/* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */ 
	public Collection<Enigma> getEnigmiSeguiti(String utente) {
		//TODO: deve accedere alla propria base di dati
		Collection<Enigma> enigmiSeguiti = new TreeSet<>(); 
		Collection<Connessione> connessioni = connessioneRepository.getConnessioniByUtente(utente);
		Collection<String> tipiSeguiti = 
			connessioni
				.stream()
				.map(c -> c.getTipo())
				.collect(Collectors.toSet()); 
		if (tipiSeguiti.size()>0) {
			//Collection<Enigma> enigmi = enigmaRepository.getEnigmiByTipi(tipiSeguiti);
			//enigmiSeguiti.addAll(enigmi);
		}
		return enigmiSeguiti; 
	}

	//TODO: aggiungere logica applicativa per quando riceve EnigmaCreatedEvent
	public Enigma addEnigma(Enigma enigma) { return enigmaRepository.save(enigma); }

	//TODO: aggiungere logica applicativa per ConnessioneCreatedEvent
	public Connessione addConnessione(Connessione connessione) {
		return connessioneRepository.save(connessione);
	}

}

package asw.edipogram.enigmiseguiti.domain;

import asw.edipogram.enigmiseguiti.rest.EnigmiSeguitiController;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.*;

@Service 
public class EnigmiSeguitiService {

	private final Logger logger = Logger.getLogger(EnigmiSeguitiService.class.toString());
	@Autowired
	private ConnessioneRepository connessioneRepository;

	@Autowired 
	private EnigmaRepository enigmaRepository;

	/* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */ 
	public Collection<Enigma> getEnigmiSeguiti(String utente) {
		Collection<Enigma> enigmiSeguiti = new TreeSet<>();
		logger.info("REPOSITORY method: getting connessioni by utente");
		Collection<Connessione> connessioni = connessioneRepository.getConnessioniByUtente(utente);
		logger.info("Connessioni utente: " + connessioni);
		Collection<String> tipiSeguiti = 
			connessioni
				.stream()
				.map(c -> c.getTipo())
				.collect(Collectors.toSet());
		logger.info("REPOSITORY method return: \n" + tipiSeguiti);
		if (tipiSeguiti.size()>0) {
			logger.info("TipiSeguiti dall'utente > 0");
			logger.info("REPOSITORY method: getting enigmi by tipi");
			//TODO: controlla se migliorare list<String> e verifica funzionamento
			Collection<Enigma> enigmi = enigmaRepository.getEnigmiByTipi(new ArrayList<String>(tipiSeguiti));
			logger.info("REPOSITORY method return: \n" + enigmi);
			enigmiSeguiti.addAll(enigmi);
		}
		return enigmiSeguiti; 
	}

	public Enigma addEnigma(Enigma enigma) {
		return enigmaRepository.save(enigma);
	}

	public Connessione addConnessione(Connessione connessione) {
		return connessioneRepository.save(connessione); 
	}

}

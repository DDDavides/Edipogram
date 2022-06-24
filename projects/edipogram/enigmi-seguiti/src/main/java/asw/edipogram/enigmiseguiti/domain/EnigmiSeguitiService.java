package asw.edipogram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.Logger;

@Service 
public class EnigmiSeguitiService {

	private final Logger logger = Logger.getLogger(EnigmiSeguitiService.class.toString());
	@Autowired
	private ConnessioneRepository connessioneRepository;

	@Autowired 
	private EnigmaRepository enigmaRepository;

	@Autowired
	private EnigmiSeguitiRepository enigmiSeguitiRepository;

	/*public Collection<Enigma> getEnigmiSeguiti(String utente) {
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
	}*/

	/* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */ 
	public Collection<EnigmiSeguiti> getEnigmiSeguiti(String utente) {
		Collection<EnigmiSeguiti> enigmiSeguiti = new TreeSet<>();
		logger.info("REPOSITORY method: getting connessioni by utente");
		enigmiSeguiti = enigmiSeguitiRepository.getEnigmiSeguitiByUtente(utente);
		logger.info("REPOSITORY method return: \n" + enigmiSeguiti);
		return enigmiSeguiti;
	}

	public Enigma addEnigma(Enigma enigma) {
		return enigmaRepository.save(enigma);
	}

	public Connessione addConnessione(Connessione connessione) {
		return connessioneRepository.save(connessione); 
	}

	public Collection<EnigmiSeguiti> addEnigmiSeguiti(Connessione connessione) {
		Collection<Enigma> enigmi = enigmaRepository.getEnigmaByTipo(connessione.getTipo());
		logger.info("Presi enigmi del tipo: " + enigmi);
		Collection<EnigmiSeguiti> enigmiSeguiti = new LinkedList<EnigmiSeguiti>();
		for (Enigma enigma: enigmi) {
			logger.info("Salvando enigma seguito per: " + enigma);
		 	EnigmiSeguiti es = new EnigmiSeguiti(connessione.getUtente(), enigma.getId(),
					enigma.getAutore(), enigma.getTipo(),  enigma.getTipoSpecifico(), enigma.getTitolo(),
					enigma.getTesto());
			logger.info("Salvando enigma seguito per: " + enigma);
			es = enigmiSeguitiRepository.save(es);
			enigmiSeguiti.add(es);
			logger.info("Salvato enigma seguito: " + es);
		}
		return enigmiSeguiti;
	}

	public Collection<EnigmiSeguiti> addEnigmiSeguiti(Enigma enigma) {
		Collection<Connessione> connessioni = connessioneRepository.getConnessioneByTipo(enigma.getTipo());
		Collection<EnigmiSeguiti> enigmiSeguiti = new LinkedList<EnigmiSeguiti>();
		for (Connessione connessione : connessioni){
			EnigmiSeguiti es = enigmiSeguitiRepository.save(new EnigmiSeguiti(connessione.getUtente(), enigma.getId(),
					enigma.getAutore(), enigma.getTipo(), enigma.getTipoSpecifico(),
					enigma.getTitolo(), enigma.getTesto()));
			enigmiSeguiti.add(es);
		}
		return enigmiSeguiti;
	}
}

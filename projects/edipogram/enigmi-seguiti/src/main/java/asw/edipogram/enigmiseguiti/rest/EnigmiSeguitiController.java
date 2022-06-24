package asw.edipogram.enigmiseguiti.rest;

import asw.edipogram.enigmiseguiti.domain.EnigmiSeguiti;
import asw.edipogram.enigmiseguiti.domain.EnigmiSeguitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.logging.Logger;

@RestController
public class EnigmiSeguitiController {

	private final Logger logger = Logger.getLogger(EnigmiSeguitiController.class.toString()); 

	@Autowired 
	private EnigmiSeguitiService enigmiSeguitiService;

	/* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */ 
	/*@GetMapping("/enigmiseguiti/{utente}")
	public Collection<Enigmi> getEnigmiSeguiti(@PathVariable String utente) {
		Instant start = Instant.now();
		logger.info("REST CALL: getEnigmiSeguiti " + utente);
		//TODO: cambia in Collection<EnigmiSeguiti>
		Collection<Enigma> enigmi = enigmiSeguitiService.getEnigmiSeguiti(utente);
		Duration duration = Duration.between(start, Instant.now()); 
		logger.info("getEnigmiSeguiti " + utente + " (trovati " + enigmi.size() + " enigmi in " + duration.toMillis() + " ms): " + enigmi);
		return enigmi; 
	}*/

	@GetMapping("/enigmiseguiti/{utente}")
	public Collection<EnigmiSeguiti> getEnigmiSeguiti(@PathVariable String utente) {
		Instant start = Instant.now();
		logger.info("REST CALL: getEnigmiSeguiti " + utente);
		Collection<EnigmiSeguiti> enigmiSeguiti = enigmiSeguitiService.getEnigmiSeguiti(utente);
		Duration duration = Duration.between(start, Instant.now());
		logger.info("getEnigmiSeguiti " + utente + " (trovati " + enigmiSeguiti.size() + " enigmi in " + duration.toMillis() + " ms): " + enigmiSeguiti);
		return enigmiSeguiti;
	}
	
}

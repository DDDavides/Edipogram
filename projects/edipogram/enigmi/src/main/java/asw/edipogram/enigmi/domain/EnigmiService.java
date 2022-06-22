package asw.edipogram.enigmi.domain;

import asw.edipogram.enigmi.api.event.EnigmaCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import asw.edipogram.common.api.event.DomainEvent;

import java.util.*;

@Service
public class EnigmiService {

	@Autowired
	private EnigmiRepository enigmiRepository;
	@Autowired
	private EnigmiMessagePublisher enigmiMessagePublisher;

 	public Enigma createEnigma(String autore, String tipo, String tipoSpecifico, String titolo, String[] testo, String[] soluzione) {
		Enigma enigma = new Enigma(autore, tipo, tipoSpecifico, titolo, testo, soluzione); 
		enigma = enigmiRepository.save(enigma);
		DomainEvent event = new EnigmaCreatedEvent(autore, tipo, tipoSpecifico, titolo, testo, soluzione);
		enigmiMessagePublisher.publish(event);
		return enigma;
	}

 	public Enigma getEnigma(Long id) {
		Enigma enigma = enigmiRepository.findById(id).orElse(null);
		return enigma;
	}

	public Collection<Enigma> getEnigmi() {
		Collection<Enigma> enigmi = enigmiRepository.findAll();
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByAutore(String autore) {
		Collection<Enigma> enigmi = enigmiRepository.findByAutore(autore);
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByAutori(Collection<String> autori) {
		Collection<Enigma> enigmi = enigmiRepository.findByAutoreIn(autori);
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByTipo(String tipo) {
		Collection<Enigma> enigmi = enigmiRepository.findByTipo(tipo);
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByTipi(Collection<String> tipi) {
		Collection<Enigma> enigmi = enigmiRepository.findByTipoIn(tipi);
		return enigmi;
	}

}

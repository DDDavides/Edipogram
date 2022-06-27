	package asw.edipogram.enigmiseguiti.domain;

	import asw.edipogram.enigmiseguiti.domain.repository.ConnessioneRepository;
	import asw.edipogram.enigmiseguiti.domain.repository.EnigmaRepository;
	import asw.edipogram.enigmiseguiti.domain.repository.EnigmiSeguitiRepository;
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

		/*
		** Metodo per ottenere gli enigmi seguiti dopo il primo gruppo di modifiche
		 */
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
			enigmiSeguiti = enigmiSeguitiRepository.getEnigmiSeguitiByUtente(utente);
			return enigmiSeguiti;
		}


		public Enigma createEnigma(Long id, String autore, String tipo, String tipoSpecifico, String titolo, String[] testo) {
			Enigma enigma = new Enigma(id, autore, tipo, tipoSpecifico, titolo, testo);
			logger.info("Aggiungo enigma: " + enigma);
			enigma = enigmaRepository.save(enigma);
			this.createEnigmiSeguiti(enigma);
			return enigma;
		}

		public Connessione createConnessione(Long id, String utente, String tipo) {
			Connessione connessione = new Connessione(id, utente, tipo);
			logger.info("Aggiungo connessione: " + connessione);
		    connessione = connessioneRepository.save(connessione);
			this.createEnigmiSeguiti(connessione);
			return connessione;
		}

		public Collection<EnigmiSeguiti> createEnigmiSeguiti(Connessione connessione) {
			String tipoEnigma = connessione.getTipo();
			Collection<Enigma> enigmi = enigmaRepository.getEnigmaByTipo(tipoEnigma);
			logger.info("[ENIGMA REPO] Presi enigmi di tipo: " + tipoEnigma);
			Collection<EnigmiSeguiti> enigmiSeguiti = new LinkedList<EnigmiSeguiti>();
			for (Enigma enigma: enigmi) {
				logger.info("[Tipo - " + tipoEnigma + "] " + "Creo enigma-seguito dall'utente " + connessione.getUtente());

				EnigmiSeguiti es = new EnigmiSeguiti(connessione.getUtente(), enigma.getId(),
						enigma.getAutore(), enigma.getTipo(),  enigma.getTipoSpecifico(), enigma.getTitolo(),
						enigma.getTesto());

				logger.info("[ENIGMI_SEGUITI REPO] Provando a salvare l'enigma-seguito: " + es);
				es = enigmiSeguitiRepository.save(es);
				logger.info("Salvato enigma seguito e lo " + es);
				enigmiSeguiti.add(es);
			}
			return enigmiSeguiti;
		}

		public Collection<EnigmiSeguiti> createEnigmiSeguiti(Enigma enigma) {
			String tipoEnigma = enigma.getTipo();
			Collection<Connessione> connessioni = connessioneRepository.getConnessioneByTipo(tipoEnigma);
			Collection<EnigmiSeguiti> enigmiSeguiti = new LinkedList<EnigmiSeguiti>();
			logger.info("[CONNESSIONI REPO] Prese connessioni di tipo: " + tipoEnigma);
			for (Connessione connessione : connessioni){
				logger.info("[Tipo - " + tipoEnigma + "] " + "Creo enigma-seguito della connessione " + connessione.getTipo());

				EnigmiSeguiti es = new EnigmiSeguiti(connessione.getUtente(), enigma.getId(),
						enigma.getAutore(), enigma.getTipo(), enigma.getTipoSpecifico(),
						enigma.getTitolo(), enigma.getTesto());

				logger.info("[ENIGMI_SEGUITI REPO] Provando a salvare l'enigma-seguito: " + es);
				es = enigmiSeguitiRepository.save(es);
				logger.info("Salvo enigma-seguito per: " + enigma);
				enigmiSeguiti.add(es);
			}
			return enigmiSeguiti;
		}
	}

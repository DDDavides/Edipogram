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
			logger.info("[ENIGMI_SEGUITI REPO] method: getting connessioni by utente");
			enigmiSeguiti = enigmiSeguitiRepository.getEnigmiSeguitiByUtente(utente);
			logger.info("[ENIGMI_SEGUITI REPO] method return: \n" + enigmiSeguiti);
			return enigmiSeguiti;
		}

		public Enigma addEnigma(Enigma enigma) {
			return enigmaRepository.save(enigma);
		}

		public Connessione addConnessione(Connessione connessione) {
			return connessioneRepository.save(connessione);
		}

		public Collection<EnigmiSeguiti> addEnigmiSeguiti(Connessione connessione) {
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

		public Collection<EnigmiSeguiti> addEnigmiSeguiti(Enigma enigma) {
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

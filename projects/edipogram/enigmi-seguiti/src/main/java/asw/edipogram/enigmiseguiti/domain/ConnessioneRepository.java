package asw.edipogram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ConnessioneRepository extends CrudRepository<Connessione, Long> {

    public Collection<Connessione> getConnessioniByUtente(String utente);
}

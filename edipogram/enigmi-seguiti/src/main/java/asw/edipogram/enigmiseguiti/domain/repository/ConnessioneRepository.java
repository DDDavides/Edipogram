package asw.edipogram.enigmiseguiti.domain.repository;

import asw.edipogram.enigmiseguiti.domain.Connessione;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ConnessioneRepository extends CrudRepository<Connessione, Long> {

    public Collection<Connessione> getConnessioniByUtente(String utente);

    public Collection<Connessione> getConnessioneByTipo(String tipo);
}

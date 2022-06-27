package asw.edipogram.enigmiseguiti.domain.repository;

import asw.edipogram.enigmiseguiti.domain.EnigmiSeguiti;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface EnigmiSeguitiRepository extends CrudRepository<EnigmiSeguiti, EnigmiSeguiti.EnigmiSeguitiId> {

    public Collection<EnigmiSeguiti> getEnigmiSeguitiByTipoEnigma(String tipo);

    public Collection<EnigmiSeguiti> getEnigmiSeguitiByUtente(String utente);
}

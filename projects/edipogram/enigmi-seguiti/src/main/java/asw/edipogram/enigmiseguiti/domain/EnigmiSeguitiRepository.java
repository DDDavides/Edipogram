package asw.edipogram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface EnigmiSeguitiRepository extends CrudRepository<EnigmiSeguiti, EnigmiSeguiti.EnigmiSeguitiId> {

    public Collection<EnigmiSeguiti> getEnigmiSeguitiByTipoEnigma(String tipo);

    public Collection<EnigmiSeguiti> getEnigmiSeguitiByEnigmiSeguitiIdUtente(String utente);
}

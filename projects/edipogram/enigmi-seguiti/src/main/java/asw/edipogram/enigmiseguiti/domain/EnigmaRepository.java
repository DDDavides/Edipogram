package asw.edipogram.enigmiseguiti.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface EnigmaRepository extends CrudRepository<Enigma, Long> {

    public Collection<Enigma> getEnigmiByTipi(Collection<String> tipi);
}

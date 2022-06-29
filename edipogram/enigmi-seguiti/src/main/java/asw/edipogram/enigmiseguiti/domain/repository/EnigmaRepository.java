package asw.edipogram.enigmiseguiti.domain.repository;

import asw.edipogram.enigmiseguiti.domain.Enigma;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface EnigmaRepository extends CrudRepository<Enigma, Long> {
    @Query(value = "SELECT e FROM Enigma e WHERE e.tipo IN :tps")
    public Collection<Enigma> getEnigmiByTipi(@Param("tps") List<String> tipi);

    public Collection<Enigma> getEnigmaByTipo(String tipo);

}

package asw.edipogram.enigmiseguiti.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@IdClass(EnigmiSeguiti.EnigmiSeguitiId.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnigmiSeguiti {

    @Id
    private String utente;
    @Id
    private Long idEnigma;
    private String autoreEnigma;
    private String tipoEnigma;
    private String tipoSpecificoEnigma;
    private String titoloEnigma;
    private String[] testoEnigma;

    @Data
    public class EnigmiSeguitiId implements Serializable {
        @Column(nullable = false)
        private String utente;
        @Column(nullable = false)
        private Long idEnigma;
    }
}

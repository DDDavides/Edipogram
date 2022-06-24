package asw.edipogram.enigmiseguiti.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnigmiSeguiti {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private EnigmiSeguitiId enigmiSeguitiId;
    private String autoreEnigma;
    private String tipoEnigma;
    private String tipoSpecificoEnigma;
    private String titoloEnigma;
    private String[] testoEnigma;

    @Embeddable
    public class EnigmiSeguitiId implements Serializable {

        @Column(nullable = false)
        private String utente;

        @Column(nullable = false)
        private Long idEnigma;
    }
}

package asw.edipogram.enigmiseguiti.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @ToString
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
    @NoArgsConstructor @AllArgsConstructor
    public static class EnigmiSeguitiId implements Serializable {
        @Column(nullable = false)
        private String utente;
        @Column(nullable = false)
        private Long idEnigma;



    }
}

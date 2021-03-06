package asw.edipogram.enigmi.api.event;

import asw.edipogram.common.api.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class EnigmaCreatedEvent implements DomainEvent {

    private Long id;
    private String autore;
    private String tipo;
    private String tipoSpecifico;
    private String titolo;
    private List<String> testo;
}

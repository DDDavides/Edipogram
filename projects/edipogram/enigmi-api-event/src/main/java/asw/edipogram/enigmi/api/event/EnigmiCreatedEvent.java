package asw.edipogram.enigmi.api.event;

import asw.edipogram.common.api.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class EnigmiCreatedEvent implements DomainEvent {

    private String autore;
    private String tipo;
    private String tipoSpecifico;
    private String titolo;
    private String[] testo;
    private String[] soluzione;
}

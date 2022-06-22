package asw.edipogram.connessioni.api.event;


import asw.edipogram.common.api.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class ConnessioneCreatedEvent implements DomainEvent {
    
    private Long id;
    private String utente;
    private String tipo;
}

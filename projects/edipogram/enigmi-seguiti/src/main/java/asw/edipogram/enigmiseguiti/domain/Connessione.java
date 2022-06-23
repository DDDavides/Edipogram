package asw.edipogram.enigmiseguiti.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor
public class Connessione {

	@Id
	private Long id;
	private String utente;
	private String tipo;

	public Connessione(Long id, String utente, String tipo) {
		this();
		this.id = id;
		this.utente = utente;
		this.tipo = tipo;
	}
}

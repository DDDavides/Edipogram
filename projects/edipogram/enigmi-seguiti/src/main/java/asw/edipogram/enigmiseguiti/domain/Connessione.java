package asw.edipogram.enigmiseguiti.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor
public class Connessione {

	@Id
	@GeneratedValue
	private Long id;
	private String utente;
	private String tipo;

	public Connessione(String utente, String tipo) {
		this();
		this.utente = utente;
		this.tipo = tipo;
	}
}

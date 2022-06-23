package asw.edipogram.enigmiseguiti.domain;

import lombok.*;

import javax.persistence.*;

/* Enigma (in formato breve, senza soluzione). */
@Entity
@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Enigma implements Comparable<Enigma> {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue
	private Long id; 
	private String autore; 
	private String tipo; 
	private String tipoSpecifico; 
	private String titolo;
	private String[] testo;

	public Enigma(String autore, String tipo, String tipoSpecifico, String titolo, String[] testo) {
		this();
		this.autore = autore;
		this.tipo = tipo;
		this.tipoSpecifico = tipoSpecifico;
		this.titolo = titolo;
		this.testo = testo;
	}

	@Override
	public int compareTo(Enigma other) {
		return this.id.compareTo(other.id); 
	}
	
}

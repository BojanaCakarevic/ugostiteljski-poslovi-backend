package backend.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pretplate")
@NamedQuery(name="Pretplate.findAll", query="SELECT p FROM Pretplate p")
public class Pretplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Korisnici
	@ManyToOne
	@JoinColumn(name="id_korisnika")
	private Korisnici korisnici;

	//bi-directional many-to-one association to Objekti
	@ManyToOne
	@JoinColumn(name="id_objekta")
	private Objekti objekti;

	//bi-directional many-to-one association to Pozicije
	@ManyToOne
	@JoinColumn(name="id_pozicije")
	private Pozicije pozicije;

	public Pretplate() {
	}

}
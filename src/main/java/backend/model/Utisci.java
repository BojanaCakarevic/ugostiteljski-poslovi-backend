package backend.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "utisci")
@NamedQuery(name="Utisci.findAll", query="SELECT u FROM Utisci u")
public class Utisci implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_utiska")
	private int idUtiska;

	@Column
	@Temporal(TemporalType.DATE)
	private Date datum;

	@Column(name="dobio_ponudu")
	private byte dobioPonudu;

	@Column
	private int godina;

	@Column
	private String grad;

	@Lob
	@Column
	private String tekst;

	@Column
	private String tip;

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

	public Utisci() {
	}

}
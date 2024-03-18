package backend.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "oglasi")
@NamedQuery(name="Oglasi.findAll", query="SELECT o FROM Oglasi o")
public class Oglasi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date datum;

	@Column
	private String naslov;

	@Lob
	@Column
	private String opis;

	//bi-directional many-to-one association to Objekti
	@ManyToOne
	@JoinColumn(name="id_objekta")
	@JsonIgnore
	private Objekti objekti;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "tip_objekta")
	private TipObjekta tipObjekta;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pozicije")
	@JsonIgnore
	private Pozicije pozicije;

	public Oglasi() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getNaslov() {
		return this.naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Objekti getObjekti() {
		return this.objekti;
	}

	public void setObjekti(Objekti objekti) {
		this.objekti = objekti;
	}

	public Pozicije getPozicije() {
		return this.pozicije;
	}

	public void setPozicije(Pozicije pozicije) {
		this.pozicije = pozicije;
	}

}
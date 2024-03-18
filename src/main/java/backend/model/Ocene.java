package backend.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ocene")
@NamedQuery(name="Ocene.findAll", query="SELECT o FROM Ocene o")
public class Ocene implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ocene")
	private int idOcene;

	@Column(name="ocena_atmosfera")
	private int ocenaAtmosfera;

	@Column(name="ocena_benefit")
	private int ocenaBenefit;

	@Column(name="ocena_kolektiv")
	private int ocenaKolektiv;

	@Column(name="ocena_napredak")
	private int ocenaNapredak;

	@Column(name="ocena_plata")
	private int ocenaPlata;

	@Column(name="ocena_uslovi_rada")
	private int ocenaUsloviRada;

	@Column(name="ocena_vlasnik")
	private int ocenaVlasnik;

	//bi-directional many-to-one association to Objekti
	@ManyToOne
	@JoinColumn(name="id_objekta")
	private Objekti objekti;

	public Ocene() {
	}

}
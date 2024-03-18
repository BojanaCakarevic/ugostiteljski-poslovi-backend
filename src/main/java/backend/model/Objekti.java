package backend.model;


import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "objekti")
@NamedQuery(name="Objekti.findAll", query="SELECT o FROM Objekti o")
public class Objekti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_objekta")
	private int idObjekta;

	@Column
	private String kontakt;

	@Column
	private String naziv;

	@Lob
	@Column
	private String opis;

	@Column
	private int osnovano;

	@Column
	private String sediste;

	@Column
	private String slika;

	@Enumerated(EnumType.STRING)
    @Column(name = "tip_objekta")
	private TipObjekta tipObjekta;

	@Column
	private String web;

	//bi-directional many-to-one association to Ocene
	@OneToMany(mappedBy="objekti")
	private List<Ocene> ocenes;

	//bi-directional many-to-one association to Oglasi
	@OneToMany(mappedBy="objekti")
	@JsonIgnore
	private List<Oglasi> oglasis;

	//bi-directional many-to-one association to Pretplate
	@OneToMany(mappedBy="objekti")
	private List<Pretplate> pretplates;

	//bi-directional many-to-one association to Utisci
	@OneToMany(mappedBy="objekti")
	private List<Utisci> utiscis;
	
	@JsonIgnore
	@OneToMany(mappedBy = "objekti", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Lokacije> lokacije;

	 // Dodajte metodu za dodavanje lokacije
    public Lokacije addLokacija(Lokacije lokacija) {
        if (lokacije == null) {
            lokacije = new ArrayList<>();
        }
        lokacije.add(lokacija);
        lokacija.setObjekti(this);
        return lokacija;
    }

    // Dodajte metodu za uklanjanje lokacije
    public Lokacije removeLokacija(Lokacije lokacija) {
        if (lokacije != null) {
            lokacije.remove(lokacija);
            lokacija.setObjekti(null);
        }
        return lokacija;
    }


	public Objekti() {
	}

	public Pretplate addPretplate(Pretplate pretplate) {
		getPretplates().add(pretplate);
		pretplate.setObjekti(this);

		return pretplate;
	}

	public Pretplate removePretplate(Pretplate pretplate) {
		getPretplates().remove(pretplate);
		pretplate.setObjekti(null);

		return pretplate;
	}

	public Utisci addUtisci(Utisci utisci) {
		getUtiscis().add(utisci);
		utisci.setObjekti(this);

		return utisci;
	}

	public Utisci removeUtisci(Utisci utisci) {
		getUtiscis().remove(utisci);
		utisci.setObjekti(null);

		return utisci;
	}
	
	public Ocene addOcene(Ocene ocene) {
		getOcenes().add(ocene);
		ocene.setObjekti(this);

		return ocene;
	}

	public Ocene removeOcene(Ocene ocene) {
		getOcenes().remove(ocene);
		ocene.setObjekti(null);

		return ocene;
	}
	
	 @Override
	    public String toString() {
	        return "Objekti{" +
	                "idObjekta=" + idObjekta +
	                ", kontakt='" + kontakt + '\'' +
	                ", naziv='" + naziv + '\'' +
	                ", opis='" + opis + '\'' +
	                ", osnovano=" + osnovano +
	                ", sediste='" + sediste + '\'' +
	                ", slika='" + slika + '\'' +
	                ", tipObjekta=" + tipObjekta +
	                ", web='" + web + '\'' +
	                ", ocenes=" + ocenes +
	                ", oglasis=" + oglasis +
	                ", pretplates=" + pretplates +
	                ", utiscis=" + utiscis +
	                '}';
	    }

}
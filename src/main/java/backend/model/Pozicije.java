package backend.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@Entity
@Table(name = "pozicije")
@NamedQuery(name="Pozicije.findAll", query="SELECT p FROM Pozicije p")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pozicije implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pozicije")
	private int idPozicije;

	@Column
	private String naziv;
	
	@Column
    private String image;

    @OneToMany(mappedBy="pozicije")
    @JsonIgnore // Dodajte ovu anotaciju
    private List<Oglasi> oglasis;

	//bi-directional many-to-one association to Pretplate
	@OneToMany(mappedBy="pozicije")
	private List<Pretplate> pretplates;

	//bi-directional many-to-one association to Utisci
	@OneToMany(mappedBy="pozicije")
	private List<Utisci> utiscis;

	//bi-directional many-to-many association to Korisnici
	@ManyToMany(mappedBy="pozicijes")
	private List<Korisnici> korisnicis;

	public Pozicije() {
	}

	public Oglasi addOglasi(Oglasi oglasi) {
		getOglasis().add(oglasi);
		oglasi.setPozicije(this);

		return oglasi;
	}

	public Oglasi removeOglasi(Oglasi oglasi) {
		getOglasis().remove(oglasi);
		oglasi.setPozicije(null);

		return oglasi;
	}

	public Pretplate addPretplate(Pretplate pretplate) {
		getPretplates().add(pretplate);
		pretplate.setPozicije(this);

		return pretplate;
	}

	public Pretplate removePretplate(Pretplate pretplate) {
		getPretplates().remove(pretplate);
		pretplate.setPozicije(null);

		return pretplate;
	}

	public Utisci addUtisci(Utisci utisci) {
		getUtiscis().add(utisci);
		utisci.setPozicije(this);

		return utisci;
	}

	public Utisci removeUtisci(Utisci utisci) {
		getUtiscis().remove(utisci);
		utisci.setPozicije(null);

		return utisci;
	}

}
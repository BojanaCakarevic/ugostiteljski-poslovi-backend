package backend.model;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "korisnici")
@NamedQuery(name="Korisnici.findAll", query="SELECT k FROM Korisnici k")
@NamedQuery(name="Korisnici.findByUsername", query="SELECT k FROM Korisnici k WHERE k.username =: username")
public class Korisnici implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_korisnika")
	private int idKorisnika;

	@Column
	private String email;

	@Column
	private String ime;

	@Column
	private String lozinka;

	@Column
	private String prezime;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TipKorisnika tip;

    @Column(name = "create_time", nullable = true)
    private LocalDateTime createTime;
	
	@Column
	private String username;

	@Column
	private byte zaposlen;

	//bi-directional many-to-one association to Pretplate
	@OneToMany(mappedBy="korisnici")
	private List<Pretplate> pretplates;

	//bi-directional many-to-one association to Utisci
	@OneToMany(mappedBy="korisnici")
	private List<Utisci> utiscis;

	//bi-directional many-to-many association to Pozicije
	@ManyToMany
	@JoinTable(
		name="korisnici_pozicije"
		, joinColumns={
			@JoinColumn(name="id_korisnika")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_pozicije")
			}
		)
	private List<Pozicije> pozicijes;

	public Korisnici() {
	}

	public Pretplate addPretplate(Pretplate pretplate) {
		getPretplates().add(pretplate);
		pretplate.setKorisnici(this);

		return pretplate;
	}

	public Pretplate removePretplate(Pretplate pretplate) {
		getPretplates().remove(pretplate);
		pretplate.setKorisnici(null);

		return pretplate;
	}

	public Utisci addUtisci(Utisci utisci) {
		getUtiscis().add(utisci);
		utisci.setKorisnici(this);

		return utisci;
	}

	public Utisci removeUtisci(Utisci utisci) {
		getUtiscis().remove(utisci);
		utisci.setKorisnici(null);

		return utisci;
	}

	@Transient
    private String accessToken;

    @Transient
    private String refreshToken;

}
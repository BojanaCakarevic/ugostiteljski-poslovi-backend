package backend.DTO;

import backend.model.Objekti;
import backend.model.TipObjekta;

import java.util.List;

import backend.model.Lokacije;

public class ObjekatDTO {

	private String kontakt;
	private String naziv;
	private String opis;
	private int osnovano;
	private String sediste;
	private String slika;
	private String web;
	private Objekti objekat;
	private List<Lokacije> lokacije;
	private TipObjekta tipObjekta;

	public ObjekatDTO() {
		this.objekat = new Objekti();
	}

	public ObjekatDTO(String kontakt, String naziv, String opis, int osnovano, String sediste, String slika,
			String web, Objekti objekat, List<Lokacije> lokacije, TipObjekta tipObjekta) {
		super();
		this.kontakt = kontakt;
		this.naziv = naziv;
		this.opis = opis;
		this.osnovano = osnovano;
		this.sediste = sediste;
		this.slika = slika;
		this.objekat = objekat;
		this.web = web;
		this.lokacije = lokacije;
		this.tipObjekta = tipObjekta;
	}

	public Objekti getObjekat() {
		return objekat;
	}

	public void setObjekat(Objekti objekat) {
		this.objekat = objekat;
	}

	public List<Lokacije> getLokacije() {
		return lokacije;
	}

	public void setLokacije(List<Lokacije> lokacije) {
		this.lokacije = lokacije;
	}

	public TipObjekta getTipObjekta() {
		return tipObjekta;
	}

	public void setTipObjekta(TipObjekta tipObjekta) {
		this.tipObjekta = tipObjekta;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getOsnovano() {
		return osnovano;
	}

	public void setOsnovano(int osnovano) {
		this.osnovano = osnovano;
	}

	public String getSediste() {
		return sediste;
	}

	public void setSediste(String sediste) {
		this.sediste = sediste;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	
}

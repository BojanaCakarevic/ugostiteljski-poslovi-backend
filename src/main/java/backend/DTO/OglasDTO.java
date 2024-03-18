package backend.DTO;

import java.util.Date;

import backend.model.Objekti;
import backend.model.TipObjekta;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class OglasDTO {

	private int id;
	private String naslov;
	private Date datum;
	private PozicijaDTO pozicija;
	private Objekti objekat;
	private TipObjekta tipObjekta;

	public TipObjekta getTipObjekta() {
		return tipObjekta;
	}

	public void setTipObjekta(TipObjekta tipObjekta) {
		this.tipObjekta = tipObjekta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public PozicijaDTO getPozicija() {
		return pozicija;
	}

	public void setPozicija(PozicijaDTO pozicija) {
		this.pozicija = pozicija;
	}

	public Objekti getObjekat() {
		return objekat;
	}

	public void setObjekat(Objekti objekat) {
		this.objekat = objekat;
	}



}

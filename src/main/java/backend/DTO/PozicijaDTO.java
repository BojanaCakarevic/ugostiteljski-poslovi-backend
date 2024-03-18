package backend.DTO;

public class PozicijaDTO {

	private int id;
	private String naziv;
	
    public PozicijaDTO() {
    }

    public PozicijaDTO(String naziv) {
        this.naziv = naziv;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}

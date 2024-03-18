package backend.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "lokacije")
public class Lokacije implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lokacije")
    private int idLokacije;
    
    @Column(name = "drzava")
    private String drzava;

    @Column(name = "grad")
    private String grad;

    @Column(name = "adresa")
    private String adresa;
    
	@ManyToOne
	@JoinColumn(name="id_objekta")
	private Objekti objekti;

}

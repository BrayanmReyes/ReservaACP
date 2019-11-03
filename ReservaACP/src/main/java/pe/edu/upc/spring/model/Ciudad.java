package pe.edu.upc.spring.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Ciudad")
public class Ciudad implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCiudad;
	
	@Column(name="nameCiudad", length=60, nullable=false)
	private String nameCiudad;
	
	@ManyToOne
	@JoinColumn(name = "idPais", nullable = false)
	private Pais pais;

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNameCiudad() {
		return nameCiudad;
	}

	public void setNameCiudad(String nameCiudad) {
		this.nameCiudad = nameCiudad;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
}

package pe.edu.upc.spring.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Pais")
public class Pais implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPais;
	
	@NotEmpty(message="No puedo estar vacio")
	@NotBlank(message="No puedo estar en blanco")
	@Column(name="namePais", length=30, nullable=false)
	private String namePais;

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getNamePais() {
		return namePais;
	}

	public void setNamePais(String namePais) {
		this.namePais = namePais;
	}
	
	
}

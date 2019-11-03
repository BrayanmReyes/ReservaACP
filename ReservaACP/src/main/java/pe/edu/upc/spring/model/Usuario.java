package pe.edu.upc.spring.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	@Column(name="nameUsuario", length=60, nullable=false)
	private String nameUsuario;
	
	@Column(name="nameApPaterno", length=60, nullable=true)
	private String nameApPaterno;
	
	@Column(name="nameApMaterno", length=60, nullable=true)
	private String nameApMaterno;
	
	@Column(name="idDocumento", length=12, nullable=false)
	private String idDocumento;
	
	@Column(name="phone", length=20, nullable=false)
	private String phone;
	
	@Email
	@Column(name="email", length=60, nullable=false)
	private String email;
	
	@Column(name="password", length=60, nullable=false)
	private String password;
	
	@Column(name="flagAdmin", nullable=true)
	private boolean flagAdmin;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNameUsuario() {
		return nameUsuario;
	}

	public void setNameUsuario(String nameUsuario) {
		this.nameUsuario = nameUsuario;
	}

	public String getNameApPaterno() {
		return nameApPaterno;
	}

	public void setNameApPaterno(String nameApPaterno) {
		this.nameApPaterno = nameApPaterno;
	}

	public String getNameApMaterno() {
		return nameApMaterno;
	}

	public void setNameApMaterno(String nameApMaterno) {
		this.nameApMaterno = nameApMaterno;
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isFlagAdmin() {
		return flagAdmin;
	}

	public void setFlagAdmin(boolean flagAdmin) {
		this.flagAdmin = flagAdmin;
	}
		
}

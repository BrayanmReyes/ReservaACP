package pe.edu.upc.spring.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Reserva")
public class Reserva implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReserva;
	
	@NotNull
	@FutureOrPresent
	@Temporal(TemporalType.DATE)
	@Column(name="dayReserva")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dayReserva;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "idCiudad", nullable = false)
	private Ciudad ciudad;
	
	//El sistema o Admin lo debe poner
	@Future
	@Temporal(TemporalType.DATE)
	@Column(name="dayLlegada", nullable = true)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dayLlegada;
	
	@Column(name="flagAnulado", nullable = true)
	private boolean flagAnulado;
	
	@Future
	@Temporal(TemporalType.DATE)
	@Column(name="dayVencimiento", nullable = true)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dayVencimiento;

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public Date getDayReserva() {
		return dayReserva;
	}

	public void setDayReserva(Date dayReserva) {
		this.dayReserva = dayReserva;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Date getDayLlegada() {
		return dayLlegada;
	}

	public void setDayLlegada(Date dayLlegada) {
		this.dayLlegada = dayLlegada;
	}

	public boolean isFlagAnulado() {
		return flagAnulado;
	}

	public void setFlagAnulado(boolean flagAnulado) {
		this.flagAnulado = flagAnulado;
	}

	public Date getDayVencimiento() {
		return dayVencimiento;
	}

	public void setDayVencimiento(Date dayVencimiento) {
		this.dayVencimiento = dayVencimiento;
	}
	
	
}

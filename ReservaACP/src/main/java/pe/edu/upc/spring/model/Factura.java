package pe.edu.upc.spring.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="Factura")
public class Factura implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFactura;
	
	@OneToOne
	@JoinColumn(name = "idReserva", nullable = false)
	private Reserva reserva;
	
	@Positive
	@Column(name="moneyPrecioFinal", nullable=false)
	private double moneyPrecioFinal;
	
	@Column(name="flagPagoTarjeta", nullable=false)
	private boolean flagPagoTarjeta;
	
	@Column(name="numFactura", nullable=false)
	private String numFactura;
	
	@Column(name="numSerie", nullable=false)
	private String numSerie;
	
	@Column(name="flagCancelado", nullable=true)
	private boolean flagCancelado;
	
	@NotNull
	@FutureOrPresent
	@Temporal(TemporalType.DATE)
	@Column(name="dayFactura")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dayFactura;

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public double getMoneyPrecioFinal() {
		return moneyPrecioFinal;
	}

	public void setMoneyPrecioFinal(double moneyPrecioFinal) {
		this.moneyPrecioFinal = moneyPrecioFinal;
	}

	public boolean isFlagPagoTarjeta() {
		return flagPagoTarjeta;
	}

	public void setFlagPagoTarjeta(boolean flagPagoTarjeta) {
		this.flagPagoTarjeta = flagPagoTarjeta;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public boolean isFlagCancelado() {
		return flagCancelado;
	}

	public void setFlagCancelado(boolean flagCancelado) {
		this.flagCancelado = flagCancelado;
	}

	public Date getDayFactura() {
		return dayFactura;
	}

	public void setDayFactura(Date dayFactura) {
		this.dayFactura = dayFactura;
	}
	
	
}

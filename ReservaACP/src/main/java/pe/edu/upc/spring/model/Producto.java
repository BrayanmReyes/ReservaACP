package pe.edu.upc.spring.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Producto")
public class Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
	
	//@NotEmpty(message="No puedo estar vacio")
	@Column(name="nameProducto", nullable=true)
	private String nameProducto;
	
	@Future(message="No puedes seleccionar un dia en el pasado")
	@Temporal(TemporalType.DATE)
	@Column(name="dayReabastecimiento", nullable=true)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dayReabastecimiento;
	
	//@Positive
	@Column(name="quantityStock")
	private double quantityStock;
	
	//@Positive
	@Column(name="moneyPrecio")
	private double moneyPrecio;
	
	@Column(name="quantityReserva")
	private double quantityReserva;

	
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producto(int idProducto, String nameProducto, Date dayReabastecimiento,double quantityStock,double moneyPrecio, double quantityReserva) {
		super();
		this.idProducto = idProducto;
		this.nameProducto = nameProducto;
		this.dayReabastecimiento = dayReabastecimiento;
		this.quantityStock = quantityStock;
		this.moneyPrecio = moneyPrecio;
		this.quantityReserva = quantityReserva;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNameProducto() {
		return nameProducto;
	}

	public void setNameProducto(String nameProducto) {
		this.nameProducto = nameProducto;
	}

	public Date getDayReabastecimiento() {
		return dayReabastecimiento;
	}

	public void setDayReabastecimiento(Date dayReabastecimiento) {
		this.dayReabastecimiento = dayReabastecimiento;
	}

	public double getQuantityStock() {
		return quantityStock;
	}

	public void setQuantityStock(double quantityStock) {
		this.quantityStock = quantityStock;
	}

	public double getMoneyPrecio() {
		return moneyPrecio;
	}

	public void setMoneyPrecio(double moneyPrecio) {
		this.moneyPrecio = moneyPrecio;
	}

	public double getQuantityReserva() {
		return quantityReserva;
	}

	public void setQuantityReserva(double quantityReserva) {
		this.quantityReserva = quantityReserva;
	}


	
}

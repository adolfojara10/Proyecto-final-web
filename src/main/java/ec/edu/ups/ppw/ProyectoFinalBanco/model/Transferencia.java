package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transferencia {
	@Id
	@Column(name="tra_id")
	private int id;
	@Column(name="tra_fecha")
	private String fecha;
	@Column(name="tra_monto")
	private double monto;
	@Column(name="tra_tipo")
	private String tipo;
	@ManyToOne
	@JoinColumn(name = "cue_id", insertable = false, updatable = false)
	private Cuenta cuenta;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	@Override
	public String toString() {
		return "Transferencia [id=" + id + ", fecha=" + fecha + ", monto=" + monto + ", tipo=" + tipo + ", cuenta="
				+ cuenta + "]";
	}
	
}

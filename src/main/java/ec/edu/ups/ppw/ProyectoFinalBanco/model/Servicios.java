package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Servicios {

	@Id
	@Column(name = "ser_id")
	private int id;

	@Column(name = "ser_tipo")
	private String tipo;

	@Column(name = "ser_deuda")
	private double deuda;

	@Column(name = "ser_fechaEmision")
	private Date fechaEmision;

	@Column(name = "ser_estado")
	private boolean estado;

	@ManyToOne
	@JoinColumn(name = "per_id", insertable = false, updatable = false)
	private Persona persona;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getDeuda() {
		return deuda;
	}

	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Servicios [id=" + id + ", tipo=" + tipo + ", deuda=" + deuda + ", fechaEmision=" + fechaEmision
				+ ", estado=" + estado + ", persona=" + persona + "]";
	}

}

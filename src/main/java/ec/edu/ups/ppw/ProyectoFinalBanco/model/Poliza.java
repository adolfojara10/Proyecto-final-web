package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "Poliza.findAll", query = "SELECT p FROM Poliza p")
public class Poliza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	private double interes;

	private double monto;

	private double rendimiento;

	// bi-directional many-to-one association to Persona
	@ManyToOne
	private Persona persona;

	public Poliza() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public double getInteres() {
		return this.interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public double getMonto() {
		return this.monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getRendimiento() {
		return this.rendimiento;
	}

	public void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Poliza [id=" + id + ", estado=" + estado + ", fechaFin=" + fechaFin + ", fechaInicio=" + fechaInicio
				+ ", interes=" + interes + ", monto=" + monto + ", rendimiento=" + rendimiento + "]";
	}

}

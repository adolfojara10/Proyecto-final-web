package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@NamedQuery(name="Prestamo.findAll", query="SELECT p FROM Prestamo p")
public class Prestamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	private double interes;

	private double monto;

	@Column(name="pago_mensual")
	private double pagoMensual;

	private int plazo;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="persona_id")
	private Persona persona1;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="garante_id")
	private Persona persona2;

	public Prestamo() {
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

	public double getPagoMensual() {
		return this.pagoMensual;
	}

	public void setPagoMensual(double pagoMensual) {
		this.pagoMensual = pagoMensual;
	}

	public int getPlazo() {
		return this.plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public Persona getPersona1() {
		return this.persona1;
	}

	public void setPersona1(Persona persona1) {
		this.persona1 = persona1;
	}

	public Persona getPersona2() {
		return this.persona2;
	}

	public void setPersona2(Persona persona2) {
		this.persona2 = persona2;
	}

	

}

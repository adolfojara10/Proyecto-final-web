package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="servicios")
@NamedQuery(name="Servicio.findAll", query="SELECT s FROM Servicio s")
public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private double deuda;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_emision")
	private Date fechaEmision;

	private String tipo;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="emisor_id")
	private Persona persona1;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="deudor_id")
	private Persona persona2;

	public Servicio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDeuda() {
		return this.deuda;
	}

	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

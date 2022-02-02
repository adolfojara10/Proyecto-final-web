package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Solicitud {
	
	@Id
	@Column(name="sol_id")
	private int id;
	@Column(name="sol_tipo")
	private String tipo;
	@Column(name="sol_fecha")
	private Date fecha;
	@Column(name="sol_estado")
	private String estado;
	@ManyToOne
	@JoinColumn(name = "per_id")
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", tipo=" + tipo + ", fecha=" + fecha + ", estado=" + estado + ", persona="
				+ persona + "]";
	}
	
	

}

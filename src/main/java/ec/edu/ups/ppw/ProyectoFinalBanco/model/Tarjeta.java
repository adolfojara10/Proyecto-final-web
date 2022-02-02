package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tarjeta {
	@Id
	@Column(name="tar_id")
	private int id;
	@Column(name="tar_nombre")
	private String nombre;
	@Column(name="tar_numero")
	private int numero;
	@Column(name="tar_codigo")
	private int codigo;
	@Column(name="tar_fecha_Inicio")
	private Date fecha_Inicio;
	@Column(name="tar_fecha_fin")
	private Date fecha_fin;
	@Column(name="tar_tipo")
	private String tipo;
	@Column(name="tar_monto")
	private double monto;
	@ManyToOne
	@JoinColumn(name = "per_id")
	private Persona persona;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFecha_Inicio() {
		return fecha_Inicio;
	}
	public void setFecha_Inicio(Date fecha_Inicio) {
		this.fecha_Inicio = fecha_Inicio;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	@Override
	public String toString() {
		return "Tarjeta [id=" + id + ", nombre=" + nombre + ", numero=" + numero + ", codigo=" + codigo
				+ ", fecha_Inicio=" + fecha_Inicio + ", fecha_fin=" + fecha_fin + ", tipo=" + tipo + ", monto=" + monto
				+ ", persona=" + persona + "]";
	}
	

}

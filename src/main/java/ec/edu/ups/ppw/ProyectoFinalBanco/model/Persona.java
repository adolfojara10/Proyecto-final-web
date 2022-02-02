package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Persona {
	
	@Column(name="per_id")
	@Id
	private int id;
	@Column(name="per_cedula")
	private String cedula;
	@Column(name="per_nombre")
	private String nombre;
	@Column(name="per_apellido")
	private String apellido;
	@Column(name="per_email")
	private String email;
	@Column(name="per_fecha_nacimineto")
	private Date fecha_nacimiento;
	@Column(name="per_tipo")
	private String tipo;
	@OneToMany
	@JoinColumn(name = "sol_id")
	private List<Solicitud> solicitud;
	@OneToMany
	@JoinColumn(name = "tar_id")
	private List<Tarjeta> tarjeta;
	@OneToMany
	@JoinColumn(name = "pol_id")
	private List<Poliza> poliza;
	@OneToOne
	@JoinColumn(name = "cue_id")
	private Cuenta cuenta;
	@OneToMany
	@JoinColumn(name = "pre_id")
	private List<Prestamo> prestamo;
	@OneToMany
	@JoinColumn(name = "pre_id")
	private List<Prestamo> garante;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<Solicitud> getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(List<Solicitud> solicitud) {
		this.solicitud = solicitud;
	}
	public List<Tarjeta> getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(List<Tarjeta> tarjeta) {
		this.tarjeta = tarjeta;
	}
	public List<Poliza> getPoliza() {
		return poliza;
	}
	public void setPoliza(List<Poliza> poliza) {
		this.poliza = poliza;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public List<Prestamo> getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(List<Prestamo> prestamo) {
		this.prestamo = prestamo;
	}
	public List<Prestamo> getGarante() {
		return garante;
	}
	public void setGarante(List<Prestamo> garante) {
		this.garante = garante;
	}
	@Override
	public String toString() {
		return "Persona [id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", email="
				+ email + ", fecha_nacimiento=" + fecha_nacimiento + ", tipo=" + tipo + ", solicitud=" + solicitud
				+ ", tarjeta=" + tarjeta + ", poliza=" + poliza + ", cuenta=" + cuenta + ", prestamo=" + prestamo
				+ ", garante=" + garante + "]";
	}
	
	

}

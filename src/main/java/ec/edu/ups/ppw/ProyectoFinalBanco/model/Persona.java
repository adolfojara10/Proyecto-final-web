package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {

	@Column(name = "per_id")
	@Id
	private int id;
	@Column(name = "per_cedula")
	private String cedula;
	@Column(name = "per_nombre")
	private String nombre;
	@Column(name = "per_apellido")
	private String apellido;
	@Column(name = "per_email")
	private String email;
	@Column(name = "per_fecha_nacimineto")
	private Date fecha_nacimiento;
	@Column(name = "per_tipo")
	private String tipo;

	@OneToMany
	@JoinColumn(name = "sol_id")
	private List<Solicitud> solicitud;

	@OneToMany
	@JoinColumn(name = "tar_id")
	private List<Tarjeta> tarjetas;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pol_id")
	private Set<Poliza> poliza;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cue_id")
	private Cuenta cuenta;

	@OneToMany
	@JoinColumn(name = "pre_id")
	private List<Prestamo> prestamos;
	@OneToMany
	@JoinColumn(name = "pre_id")
	private List<Prestamo> garante;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ser_id")
	private List<Servicios> serviciosEmitidos;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ser_id")
	private List<Servicios> serviciosPagados;

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
		return tarjetas;
	}

	public void setTarjeta(List<Tarjeta> tarjeta) {
		this.tarjetas = tarjeta;
	}

	public Set<Poliza> getPoliza() {
		return poliza;
	}

	public void setPoliza(Set<Poliza> poliza) {
		this.poliza = poliza;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public List<Prestamo> getPrestamo() {
		return prestamos;
	}

	public void setPrestamo(List<Prestamo> prestamo) {
		this.prestamos = prestamo;
	}

	public List<Prestamo> getGarante() {
		return garante;
	}

	public void setGarante(List<Prestamo> garante) {
		this.garante = garante;
	}

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public List<Servicios> getServiciosEmitidos() {
		return serviciosEmitidos;
	}

	public void setServiciosEmitidos(List<Servicios> serviciosEmitidos) {
		this.serviciosEmitidos = serviciosEmitidos;
	}

	public List<Servicios> getServiciosPagados() {
		return serviciosPagados;
	}

	public void setServiciosPagados(List<Servicios> serviciosPagados) {
		this.serviciosPagados = serviciosPagados;
	}

	public void addTarjeta(Tarjeta tarjeta) {
		if (tarjetas == null) {
			tarjetas = new ArrayList<Tarjeta>();
		}
		tarjetas.add(tarjeta);
	}

	public void addPoliza(Poliza p) {
		if (poliza == null) {
			poliza = new HashSet<>();
		}
		poliza.add(p);
	}

	public void addServicioEmitido(Servicios ser) {
		if (serviciosEmitidos == null)
			serviciosEmitidos = new ArrayList<Servicios>();

		serviciosEmitidos.add(ser);
	}

	public void addServicioPagados(Servicios ser) {
		if (serviciosPagados == null)
			serviciosPagados = new ArrayList<Servicios>();

		serviciosPagados.add(ser);
	}

	public void addPrestamo(Prestamo prestamo) {
		if (prestamos == null) {
			prestamos = new ArrayList<Prestamo>();
		}
		prestamos.add(prestamo);
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", email="
				+ email + ", fecha_nacimiento=" + fecha_nacimiento + ", tipo=" + tipo + ", solicitud=" + solicitud
				+ ", tarjetas=" + tarjetas + ", poliza=" + poliza + ", cuenta=" + cuenta + ", prestamos=" + prestamos
				+ ", garante=" + garante + ", serviciosEmitidos=" + serviciosEmitidos + ", serviciosPagados="
				+ serviciosPagados + "]";
	}

}

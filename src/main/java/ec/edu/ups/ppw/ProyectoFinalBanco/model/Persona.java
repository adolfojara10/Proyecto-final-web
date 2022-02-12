package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;

@Entity
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String apellido;

	private String cedula;

	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private String nombre;
	
	private String tipo;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne
	private Cuenta cuenta;

	//bi-directional many-to-one association to Poliza
	@OneToMany(mappedBy="persona")
	private List<Poliza> polizas;

	//bi-directional many-to-one association to Prestamo
	@OneToMany(mappedBy="persona1")
	private List<Prestamo> prestamos1;

	//bi-directional many-to-one association to Prestamo
	@OneToMany(mappedBy="persona2")
	private List<Prestamo> prestamos2;

	//bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy="persona1")
	private List<Servicio> servicios1;

	//bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy="persona2")
	private List<Servicio> servicios2;

	//bi-directional many-to-one association to Tarjeta
	@OneToMany(mappedBy="persona")
	private List<Tarjeta> tarjetas;

	public Persona() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public List<Poliza> getPolizas() {
		return this.polizas;
	}

	public void setPolizas(List<Poliza> polizas) {
		this.polizas = polizas;
	}
	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Poliza addPoliza(Poliza poliza) {
		getPolizas().add(poliza);
		poliza.setPersona(this);

		return poliza;
	}

	public Poliza removePoliza(Poliza poliza) {
		getPolizas().remove(poliza);
		poliza.setPersona(null);

		return poliza;
	}

	public List<Prestamo> getPrestamos1() {
		return this.prestamos1;
	}

	public void setPrestamos1(List<Prestamo> prestamos1) {
		this.prestamos1 = prestamos1;
	}

	public Prestamo addPrestamos1(Prestamo prestamos1) {
		getPrestamos1().add(prestamos1);
		prestamos1.setPersona1(this);

		return prestamos1;
	}

	public Prestamo removePrestamos1(Prestamo prestamos1) {
		getPrestamos1().remove(prestamos1);
		prestamos1.setPersona1(null);

		return prestamos1;
	}

	public List<Prestamo> getPrestamos2() {
		return this.prestamos2;
	}

	public void setPrestamos2(List<Prestamo> prestamos2) {
		this.prestamos2 = prestamos2;
	}

	public Prestamo addPrestamos2(Prestamo prestamos2) {
		getPrestamos2().add(prestamos2);
		prestamos2.setPersona2(this);

		return prestamos2;
	}

	public Prestamo removePrestamos2(Prestamo prestamos2) {
		getPrestamos2().remove(prestamos2);
		prestamos2.setPersona2(null);

		return prestamos2;
	}

	public List<Servicio> getServicios1() {
		return this.servicios1;
	}

	public void setServicios1(List<Servicio> servicios1) {
		this.servicios1 = servicios1;
	}

	public Servicio addServicios1(Servicio servicios1) {
		getServicios1().add(servicios1);
		servicios1.setPersona1(this);

		return servicios1;
	}

	public Servicio removeServicios1(Servicio servicios1) {
		getServicios1().remove(servicios1);
		servicios1.setPersona1(null);

		return servicios1;
	}

	public List<Servicio> getServicios2() {
		return this.servicios2;
	}

	public void setServicios2(List<Servicio> servicios2) {
		this.servicios2 = servicios2;
	}

	public Servicio addServicios2(Servicio servicios2) {
		getServicios2().add(servicios2);
		servicios2.setPersona2(this);

		return servicios2;
	}

	public Servicio removeServicios2(Servicio servicios2) {
		getServicios2().remove(servicios2);
		servicios2.setPersona2(null);

		return servicios2;
	}

	public List<Tarjeta> getTarjetas() {
		return this.tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public Tarjeta addTarjeta(Tarjeta tarjeta) {
		getTarjetas().add(tarjeta);
		tarjeta.setPersona(this);

		return tarjeta;
	}

	public Tarjeta removeTarjeta(Tarjeta tarjeta) {
		getTarjetas().remove(tarjeta);
		tarjeta.setPersona(null);

		return tarjeta;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", apellido=" + apellido + ", cedula=" + cedula + ", email=" + email
				+ ", fechaNacimiento=" + fechaNacimiento + ", nombre=" + nombre + ", tipo=" + tipo 
				+ ",\n --- > cuenta=" + cuenta; 
//				+ ",\n --- > polizas=" + polizas 
//				+ ",\n --- > prestamos1=" + prestamos1 
//				+ ",\n --- > prestamos2=" + prestamos2
//				+ ",\n --- > servicios1=" + servicios1 
//				+ ",\n --- > servicios2=" + servicios2 
//				+ ",\n --- > tarjetas=" + tarjetas + "]";
	}

	
}

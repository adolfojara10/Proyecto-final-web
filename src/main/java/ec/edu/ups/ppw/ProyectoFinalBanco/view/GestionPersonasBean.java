package ec.edu.ups.ppw.ProyectoFinalBanco.view;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.ups.ppw.ProyectoFinalBanco.model.Cuenta;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Persona;
import ec.ups.edu.ppw.ProyectoFinalBanco.business.CuentaON;
import ec.ups.edu.ppw.ProyectoFinalBanco.business.PersonaON;

@Named
@RequestScoped
public class GestionPersonasBean {

	@Inject
	private PersonaON perON;

	@Inject
	private CuentaON cueON;

	private String cedula;

	private String nombre;

	private String apellido;

	private String email;

	private Date fecha_nacimiento;

	private String tipo;
	private String contraseña;

	private Persona newCliente = new Persona();
	private Cuenta newCuenta = new Cuenta();

	@PostConstruct
	public void init() {
		newCliente = new Persona();
		newCuenta = new Cuenta(); 
	}
	
	public String guardar() {
		System.out.println("1111 - " + tipo);		
		
		newCliente.setId(1);						
		newCliente.setTipo(tipo);		
		
		newCuenta.setId(1);		
		newCuenta.setNombre_usuario("aaa");
		newCliente.setCuenta(newCuenta);
		
		System.out.println(newCliente);
		System.out.println(newCuenta);
		cueON.guardarCuenta(newCuenta);
		perON.guardarCliente(newCliente);
		System.out.println(" user > " + newCliente);
		System.out.println(" user > " + newCuenta);
		//System.out.println("user > clientes > " perON.g);		
		return null;
	}
	
	public String guardarTipo() {
		System.out.println(tipo);
		return null;
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Persona getNewCliente() {
		return newCliente;
	}

	public void setNewCliente(Persona newCliente) {
		this.newCliente = newCliente;
	}

	public Cuenta getNewCuenta() {
		return newCuenta;
	}

	public void setNewCuenta(Cuenta newCuenta) {
		this.newCuenta = newCuenta;
	}

}

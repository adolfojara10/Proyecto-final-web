package ec.edu.ups.ppw.ProyectoFinalBanco.view;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

	private List<Persona> clientesList;
//	private List<Persona> cuentasList;
	
	@PostConstruct
	public void init() {
		newCliente = new Persona();
		newCuenta = new Cuenta(); 
		clientesList = perON.getClientes();
	}
	
	public String guardar() {
		System.out.println("1111 - " + tipo + " el id es > " + perON.calcularID() );		
		
		newCliente.setId(perON.calcularID());						
		newCliente.setTipo(tipo);		
		
		newCuenta.setId(cueON.calcularID());		
		newCuenta.setNombre_usuario("aaa");
		cueON.guardarCuenta(newCuenta);
		
		newCliente.setCuenta(newCuenta);
		//newCuenta.setPersona(newCliente);
		System.out.println(newCliente);
		System.out.println(newCuenta);
		cueON.guardarCuenta(newCuenta);
		perON.guardarCliente(newCliente);
		
		System.out.println(" user > " + newCliente);
		System.out.println(" user > " + newCuenta);
//		System.out.println("user > clientes > " + clientesList);		
		return null;
	}
	
	public void error() {
        FacesMessage msg = null;
        boolean valCed = perON.validarCedula(newCliente.getCedula());
        if (!valCed) {
            msg = new FacesMessage("Cedula Invalida");
        }
        else {
            msg = new FacesMessage("cedula valida");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
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
//
//	public List<Persona> getClientesList() {
//		return clientesList;
//	}
//
//	public void setClientesList(List<Persona> clientesList) {
//		this.clientesList = clientesList;
//	}

}
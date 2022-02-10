package ec.edu.ups.ppw.ProyectoFinalBanco.view;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

//import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.ups.ppw.ProyectoFinalBanco.business.CuentaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.PersonaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Cuenta;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Persona;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Servicios;

@Named
@RequestScoped
public class GestionPersonasBean {
//jovnes si hicieron pull?
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
	private String usuario;

	private Persona newCliente = new Persona();
	private Cuenta newCuenta = new Cuenta();

	private Cuenta cuentaLogIn = new Cuenta();

	private List<Persona> clientesList;
//	private List<Persona> cuentasList;
	private List<Servicios> listaServiciosActivos;

	@PostConstruct
	public void init() {
		newCliente = new Persona();
		newCuenta = new Cuenta();
		clientesList = perON.getClientes();
		// listaServiciosActivos = new ArrayList<Servicios>();

	}

	public String guardar() throws NoSuchAlgorithmException {
		System.out.println("1111 - " + tipo + " el id es > " + perON.calcularID());
		String contraencrip = cueON.sha1(newCuenta.getContraseña());
		newCliente.setId(perON.calcularID());
		newCliente.setTipo(tipo);

		newCuenta.setId(cueON.calcularID());
		// newCuenta.setNombre_usuario(cueON.crearNombreUsuario(newCliente.getNombre(),
		// newCliente.getApellido()));
		newCuenta.setNombre_usuario("aa");
		newCuenta.setContraseña(contraencrip);
		newCuenta.setSaldo(3000);
		cueON.guardarCuenta(newCuenta);

		System.out.println(newCliente);
		System.out.println(newCuenta);
		cueON.guardarCuenta(newCuenta);
		newCliente.setCuenta(newCuenta);
		perON.guardarCliente(newCliente);

		this.cedula = newCliente.getCedula();
		System.out.println(" user > " + newCliente);
		System.out.println(" user > " + newCuenta);
		System.out.println("cd > " + cedula);

		return null;
	}

	public void error() {
		FacesMessage msg = null;
		boolean valCed = perON.validarCedula(newCliente.getCedula());
		if (!valCed) {
			msg = new FacesMessage("Cedula Invalida");
		} else {
			msg = new FacesMessage("cedula valida");
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String login() {
		System.out.println(usuario);
		System.out.println(contraseña);
		cuentaLogIn = cueON.logIn(usuario, contraseña);

		if (cuentaLogIn != null) {
			cueON.setCuentaLogIn(cuentaLogIn);
			if (cueON.getCuentaLogIn() != null) {
				this.cargarDeudas();
				return "poliza";
			}else {
				return null;
			}
			
		}

		System.out.println("Cuenta Iniciada con exito");
		System.out.println(cuentaLogIn);
		return null;
	}

	public String cargarDeudas() {

		var listaDeudas = cueON.getPersonaLogIn().getServicios();
		listaServiciosActivos = new ArrayList<Servicios>();
		if (listaDeudas.size() > 0) {
			for (Servicios s : listaDeudas) {
				if (s.isEstado()) {
					listaServiciosActivos.add(s);
				}
			}
		}

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Cuenta getCuentaLogIn() {
		return cuentaLogIn;
	}

	public void setCuentaLogIn(Cuenta cuentaLogIn) {
		this.cuentaLogIn = cuentaLogIn;
	}

	public List<Servicios> getListaServiciosActivos() {
		return listaServiciosActivos;
	}

	public void setListaServiciosActivos(List<Servicios> listaServiciosActivos) {
		this.listaServiciosActivos = listaServiciosActivos;
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

package ec.edu.ups.ppw.ProyectoFinalBanco.view;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Prestamo;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Servicio;

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
	private static List<Servicio> listaServiciosActivos;

	private static List<Prestamo> listaPrestamosActivos;

	@PostConstruct
	public void init() {
		newCliente = new Persona();
		newCuenta = new Cuenta();
		clientesList = perON.getClientes();
		// listaServiciosActivos = new ArrayList<Servicios>();
//		System.out.println(cueON.getPersonaLogIn().getCuenta().getNombreUsuario());
	}

	public String guardar() throws NoSuchAlgorithmException {
		System.out.println("1111 - " + tipo + " el id es > " + perON.calcularID());
		String contraencrip = cueON.sha1(newCuenta.getContrasenia());
		newCliente.setId(perON.calcularID());
		newCliente.setTipo(tipo);

		newCuenta.setId(cueON.calcularID());
		newCuenta.setNombreUsuario(cueON.crearNombreUsuario(newCliente.getNombre(), newCliente.getApellido()));
		// newCuenta.setNombre_usuario("aa");
		newCuenta.setContrasenia(contraencrip);
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

	@SuppressWarnings("finally")
	public String verificarLogIn() {
		try {
			usuario = cueON.getPersonaLogIn().getCuenta().getNombreUsuario();
			System.out.println("us " + usuario);
			return usuario;

		} finally {
			// TODO: handle finally clause
			if (usuario == null) {
				return "iniciarSesion";
			} else {
				return null;
			}
		}

	}

	@SuppressWarnings("finally")
	public String obtenerUser() {

		try {
			System.out.println("--** " + cueON.getPersonaLogIn().getCuenta().getNombreUsuario());
			usuario = cueON.getPersonaLogIn().getCuenta().getNombreUsuario();
			return cueON.getPersonaLogIn().getCuenta().getNombreUsuario();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("--- >// " + e);
		} finally {
			return cueON.getPersonaLogIn().getCuenta().getNombreUsuario();
		}

		// return null;
	}

	public String logout() {
		cueON.logOut();
		return this.verificarLogIn();
	}

	public String login() {
		System.out.println(usuario);
		System.out.println(contraseña);
		cuentaLogIn = cueON.logIn(usuario, contraseña);

		try {
			if (cuentaLogIn != null) {
				cueON.setCuentaLogIn(cuentaLogIn);
				if (cueON.getCuentaLogIn() != null) {

					if (cueON.getPersonaLogIn().getTipo().equals("Común")) {

						System.out.println("brrrrrrrrrrrrrrrrr");
					}
					this.cargarDeudas();
					this.cargarDeudasPrestamos();
					return "poliza.xhtml";
				} else {
					return null;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Cuenta Iniciada con exito");
		System.out.println(cuentaLogIn);
		return null;
	}

	public void cargarDeudas() {

		var listaDeudas = cueON.getPersonaLogIn().getServicios2();
		listaServiciosActivos = new ArrayList<Servicio>();
		if (listaDeudas.size() > 0) {
			for (Servicio s : listaDeudas) {
				if (s.getEstado().equals("Pagar")) {
					listaServiciosActivos.add(s);
				}
			}
		}

		System.out.println(listaServiciosActivos);

	}

	public void cargarDeudasPrestamos() {

		var listaDeudas = cueON.getPersonaLogIn().getPrestamos1();
		System.out.println(listaDeudas);
		listaPrestamosActivos = new ArrayList<Prestamo>();
		if (listaDeudas.size() > 0) {
			for (Prestamo s : listaDeudas) {
				if (s.getEstado().equals("Pendiente")) {

					Calendar hoy = Calendar.getInstance();
					Calendar fin = Calendar.getInstance();

					fin.setTime(s.getFechaFin());

					Period period = Period.between(LocalDate.ofInstant(hoy.toInstant(), ZoneId.systemDefault()),
							LocalDate.ofInstant(fin.toInstant(), ZoneId.systemDefault()));

					int mesesSobrantes = 0;
					if (Math.abs(period.getYears()) > 0) {
						mesesSobrantes = Math.abs(period.getMonths()) + (Math.abs(period.getYears()) * 12);
					} else if (Math.abs(period.getDays()) == 0) {
						mesesSobrantes = Math.abs(period.getMonths());
					} else {
						mesesSobrantes = Math.abs(period.getMonths()) + 1;
					}

					int mesesRestantes = s.getPlazo() - s.getCoutasPagadas();

					System.out.println(mesesSobrantes + "-----------" + mesesRestantes + "---------------"
							+ Math.abs(period.getYears()) + "------------" + Math.abs(period.getMonths())
							+ "------------" + Math.abs(period.getDays()));

					if (mesesSobrantes - mesesRestantes == 0) {
						listaPrestamosActivos.add(s);
					}

				}
			}
		}

		System.out.println(listaPrestamosActivos);

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

	public List<Servicio> getListaServiciosActivos() {
		return listaServiciosActivos;
	}

	public void setListaServiciosActivos(List<Servicio> listaServiciosActivos) {
		this.listaServiciosActivos = listaServiciosActivos;
	}

	public List<Prestamo> getListaPrestamosActivos() {
		return listaPrestamosActivos;
	}

	public void setListaPrestamosActivos(List<Prestamo> listaPrestamosActivos) {
		this.listaPrestamosActivos = listaPrestamosActivos;
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

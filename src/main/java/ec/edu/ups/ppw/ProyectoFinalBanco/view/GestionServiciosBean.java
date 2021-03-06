package ec.edu.ups.ppw.ProyectoFinalBanco.view;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.ups.ppw.ProyectoFinalBanco.business.CuentaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.PersonaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.ServiciosON;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Persona;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Servicio;

@Named
@RequestScoped
public class GestionServiciosBean {

	@Inject
	private ServiciosON serviciosON;

	@Inject
	private PersonaON personaON;

	@Inject
	private CuentaON cuentaON;

	private Servicio servicio = new Servicio();
	private Servicio servicioPagar;
	private Persona persona = new Persona();

	private Date fechaEmision;
	private String tipo;
	private double deuda;
	private boolean estado;
	private int idServicio;

	private List<Servicio> listaServiciosActivos;

	private String cedula;

	@PostConstruct
	public void init() {
		servicio = new Servicio();
		fechaEmision = new Date();
		// persona = new Persona();
		// servicioPagar = new Servicios();

		this.cargarListaDeudas();
	}

	public String guardarServicio() {
		servicio.setId(serviciosON.calcularID());
		servicio.setDeuda(deuda);
		servicio.setEstado("Pagar");
		servicio.setTipo(tipo);
		servicio.setFechaEmision(fechaEmision);
		this.persona = personaON.buscarCedula(cedula);

		serviciosON.guardarServicios(servicio);

		cuentaON.getPersonaLogIn().addServiciosEmitido(servicio);

		this.cargarPersona();
		this.persona.addServiciosPagados(servicio);
		personaON.guardarCliente(persona);
		personaON.guardarCliente(cuentaON.getPersonaLogIn());

		System.out.println(servicio);

		return null;
	}

	public String cargarPersona() {

		this.persona = personaON.buscarCedula(cedula);
		return null;
	}

	public String pagarServicio() {

		servicioPagar = serviciosON.buscarServicio(idServicio);

		if (servicioPagar != null) {
			if (cuentaON.getCuentaLogIn().getSaldo() > servicioPagar.getDeuda() && servicioPagar != null && servicioPagar.getEstado().equals("Pagar")) {

				cuentaON.pagoServicio(servicioPagar);

				// servicioPagar.setEstado(false);

				// serviciosON.guardarServicios(servicioPagar);

				System.out.println("siuuu");
				servicioPagar = null;
				idServicio = 0;
				this.cargarListaDeudas();
				return "SIUUU";

			} else {
				System.out.println("noooo");
				return "No";
			}
		}
		return null;
	}

	public List<Servicio> cargarListaDeudas() {
		
		listaServiciosActivos = personaON.cargarDeudas();

		return listaServiciosActivos;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getDeuda() {
		return deuda;
	}

	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Servicio getServicioPagar() {
		return servicioPagar;
	}

	public void setServicioPagar(Servicio servicioPagar) {
		this.servicioPagar = servicioPagar;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public List<Servicio> getListaServiciosActivos() {
		return listaServiciosActivos;
	}

	public void setListaServiciosActivos(List<Servicio> listaServiciosActivos) {
		this.listaServiciosActivos = listaServiciosActivos;
	}

}

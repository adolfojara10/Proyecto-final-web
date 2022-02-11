package ec.edu.ups.ppw.ProyectoFinalBanco.view;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.ups.ppw.ProyectoFinalBanco.business.CuentaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.PersonaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.TarjetaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Persona;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Tarjeta;

@Named
@RequestScoped
public class GestionTargetaBean {
	@Inject
	private PersonaON perON;
	
	@Inject
	private TarjetaON tarON;
	
	@Inject
	private CuentaON cuentaON;
	
	private int id;
	private String nombre;
	private int numero;
	private int codigo;
	private Date fechaInicio;
	private Date fechaFin;
	private String tipo;
	
	private String cedula;
	private double saldo;
	
	private Tarjeta newTarjet = new Tarjeta();
	private Persona newCliente = new Persona();
	
	@PostConstruct
	public void init() {
		newCliente = new Persona();
		newTarjet = new Tarjeta();
	}
	
	public String guardar() {
		var p = cuentaON.getPersonaLogIn();
		double saldo = p.getCuenta().getSaldo();
		if(saldo > 5) {
			//newTarjet.setId(tarON.calcularID());
			newTarjet.setTipo(tipo);
			newTarjet.setNumero(tarON.generarNumero());
			newTarjet.setCodigo(tarON.generarcodigoseguridad());
			System.out.println(newTarjet.toString());
			tarON.guardarTarjeta(newTarjet);
			
			cuentaON.getPersonaLogIn().addTarjeta(newTarjet);
			System.out.println(p);
			perON.guardarCliente(cuentaON.getPersonaLogIn());
			
			System.out.println("Solicitud aprovada");
		}else {
			System.out.println("Faltan fondos para aprovar su Solicitud");
		}
		return null;
	}

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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Tarjeta getNewTarjet() {
		return newTarjet;
	}

	public void setNewTarjet(Tarjeta newTarjet) {
		this.newTarjet = newTarjet;
	}

	public Persona getNewCliente() {
		return newCliente;
	}

	public void setNewCliente(Persona newCliente) {
		this.newCliente = newCliente;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
	

}

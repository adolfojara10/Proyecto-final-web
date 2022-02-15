package ec.edu.ups.ppw.ProyectoFinalBanco.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public String fechaN() throws ParseException  {			
		return tarON.generarFecha();
	}
	
	public String idTargeta() throws ParseException {
		int it=tarON.calcularID();
		String ita = String.valueOf(it);
		return ita;
	}
	
	public String guardar() throws ParseException {
		var p = cuentaON.getPersonaLogIn();
		double saldo = p.getCuenta().getSaldo();
		if(saldo > 5) {
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			newTarjet.setFechaInicio(sdf.parse(tarON.generarFecha()));
			newTarjet.setId(tarON.calcularID());
			newTarjet.setTipo(tipo);
			newTarjet.setNumero(tarON.generarNumero());
			newTarjet.setCodigo(tarON.generarcodigoseguridad());
			newTarjet.setPersona(p);
			
			tarON.guardarTarjeta(newTarjet);
			System.out.println(newTarjet);
			//p.addTarjeta(newTarjet);
			p.addTarjeta(newTarjet);
			
			perON.guardarCliente(p);
			System.out.println(p);
			
			System.out.println("Solicitud aprovada");
			return "AvisoTarjeta";
		}else {
			System.out.println("Faltan fondos para aprovar su Solicitud");
			return "AvisoRechaso";
		}

	}
	
	public void cargarDatosCliente() {
		Tarjeta t = tarON.buscarTarjeta(id);
		this.nombre = t.getNombre();
		this.numero = t.getNumero();
		this.codigo = t.getCodigo();
		this.tipo = t.getTipo();
		this.fechaInicio= t.getFechaInicio();
		this.fechaFin = t.getFechaFin();
		// return null;
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

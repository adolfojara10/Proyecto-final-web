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
import ec.edu.ups.ppw.ProyectoFinalBanco.business.PrestamosON;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Persona;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Prestamo;


@Named
@RequestScoped
public class GestionPrestamosBean {
	@Inject
	private PersonaON perON;
	
	@Inject
	private PrestamosON presON;
	
	@Inject
	private CuentaON cuentaON;
	
	private int id;
	private double monto;
	private int cuotasPagadas;
	private boolean estado;
	private Date fecha_inicio;
	private Date fecha_fin;
	private double pagoMensual;
	private int plazo;
	private double pagoProgreso;
	private double porInteres;
	
	private String cedula;
	private String nombre;
	private String apellidos;
	private int cuenta;
	private boolean aprovacion;
	
	private String cedulaP;
	private String nombreP;
	private String apellidosP;
	private int cuentaP;
	
	private Prestamo newPrestamo = new Prestamo();
	private Persona newCliente = new Persona();
	
	@PostConstruct
	public void init() {
		newCliente = new Persona();
		newPrestamo = new Prestamo();
		aprovacion = false;
	}
	
	public void cargarDatosGarante () {
		Persona p = perON.buscarCedula(this.cedula);
		this.nombre = p.getNombre();
		this.apellidos = p.getApellido();
		this.cuenta = p.getCuenta().getId();
		//return null;
	}
	
	public void cargarDatosCliente () {
		Persona p = perON.buscarCedula(this.cedulaP);
		this.nombreP = p.getNombre();
		this.apellidosP = p.getApellido();
		this.cuentaP = p.getCuenta().getId();
		//return null;
	}
	
	public void aprobarGarante() {
		Persona p = perON.buscarCedula(this.cedula);
		System.out.println(p);
		double saldo = p.getCuenta().getSaldo();
		if(saldo > 2000) {
			System.out.println("Garante Aprobado");
			aprovacion = true;
			System.out.println(aprovacion);
			//return null;
		}else {
			System.out.println("Garante No Aprobado");
			aprovacion = false;
			System.out.println(aprovacion);
		}
		//return null;
	}
	
	public void calculoPagoCuotas() {
		System.out.println(this.monto);
		this.porInteres= presON.calculoInteres(this.monto);
		this.pagoMensual= presON.calculoPago(this.monto, this.plazo);
		//return null;
	}
	
	public String guardarPrestamo() throws ParseException {
		aprobarGarante();
		System.out.println(aprovacion);
		if(aprovacion == true) {
			newPrestamo.setId(presON.calcularID());
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			newPrestamo.setFechaInicio(sdf.parse(presON.generarFecha()));
			calculoPagoCuotas();
			newPrestamo.setMonto(monto);
			newPrestamo.setEstado("Pendiente");
			newPrestamo.setPagoMensual(pagoMensual);
			newPrestamo.setPlazo(plazo);
			newPrestamo.setFechaFin(presON.generarFechaFin(plazo));
			newPrestamo.setInteres(porInteres);
			Persona p = perON.buscarCedula(this.cedula);
			newPrestamo.setPersona2(p);
			var c = cuentaON.getPersonaLogIn();
			newPrestamo.setPersona1(c);
			presON.guardarPrestamo(newPrestamo);
			c.addPrestamos1(newPrestamo);
			perON.guardarCliente(c);
			p.addPrestamos2(newPrestamo);
			perON.guardarCliente(p);
			System.out.println(c);
			System.out.println("Solicitud enviada");
		}else {
			System.out.println("No se puede enviar la solucitud garante no aprovado");
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getCuotasPagadas() {
		return cuotasPagadas;
	}

	public void setCuotasPagadas(int cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public double getPagoMensual() {
		return pagoMensual;
	}

	public void setPagoMensual(double pagoMensual) {
		this.pagoMensual = pagoMensual;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public double getPagoProgreso() {
		return pagoProgreso;
	}

	public void setPagoProgreso(double pagoProgreso) {
		this.pagoProgreso = pagoProgreso;
	}

	public double getPorInteres() {
		return porInteres;
	}

	public void setPorInteres(double porInteres) {
		this.porInteres = porInteres;
	}

	public Prestamo getNewPrestamo() {
		return newPrestamo;
	}

	public void setNewPrestamo(Prestamo newPrestamo) {
		this.newPrestamo = newPrestamo;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}

	public String getCedulaP() {
		return cedulaP;
	}

	public void setCedulaP(String cedulaP) {
		this.cedulaP = cedulaP;
	}

	public String getNombreP() {
		return nombreP;
	}

	public void setNombreP(String nombreP) {
		this.nombreP = nombreP;
	}

	public int getCuentaP() {
		return cuentaP;
	}

	public void setCuentaP(int cuentaP) {
		this.cuentaP = cuentaP;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getApellidosP() {
		return apellidosP;
	}

	public void setApellidosP(String apellidosP) {
		this.apellidosP = apellidosP;
	}

	public boolean isAprovacion() {
		return aprovacion;
	}

	public void setAprovacion(boolean aprovacion) {
		this.aprovacion = aprovacion;
	}
	
	
	
	
	
}

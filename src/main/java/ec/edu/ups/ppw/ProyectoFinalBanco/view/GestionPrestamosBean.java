package ec.edu.ups.ppw.ProyectoFinalBanco.view;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	
	private Prestamo newPrestamo = new Prestamo();
	private Persona newCliente = new Persona();
	
	@PostConstruct
	public void init() {
		newCliente = new Persona();
		newPrestamo = new Prestamo();
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
	
	

}

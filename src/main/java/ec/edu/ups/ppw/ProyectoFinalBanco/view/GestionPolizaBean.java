package ec.edu.ups.ppw.ProyectoFinalBanco.view;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.PolizaDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.dao.SolicitudDAO;
import ec.ups.edu.ppw.ProyectoFinalBanco.business.PolizaON;

@Named
@RequestScoped
public class GestionPolizaBean {

	@Inject
	private PolizaON polizaON;

	private String fechaString;

	private Date fechaInicio;
	private Date fechaFin;

	private double monto;
	private int tiempo;
	private double intereses;
	private double rendimiento;

	public String calcularRendimiento() {

		this.rendimiento = polizaON.calcularRendimiento(monto, tiempo);

		return null;
	}

	public String calcularDatos() {

		this.rendimiento = polizaON.calcularRendimiento(monto, tiempo);
		this.fechaFin = polizaON.calcularFechaFin(fechaInicio, tiempo);
		this.fechaString = polizaON.calcularFechaFinString(fechaInicio, tiempo);
		this.intereses = polizaON.calcularInteres(tiempo);
		return null;
	}

	public PolizaON getPolizaON() {
		return polizaON;
	}

	public void setPolizaON(PolizaON polizaON) {
		this.polizaON = polizaON;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public double getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}

	public double getIntereses() {
		return intereses;
	}

	public void setIntereses(double intereses) {
		this.intereses = intereses;
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

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

}

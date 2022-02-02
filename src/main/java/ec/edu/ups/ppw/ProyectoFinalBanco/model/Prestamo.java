package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Prestamo {

	@Id
	@Column(name = "pre_id")
	private int id;
	@Column(name = "pre_monto")
	private double monto;
	@Column(name = "pre_plazo")
	private int plazo;
	@Column(name = "pre_por_interes")
	private double por_interes;
	@Column(name = "pre_pago_mensual")
	private double pago_mensual;
	@Column(name = "pre_fecha_inicio")
	private Date fecha_inicio;
	@Column(name = "pre_fecha_fin")
	private Date fecha_fin;
	@Column(name = "pre_cuotas_pagadas")
	private int cuotasPagadas;
	@Column(name = "pre_pago_progreso")
	private double pago_progreso;
	@Column(name = "pre_estado")
	private boolean estado;
	@ManyToOne
	@JoinColumn(name = "per_id", insertable = false, updatable = false)
	private Persona persona;
	@ManyToOne
	@JoinColumn(name = "per_id", insertable = false, updatable = false)
	private Persona garante;

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

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public double getPor_interes() {
		return por_interes;
	}

	public void setPor_interes(double por_interes) {
		this.por_interes = por_interes;
	}

	public double getPago_mensual() {
		return pago_mensual;
	}

	public void setPago_mensual(double pago_mensual) {
		this.pago_mensual = pago_mensual;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public double getPago_progreso() {
		return pago_progreso;
	}

	public void setPago_progreso(double pago_progreso) {
		this.pago_progreso = pago_progreso;
	}

	public int getCuotasPagadas() {
		return cuotasPagadas;
	}

	public void setCuotasPagadas(int cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Persona getGarante() {
		return garante;
	}

	public void setGarante(Persona garante) {
		this.garante = garante;
	}

	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", monto=" + monto + ", plazo=" + plazo + ", por_interes=" + por_interes
				+ ", pago_mensual=" + pago_mensual + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin
				+ ", estado=" + estado + ", persona=" + persona + ", garante=" + garante + "]";
	}

}

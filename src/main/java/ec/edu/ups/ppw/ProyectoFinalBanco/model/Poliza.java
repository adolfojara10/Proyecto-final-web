package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Poliza {
	@Id
	@Column(name = "pol_id")
	private int id;
	@Column(name = "pol_monto")
	private double monto;
	@Column(name = "pol_tiempo")
	private int tiempo;
	@Column(name = "pol_por_interez")
	private double por_interes;
	@Column(name = "pol_fecha_inicio")
	private Date fecha_inicio;
	@Column(name = "pol_fecha_fin")
	private Date fecha_fin;
	@Column(name = "pol_rendimiento")
	private double rendimiento;
	@Column(name = "pol_estado")
	private boolean estado;
	
	/*@ManyToOne
	@JoinColumn(name = "per_id")
	private Persona persona;
*/
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

	public double getPor_interes() {
		return por_interes;
	}

	public void setPor_interes(double por_interez) {
		this.por_interes = por_interez;
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

	public double getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean activo) {
		this.estado = activo;
	}

	
	
	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public String toString() {
		return "Poliza [id=" + id + ", monto=" + monto + ", por_interes=" + por_interes + ", fecha_inicio="
				+ fecha_inicio + ", fecha_fin=" + fecha_fin + ", rendimiento=" + rendimiento + ", estado=" + estado +"]";
	}

}

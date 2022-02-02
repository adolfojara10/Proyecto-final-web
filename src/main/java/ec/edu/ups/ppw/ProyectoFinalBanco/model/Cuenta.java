package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cuenta {
	@Id
	@Column(name="cue_id")
	private int id;
	@Column(name="cue_saldo")
	private double saldo;
	@Column(name="cue_nombre_usuario")
	private String nombre_usuario;
	@Column(name="cue_contraseña")
	private String contraseña;
	@OneToOne
	@JoinColumn(name = "per_id")
	private Persona persona;
	@OneToMany
	@JoinColumn(name = "tra_id")
	private List<Transferencia> tranferencia;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public List<Transferencia> getTranferencia() {
		return tranferencia;
	}
	public void setTranferencia(List<Transferencia> tranferencia) {
		this.tranferencia = tranferencia;
	}
	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", saldo=" + saldo + ", nombre_usuario=" + nombre_usuario + ", contraseña="
				+ contraseña + ", persona=" + persona + ", tranferencia=" + tranferencia + "]";
	}
	
	
	
	

}

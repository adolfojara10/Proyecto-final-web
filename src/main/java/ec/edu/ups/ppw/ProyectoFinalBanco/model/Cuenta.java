package ec.edu.ups.ppw.ProyectoFinalBanco.model;

import java.io.Serializable;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c")
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String contrasenia;

	@Column(name="nombre_usuario")
	private String nombreUsuario;

	private double saldo;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="cuenta", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Persona> personas;

	//bi-directional many-to-one association to Transferencia
	@OneToMany(mappedBy="cuenta", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Transferencia> transferencias;

	public Cuenta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Set<Persona> getPersonas() {
		return this.personas;
	}

	public void setPersonas(Set<Persona> personas) {
		this.personas = personas;
	}

	public Persona addPersona(Persona persona) {
		getPersonas().add(persona);
		persona.setCuenta(this);

		return persona;
	}

	public Persona removePersona(Persona persona) {
		getPersonas().remove(persona);
		persona.setCuenta(null);

		return persona;
	}

	public Set<Transferencia> getTransferencias() {
		return this.transferencias;
	}

	public void setTransferencias(Set<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public Transferencia addTransferencia(Transferencia transferencia) {
		getTransferencias().add(transferencia);
		transferencia.setCuenta(this);

		return transferencia;
	}

	public Transferencia removeTransferencia(Transferencia transferencia) {
		getTransferencias().remove(transferencia);
		transferencia.setCuenta(null);

		return transferencia;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", contrasenia=" + contrasenia + ", nombreUsuario=" + nombreUsuario + 
		", saldo=" + saldo;
//		+ ",\n --- > personas=" + personas 
//		+",\n --- > transferencias=" + transferencias + "]";
	}

}


package ec.edu.ups.ppw.ProyectoFinalBanco.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.PersonaDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Cuenta;
//import ec.edu.ups.ppw.ProyectoFinalBanco.dao.TransferenciaDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Persona;

@Stateless
public class PersonaON {

	@Inject
	private PersonaDAO personaDAO;

//	@Inject
//	private TransferenciaDAO transferenciaDAO;

	@Inject
	private CuentaON cuentaON;

	public void guardarCliente(Persona persona) {

		Persona p = personaDAO.read(persona.getId());
		if (p == null) {
			personaDAO.insert(persona);
		} else {
			personaDAO.update(persona);
		}
	}

	public Persona consultarCuentaUsuario(Cuenta cuenta) {
		var personas = personaDAO.getList();

		for (Persona p : personas) {
			if (p.getCuenta().getId() == cuenta.getId()) {
				return p;
			}
		}

		return null;
	}

	public boolean validarCedula(String cedula) {
		if (cedula.length() == 10) {
			int verificador = Integer.parseInt(cedula.substring(cedula.length() - 1, cedula.length()));
			int aux = 0;
			int suma = 0;
			int[] multiplicador = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
			for (int i = 0; i < cedula.length() - 1; i++) {
				int valor = Integer.parseInt(String.valueOf(cedula.charAt(i))) * multiplicador[i];
				if (valor > 9) {
					valor -= 9;
				}
				suma += valor;
			}
			if (suma % 10 == 0) {
				return true;
			} else if ((10 - (suma % 10)) == verificador) {
				return true;

			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public boolean validarUsuario(String nombre) {
		for (Persona per : personaDAO.getList()) {
			System.out.println("------------- " + per.getNombre());
			if (per.getNombre().equals(nombre)) {
				System.out.println(per.getNombre() + "     " + nombre);
				return true;
			} else {
				System.out.println("No existe");
			}
		}
		return false;
	}

	public String determinarInicioSesion(Persona persona) {

		String pagina = "";
		if (persona.getTipo().equals("Administrador")) {
			pagina = "InicioAdmin";
		} else if (persona.getTipo().equals("Cajero")) {
			pagina = "InicioCajero";
		} else if (persona.getTipo().equals("Jefe de credito")) {
			pagina = "CreditoJefeCredito";
		} else {
			pagina = "DetalleCuenta";
		}

		return pagina;

	}

	public int calcularID() {
		var lista = personaDAO.getList();

		if (lista.size() == 0) {
			return 1;
		} else {
			return lista.size() + 1;
		}

	}

	public List<Persona> getClientes() {
		return personaDAO.getList();
	}

	public Persona buscarCedula(String cedula) {
		var listaPersonas = personaDAO.getList();
		Persona per;
		per = listaPersonas.stream().filter(p -> p.getCedula().equals(cedula)).findFirst().get();
		System.out.println(per);
		return per;
	}
}

package ec.ups.edu.ppw.ProyectoFinalBanco.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.CuentaDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Cuenta;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Persona;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Transferencia;

@Stateless
public class CuentaON {

	@Inject
	private CuentaDAO cuentaDAO;

	@Inject
	private PersonaON personaON;

//	@Inject
//	private TransferenciaON transferenciaON;

	public void guardarCuenta(Cuenta cuenta) {
		var c = cuentaDAO.read(cuenta.getId());
		if (c == null) {
			cuentaDAO.insert(cuenta);
		} else {
			cuentaDAO.update(cuenta);
		}
	}

	public Cuenta BuscarCuenta(int cuenta) {
		return cuentaDAO.read(cuenta);
	}

	public void deposito(double monto, Persona per) {

		System.out.println(per.getCuenta().getSaldo() + monto);
		per.getCuenta().setSaldo(per.getCuenta().getSaldo() + monto);
		Cuenta cuenta = per.getCuenta();
		guardarCuenta(cuenta);
//		transferenciaON.guardarTransferencia(cuenta, monto, "Deposito");

	}

	public boolean transferencia(Transferencia t) {
		Persona us = personaON.consultarCuentaUsuario(t.getCuenta().getId()).get(0);
		Persona us1 = personaON.consultarCuentaUsuario(t.getCuenta().getId()).get(0);

		if (us == null && us1 == null || t.getMonto() > us.getCuenta().getSaldo()) {
			return false;
		} else {
			us.getCuenta().setSaldo(us.getCuenta().getSaldo() - t.getMonto());
			us1.getCuenta().setSaldo(us1.getCuenta().getSaldo() + t.getMonto());
			Cuenta cuenta = us.getCuenta();
			this.guardarCuenta(cuenta);

			Cuenta cuenta1 = us1.getCuenta();

			this.guardarCuenta(cuenta1);
			return true;
		}
	}

	public int calcularID() {
		var lista = cuentaDAO.getList();

		if (lista == null) {
			return 1;
		} else {
			return lista.size() + 1;
		}

	}

	public String crearNombreUsuario(String nombre, String apellido) {
		String nombreUsuario = nombre.concat(Character.toString(apellido.charAt(0)));
		nombreUsuario = nombreUsuario.concat(Integer.toString(calcularID()));
		
		return nombreUsuario;
	}
}

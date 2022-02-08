package ec.edu.ups.ppw.ProyectoFinalBanco.business;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private Cuenta cuentaLogIn = new Cuenta();
	
	private Persona personaLogIn = new Persona();

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

	// metodo para convertir texto hash en encriptar contraseñas
	public String sha1(String contraseña) throws NoSuchAlgorithmException {
		String hash;
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(contraseña.getBytes());
		byte[] digest = md.digest();
		hash = hexaToString(digest);
		return hash;
	}

	public String hexaToString(byte[] digest) {
		// Convert digest to a string
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			if ((0xff & digest[i]) < 0x10) {
				hexString.append("0" + Integer.toHexString((0xFF & digest[i])));
			} else {
				hexString.append(Integer.toHexString(0xFF & digest[i]));
			}
		}
		return hexString.toString();
	}

	public Cuenta logIn(String nombreUsu, String contra) {
		var listaCuentas = cuentaDAO.getList();
		Cuenta usu;
		String passwordHash = null;
		try {
			passwordHash = sha1(contra);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		final String pass = passwordHash;
		System.out.println(pass);
		try {
			usu = listaCuentas.stream()
					.filter(cue -> cue.getNombre_usuario().equals(nombreUsu) && pass.equals(cue.getContraseña()))
					.findFirst().get();
			System.out.println(usu);

			return usu;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Cuenta getCuentaLogIn() {
		return cuentaLogIn;
	}

	public void setCuentaLogIn(Cuenta cuentaLogIn) {
		this.cuentaLogIn = cuentaLogIn;
		this.setPersonaLogIn(this.personaON.consultarCuentaUsuario(cuentaLogIn.getId()).get(0)); 
	}

	public Persona getPersonaLogIn() {
		return personaLogIn;
	}

	public void setPersonaLogIn(Persona personaLogIn) {
		this.personaLogIn = personaLogIn;
	}
	
	

}

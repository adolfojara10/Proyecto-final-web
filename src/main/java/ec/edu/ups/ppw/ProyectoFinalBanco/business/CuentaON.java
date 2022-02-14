package ec.edu.ups.ppw.ProyectoFinalBanco.business;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.CuentaDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.dao.ServiciosDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Cuenta;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Persona;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Servicio;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Transferencia;

@Stateless
public class CuentaON {

	@Inject
	private CuentaDAO cuentaDAO;

	@Inject
	private PersonaON personaON;

	@Inject
	private ServiciosON serviciosON;

	@Inject
	private TransferenciaON transferenciaON;

	private Cuenta cuentaLogIn = new Cuenta();

	private Persona personaLogIn = new Persona();

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

	public void deposito(double monto, Persona per) throws ParseException {

		System.out.println(per.getCuenta().getSaldo() + " -- " + monto);
		

		per.getCuenta().setSaldo(monto + per.getCuenta().getSaldo());
		Cuenta cuenta = per.getCuenta();

		var t = new Transferencia();
		t.setId(transferenciaON.calcularID());

		String pattern = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		t.setFecha(sdf.parse(transferenciaON.generarFecha()));

		t.setMonto(monto);
		t.setTipo("Deposito");

		transferenciaON.guardarTransferencia(t);

		cuenta.addTransferencia(t);

		guardarCuenta(cuenta);
		System.out.println(transferenciaON.getTransferencias());
		System.out.println(cuenta + " " + cuenta.getTransferencias());

	}

	public boolean transferencia(Transferencia t) {
		Persona us = personaON.consultarCuentaUsuario(t.getCuenta());
		Persona us1 = personaON.consultarCuentaUsuario(t.getCuenta());

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

	public boolean pagoServicio(Servicio s) {

		// var lista = personaON.personasServicio(s);

		var p1 = s.getPersona1();
		var p2 = s.getPersona2();

		if (p1 != null && p2 != null) {
			p1.getCuenta().setSaldo(p1.getCuenta().getSaldo() + s.getDeuda());
			p2.getCuenta().setSaldo(p2.getCuenta().getSaldo() - s.getDeuda());

			var t = new Transferencia();
			t.setId(transferenciaON.calcularID());
			t.setFecha(new Date());
			t.setMonto(s.getDeuda());
			t.setTipo("Servicios");
			transferenciaON.guardarTransferencia(t);

			Cuenta cuenta = p1.getCuenta();
			cuenta.addTransferencia(t);
			this.guardarCuenta(cuenta);

			Cuenta cuenta1 = p2.getCuenta();
			cuenta1.addTransferencia(t);
			this.guardarCuenta(cuenta1);

			s.setEstado("Pagado");
			serviciosON.guardarServicios(s);
			
			System.out.println("sipi");
		}

		return true;
		/*
		 * Persona us = personaON.consultarCuentaUsuario(t.getCuenta()); Persona us1 =
		 * personaON.consultarCuentaUsuario(t.getCuenta());
		 * 
		 * if (us == null && us1 == null || t.getMonto() > us.getCuenta().getSaldo()) {
		 * return false; } else { us.getCuenta().setSaldo(us.getCuenta().getSaldo() -
		 * t.getMonto()); us1.getCuenta().setSaldo(us1.getCuenta().getSaldo() +
		 * t.getMonto()); Cuenta cuenta = us.getCuenta(); this.guardarCuenta(cuenta);
		 * 
		 * Cuenta cuenta1 = us1.getCuenta();
		 * 
		 * this.guardarCuenta(cuenta1); return true; }
		 */
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

		String nom = nombre.split(" ")[0].toLowerCase();
		String ape = apellido.split(" ")[0].toLowerCase();

		String nombreUsuario = nom.concat(ape);
		nombreUsuario = nombreUsuario.concat(String.valueOf(this.calcularID()));
		/*
		 * String nombreUsuario = nombre.concat(Character.toString(apellido.charAt(0)));
		 * nombreUsuario = nombreUsuario.concat(Integer.toString(calcularID()));
		 */

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
		System.out.println("contra > " + pass);
		try {
			usu = listaCuentas.stream()
					.filter(cue -> cue.getNombreUsuario().equals(nombreUsu) && pass.equals(cue.getContrasenia()))
					.findFirst().get();
			System.out.println(usu);

			this.cuentaLogIn = usu;
			this.personaLogIn = personaON.consultarCuentaUsuario(this.cuentaLogIn);
			
			personaON.cargarDeudas();

			return usu;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void logOut() {
		try {
			this.setCuentaLogIn(null);
			this.setPersonaLogIn(null);
			System.out.println("lof " + personaLogIn + " log " + cuentaLogIn);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("+++ > " + e);
		}

	}

	public Cuenta getCuentaLogIn() {
		return cuentaLogIn;
	}

	public void setCuentaLogIn(Cuenta cuentaLogIn) {
		this.cuentaLogIn = cuentaLogIn;
		this.setPersonaLogIn(this.personaON.consultarCuentaUsuario(cuentaLogIn));
	}

	public Persona getPersonaLogIn() {
		return personaLogIn;
	}

	public void setPersonaLogIn(Persona personaLogIn) {
		this.personaLogIn = personaLogIn;
	}

}

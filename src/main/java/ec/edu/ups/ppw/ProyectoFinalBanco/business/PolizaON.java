package ec.edu.ups.ppw.ProyectoFinalBanco.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.CuentaDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.dao.PolizaDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Cuenta;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Persona;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Poliza;

@Stateless
public class PolizaON {

	@Inject
	private PolizaDAO polizaDAO;

	@Inject
	private CuentaON cuentaON;

	@Inject
	private PersonaON personaON;

	public void guardarPoliza(Cuenta cuenta, Poliza poliza) {

		var p = polizaDAO.read(poliza.getId());
		if (p == null) {
			// poliza.setPersona(cuentaON.getPersonaLogIn());
			polizaDAO.insert(poliza);
			cuentaON.getPersonaLogIn().addPoliza(poliza);
			cuenta.setSaldo(cuenta.getSaldo() - poliza.getMonto());
			cuentaON.guardarCuenta(cuenta);
			
		} else {
			polizaDAO.update(poliza);
		}

		//this.cambiarEstado();

	}

	public int calcularID() {
		var lista = polizaDAO.getList();

		if (lista.size() == 0) {
			return 1;
		} else {
			return lista.size() + 1;
		}

	}

	public Map<Integer, Double> listaInteres() {

		var mapInteres = new HashMap<Integer, Double>();
		mapInteres.put(3, 5.75);
		mapInteres.put(6, 6.50);
		mapInteres.put(12, 7.25);

		return mapInteres;

	}

	public double calcularRendimiento(double monto, int tiempo) {
		double interes = 0;

		if (tiempo <= 3) {
			interes = this.listaInteres().get(3);
		} else if (tiempo > 3 && tiempo <= 6) {
			interes = this.listaInteres().get(6);
		} else {
			interes = this.listaInteres().get(12);
		}

		double rendimiento = (((monto * interes) / 100) / 12) * tiempo;
		return Math.round(rendimiento);
	}

	public Date calcularFechaFin(Date fechaInicio, int tiempo) {

		Calendar c = Calendar.getInstance();

		c.setTime(fechaInicio);

		c.add(Calendar.MONTH, tiempo + 1);

		/*DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String requiredDate = df.format(c);
		*/
		
		Date date = c.getTime();

		/*
		 * int mes = fechaInicio.getMonth();
		 * 
		 * mes += tiempo;
		 * 
		 * var fechaFin = new Date(fechaInicio.getYear(), mes, fechaInicio.getDay());
		 * 
		 * DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); String requiredDate =
		 * df.format(fechaFin);
		 * 
		 * try { SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); Date
		 * date = formatter.parse(requiredDate); fechaFin = date;
		 * System.out.println(requiredDate + "--0" + date); } catch (ParseException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		return date;
	}

	public String calcularFechaFinString(Date fechaInicio, int tiempo) {

		Calendar c = Calendar.getInstance();

		c.setTime(fechaInicio);

		c.add(Calendar.MONTH, tiempo + 1);
		
		Date date = c.getTime();

		/*DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String requiredDate = df.format(c);
*/
		/*
		 * int mes = fechaInicio.getMonth();
		 * 
		 * mes += tiempo;
		 * 
		 * var fechaFin = new Date(fechaInicio.getYear(), mes, fechaInicio.getDay());
		 * 
		 * DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); String requiredDate =
		 * df.format(fechaFin);
		 */

		return date.toString();
	}

	public void cambiarEstado() {
		var polizas = polizaDAO.getList();

		for (Poliza p : polizas) {
			if (p.getFecha_fin().after(new Date())) {
				p.setEstado(false);
				var persona = personaON.buscarPorPoliza(p);
				var cuenta = persona.getCuenta();
				cuenta.setSaldo(cuenta.getSaldo() + p.getMonto() + p.getRendimiento());
				cuentaON.guardarCuenta(cuenta);

			}
		}
	}

	public double calcularInteres(int tiempo) {
		double interes = 0;

		if (tiempo <= 3) {
			interes = this.listaInteres().get(3);
		} else if (tiempo > 3 && tiempo <= 6) {
			interes = this.listaInteres().get(6);
		} else {
			interes = this.listaInteres().get(12);
		}

		return interes;
	}

	public boolean validarPoliza(Cuenta cue, double monto) {
		if (cue.getSaldo() >= monto) {
			return true;
		} else {
			return false;
		}
	}
}

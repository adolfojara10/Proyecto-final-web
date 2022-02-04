package ec.ups.edu.ppw.ProyectoFinalBanco.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.PolizaDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Cuenta;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Poliza;

@Stateless
public class PolizaON {

	@Inject
	private PolizaDAO polizaDAO;

	public void guardarPoliza(Cuenta cuenta, Double monto, int tiempo, Date fechaInicio) {

		var p = new Poliza();
		p.setId(calcularID());
		p.setMonto(monto);
		p.setTiempo(tiempo);
		p.setPor_interes(listaInteres().get(tiempo));
		p.setRendimiento(this.calcularRendimiento(monto, tiempo));
		p.setFecha_inicio(fechaInicio);
		p.setFecha_fin(calcularFechaFin(fechaInicio, tiempo));
		p.setEstado(true);
		// p.setPersona(cuenta.getPersona());

		this.cambiarEstado();

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

		int mes = fechaInicio.getMonth();

		mes += tiempo;

		var fechaFin = new Date(fechaInicio.getYear(), mes, fechaInicio.getDay());

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String requiredDate = df.format(fechaFin);
		
		
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formatter.parse(requiredDate);
			fechaFin = date;
			System.out.println(requiredDate + "--0" + date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fechaFin;
	}
	
	public String calcularFechaFinString(Date fechaInicio, int tiempo) {

		int mes = fechaInicio.getMonth();

		mes += tiempo;

		var fechaFin = new Date(fechaInicio.getYear(), mes, fechaInicio.getDay());

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String requiredDate = df.format(fechaFin);
	

		return requiredDate;
	}

	public void cambiarEstado() {
		var polizas = polizaDAO.getList();

		for (Poliza p : polizas) {
			if (p.getFecha_fin().after(new Date())) {
				p.setEstado(false);
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
}

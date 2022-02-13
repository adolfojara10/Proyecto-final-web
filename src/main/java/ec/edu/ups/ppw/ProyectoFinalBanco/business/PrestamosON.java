package ec.edu.ups.ppw.ProyectoFinalBanco.business;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.PrestamoDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Prestamo;

@Stateless
public class PrestamosON {
	@Inject
	private PrestamoDAO prestamoDAO;
	
	public void guardarPrestamo(Prestamo prestamo) {

		Prestamo p = prestamoDAO.read(prestamo.getId());
		if (p == null) {
			prestamoDAO.insert(prestamo);
		} else {
			prestamoDAO.update(prestamo);
		}
	}
	
	public int calcularID() {
		var lista = prestamoDAO.getList();

		if (lista.size() == 0) {
			return 1;
		} else {
			return lista.size() + 1;
		}

	}
	
	public List<Prestamo> getprestamo(){
		return prestamoDAO.getList();
	}
	
	public double calculoInteres(double monto) {
		double interes = 0;
		if(monto >= 0 && monto < 20000) {
			interes = 0.5;
		}else if (monto >= 20000 && monto < 40000) {
			interes = 0.6;
		}else if (monto >= 40000 && monto < 60000) {
			interes = 0.7;
		}else if (monto >= 60000 && monto < 80000) {
			interes = 0.8;
		}else if (monto >= 80000 && monto < 100000) {
			interes = 0.9;
		}else if (monto >= 100000) {
			interes = 1;
		}
		return interes;
	}
	
	public double calculoPago(double monto, int plazo) {
		double pagoMensual = monto/plazo;
		double interes = calculoInteres(monto);
		double calucloPago = pagoMensual * interes;
		double total = pagoMensual + calucloPago;
		return total;
	}
	
	public String generarFecha() throws ParseException {
		String pattern = "dd/MM/yyyy";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		String date = dtf.format(LocalDateTime.now());
		
		return date;
	}
	
	public Date generarFechaFin(int plazo) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, plazo + 1);
		Date fina = c.getTime();
		
		
		return fina;
	}

}

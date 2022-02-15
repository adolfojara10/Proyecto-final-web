package ec.edu.ups.ppw.ProyectoFinalBanco.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.TarjetaDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Tarjeta;

@Stateless
public class TarjetaON {
	
	@Inject
	private TarjetaDAO tarjetaDAO;
	
	public void guardarTarjeta(Tarjeta tarjeta) {

		Tarjeta t = tarjetaDAO.read(tarjeta.getId());
		if (t == null) {
			tarjetaDAO.insert(tarjeta);
		} else {
			tarjetaDAO.update(tarjeta);
		}
	}
	
	public int calcularID() {
		var lista = tarjetaDAO.getList();

		if (lista.size() == 0) {
			return 1;
		} else {
			return lista.size() + 1;
		}

	}
	
	public List<Tarjeta> getTarjetas(){
		return tarjetaDAO.getList();
	}
	
	public int generarNumero() {
		Random aleatorio = new Random();
		int numero = aleatorio.nextInt(1000000000 - 100000000 + 1) + 1000000000;
		return numero;
	}
	
	public int generarcodigoseguridad() {
		Random aleatorio = new Random();
		int numero = aleatorio.nextInt(1000 - 100 + 1) + 100;
		return numero;
	}
	
	public String generarFecha() throws ParseException {
		String pattern = "dd/MM/yyyy";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		String date = dtf.format(LocalDateTime.now());
		
		return date;
	}
	
	public String generarFechaFin() throws ParseException {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 5);
		Date actual = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		String date = sdf.format(actual);
		
		return date;
	}
	
	public Tarjeta buscarTarjeta(int id) {
		return tarjetaDAO.read(id);
	}

}

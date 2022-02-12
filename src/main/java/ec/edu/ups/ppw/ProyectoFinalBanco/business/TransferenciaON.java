package ec.edu.ups.ppw.ProyectoFinalBanco.business;

import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.TransferenciaDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Transferencia;

@Stateless
public class TransferenciaON {

	@Inject
	private TransferenciaDAO transferenciaDAO;

	public void guardarTransferencia(Transferencia t) {
		var c = transferenciaDAO.read(t.getId());
		if (c == null) {

			transferenciaDAO.insert(t);
		} else {
			transferenciaDAO.update(t);
		}
	}

	public String generarFecha() throws ParseException {
		String pattern = "dd/MM/yyyy";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		String date = dtf.format(LocalDateTime.now());
		
//		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//		Date fecha = sdf.parse(date);
		
		return date;
	}

	public int calcularID() {
		var lista = transferenciaDAO.getList();

		if (lista == null) {
			return 1;
		} else {
			return lista.size() + 1;
		}

	}
	
	public List<Transferencia> getTransferencias(){
		return transferenciaDAO.getList();
	}

}

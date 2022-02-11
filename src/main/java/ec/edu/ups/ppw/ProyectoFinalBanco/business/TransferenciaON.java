package ec.edu.ups.ppw.ProyectoFinalBanco.business;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.TransferenciaDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Cuenta;
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

	public String generarFecha() {
		String pattern = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
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

}

package ec.edu.ups.ppw.ProyectoFinalBanco.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.PrestamoDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Prestamo;

@Stateless
public class PrestamosON {
	@Inject
	private PrestamoDAO prestamoDAO;
	
	public void guardarTarjeta(Prestamo prestamo) {

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
	
	public List<Prestamo> getTarjetas(){
		return prestamoDAO.getList();
	}

}

package ec.edu.ups.ppw.ProyectoFinalBanco.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ppw.ProyectoFinalBanco.dao.ServiciosDAO;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Servicios;

@Stateless
public class ServiciosON {

	@Inject
	private ServiciosDAO serviciosDAO;

	public void guardarServicios(Servicios servicios) {

		Servicios t = serviciosDAO.read(servicios.getId());
		if (t == null) {
			serviciosDAO.insert(servicios);
		} else {
			serviciosDAO.update(servicios);
		}
	}

	public int calcularID() {
		var lista = serviciosDAO.getList();

		if (lista.size() == 0) {
			return 1;
		} else {
			return lista.size() + 1;
		}

	}

	public List<Servicios> getServicioss() {
		return serviciosDAO.getList();
	}

	public Servicios buscarServicio(int id) {
		return serviciosDAO.read(id);
	}
}

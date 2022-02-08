package ec.edu.ups.ppw.ProyectoFinalBanco.business;

import java.util.List;

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

}

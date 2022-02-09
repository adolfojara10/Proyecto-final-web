package ec.edu.ups.ppw.ProyectoFinalBanco.business;

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

}

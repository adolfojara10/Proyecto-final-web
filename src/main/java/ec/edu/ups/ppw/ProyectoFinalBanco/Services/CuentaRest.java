package ec.edu.ups.ppw.ProyectoFinalBanco.Services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.ppw.ProyectoFinalBanco.business.CuentaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.PersonaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.PrestamosON;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.*;

@Path("cuenta")
public class CuentaRest {

	@Inject
	private CuentaON cueON;

	@Inject
	private PersonaON perON;
	
	@Inject
	private PrestamosON presON;

	@POST
	@Path("monedaC")
	public double calcular(@QueryParam("moneda") String moneda, @QueryParam("cantidad") double cantidad) {
		System.out.println("emntro");
		double resultado = 0.0;
		if (moneda.equals("Yuan")) {

			resultado = (Math.round((cantidad * 6.36) * 100.0) / 100.0);

		} else if (moneda.equals("Euro")) {

			resultado = (Math.round((cantidad * 0.90) * 100.0) / 100.0);

		} else {

			resultado = (cantidad * 0.000028);

		}
		System.out.println(resultado + "jkfghljkadfhjklgsdf");
		return resultado;

	}

	@GET
	@Path("Saldo")
	@Produces(MediaType.APPLICATION_JSON)
	public double estado() {
		System.out.println("brrrrrrrrrrrrrrrrr" + cueON.getCuentaLogIn().getSaldo());
		return cueON.getCuentaLogIn().getSaldo();
	}

	@GET
	@Path("personas")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona getPersonas() {
		for (Persona persona: perON.getClientes()) {
			System.err.println(persona.getCedula());
			return persona;
		}
		return null;
		
	}
	
	@GET
	@Path("prestamos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Prestamo> getPrestamo() {
		List<Prestamo> prestamo = presON.getprestamo();
		return prestamo;
	}

}

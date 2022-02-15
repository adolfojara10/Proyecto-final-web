package ec.edu.ups.ppw.ProyectoFinalBanco.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.SafeHtml.Attribute;

import ec.edu.ups.ppw.ProyectoFinalBanco.business.CuentaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.PersonaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.PrestamosON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.ServiciosON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.TransferenciaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.*;

@Path("cuenta")
public class CuentaRest {

	@Inject
	private CuentaON cueON;

	@Inject
	private PersonaON perON;

	@Inject
	private PrestamosON presON;

	@Inject
	private TransferenciaON transON;

	@Inject
	private ServiciosON serviciosON;

	@POST
	@Path("monedaC")
	public double calcular(@QueryParam("moneda") String moneda, @QueryParam("cantidad") double cantidad) {
		System.out.println("emntro");
		double resultado = 0.0;
		if (moneda.equals("Yuan")) {

			resultado = (Math.round((cantidad * 6.36) * 100.0) / 100.0);

		} else if (moneda.equals("Euro")) {

			resultado = (Math.round((cantidad * 0.90) * 100.0) / 100.0);

		} else if (moneda.equals("Bitcoin")) {

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

	@POST
	@Path("rendimientoCuenta")
	public double calcular(@QueryParam("monto") double monto, @QueryParam("tiempo") double tiempo) {
		System.out.println("emntro");

		double rendimiento = (((monto * 3) / 100) * tiempo) / 12;

		rendimiento = Math.round(rendimiento * 100) / 100;

		System.out.println(rendimiento + "jkfghljkadfhjklgsdf");
		return rendimiento;
	}

	@GET
	@Path("CargarUs")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona realizarTransf() {
		Persona pers = new Persona();
		if (cueON.getPersonaLogIn() != null) {
			pers.setId(cueON.getPersonaLogIn().getId());
			pers.setCedula(cueON.getPersonaLogIn().getCedula());
			pers.setNombre(cueON.getPersonaLogIn().getNombre());
			pers.setApellido(cueON.getPersonaLogIn().getApellido());
			return pers;
		}
		return null;
	}

	@GET
	@Path("CargarUsSaldo")
	@Produces(MediaType.APPLICATION_JSON)
	public double getSaldo() {
		if (cueON.getPersonaLogIn() != null) {
			return cueON.getPersonaLogIn().getCuenta().getSaldo();
		}
		return 0;

	}

	@POST
	@Path("cedulaBusc")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Persona obtenerCedula(@QueryParam("cedulab") String cedula) {
		if (perON.buscarCedula(cedula) != null) {
			Persona pers = new Persona();
			pers.setId(perON.buscarCedula(cedula).getId());
			pers.setCedula(perON.buscarCedula(cedula).getCedula());
			pers.setNombre(perON.buscarCedula(cedula).getNombre());
			pers.setApellido(perON.buscarCedula(cedula).getApellido());
			return pers;
		}
		return null;
	}

	@POST
	@Path("transferir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String transferir(@QueryParam("cedulaR") String cedulaR, @QueryParam("montoT") double monto) {
		try {
			Persona receptor = perON.buscarCedula(cedulaR);
			System.out.println("receptor " + receptor);
			System.out.println("emisor " + cueON.getPersonaLogIn());
			if (cueON.getPersonaLogIn() != null || receptor != null) {

				Persona emisor = cueON.getPersonaLogIn();

				cueON.transferencia(emisor, receptor, monto);

				System.out.println(transON.generarFecha());

				return "Transaccion realizada exitosamente";

			}
			if (cueON.getCuentaLogIn() == null) {
				return "Error de session, vuelva inciar sesion";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Hubo un problema, intente otra vez";

	}

	// ---------------------------------------------------------------------------------------------
	@GET
	@Path("personas")
	@Produces(MediaType.APPLICATION_JSON)

	public List<Persona> getPersonas() {
		System.out.println("entro");
		List<Persona> listaper = new ArrayList<Persona>();

		try {
			System.out.println("personarest = " + perON.getClientes());
			for (Persona persona : perON.getClientes()) {
				Persona p = new Persona(persona.getId(), persona.getApellido(), persona.getCedula(), persona.getEmail(),
						persona.getFechaNacimiento(), persona.getNombre(), persona.getTipo());

				listaper.add(p);
			}
			System.out.println("per " + listaper);
			return listaper;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return null;

	}

	@GET
	@Path("prestamos")
	@Produces(MediaType.APPLICATION_JSON)

	public List<Prestamo> getPrestamo() {
		List<Prestamo> listapres = new ArrayList<Prestamo>();
		try {
			for (Prestamo prestamo : presON.getprestamo()) {

				if (prestamo.getEstado().equals("Pendiente")) {
					Prestamo pres = new Prestamo(prestamo.getId(), prestamo.getEstado(), prestamo.getFechaFin(),
							prestamo.getFechaInicio(), prestamo.getInteres(), prestamo.getMonto(),
							prestamo.getCoutasPagadas(), prestamo.getPagoMensual(), prestamo.getPlazo());

					listapres.add(pres);
				}
			}
			System.out.println("per " + listapres);
			return listapres;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return null;
	}

	@POST
	@Path("Aprovar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Prestamo> actualizar(@QueryParam("id") int id, @QueryParam("estado") String estado) {
		List<Prestamo> listapres = new ArrayList<Prestamo>();
		Prestamo p = presON.buscarPrestamo(id);

		if (estado.equals("Aprobado")) {
			System.out.println("Entra");
			p.setEstado(estado);
			Cuenta c = p.getPersona1().getCuenta();
			double valorEntregar = c.getSaldo() + p.getMonto();
			c.setSaldo(valorEntregar);
			cueON.guardarCuenta(c);
			presON.guardarPrestamo(p);
		}

		try {
			for (Prestamo prestamo : presON.getprestamo()) {
				if (prestamo.getEstado().equals("Pendiente")) {
					Prestamo pres = new Prestamo(prestamo.getId(), prestamo.getEstado(), prestamo.getFechaFin(),
							prestamo.getFechaInicio(), prestamo.getInteres(), prestamo.getMonto(),
							prestamo.getCoutasPagadas(), prestamo.getPagoMensual(), prestamo.getPlazo());
					listapres.add(pres);
				}
			}
			System.out.println("per " + listapres);
			return listapres;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return presON.getprestamo();
	}

	@POST
	@Path("pagoPrestamo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String pagarDeuda(@QueryParam("idPrestamo") int id, @QueryParam("tipoDeuda") String tipo) {

		if (tipo.equals("Prestamo")) {
			var prestamoPagar = presON.buscarPrestamo(id);

			System.out.println(prestamoPagar + "-----------------");

			if (prestamoPagar != null) {

				if (prestamoPagar.getPersona1().getCuenta().getSaldo() > prestamoPagar.getPagoMensual()
						&& prestamoPagar != null && prestamoPagar.getEstado().equals("Aprobado")) {

					cueON.pagoPrestamo(prestamoPagar);

					System.out.println("siuuu");

					return "SIUUU";

				} else {
					System.out.println("noooo");
					return "No";
				}

			}

			return "noo";
		} else if (tipo.equals("Servicio")) {

			var servicioPagar = serviciosON.buscarServicio(id);

			if (servicioPagar != null) {
				if (servicioPagar.getPersona2().getCuenta().getSaldo() > servicioPagar.getDeuda()
						&& servicioPagar != null && servicioPagar.getEstado().equals("Pagar")) {

					cueON.pagoServicio(servicioPagar);

					// servicioPagar.setEstado(false);

					// serviciosON.guardarServicios(servicioPagar);

					System.out.println("siuuu");

					return "SIUUU";

				} else {
					System.out.println("noooo");
					return "No";
				}
			}
		}

		return "nel";
	}

}

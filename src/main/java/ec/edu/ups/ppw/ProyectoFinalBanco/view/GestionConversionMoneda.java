package ec.edu.ups.ppw.ProyectoFinalBanco.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class GestionConversionMoneda {

	private String moneda;

	private double cantidad;

	private double resultado;

	public double calcular() {
		if (moneda.equals("Yuan")) {

			this.resultado = (Math.round((cantidad * 6.36) * 100.0) / 100.0);

		} else if (moneda.equals("Euro")) {

			this.resultado = (Math.round((cantidad * 0.90) * 100.0) / 100.0);

		} else {

			this.resultado = (cantidad * 0.000028);

		}
		System.out.println(this.resultado + "jkfghljkadfhjklgsdf");
		return this.resultado;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getResultado() {
		return resultado;
	}

	public void setResultado(double resultado) {
		this.resultado = resultado;
	}

}

package ec.edu.ups.ppw.ProyectoFinalBanco.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.ups.ppw.ProyectoFinalBanco.business.CuentaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.business.TransferenciaON;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Transferencia;

@Named
@RequestScoped
public class GestionTransaferenciaBean {

		@Inject
		private TransferenciaON transON;
		
		@Inject
		private CuentaON cueON;
		
		private Transferencia transferencia;
		private double monto;
		private List<Transferencia> listaTransferencias;
		
		@PostConstruct
		public void init() {
			System.out.println(cueON.getPersonaLogIn());
			System.out.println("la t > " + transON.getTransferencias());
			transferencia = new Transferencia();
			listaTransferencias = transON.getTransferencias();
		}
		
		public void GuardarDep() throws ParseException {
			cueON.deposito(monto, cueON.getPersonaLogIn());
		}
		
		public String fechaN() throws ParseException  {			
			return transON.generarFecha();
		}
		public Transferencia getTransferencia() {
			return transferencia;
		}

		public void setTransferencia(Transferencia transferencia) {
			this.transferencia = transferencia;
		}

		public double getMonto() {
			return monto;
		}

		public void setMonto(double monto) {
			this.monto = monto;
		}

		public List<Transferencia> getListaTransferencias() {
			return listaTransferencias;
		}

		public void setListaTransferencias(List<Transferencia> listaTransferencias) {
			this.listaTransferencias = listaTransferencias;
		}

		

}

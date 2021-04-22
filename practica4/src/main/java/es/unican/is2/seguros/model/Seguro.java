package es.unican.is2.seguros.model;

import java.time.LocalDate;

public class Seguro {
	private LocalDate fechaUltimoSiniestro;
	private int potenciaCV;
	
	private Cliente cliente;
	private Cobertura cobertura;
	private double precio;
	
	public Seguro(int potencia, Cliente cliente, Cobertura cobertura) {
		this.cliente=cliente;
		this.cobertura=cobertura;
		this.potenciaCV=potencia;
		
	}
	
	public double precio() {return precio;}
	
	public String toString() {
		return("Seguro a nombre de: "+cliente.toString()+" con potencia: "+ potenciaCV + "cobertura: "+ cobertura.toString());
	}
	
	
	//setFechaUltimoSiniesto TODO
}

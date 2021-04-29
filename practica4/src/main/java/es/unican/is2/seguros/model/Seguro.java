package es.unican.is2.seguros.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
	

	public void setPrecio() {
		double base=cobertura.getCosteBase();
		double incPotencia;
		if(potenciaCV<90) {
			incPotencia=0;
		}else {
			if(90<=potenciaCV&&potenciaCV<=110) {
				incPotencia=base*0.05;
			}else {
				incPotencia=base*0.2;
			}
		}

		
		double incSiniestros=0;
		if(fechaUltimoSiniestro!=null) {
			Long since = ChronoUnit.DAYS.between(fechaUltimoSiniestro,LocalDate.now());
			double anhos=since/365;
			if(anhos<=1) {
				incSiniestros=200;
			}else {
				if(1<anhos&&anhos<=3) {
					incSiniestros=50;
				}
			}
		}
		
		if(cliente.getMinusvalia()) {
			precio=(base+incPotencia+incSiniestros)*0.75;
		}else {
			precio=base+incPotencia+incSiniestros;
		}
		
	}
	
	public double precio() {
		return precio;
	}
	
	
	public String toString() {
		return("Seguro a nombre de: "+cliente.toString()+" con potencia: "+ potenciaCV + "cobertura: "+ cobertura.toString()+ " con precio:" + precio);
	}
	
	
	public void setFechaUltimoSiniesto(LocalDate fecha) {
		fechaUltimoSiniestro=fecha;
		setPrecio();
	}
	
	
}

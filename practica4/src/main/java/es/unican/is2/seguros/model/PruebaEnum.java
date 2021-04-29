package es.unican.is2.seguros.model;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class PruebaEnum {
	 public static void main (String[ ] Args) {

		 	LocalDate fechaUltimoSiniestro=LocalDate.parse("11/04/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	       Cobertura cobertura1 = Cobertura.valueOf("TERCEROS");

	       System.out.println ("La Cobertura elegida por el usuario es " + cobertura1.toString() + "\n " +
			cobertura1.getCobertura() + " y con un precio base de " + cobertura1.getCosteBase() + " euros");
	       
	       
	       System.out.println(fechaUltimoSiniestro);
	       System.out.println(LocalDate.now());
	       
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

	 }      

}

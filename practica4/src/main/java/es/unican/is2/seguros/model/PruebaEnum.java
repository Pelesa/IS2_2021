package es.unican.is2.seguros.model;

public class PruebaEnum {
	 public static void main (String[ ] Args) {

		 

	       Cobertura cobertura1 = Cobertura.valueOf("TERCEROS");

	       System.out.println ("La Cobertura elegida por el usuario es " + cobertura1.toString() + "\n " +

			cobertura1.getCobertura() + " y con un precio base de " + cobertura1.getCosteBase() + " euros");

	 

	 }      

}

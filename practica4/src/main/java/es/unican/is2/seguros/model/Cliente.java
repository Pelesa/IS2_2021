package es.unican.is2.seguros.model;

public class Cliente {

	private String nombre;
	private String dni;
	private boolean minusvalia;
	
	public Cliente(String nombre, String dni, boolean minusvalia) {
		this.nombre=nombre;
		this.dni=dni;
		this.minusvalia=minusvalia;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDNI() {
		return dni;
	}
	
	public boolean getMinusvalia() {
		return minusvalia;
	}
	
	
	
	public String toString() {
		return "nombre: "+ nombre +" DNI: "+ dni+ " Minusvalia: "+minusvalia;
	}
	
	
}

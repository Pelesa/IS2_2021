package es.unican.is2.practica5;

public abstract class Cuenta {
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) { //WMC +1
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() { //WMC +1
		return numCuenta;
	}
	
	
}

package es.unican.is2.practica5;

public abstract class Cuenta {
	
	private String mNumCuenta;
	
	public Cuenta(String numCuenta) { //WMC +1
		this.mNumCuenta = numCuenta;
	}
	
	public String getNumCuenta() { //WMC +1
		return mNumCuenta;
	}
	
}

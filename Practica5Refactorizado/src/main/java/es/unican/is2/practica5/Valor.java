package es.unican.is2.practica5;

public class Valor {
	
	private String mEntidad;	
	private int mNumValores;
	private double mCotizacionActual;
	
	public Valor(String entidad, int numValores, double cotizacionActual) {//WMC +1
		super();
		this.mEntidad = entidad;
		this.mNumValores = numValores;
		this.mCotizacionActual = cotizacionActual;
	}
	
	public int getNumValores() {//WMC +1
		return mNumValores;
	}

	public void setNumValores(int numValores) {//WMC +1
		this.mNumValores = numValores;
	}

	public double getCotizacionActual() {//WMC +1
		return mCotizacionActual;
	}

	public void setCotizacionActual(double cotizacionActual) {//WMC +1
		this.mCotizacionActual = cotizacionActual;
	}

	public String getEntidad() {//WMC +1
		return mEntidad;
	}


}
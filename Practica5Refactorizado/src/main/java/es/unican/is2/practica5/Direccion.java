package es.unican.is2.practica5;

public class Direccion {
	private String mCalle;
	private String mZip;
	private String mLocalidad;
	
	public Direccion(String calle, String zip, String localidad) {
		this.mCalle = calle;
		this.mZip = zip;
		this.mLocalidad = localidad;
	}
	
	public String getCalle() {
		return mCalle;
	}

	public void setCalle(String calle) {
		this.mCalle = calle;
	}

	public String getZip() {
		return mZip;
	}

	public void setZip(String zip) {
		this.mZip = zip;
	}

	public String getLocalidad() {
		return mLocalidad;
	}

	public void setLocalidad(String localidad) {
		this.mLocalidad = localidad;
	}
	
}

package es.unican.is2.practica5;

public class Direccion {
	private String mCalle;
	private String mZip;
	private String mLocalidad;
	
	public Direccion(String calle, String zip, String localidad) { //WCM+1
		this.mCalle = calle;
		this.mZip = zip;
		this.mLocalidad = localidad;
	}
	
	public String getCalle() { //WCM+1
		return mCalle;
	}

	public void setCalle(String calle) { //WCM+1
		this.mCalle = calle;
	}

	public String getZip() { //WCM+1
		return mZip;
	}

	public void setZip(String zip) { //WCM+1
		this.mZip = zip;
	}

	public String getLocalidad() { //WCM+1
		return mLocalidad;
	}

	public void setLocalidad(String localidad) { //WCM+1
		this.mLocalidad = localidad;
	}
	
}

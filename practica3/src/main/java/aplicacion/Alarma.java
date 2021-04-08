package aplicacion;

import java.util.Date;

public class Alarma {
	private String id;
	private Date hora;
	public Alarma(String id, Date hora) {
		this.id=id;
		this.hora=hora;
	}
	
	public String id() { return id; }
	public Date hora() { return hora; }
	
	@Override
	public String toString() {
		return id + ": " + hora.toString(); 
	}
}
package aplicacion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Alarma implements Comparable<Alarma>{
	private String id;
	private Date hora;

	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public Alarma(String id, Date hora) {
		this.id=id;
		this.hora=hora;
	}

	public String id() { 
		return id; 
	}
	public Date hora() { 
		return hora; 
	}

	@Override
	public String toString() {
		return id + ": " + formatter.format(hora); 
	}

	public int compareTo(Alarma a) {
		return this.hora().compareTo(a.hora());
	}
}
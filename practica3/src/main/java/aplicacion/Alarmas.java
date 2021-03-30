package aplicacion;

import Estados.*;

public class Alarmas {
	
	/*Atributos*/
	AlarmasState state;
	Queue<Alarma> alarmasActivas = new Queue<Alarma>();
	ArrayList<Alarma> alarmasDesactivadas = new ArrayList<Alarma>();

	/*Constructor */
	public Alarmas(AlarmasState state) {
		this.state = state;
	};

	/*Métodos*/
	public Alarma alarma(String id) {
		for (Alarma a : alarmasActivas) {
			if(a.toString().equals(id)) {
				return a;
			}
		}
		for(Alarma a : alarmasDesactivadas) {
			if(a.toString().equals(id)) {
				return a;
			}
		}
		return null;
	}

	public boolean anhadeAlarma(Alarma a) {
		alarmasActivas.add(a);

		return false;
	}

	public boolean eliminaAlarma(Alarma a) {
		return alarmasActivadas.remove(a) || alarmasDesactivadas.remove(a); //false si no se encontraba en ninguna de las listas
	}

	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek();
	}

	Alarma activaAlarma(Alarma a) {
		alarmasDesactivadas.remove(a);
		alarmasActivas.add(a);
	}

	public void desactivaAlarma(Alarma a) {
		alarmasActivadas.remove(a);
		alarmasDesactivadas.add(a);
	}

	public Alarma[] alarmasActivadas(){
		return alarmasActivadas.toArray();
	}

	public Alarma[] alarmasDesactivadas(){
		return alarmasDesactivadas.toArray();
	}
	
	public Alarma activarMelodia() {
		Alarma a = alarmasActivadas.poll();

		return a;
	}

	public Alarma desactivarMelodia() {
		
	}

	public void NuevaAlarma(Alarma a) {
		state.NuevaAlarma(this, a);
	}

	public void Apagar() {
		state.Apagar(this);
	}

	public void AlarmaOff(Alarma a) {
		state.AlarmaOff(this, a);
	}

	public void AlarmaOn(Alarma a) {
		state.AlarmaOn(this);
	}
	
	public void BorraAlarma(Alarma a) {
		state.BorraAlarma(this, a);
	}

	public void setState(AlarmasState state) {
		this.state = state;
	}
}

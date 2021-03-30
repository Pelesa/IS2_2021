package aplicacion;

import Estados.*;

public class Alarmas {
	
	/*Atributos*/
	AlarmasState state; 
	Queue<Alarma> alarmasActivas = new Queue<Alarma>();
	ArrayList<Alarma> alarmasDesaactivadas = new ArrayList<Alarma>();

	/*Constructor */
	public Alarmas(AlarmasState state) {
		this.state = state;
	};

	/*Métodos*/
	public boolean anhadeAlarma(Alarma a) {
		alarmasActivas.add(a);
		return false;
	}
	public boolean eliminaAlarma() {
		return false;
	}
	public Alarma alarmaMasProxima() {
		return null;
	}

	Alarma activaAlarma(Alarma a) {
		alarmasDesactivadas.remove(a);
		alarmasActivas.add(a);
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

	public void NuevaAlarma() {
		state.entryAction();
		state.NuevaAlarma();
	}

	public void Apagar() {
		state.entryAction();
		state.Apagar();
	}

	public void AlarmaOff() {
		state.entryAction();
		state.AlarmaOff();
	}

	public void AlarmaOn() {
		state.entryAction();
		state.AlarmaOn();
	}
	
	public void BorraAlarma() {
		state.entryAction();
		state.BorraAlarma();
	}

	public void setState(AlarmasState state) {
		this.state = state;
	}
}

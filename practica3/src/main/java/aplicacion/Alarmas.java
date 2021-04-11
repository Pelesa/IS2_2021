package aplicacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import estados.*;

public class Alarmas {

	/*Atributos*/
	AlarmasState state;
	PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	ArrayList<Alarma> alarmasDesactivadas = new ArrayList<Alarma>();

	public static final  int INTERVALO_SONAR = 3000; //3000ms = 3segundos sonando la alarma

	/*Constructor */
	public Alarmas(AlarmasState state) {
		this.state = state;
	};

	/* Metodos*/
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
		return alarmasActivas.add(a);
	}

	public boolean eliminaAlarma(Alarma a) {
		return alarmasActivas.remove(a) || alarmasDesactivadas.remove(a); //false si no se encontraba en ninguna de las listas
	}

	public Alarma alarmaMasProxima() {
		/*
		 * Si se usa para que suene se borra en ese momento, por ello usamos peek, 
		 * para poder ver la cabeza de la cola sin eliminar el elemento.
		 * */
		return alarmasActivas.peek(); 
	}

	public Alarma activaAlarma(Alarma a) {
		alarmasDesactivadas.remove(a);
		alarmasActivas.add(a);
		return a;
	}

	public void desactivaAlarma(Alarma a) {
		alarmasActivas.remove(a);
		alarmasDesactivadas.add(a);
	}

	public Collection<Alarma> alarmasActivadas(){
		return alarmasActivas;
	}

	public Collection<Alarma> alarmasDesactivadas(){
		return alarmasDesactivadas;
	}

	public Alarma activarMelodia() {
		Alarma a = alarmasActivas.poll();

		return a;
	}

	public Alarma desactivarMelodia() {
		return null;
	}

	public void NuevaAlarma(Alarma a) {
		if (!(state instanceof Sonando)) {
			state.NuevaAlarma(this, a);
		}
	}

	public void Apagar() {
		state.Apagar(this);
	}

	public void AlarmaOff(Alarma a) {
		if (!(state instanceof Sonando)) {
			state.AlarmaOff(this, a);
		}
	}

	public void AlarmaOn(Alarma a) {
		if (!(state instanceof Sonando)) {
			state.AlarmaOn(this, a);
		}
	}

	public void BorraAlarma(Alarma a) {
		if (!(state instanceof Sonando)) {
			state.BorraAlarma(this, a);
		}
	}

	public void setState(AlarmasState state) {
		this.state = state;
	}
}

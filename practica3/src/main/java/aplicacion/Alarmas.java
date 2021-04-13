package aplicacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;


import estados.*;

public class Alarmas {

	/* Atributos */
	AlarmasState state;
	PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	ArrayList<Alarma> alarmasDesactivadas = new ArrayList<Alarma>();

	private static final  int INTERVALO_SONAR = 3*1000; //3 segundos sonando la alarma

	/* Constructor */
	public Alarmas(AlarmasState state) {
		this.state = state;
	};

	/* Metodos */
	
	/**
	 * Método que busca una alarma en el sistema
	 * @param id de la alarma buscada
	 * @return la alarma si esta o null si no.
	 */
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

	/**
	 * Anhade una alarma al sistema, por defecto esta activa
	 * @param a alarma a anhadir
	 * @return true si no ha habido fallo
	 */
	public boolean anhadeAlarma(Alarma a) {
		return alarmasActivas.add(a);
	}

	/**
	 * Anhade una alarma al sistema, por defecto esta activa
	 * @param a alarma a anhadir
	 * @return true si no ha habido fallo
	 */
	public boolean eliminaAlarma(Alarma a) {
		return alarmasActivas.remove(a) || alarmasDesactivadas.remove(a); //false si no se encontraba en ninguna de las listas
	}

	/**
	 * Devuelve la cabeza de la lista de alarmas activas
	 * @return la cabeza de la lista de alarmas activas o null si no hay ninguna alarma
	 */
	public Alarma alarmaMasProxima() {
		/*
		 * Si se usa para que suene se borra en ese momento, por ello usamos peek, 
		 * para poder ver la cabeza de la cola sin eliminar el elemento.
		 * */
		return alarmasActivas.peek(); 
	}

	/**
	 * Activa una alarma
	 * @param a alarma a ser activada
	 * @return la alarma en cuestion
	 */
	public Alarma activaAlarma(Alarma a) {
		alarmasDesactivadas.remove(a);
		alarmasActivas.add(a);
		return a;
	}
	
	/**
	 * Desactiva una alarma
	 * @param a alarma a ser desacctivada
	 * @return la alarma en cuestion
	 */
	public void desactivaAlarma(Alarma a) {
		alarmasActivas.remove(a);
		alarmasDesactivadas.add(a);
		if(alarmasActivas.size() == 0) {
			setState(AlarmasState.AlarmasState()); //pasa a desporgramado
		}
	}

	/**
	 * Retorna las alarma activas en el sistema
	 * @return Colletion con las alarmas activas
	 */
	public Collection<Alarma> alarmasActivadas(){
		return alarmasActivas;
	}

	/**
	 * Retorna las alarma desactivadas en el sistema
	 * @return Colletion con las alarmas desactivadas
	 */
	public Collection<Alarma> alarmasDesactivadas(){
		return alarmasDesactivadas;
	}

	/**
	 * Implementa el sonido de la alarma
	 */
	public void activarMelodia() {
		System.out.println("Sonando alarma: " + alarmaMasProxima().toString());
		eliminaAlarma(alarmaMasProxima());
	}

	/**
	 * Implementa la finalizacion del sonido de la alarma
	 */
	public void desactivarMelodia() {
		System.out.println("Alarma desactivada");
	}
	
	public int intervaloSonar() {
		return INTERVALO_SONAR;
	} 

	/**
	 * Señal NuevaAlarma
	 * @param a alarma a anhadir
	 */
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
	
	public void sonando() {
		setState(AlarmasState.sonando());
		state.entryAction(this);
	}
}

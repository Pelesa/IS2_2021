package estados;

import aplicacion.*;

public abstract class AlarmasState {	
	/* Atributos */
	/* Instancias */
	static AlarmasState programado = new Programado();
	static AlarmasState desprogramado = new Desprogramado();
	static AlarmasState sonando = new Sonando();

	public static AlarmasState AlarmasState()
	{
		return desprogramado; //Estado inicial
	};

	/*Métodos*/
	public void NuevaAlarma(Alarmas context, Alarma a) {};	

	public void Apagar(Alarmas context) {};

	public void AlarmaOff(Alarmas context, Alarma a) {};

	public void AlarmaOn(Alarmas context, Alarma a) {};
	
	public void BorraAlarma(Alarmas context, Alarma a) {};
	
	public void entryAction(Alarmas context) {};
	
	public void doAction(Alarmas context) {};
	
	public void exitAction(Alarmas context) {};
} 
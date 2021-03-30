package Estados;

import Aplicacion.*;

public abstract Class AlarmasState
{	
	/* Atributos */
	/* Instancias */
	AlarmasState programado = new Programdo();
	AlarmasState desprogramado = new Desprogramado();
	AlarmasState sonando = new Sonando();

	/* Constructor que retorna la clase incial */
	public AlarmasState AlarmasState()
	{
		return desprogramado; //Estado inicial
	};

	/*Métodos*/
	public void NuevaAlarma(AlarmasState context, Alarma a){};	

	public void Apagar(AlarmasState context){};

	public void AlarmaOff(AlarmasState context, Alarma a){};

	public void AlarmaOn(AlarmasState context, Alarma a){};
	
	public void BorraAlarma(AlarmasState context, Alarma a){};
} 
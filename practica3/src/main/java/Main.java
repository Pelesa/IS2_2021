import java.util.ArrayList;

import Aplicacion.*;
import Estados.*;

public class Main {
	
	public static void main(String[] args) {
		Alarmas context = new Alarmas(AlarmasState.AlarmasState());

		//ArrayList<Alarma> as = new ArrayList<Alarma>();

		Alarma a = new Alarma ("A1", null);
		Alarma b = new Alarma ("A2", null);
		context.NuevaAlarma(a);
		context.AlarmaOff(a);
		context.NuevaAlarma(b);
		System.out.println(context.alarmasDesactivadas().toString());
		System.out.println(context.alarmasActivadas().toString());
	}
}

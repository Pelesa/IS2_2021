package estados;
import aplicacion.*;
public class Programado extends AlarmasState {

	/* Metodos */
	public void NuevaAlarma(Alarmas context, Alarma a)
	{
		this.exitAction(context);
		context.setState(programado);

		context.anhadeAlarma(a);

		programado.entryAction(context);
		programado.doAction(context);
	};	

	public void AlarmaOff(Alarmas context, Alarma a)
	{
		this.exitAction(context);
		context.setState(programado);

		context.desactivaAlarma(a);

		programado.entryAction(context);
		programado.doAction(context);
	};

	public void AlarmaOn(Alarmas context, Alarma a)
	{
		this.exitAction(context);
		context.setState(programado);

		context.activaAlarma(a);

		programado.entryAction(context);
		programado.doAction(context);
	};
	
	public void BorraAlarma(Alarmas context, Alarma a)
	{
		this.exitAction(context);
		context.setState(programado);

		context.eliminaAlarma(a);

		programado.entryAction(context);
		programado.doAction(context);
	};
}
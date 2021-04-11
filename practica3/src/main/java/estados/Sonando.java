package estados;
import aplicacion.*;
public class Sonando extends AlarmasState {

	/* Metodos */
	public void Apagar(Alarmas context)
	{
		this.exitAction(context);
		context.setState(programado);

		programado.entryAction(context);
		programado.doAction(context);
	};

	public void entryAction(Alarmas context)
	{
		context.activarMelodia();
	}

	public void exitAction(Alarmas context)
	{
		context.desactivarMelodia();
	}

}
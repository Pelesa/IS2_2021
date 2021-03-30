package aplicacion;

public Class Sonando extends AlarmasState {

	/*Métodos*/
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
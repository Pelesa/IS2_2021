package es.unican.is2.seguros.model;

public enum Cobertura {
	TERCEROS("Terceros",400),
	TODO_RIESGO("Todo Riesgo",1000),
	TERCEROS_LUNAS("Terceros lunas",600);
	
	private final String tipoCobertura;
	private final int costeBase;
	
	Cobertura(String cobertura, int coste){
		this.tipoCobertura=cobertura;
		this.costeBase=coste;
	}
	
	public String getCobertura() {return tipoCobertura;}
	public int getCosteBase() {return costeBase;}
	
	
	
	//Cobertura.valueOf
}


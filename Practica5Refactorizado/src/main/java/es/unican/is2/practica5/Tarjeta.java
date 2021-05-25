package es.unican.is2.practica5;

public abstract class Tarjeta {
	protected String mNumero;		
	protected String mTitular;
	protected CuentaAhorro mCuentaAsociada;

	protected Tarjeta(String numero, String titular, CuentaAhorro cuenta) { //WMC +1
		mNumero = numero;
		mTitular = titular;
		mCuentaAsociada = cuenta;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x) 
			throws saldoInsuficienteException, datoErroneoException;
	
}
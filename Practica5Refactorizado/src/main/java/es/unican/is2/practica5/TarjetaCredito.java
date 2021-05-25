package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class TarjetaCredito extends Tarjeta {
	
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	
	
	public TarjetaCredito(String numero, String titular, CuentaAhorro c, double credito) { //WMC +1
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param cantidad Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double cantidad) throws saldoInsuficienteException, datoErroneoException {//WMC +1
		if (cantidad<0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");

		cantidad += cantidad * 0.05; // A�adimos una comisi�n de un 5%
		if (getGastosAcumulados() + cantidad > mCredito) //WMC +1 CCog +1
			throw new saldoInsuficienteException("Cr�dito insuficiente");
		
		creaMovimiento("Retirada en cajero autom�tico", cantidad);
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double cantidad) throws saldoInsuficienteException, datoErroneoException { //WMC +1
		if (getGastosAcumulados() + cantidad > mCredito) //WMC +1  CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");

		creaMovimiento("Compra a cr�dito en: " + datos, cantidad);
	}
	
	private void creaMovimiento(String concepto, double cantidad) throws datoErroneoException{
		if (cantidad<0)  //WMC +1 CCog +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto(concepto);
		movimiento.setImporte(-cantidad);
		mMovimientosMensuales.add(movimiento);
	}	
	
    public double getGastosAcumulados() { //WMC +1
		return -calculaImporte();
	}
	
	
	public LocalDate getCaducidadCredito() { //WMC +1
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	/**
	 * M�todo que se invoca autom�ticamente el d�a 1 de cada mes
	 */
	public void liquidar() { //WMC +1
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setFecha(now);
		liq.setConcepto("Liquidaci�n de operaciones tarjeta cr�dito");
		double importe = calculaImporte();
		liq.setImporte(importe);
	
		if (importe != 0) //WMC +1 CCog +1
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}
	
	private double calculaImporte() {
		double importe = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { //WMC +1 CCog +1
			Movimiento m = mMovimientosMensuales.get(i);
			importe += m.getImporte();
		}
		return importe;
	}
	

	public List<Movimiento> getMovimientosUltimoMes() { //WMC +1
		return mMovimientosMensuales;
	}
	
	public Cuenta getCuentaAsociada() { //WMC +1
		return mCuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() { //WMC +1
		return mhistoricoMovimientos;
	}

}
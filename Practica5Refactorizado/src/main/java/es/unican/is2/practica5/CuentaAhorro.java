package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> mMovimientos;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	private final static double LIMITE_DEBITO_INCIAL = 1000;
	private double mLimiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) { //WMC +1
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
		mLimiteDebito = LIMITE_DEBITO_INCIAL;
	}

	public void ingresar(double cantidad) throws datoErroneoException { //WMC +1
		if (cantidad <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		creaMovimiento("Ingreso en efectivo", cantidad);
	}

	public void retirar(double cantidad) throws saldoInsuficienteException, datoErroneoException { //WMC +1
		if (cantidad <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		if (getSaldo() < cantidad) //WMC +1 CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		creaMovimiento("Retirada de efectivo", -cantidad);
	}

	public void ingresar(String concepto, double cantidad) throws datoErroneoException { //WMC +1
		if (cantidad <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		creaMovimiento(concepto, cantidad);
	}
	
	public void retirar(String concepto, double cantidad) throws saldoInsuficienteException, datoErroneoException { //WMC +1
		if (cantidad <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		if (getSaldo() < cantidad) //WMC +1 CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		creaMovimiento(concepto, -cantidad);
	}
	
	private void creaMovimiento(String concepto, double cantidad) throws datoErroneoException { //WMC +1
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto(concepto);
		movimiento.setImporte(cantidad);
		this.mMovimientos.add(movimiento);
	}

	public double getSaldo() { //WMC +1
		double importe = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) { //WMC +1 CCog +1
			Movimiento m = (Movimiento) mMovimientos.get(i);
			importe += m.getImporte();
		}
		return importe;
	}

	public void addMovimiento(Movimiento m) { //WMC +1
		mMovimientos.add(m);
	}

	public List<Movimiento> getMovimientos() { //WMC +1
		return mMovimientos;
	}

	public LocalDate getCaducidadDebito() { //WMC +1
		return this.mFechaDeCaducidadTarjetaDebito;
	}

	public LocalDate getCaducidadCredito() { //WMC +1
		return this.mFechaDeCaducidadTarjetaCredito;
	}

	public double getLimiteDebito() { //WMC +1
		return mLimiteDebito;
	}

}
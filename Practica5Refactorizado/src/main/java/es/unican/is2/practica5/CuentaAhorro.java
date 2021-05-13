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
	private double limiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) { //WMC +1
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
		limiteDebito = LIMITE_DEBITO_INCIAL;
	}

	public void ingresar(double x) throws datoErroneoException { //WMC +1
		if (x <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		creaMovimiento("Ingreso en efectivo", x);
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { //WMC +1
		if (x <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		if (getSaldo() < x) //WMC +1 CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		creaMovimiento("Retirada de efectivo", -x);
	}

	public void ingresar(String concepto, double x) throws datoErroneoException { //WMC +1
		if (x <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		creaMovimiento(concepto, x);
	}
	

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException { //WMC +1
		if (x <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		if (getSaldo() < x) //WMC +1 CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		creaMovimiento(concepto, -x);
	}
	
	private void creaMovimiento(String concepto, double x) throws datoErroneoException { //WMC +1
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto(concepto);
		m.setImporte(x);
		this.mMovimientos.add(m);
	}

	public double getSaldo() { //WMC +1
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) { //WMC +1 CCog +1
			Movimiento m = (Movimiento) mMovimientos.get(i);
			r += m.getImporte();
		}
		return r;
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
		return limiteDebito;
	}

}
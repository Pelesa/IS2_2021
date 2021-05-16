package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> mMovimientos;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) { //WMC +1
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	public void ingresar(double x) throws datoErroneoException { //WMC +1
		if (x <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Ingreso en efectivo");
		m.setI(x);
		this.mMovimientos.add(m);
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { //WMC +1
		if (x <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < x) //WMC +1 CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada de efectivo");
		m.setI(-x);
		this.mMovimientos.add(m);
	}

	public void ingresar(String concepto, double x) throws datoErroneoException { //WMC +1
		if (x <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(x);
		this.mMovimientos.add(m);
	}

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException { //WMC +1
		if (getSaldo() < x) //WMC +1 CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0) //WMC +1 CCog +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(-x);
		this.mMovimientos.add(m);
	}

	public double getSaldo() { //WMC +1
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) { //WMC +1 CCog +1
			Movimiento m = (Movimiento) mMovimientos.get(i);
			r += m.getI();
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
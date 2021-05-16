package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	private String mNombre;
	private String mTelefono;
	private String mDni;
	private Direccion mDireccion;
	
    private List<Cuenta> mCuentas = new LinkedList<Cuenta>();

    
 	public Cliente(String titular, String telefono, String dni) { //WMC +1  
		this.mNombre = titular;
		this.mTelefono = telefono;
		this.mDni = dni;
	}
	
	public double getSaldoTotal() {//WMC +1
		double total = 0.0;
		for (Cuenta c: mCuentas) {  //WMC +1 CCog +1
			if (c instanceof CuentaAhorro) { //WMC +1 CCog +2 
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  { //WMC +1 CCog +3
				for (Valor v: ((CuentaValores) c).getValores()) { //WMC +1 CCog +4
					total += v.getCotizacionActual()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	public void anhadeCuenta(Cuenta c) { //WMC +1
		mCuentas.add(c);
	}

	public String getNombre() { //WMC +1
		return mNombre;
	}

	public void setNombre(String nombre) { //WMC +1
		this.mNombre = nombre;
	}

	public String getTelefono() { //WMC +1
		return mTelefono;
	}

	public void setTelefono(String telefono) { //WMC +1
		this.mTelefono = telefono;
	}

	public String getDni() { //WMC +1
		return mDni;
	}

	public void setDni(String dni) { //WMC +1
		this.mDni = dni;
	}

	public Direccion getDireccion() { //WMC +1
		return mDireccion;
	}

	public void setDireccion(Direccion direccion) { //WMC +1
		this.mDireccion = direccion;
	}
	
}
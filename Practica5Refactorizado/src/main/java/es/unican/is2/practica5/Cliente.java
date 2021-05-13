package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	private String nombre;
	private String telefono;
	private String dni;
	private Direccion direccion;
	
    private List<Cuenta> cuentas = new LinkedList<Cuenta>();

    
 	public Cliente(String titular, String telefono, String dni) { //WMC +1  
		this.nombre = titular;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public double getSaldoTotal() {//WMC +1
		double total = 0.0;
		for (Cuenta c: cuentas) {  //WMC +1 CCog +1
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
		cuentas.add(c);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	
	
}
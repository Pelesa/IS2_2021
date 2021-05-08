import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;
import es.unican.is2.seguros.model.*;



public class SegurosTest{

	@Test
    public void constructorTest() {
        Cliente c = new Cliente("C1", "DNI1", true);
        int potencia = 100;
        Seguro seguro = new Seguro(potencia, c, Cobertura.TODO_RIESGO);
        assertTrue(seguro.getPotenciaCV() == potencia);
        assertTrue(seguro.getCliente() == c);
        assertTrue(seguro.getCobertura() == Cobertura.TODO_RIESGO);
    }
	
	@Test
	public void precioTest(){ 
		Cliente c_con = new Cliente("A", "DNI", true); //minusvalia = true
		Cliente c_sin = new Cliente("B", "DNI", false); //minusvalia = false
		Seguro s;
		LocalDate fechaUltimoSiniestro;
		try {
			s = new Seguro(1, c_sin, Cobertura.TERCEROS_LUNAS);
			fechaUltimoSiniestro=LocalDate.parse("01/01/2005", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			s.setFechaUltimoSiniesto(fechaUltimoSiniestro); //Valor muy superior para indicar que no ha tenido un siniesto
			s.setPrecio();
			assertTrue(s.precio() == 600); 
			
			s = new Seguro(50, c_sin, Cobertura.TERCEROS_LUNAS);
			fechaUltimoSiniestro=LocalDate.parse("01/01/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			s.setFechaUltimoSiniesto(fechaUltimoSiniestro);
			s.setPrecio();
			assertTrue(s.precio() == 800);
			
			s = new Seguro(89, c_con, Cobertura.TERCEROS);
			fechaUltimoSiniestro=LocalDate.parse("01/01/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			s.setFechaUltimoSiniesto(fechaUltimoSiniestro);
			s.setPrecio();
			assertTrue(s.precio() == 450.0);
			
			s = new Seguro(90, c_con, Cobertura.TERCEROS_LUNAS);
			fechaUltimoSiniestro=LocalDate.parse("01/01/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			s.setFechaUltimoSiniesto(fechaUltimoSiniestro);
			s.setPrecio();
			assertTrue(s.precio() == 622.5);
			
			s = new Seguro(110, c_sin, Cobertura.TODO_RIESGO);
			fechaUltimoSiniestro=LocalDate.parse("01/01/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			s.setFechaUltimoSiniesto(fechaUltimoSiniestro);
			s.setPrecio();
			assertTrue(s.precio() == 1100);
			
			s = new Seguro(110, c_con, Cobertura.TERCEROS);
			fechaUltimoSiniestro=LocalDate.parse("01/01/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			s.setFechaUltimoSiniesto(fechaUltimoSiniestro);
			s.setPrecio();
			assertTrue(s.precio() == 465);
			
			s = new Seguro(111, c_con, Cobertura.TERCEROS);
			fechaUltimoSiniestro=LocalDate.parse("01/01/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			s.setFechaUltimoSiniesto(fechaUltimoSiniestro);
			s.setPrecio();
			assertTrue(s.precio() == 510);
			
			s = new Seguro(120, c_sin, Cobertura.TODO_RIESGO);
			fechaUltimoSiniestro=LocalDate.parse("01/01/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			s.setFechaUltimoSiniesto(fechaUltimoSiniestro);
			s.setPrecio();
			assertTrue(s.precio() == 1250);
		} catch (DatoIncorrectoException e) {
			Assert.fail("Dato incorrecto");
		}

		
		//Excepciones, TODO: Preguntar a juan en tests caja negra
	}
}



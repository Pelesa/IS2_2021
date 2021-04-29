import static org.junit.Assert.*;

import org.junit.Test;
import es.unican.is2.seguros.model.*;

public class SegurosTestCajaNegra {

	@Test
	public void constructorTest() {
		//Seguro(potencia, cliente, cobertura)
		Cliente c = new Cliente("C1", "DNI1", true);
	
		int potencia = 100;
		assertNull(new Seguro(potencia,null, Cobertura.TERCEROS_LUNAS));
		assertNull(new Seguro(-1,c, Cobertura.TERCEROS_LUNAS));
		assertNull(new Seguro(0,null, Cobertura.TODO_RIESGO));
		assertNull(new Seguro(potencia,c, Cobertura.null));
		assertNotNull(new Seguro(potencia, c, Cobertura.TODO_RIESGO));
	}
	
	@Test
	public void precioTest() { 
		Cliente c_con = new Cliente("A", "DNI", true); //minusvalia = true
		Cliente c_sin = new Cliente("B", "DNI", false); //minusvalia = false
		Seguro s;
		
		s = new Seguro(1, c_sin, Cobertura.TERCEROS_LUNAS);
		s.setFechaUltimoSiniestro(10000); //Valor muy superior para indicar que no ha tenido un siniesto
		assertTrue(s.precio() == 600); 
		
		s = new Seguro(50, c_sin, Cobertura.TERCEROS_LUNAS);
		s.setFechaUltimoSiniestro(0);
		assertTrue(s.precio() == 800);
		
		s = new Seguro(89, c_con, Cobertura.TERCEROS_LUNAS);
		s.setFechaUltimoSiniestro(0);
		assertTrue(s.precio() == 450);
		
		s = new Seguro(90, c_con, Cobertura.TERCEROS);
		s.setFechaUltimoSiniestro(0);
		assertTrue(s.precio() == 465);
		
		s = new Seguro(110, c_sin, Cobertura.TODO_RIESGO);
		s.setFechaUltimoSiniestro(1);
		assertTrue(s.precio() == 1100);
		
		s = new Seguro(1, c_con, Cobertura.TERCEROS);
		s.setFechaUltimoSiniestro(0);
		assertTrue(s.precio() == 465);
		
		s = new Seguro(1, c_con, Cobertura.TERCEROS);
		s.setFechaUltimoSiniestro(0);
		assertTrue(s.precio() == 510);
		
		s = new Seguro(1, c_sin, Cobertura.TODO_RIESGO);
		s.setFechaUltimoSiniestro(3);
		assertTrue(s.precio() == 1250);
		
		//Excepciones, TODO: Preguntar a juan en tests caja negra
	}
}



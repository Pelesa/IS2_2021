

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.containers.ListaOrdenada;

public class ListaOrdenadaTest {

	ListaOrdenada<Integer> lista = new ListaOrdenada<Integer>();
	
	@Before
	public void init() throws Exception {
		lista.add(2);
		lista.add(1);
	}
	
	@Test
	public void gettest() {
		assertTrue(lista.get(0) == 1);
		assertTrue(lista.get(1) == 2);
		try {
			lista.get(-1);
		} catch(IndexOutOfBoundsException e) {}
	}

	@Test
	public void sizeTest() {
		assertTrue(lista.size() == 2);
	}
	
	@Test
	public void addTest() {
		lista.add(3);
		assertTrue(lista.get(lista.size()-1) == 3);
	}

	@Test
	public void clearTest() {
		lista.clear();
		assertTrue(lista.size() == 0);
	}
}

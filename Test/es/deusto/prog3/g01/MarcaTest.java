package es.deusto.prog3.g01;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Test;

public class MarcaTest {
	
	private Marca marca;
	private String nombreMarca = "AAAA";
	private int idMarca = 01;
	private ArrayList<Marca> listaProductosMarca;

	@Test
	public void testGetNombreMarca() {
		assertEquals(nombreMarca, marca.getNombreMarca());
	}
	
	@Test
	public void testSetNombre() {
		String newNombreMarca = "AAAA";
		
		assertEquals(nombreMarca, marca.getNombreMarca(),"");
		marca.setNombreMarca(newNombreMarca);
		assertEquals(newNombreMarca, marca.getNombreMarca(), "");
		
		
	}
	
	
	@Test
	public void testIdMarca() {
		assertEquals(idMarca, marca.getIdMarca());
	}
	
	@Test
	public void testSetIdMarca() {
		Integer newIdMarca = 02;
		
		assertEquals(idMarca, marca.getIdMarca(), 0);
		marca.setIdMarca(newIdMarca);
		assertEquals(newIdMarca, marca.getIdMarca(), 0);
		
		
	}
	

}

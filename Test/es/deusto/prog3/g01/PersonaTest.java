package es.deusto.prog3.g01;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class PersonaTest {
	
	private Persona persona;
	private String nombre = "AAAA";
	private String apellido = "BBBB";
	private LocalDateTime fechaNacimiento;
	

	@Test
	public void testGetNombre() {
		assertEquals(nombre, persona.getNombre());
	}
	
	@Test
	public void testSetNombre() {
		String newNombre = "AAAA";
		
		assertEquals(nombre, persona.getNombre(),"");
		persona.setNombre(newNombre);
		assertEquals(newNombre, persona.getNombre(), "");
		
		
	}
	
	
	@Test
	public void testGetApellido() {
		assertEquals(apellido, persona.getApellido());
	}
	
	@Test
	public void testSetApellido() {
		String newApellido = "BBBB";
		
		assertEquals(nombre, persona.getApellido(),"");
		persona.setApellido(newApellido);
		assertEquals(newApellido, persona.getApellido(), "");
		
		
	}
	
	
	@Test
	public void testToString() {
		String toString = "Persona [nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + "]";
		
		assertEquals(toString, persona.toString());
	}
}

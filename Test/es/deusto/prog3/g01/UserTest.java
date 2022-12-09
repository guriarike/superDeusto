package es.deusto.prog3.g01;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	
	private Usuario usuario;
	private String nombre = "aaa";
	private int id = 123;
	private String contrasena = "bbb" ;

	@Test
	public void testGetUserName() {
		assertEquals(nombre, usuario.getNombre());
	}
	
	@Test
	public void testSetUserName() {
		String newUserName = "aaa";
		
		assertEquals(nombre, usuario.getNombre(),"");
		usuario.setNombre(newUserName);
		assertEquals(newUserName, usuario.getNombre(), "");
		
	}
	
	
	@Test
	public void testGetId_usuario() {
		assertEquals(id, usuario.getId());
	}
	
	@Test
	public void testSetId_usuario() {
		int newId = 123;
		
		assertEquals(id, usuario.getId(), 0);
		usuario.setId(newId);
		assertEquals(newId, usuario.getId(), 0);
		
	}
	
	
	@Test
	public void testGetUserContraseña() {
		assertEquals(contrasena, usuario.getContrasena());
	}
	
	@Test
	public void testSetUserContraseña() {
		String newContraseña = "bbb";
		
		assertEquals(contrasena, usuario.getContrasena(),"");
		usuario.setContrasena(newContraseña);
		assertEquals(newContraseña, usuario.getContrasena(), "");
		
	}
	
	
	@Test
	public void testToString() {
		String toString = "User [nombre=" + nombre + ", id=" + id + "]";
		
		assertEquals(toString, usuario.toString());
	}
}

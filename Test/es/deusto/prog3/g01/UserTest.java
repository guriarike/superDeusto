package es.deusto.prog3.g01;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	
	private User user;
	private String userName = "aaa";
	private int id_usuario = 123;
	private String userContraseña = "bbb" ;

	@Test
	public void testGetUserName() {
		assertEquals(userName, user.getUserName());
	}
	
	@Test
	public void testSetUserName() {
		String newUserName = "aaa";
		
		assertEquals(userName, user.getUserName(),"");
		user.setUserName(newUserName);
		assertEquals(newUserName, user.getUserName(), "");
		
	}
	
	
	@Test
	public void testGetId_usuario() {
		assertEquals(id_usuario, user.getId_usuario());
	}
	
	@Test
	public void testSetId_usuario() {
		int newId_usuario = 123;
		
		assertEquals(id_usuario, user.getId_usuario(), 0);
		user.setId_usuario(newId_usuario);
		assertEquals(newId_usuario, user.getId_usuario(), 0);
		
	}
	
	
	@Test
	public void testGetUserContraseña() {
		assertEquals(userContraseña, user.getUserContraseña());
	}
	
	@Test
	public void testSetUserContraseña() {
		String newUserContraseña = "bbb";
		
		assertEquals(userContraseña, user.getUserContraseña(),"");
		user.setUserContraseña(newUserContraseña);
		assertEquals(newUserContraseña, user.getUserContraseña(), "");
		
	}
	
	
	@Test
	public void testToString() {
		String toString = "User [userName=" + userName + ", id_usuario=" + id_usuario + "]";
		
		assertEquals(toString, user.toString());
	}
}

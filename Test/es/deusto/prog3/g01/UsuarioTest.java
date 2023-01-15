package es.deusto.prog3.g01;

import static org.junit.Assert.*;
import org.junit.Test;

public class UsuarioTest {
    Usuario usuario = new Usuario();
    int id = 1;
    String correo = "example@example.com";
    String contrasena = "password";
    String nombre = "John";
    String apellido = "Doe";
    
    @Test
    public void testGetId() {
        usuario.setId(id);
        assertEquals(id, usuario.getId());
    }
    
    @Test
    public void testSetId() {
        usuario.setId(2);
        assertEquals(2, usuario.getId());
    }
    
    @Test
    public void testGetCorreo() {
        usuario.setCorreo(correo);
        assertEquals(correo, usuario.getCorreo());
    }
    
    @Test
    public void testSetCorreo() {
        usuario.setCorreo("newemail@example.com");
        assertEquals("newemail@example.com", usuario.getCorreo());
    }
    
    
    @Test
    public void testGetContrasena() {
        usuario.setContrasena(contrasena);
        assertEquals(contrasena, usuario.getContrasena());
    }
    
    @Test
    public void testSetContrasena() {
        usuario.setContrasena("newpassword");
        assertEquals("newpassword", usuario.getContrasena());
    }
    
    @Test
    public void testGetNombre() {
        usuario.setNombre(nombre);
        assertEquals(nombre, usuario.getNombre());
    }
    
    @Test
    public void testSetNombre() {
        usuario.setNombre("Jane");
        assertEquals("Jane", usuario.getNombre());
    }
    
    @Test
    public void testGetApellido() {
        usuario.setApellido(apellido);
        assertEquals(apellido, usuario.getApellido());
    }
    
    @Test
    public void testSetApellido() {
        usuario.setApellido("Smith");
        assertEquals("Smith", usuario.getApellido());
    }
    
    @Test
    public void testToString() {
        usuario.setId(id);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        
        String expected = "Persona [correo=" + correo + ", nombre=" + nombre + ", apellido=" + apellido + "]";
        assertEquals(expected, usuario.toString());
    }

    
}

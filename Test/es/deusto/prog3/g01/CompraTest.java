package es.deusto.prog3.g01;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class CompraTest {
	

	    
	Compra compra = new Compra();
    int idCompra = 1;
    Date fechaCompra = new Date();
    double precioCompra = 10.0;
    String detalles = "detalles";
    Usuario usuario = new Usuario(1, "correo@ejemplo.com", "nombre", "apellido", "contraseña");
	    
    @Test
    public void testGetIdCompra() {
        compra.setIdCompra(idCompra);
        assertEquals(idCompra, compra.getIdCompra());
	}
	    
	@Test
	public void testSetIdCompra() {
		compra.setIdCompra(2);
	    assertEquals(2, compra.getIdCompra());
    }
	    
	@Test
	public void testGetFechaCompra() {
		compra.setFechaCompra(fechaCompra);
	    assertEquals(fechaCompra, compra.getFechaCompra());
    }
	    
	@Test
	public void testSetFechaCompra() {
		Date nuevaFechaCompra = new Date();
	    compra.setFechaCompra(nuevaFechaCompra);
	    assertEquals(nuevaFechaCompra, compra.getFechaCompra());
	}
	    
	@Test
	public void testGetPrecioCompra() {
		compra.setPrecioCompra(precioCompra);
	    assertEquals(precioCompra, compra.getPrecioCompra(), 0);
	}
	   
	@Test
	public void testSetPrecioCompra() {
		compra.setPrecioCompra(20.0);
	    assertEquals(20.0, compra.getPrecioCompra(), 0);
	}
	   
	@Test
	public void testGetDetalles() {
		compra.setDetalles(detalles);
	    assertEquals(detalles, compra.getDetalles());
	}
	    
	@Test
	public void testSetDetalles() {
		compra.setDetalles("nuevos detalles");
	    assertEquals("nuevos detalles", compra.getDetalles());
	}
	   
	@Test
	public void testGetUsuario() {
		compra.setUsuario(usuario);
	    assertEquals(usuario, compra.getUsuario());
    }
	    
	@Test
	public void testSetUsuario() {
		Usuario nuevoUsuario = new Usuario(2, "correo2@ejemplo.com", "nombre2", "apellido2", "contraseña2");
	    compra.setUsuario(nuevoUsuario);
	    assertEquals(nuevoUsuario, compra.getUsuario());
	}


}

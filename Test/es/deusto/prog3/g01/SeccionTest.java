package es.deusto.prog3.g01;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class SeccionTest {
    
    Seccion seccion = new Seccion();
    int seccionID = 1;
    String nombre = "seccion1";
    ArrayList<Producto> listaProductosSeccion = new ArrayList<Producto>();
    
    @Test
    public void testGetSeccionID() {
        seccion.setSeccionID(seccionID);
        assertEquals(seccionID, seccion.getSeccionID());
    }
    
    @Test
    public void testSetSeccionID() {
        seccion.setSeccionID(2);
        assertEquals(2, seccion.getSeccionID());
    }
    
    @Test
    public void testGetNombre() {
        seccion.setNombre(nombre);
        assertEquals(nombre, seccion.getNombre());
    }
    
    @Test
    public void testSetNombre() {
        seccion.setNombre("nuevoNombreSeccion");
        assertEquals("nuevoNombreSeccion", seccion.getNombre());
    }
    
    @Test
    public void testToString() {
        seccion.setSeccionID(seccionID);
        seccion.setNombre(nombre);
        
        String expected = "Seccion [seccionID=" + seccionID + ", nombre=" + nombre + "]";
        assertEquals(expected, seccion.toString());
    }
   
}


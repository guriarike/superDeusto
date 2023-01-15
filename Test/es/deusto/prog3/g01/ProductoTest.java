package es.deusto.prog3.g01;


import static org.junit.Assert.*;
import org.junit.Test;

public class ProductoTest {
    
    Producto producto = new Producto();
    int idProducto = 1;
    String nombreProducto = "producto1";
    String marca = "marca1";
    int precioProducto = 10;
    String seccion = "seccion1";
    
    @Test
    public void testGetIdProducto() {
        producto.setIdProducto(idProducto);
        assertEquals(idProducto, producto.getIdProducto());
    }
    
    @Test
    public void testSetIdProducto() {
        producto.setIdProducto(2);
        assertEquals(2, producto.getIdProducto());
    }
    
    @Test
    public void testGetNombreProducto() {
        producto.setNombreProducto(nombreProducto);
        assertEquals(nombreProducto, producto.getNombreProducto());
    }
    
    @Test
    public void testSetNombreProducto() {
        producto.setNombreProducto("nuevoNombreProducto");
        assertEquals("nuevoNombreProducto", producto.getNombreProducto());
    }
    
    @Test
    public void testGetMarca() {
        producto.setMarca(marca);
        assertEquals(marca, producto.getMarca());
    }
    
    @Test
    public void testSetMarca() {
        producto.setMarca("nuevaMarca");
        assertEquals("nuevaMarca", producto.getMarca());
    }
    
    @Test
    public void testGetPrecioProducto() {
        producto.setPrecioProducto(precioProducto);
        assertEquals(precioProducto, producto.getPrecioProducto());
    }
    
    @Test
    public void testSetPrecioProducto() {
        producto.setPrecioProducto(20);
        assertEquals(20, producto.getPrecioProducto());
    }
   
    @Test
    public void testGetSeccion() {
        producto.setSeccion(seccion);
        assertEquals(seccion, producto.getSeccion());
    }
    
    @Test
    public void testSetSeccion() {
        producto.setSeccion("nuevaSeccion");
        assertEquals("nuevaSeccion", producto.getSeccion());
    }
}


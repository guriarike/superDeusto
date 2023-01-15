package es.deusto.prog3.g01;

import static org.junit.Assert.*;
import org.junit.Test;


public class PagoTest {

    
    Pago pago = new Pago(1, "correo@ejemplo.com", "nombre", "apellido", "contraseña", "cuentaBancaria", "caducidad", "cvv");
    
    @Test
    public void testGetCuentaBancaria() {
        assertEquals("cuentaBancaria", pago.getCuentaBancaria());
    }
    
    @Test
    public void testSetCuentaBancaria() {
        pago.setCuentaBancaria("nuevaCuentaBancaria");
        assertEquals("nuevaCuentaBancaria", pago.getCuentaBancaria());
    }
    
    @Test
    public void testGetCaducidad() {
        assertEquals("caducidad", pago.getCaducidad());
    }
    
    @Test
    public void testSetCaducidad() {
        pago.setCaducidad("nuevaCaducidad");
        assertEquals("nuevaCaducidad", pago.getCaducidad());
    }
    
    @Test
    public void testGetCvv() {
        assertEquals("cvv", pago.getCvv());
    }
    
    @Test
    public void testSetCvv() {
        pago.setCvv("nuevoCvv");
        assertEquals("nuevoCvv", pago.getCvv());
    }
    
}


package es.deusto.prog3.g01;

import es.deusto.prog3.gui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		try {
			GestorBD.crearBBDD();
			Seccion s = new Seccion();
			s.setNombre("Lacteos");
			
			Marca m = new Marca();
			m.setNombreMarca("Pascual");
			
			Producto p = new Producto();
			p.setNombreProducto("Leche semidesnatada");
			p.setPrecioProducto(2);
			p.setMarca(m);
			p.setSeccion(s);
			GestorBD.insetarProductos(p);
			
			VentanaPrincipal ventana = new VentanaPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
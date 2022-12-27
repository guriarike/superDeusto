package es.deusto.prog3.g01;

import es.deusto.prog3.gui.VentanaCuenta;
import es.deusto.prog3.gui.VentanaInicioSesion;
import es.deusto.prog3.gui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		
		try {
			GestorBD.crearBBDD();
			GestorBD.initProductos2();
			
			
			Usuario uu = new Usuario();
			uu.setNombre("GURILA");
			uu.setApellido("Rike");
			uu.setContrasena("guriguri");
			uu.setCorreo("guria");
			
			VentanaCuenta ven = new VentanaCuenta(uu);
			ven.setVisible(true);
			/**
			VentanaPrincipal ventana = new VentanaPrincipal(uu, null);
			VentanaInicioSesion ventana2 = new VentanaInicioSesion();
**/
			
			
			/*int contador = 0;
			for (Usuario u:GestorBD.todosLosUsuarios()) {
				
				System.out.println("#"+contador+"--"+u.getNombre());
				contador++;
			}*/
			
			
			//VentanaPrincipal ventana = new VentanaPrincipal(pepe, venn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
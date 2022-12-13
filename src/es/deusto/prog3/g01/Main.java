package es.deusto.prog3.g01;

import es.deusto.prog3.gui.VentanaInicioSesion;
import es.deusto.prog3.gui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		try {
			GestorBD.borrarProductos();
			GestorBD.crearBBDD();
			
			GestorBD.InitUsuarios();
			
			GestorBD.insertarUsuarios();
		
			for(Usuario u:GestorBD.todosLosUsuarios()) {
				System.out.println(u.getCorreo());
			}
			Usuario u= new Usuario();
			u.setNombre("Asier");
			u.setNombre( "Guria");
			VentanaInicioSesion venn = new VentanaInicioSesion();
			VentanaPrincipal ventana = new VentanaPrincipal(u, venn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
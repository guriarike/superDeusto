package es.deusto.prog3.g01;

import es.deusto.prog3.gui.VentanaInicioSesion;
import es.deusto.prog3.gui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		
		try {
			GestorBD.crearBBDD();
			
			
			Usuario uu = new Usuario();
			uu.setNombre("guri");
			
			VentanaPrincipal vn = new VentanaPrincipal(uu, null);
			vn.setVisible(true);
			
			
			
			
			
			for (Usuario u:GestorBD.todosLosUsuarios()) {
				System.out.println(u.getNombre());
				
			}
			VentanaInicioSesion venn = new VentanaInicioSesion();
			//VentanaPrincipal ventana = new VentanaPrincipal(pepe, venn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
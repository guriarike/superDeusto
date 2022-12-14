package es.deusto.prog3.g01;

import es.deusto.prog3.gui.VentanaInicioSesion;
import es.deusto.prog3.gui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		
		try {
			Usuario pepe = new Usuario();
			pepe.setId(1);
			pepe.setNombre("Guria");
			pepe.setApellido("Rike");
			pepe.setContrasena("guriguri");
			pepe.setCorreo("guriarike@opendeusto.es");
			
			
			
			
			GestorBD.insertarUsuarios(pepe);
			GestorBD.borrarProductos();
			GestorBD.crearBBDD();
			
			for (Usuario u:GestorBD.todosLosUsuarios()) {
				System.out.println(u.getNombre());
			}
			VentanaInicioSesion venn = new VentanaInicioSesion();
			VentanaPrincipal ventana = new VentanaPrincipal(pepe, venn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package es.deusto.prog3.g01;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import es.deusto.prog3.gui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	VentanaPrincipal ventana = new VentanaPrincipal();

	GestorBD g = new GestorBD();
	g.crearBBDD();
	try {
		GestorBD.crear100UsuariosRandom();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		for(User u:GestorBD.todosLosUsuarios()) {
			System.out.println(u.toString());
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		

	}
}
	

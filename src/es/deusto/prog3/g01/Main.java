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
		try {
			GestorBD.InitProductosMarcasSecciones();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VentanaPrincipal ventana = new VentanaPrincipal();

	}
}

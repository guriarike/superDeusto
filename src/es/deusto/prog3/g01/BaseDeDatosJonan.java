package es.deusto.prog3.g01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.deusto.prog3.g01.*;
import es.deusto.prog3.gui.*;

public class BaseDeDatosJonan {
	private static Connection conexion;
	private static Logger logger = Logger.getLogger("BaseDeDatos");
	
	
	
	/*
	 * Iniciamos conexion con la base de datos
	 * Creamos las tablas que vamos a tener que utilizar
	 * En caso de cargar por medio de un fichero de texto borramos la tablaantes de crearla
	 */

	public static boolean inicioConexion(String nombreBD) {
		try {
			// No terminado
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			Statement statement = conexion.createStatement();
			
			
			  String sent = "DROP TABLE IF EXISTS usuario";
			  logger.log( Level.INFO, "Statement: " + sent);
			  statement.executeUpdate(sent);
 
			  
			  
			  sent ="CREATE TABLE IF NOT EXISTS usuario(id INTEGER PRIMARY KEY AUTOINCREMENT, correo varchar(50), contrasena varchar(50), nombre varchar(50), apellido varchar(50), telefono int(9), codigoPostal varchar(5));"
			  ; logger.log( Level.INFO, "Statement: " + sent ); 
			  statement.executeUpdate(sent );
			  
			  
			  
			  try { Scanner scanner = new Scanner(BaseDeDatosJonan.class.getResourceAsStream("usuarios.txt") );
			  while (scanner.hasNextLine()) { //Mientras en el fichero haya lineas que leer
			  String linea = scanner.nextLine(); //Lee una linea del fichero String[] datos
			  String[] datos = linea.split( "\t" );
			  sent = "insert into usuario (correo, contrasena, nombre, apellido, telefono, codigoPostal) values ('" 
			  + datos[0] + "'," + datos[1] + "','" + datos [2] + "','" + datos[3] + "'," + datos[4] + "," + datos[5] + ");"; 
			  logger.log( Level.INFO, "Statement: " + sent );
			  statement.executeUpdate( sent ); }
			  scanner.close();
			  
			  
			  
			  } catch(Exception e) { logger.log( Level.SEVERE, "Excepción", e ); }
			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * Cerramos conexion con la base de datos una vez hayamos terminado de usarla
	 */
	
	public static void cerrarConexion() {
		try {
			logger.log(Level.INFO, "Cerrando conexión");
			conexion.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Excepción", e);
		}
	}
			
	/*
	 * Inserta en la tabla cliente un nuevo cliente
	 */
	
	
	
	public static void crearUsuario(Usuario usuario) {
		try (Statement statement = conexion.createStatement();) {
			String sent = "insert into usuario (nombre, contrasena) values('" + usuario.getNombre() + "','" + usuario.getApellido() + ")";
			statement.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	/*
	 * Devuelve la contrasenia de un determinado correo de la tabla pago
	 */
	
	
	public static String getContrasenaUsuario(String usuario) {
		try (Statement statement = conexion.createStatement()) {
			String contrasenaDev = "";
			String sent = "select contrasena from usuario where correo='"+usuario+"'";
			logger.log(Level.INFO, "Statement: " + sent);
			ResultSet rs = statement.executeQuery(sent);
			while (rs.next()) { // Leer el resultset
				String contrasena = rs.getString("contrasena");
				contrasenaDev = contrasena;
			}
			
			return contrasenaDev;
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}
	}
	
	
	/*
	 * Devuelve una lista de los correos de todos los clientes
	 */
	
	public static ArrayList<String> getUsuarios() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<String> ret = new ArrayList<>();
			String sent = "select correo from usuario;";
			logger.log(Level.INFO, "Statement: " + sent);
			ResultSet rs = statement.executeQuery(sent);
			while (rs.next()) { // Leer el resultset
				String correo = rs.getString("correo");
				ret.add(correo);
			}
			return ret;
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}

	}
	

}

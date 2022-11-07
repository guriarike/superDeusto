package es.deusto.prog3.g01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GestorBD {
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "db/database.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;
	
	public GestorBD() {		
		try {
			//Cargar el diver SQLite
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.println(String.format("* Error al cargar el driver de BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	public void crearBBDD() {
		//Se abre la conexión y se obtiene el Statement
		//Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			String Producto= "CREATE TABLE IF NOT EXISTS PRODUCTO(\n"
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " NAME TEXT NOT NULL,\n"
					+ "PRECIO DOUBLE NOT NULL,\n"
					+ ");\n";
			String Seccion= "CREATE TABLE IF NOT EXISTS SECCION(\n"
					   + "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "NAMESECCION TEXT NOT NULL\n"
					   +");\n";
			String Marca= "CREATE TABEL IF NOT EXISTS MARCA(\n"
					+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "NAMEMARCA TEXT NOT NULL\n"
					+ ");\n";
			String Compra= "CREATE TABLE IF NOT EXISTS COMPRA(\n"
					+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "FECHA DATE NOT NULL,\n"
					+ "PRECIO DOUBLE NOT NULL\n"
					+ ");\n";
			
	        String sql = "CREATE TABLE IF NOT EXISTS USER (\n"
	                   + " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
	                   + " NAME TEXT NOT NULL,\n"
	                   + " PASSWORD TEXT NOT NULL\n"
	                   + ");\n"
	                   + Producto+ Seccion + Marca + Compra;
	        stmt.executeUpdate(sql);
	        	        
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Cliente");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
	}
	public void borrarBBDD() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS USER";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Cliente");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		
	}
	
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	//Metodos para leer y creer CSVs
	public static ArrayList<User> crearListaDeUsuariosConCSV(String ruta) {
		ArrayList<User> listaUsuarios = null;
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(ruta));
			String strLine = "";
			StringTokenizer st = null;
			 listaUsuarios = new ArrayList<>();

			while ((strLine = br.readLine()) != null) {
				// break comma separated line using ","
				st = new StringTokenizer(strLine, ",");
				User u = new User();

				u.setNombre(st.nextToken());

				u.setApellido(st.nextToken());
				String f = st.nextToken();

				/*
				 * aqui cambiamos el String del csv a LocalDateTimeDateTimeFormatter formatter =
				 * DateTimeFormatter. ofPattern("yyyy-MM-dd"); LocalDateTime dateTime =
				 * LocalDateTime. parse(f); u.setFechaNacimiento(dateTime);
				 */

				u.setUserName(st.nextToken());
				u.setId_usuario(Integer.parseInt((String) st.nextElement()));
				// añadimos el usuario de esta linea a la lista
				listaUsuarios.add(u);

			}
			System.out.println("NOMBRES:");
			for (User u : listaUsuarios) {
				System.out.println("#" + u.getNombre());
			}

		} catch (Exception e) {
			System.out.println("Exception while reading csv file: " + e);
		}
		return listaUsuarios;

	}

	public static void crearCSVusuarios(ArrayList<User> listaUsuarios, String rutaNuevoFichero) {
		try {
			//Creamos el escritor 
			File file = new File(rutaNuevoFichero);
			PrintWriter pr = new PrintWriter(file);
			//Recorremos los usuarios uno por uno
			for(User u:listaUsuarios) {
				pr.println(""+u.getNombre()+";"+u.getApellido()+";"+u.getFechaNacimiento()+";"+u.getUserName()+";"+u.getId_usuario());
				//comprobacion
				System.out.println("#"+u.getNombre());
			}
			pr.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

package es.deusto.prog3.g01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class GestorBD {
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "db/database.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;

	public GestorBD() {
		try {
			// Cargar el diver SQLite
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.println(String.format("* Error al cargar el driver de BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}

	public void crearBBDD() {
		// Se abre la conexión y se obtiene el Statement
		// Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String Producto = "CREATE TABLE IF NOT EXISTS PRODUCTO(\n" + " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " NAME TEXT NOT NULL,\n" + "PRECIO DOUBLE NOT NULL,\n"
					+ "MARCA INT FOREING KEY REFERENCES MARCA(ID),"
					+ "SECCION INT FOREING KEY REFERENCES SECCION(ID),\n"
					+ "COMPRA INT FOREING KEY REFERENCES COMPRA(ID)"
					
					+ ")";
			String Seccion = "CREATE TABLE IF NOT EXISTS SECCION(\n" + "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "NAMESECCION TEXT NOT NULL)";
			String Marca = "CREATE TABLE IF NOT EXISTS MARCA(\n" + "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "NAMEMARCA TEXT NOT NULL)";
			String Compra = "CREATE TABLE IF NOT EXISTS COMPRA(\n" + "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "FECHA DATE NOT NULL,\n" + "PRECIO DOUBLE NOT NULL)";

			String sql = "CREATE TABLE IF NOT EXISTS USER (\n" + " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " NAME TEXT NOT NULL,\n" + "APELLIDO TEXT NOT NULL,\n" + "FECHANACIMIENTO DATE,\n"
					+ "USERNAME TEXT NOT NULL,\n" + " PASSWORD TEXT NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.executeUpdate(Producto);
			stmt.executeUpdate(Seccion);
			stmt.executeUpdate(Marca);
			stmt.executeUpdate(Compra);

			if (!stmt.execute(sql)) {
				System.out.println("- Se ha creado la tabla Cliente");
			}
		} catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}

	public void borrarBBDD() {
		// Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

			String sql = "DROP TABLE IF EXISTS USER";

			// Se ejecuta la sentencia de creación de la tabla Estudiantes
			if (!stmt.execute(sql)) {
				System.out.println("- Se ha borrado la tabla Cliente");
			}
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}

	}

	// Metodo para insertar usuarios en la base de datos
	public void insertarUsuarios(User ... listaUsuariosAInsertar) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			for (User u : listaUsuariosAInsertar) {
				String nombre = u.getNombre();
				String apellido = u.getApellido();
				LocalDateTime fechaNacimiento = u.getFechaNacimiento();
				int id = u.getId_usuario();
				String contraseña = u.getUserContraseña();
				String sql = "INSERT INTO USER (nombre,apellido,fechaNacimiento,username,userID,userContraseña)"
						+ "values(%s,%s,%s,%s,%s,%s";
				String.format(sql, nombre, apellido, fechaNacimiento, id, contraseña);
				stmt.executeUpdate(sql);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		;

	}
	public void insetarProductos(Producto ... listaProductos) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			for (Producto p : listaProductos) {
				String nombre = p.getNombreProducto();
				Double precio = p.getPrecioProducto();
				String sql = "INSERT INTO USER (nombre,apellido,fechaNacimiento,username,userID,userContraseña)"
						+ "values(%s,%s,%s,%s,%s,%s";
			
				stmt.executeUpdate(sql);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// METODOS QUE DEVUELVEN COSAS DE LA BASE DE DATOS
	public static ArrayList<User> todosLosUsuarios() throws SQLException {
		ArrayList<User> listaUsuarios = null;
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

			String sql = "SELECT * FROM USER";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setNombre(rs.getString("NAME"));
				user.setApellido(rs.getString("APELLIDO"));
				// user.setFechaNacimiento(rs.getDate("FECHANACIMIENTO"));
				user.setUserName(rs.getString("USERNAME"));
				user.setUserContraseña("PASSWORD");
				listaUsuarios.add(user);
			}

		}
		return listaUsuarios;

	}

	public static User usuarioPorUserName(String userName) throws SQLException {
		User user = null;
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

			String sql = "SELECT * FROM USER WHERE USERNAME = %s";
			String.format(sql, userName);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				user = new User();
				user.setNombre(rs.getString("NAME"));
				user.setApellido(rs.getString("APELLIDO"));
				// user.setFechaNacimiento(rs.getDate("FECHANACIMIENTO"));
				user.setUserName(rs.getString("USERNAME"));
				user.setUserContraseña("PASSWORD");

			}
			return user;

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
	// Metodos para leer y creer CSVs y para crear usuarios y productos
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
			// Creamos el escritor
			File file = new File(rutaNuevoFichero);
			PrintWriter pr = new PrintWriter(file);
			// Recorremos los usuarios uno por uno
			for (User u : listaUsuarios) {
				pr.println("" + u.getNombre() + ";" + u.getApellido() + ";" + u.getFechaNacimiento() + ";"
						+ u.getUserName() + ";" + u.getId_usuario());
				// comprobacion
				System.out.println("#" + u.getNombre());
			}
			pr.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void crear100UsuariosRandom() throws SQLException {
		int i = 100;
		String[] posiblesNombres = { "Guria", "Jon_Ander", "Jon", "Iker", "Asier", "Iñigo", "Roberto", "Ander", "Unai",
				"Oier", "Julen", "Aingeru", "Biggie", "Yago", "Veronica", "Hairong", "Angela", "Sara", "Janire",
				"Laura", "Marta", "Naia", "June", "Leire", "Ariane", "Tatiana", "Olatz", "Marina", "Julene", "Ane",
				"Maite", "Blanca" };
		String[] posiblesApellidos = { "Rike", "Jauregi", "Gallastegi", "Gipu", "Fernandez", "Calvo", "Nuñez", "Zelaia",
				"Libano", "Pradera", "Canales", "Muñoz", "Artetxe", "Salinas", "Rio", "Barandiaran", "Guelvenzu",
				"Rodrigez", "Busquets", "Messi", "Ronaldo", "Williams", "Gonzalez", "Fitipaldi", "West", "Smalls",
				"Hernandez", "Ancelloti", "Guardiola" };

		Random random = new Random();
		int upperboundNombres = posiblesNombres.length;
		int upperboundApellidos = posiblesApellidos.length;

		for (i = 0; i < 100; i++) {
			int numeroRandomParaUserName = random.nextInt(100);
			String randomNombre = posiblesNombres[random.nextInt(upperboundNombres - 1)];
			String randomApellido = posiblesApellidos[random.nextInt(upperboundApellidos - 1)];
			String userName = randomNombre + randomApellido + numeroRandomParaUserName;
			String contraseña = userName;
			System.out.println(userName);
			// System.out.println("NOMBRE :"+ randomNombre);
			// System.out.println("APELLIDO :"+ randomApellido);
			System.out.println("#######################################");
			try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
				String sql = "INSERT INTO USER (ID,NAME,APELLIDO,FECHANACIMIENTO,USERNAME,CONTRASEÑA) VALUES(0,'"+randomNombre+"','"+randomApellido+"',null,'"+userName+"','"+contraseña+"'";
				stmt.executeUpdate(sql);
				if (!stmt.execute(sql)) {
					System.out.println("- Se han insertado los 100 usuarios en la tabla Cliente");
				}
			} catch (Exception ex) {
				System.err.println(String.format("* Error al Insertar usuarios en la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();
			}
		}
	

	}
}

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

	
	
	// METODO QUE CREA LA BASE DE DATOS
	public static void crearBBDD() {
		// Se abre la conexión y se obtiene el Statement
		// Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String Producto = "CREATE TABLE IF NOT EXISTS PRODUCTO(\n" 
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " NAME TEXT NOT NULL,\n" 
					+ " PRECIO INT NOT NULL,\n"
					+ " MARCA INT NOT NULL,\n"
					+ " SECCION INT NOT NULL,\n"
					+ " COMPRA INT NOT NULL,\n"
					+ " FOREIGN KEY(MARCA) REFERENCES MARCA(ID) ON DELETE CASCADE,\n"
					+ " FOREIGN KEY(SECCION) REFERENCES SECCION(ID) ON DELETE CASCADE,\n"
					+ " FOREIGN KEY(COMPRA) REFERENCES COMPRA(ID) ON DELETE CASCADE"
					
					
					+ ");";
			String Seccion = "CREATE TABLE IF NOT EXISTS SECCION(\n" 
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " NAMESECCION TEXT NOT NULL);";
			String Marca = "CREATE TABLE IF NOT EXISTS MARCA(\n" 
					+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "NAMEMARCA TEXT NOT NULL);";
			String Compra = "CREATE TABLE IF NOT EXISTS COMPRA(\n" 
					+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "FECHA DATE NOT NULL,\n" 
					+ "PRECIO DOUBLE NOT NULL);";

			String sql = "CREATE TABLE IF NOT EXISTS USER (\n" 
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " NAME TEXT NOT NULL,\n" 
					+ " APELLIDO TEXT NOT NULL,\n" 
					+ " FECHANACIMIENTO DATE,\n"
					+ " USERNAME TEXT NOT NULL,\n" 
					+ " PASSWORD TEXT NOT NULL);"+ Producto+Seccion+Marca+Compra;
			stmt.executeUpdate(sql);
			//stmt.executeUpdate(Producto);
			//stmt.executeUpdate(Seccion);
			//stmt.executeUpdate(Marca);
			//stmt.executeUpdate(Compra);

			if (!stmt.execute(sql)) {
				System.out.println("- Se ha creado la BBDD");
			}
		} catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}

	// METODO QUE BORRA LA BASE DE DATOS 
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

	
	/*
	 * 
	 * 
	 * 
	 * 
	 
	  
	 METODOS PARA INSERTAR DATOS A LA BBDD 
	  
	 * 
	 * 
	 * 
	 * 
	 * */
	
	
	
	
	

	// Metodo para insertar usuarios en la base de datos
	public static void insertarUsuarios(User... listaUsuariosAInsertar ) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			for (User u : listaUsuariosAInsertar) {
				String nombre = u.getNombre();
				String apellido = u.getApellido();
				String userName = u.getUserName();
				String contraseña = u.getUserContraseña();
				
				
				
				String sql = "INSERT INTO USER (NAME,APELLIDO,USERNAME,PASSWORD)"
						+ "VALUES('"+nombre+"','"+apellido+"','"+userName+"','"+contraseña+"');";
				stmt.executeUpdate(sql);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		;

	}
	public static void insetarProductos(ArrayList<Producto> listaProductos) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			for (Producto p : listaProductos) {
				String nombre = p.getNombreProducto();
				Double precio = p.getPrecioProducto();
				int marca = p.getMarca().getIdMarca();
				int seccion = p.getSeccion().getSeccionID();
				String sql = "INSERT INTO PRODUCTO (NAME,MARCA,PRECIO,SECCION)"
						+ "VALUES('"+nombre+"','"+marca+"','"+precio+"','"+seccion+"');";
				
				stmt.executeUpdate(sql);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	
	/*
	 * 
	 * 
	 * 
	 * 
	 
	  
	 METODOS PARA INSERTAR DATOS O DEVOLVER DATOS  BBDD 
	 
	 USANDO CSVs
	  
	 * 
	 * 
	 * 
	 * 
	 * */
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

	/*
	 * 
	 * 
	 * 
	 * 
	 
	  
	 METODOS PARA DEVOLVER DATOS DE LA BBDD 
	  
	 * 
	 * 
	 * 
	 * 
	 * */
	
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
	public static ArrayList<Producto> todosLosProductos() throws SQLException {
		ArrayList<Producto> listaProductos = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

			String sql = "SELECT * FROM PRODUCTO";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Producto p  = new Producto();
				p.setNombreProducto(rs.getString("NAME"));
				p.setPrecioProducto((Integer)rs.getInt("PRECIO"));
				p.setMarca((Marca)rs.getObject("MARCA"));
				p.setSeccion((Seccion)rs.getObject("SECCION"));
				listaProductos.add(p);
			}

		}
		return listaProductos;

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


	public static void crear100UsuariosRandom() throws SQLException {
		ArrayList<User>listaUsuarios = null ;
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
		try(Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()){
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
				User u = new User();
				u.setNombre(randomNombre);
				u.setApellido(randomApellido);
				u.setFechaNacimiento(null);
				u.setUserName(userName);
				u.setUserContraseña(contraseña);
				insertarUsuarios(u);
			}
			
			
		}
		
	

	}
	
	
	// Inicializador de secciones Marcas y Productos
	public static void InitProductosMarcasSecciones() throws SQLException {
		
		ArrayList<Producto> listaProductos = new ArrayList<>() ;
		ArrayList<Marca> listaMarcas = new ArrayList<>();
		ArrayList<Seccion> listaSecciones = new ArrayList<>() ;
		 
		Seccion s = new Seccion();
		s.setNombre("Lacteos");
		
		Marca m = new Marca();
		m.setNombreMarca("Pascual");
		
		Producto p = new Producto();
		p.setNombreProducto("Leche semidesnatada");
		p.setPrecioProducto(2);
		p.setMarca(m);
		p.setSeccion(s);
		
		
		
		
		Marca m1= new Marca();
		m1.setNombreMarca("Puleva");
		
		Producto p1 = new Producto();
		p1.setNombreProducto("Batido chocolate");
		p1.setPrecioProducto(2);
		p1.setMarca(m1);
		p1.setSeccion(s);
		
		
		//######################################################
		Seccion s1 = new Seccion();
		s1.setNombre("Carnes");
		
		Marca m2 = new Marca();
		m2.setNombreMarca("KFC");
		
		Producto p2 = new Producto();
		p2.setNombreProducto("Alitas de pollo");
		p2.setPrecioProducto(8);
		p2.setMarca(m2);
		p2.setSeccion(s1);
		
		
		
		
		Marca m3= new Marca();
		m3.setNombreMarca("Eusko carne");
		
		Producto p3 = new Producto();
		p3.setNombreProducto("Chuleta");
		p3.setPrecioProducto(9);
		p3.setMarca(m3);
		p3.setSeccion(s1);
		
		//######################################################
		Seccion s2 = new Seccion();
		s2.setNombre("Panaderia y bolleria");
		
		Marca m4 = new Marca();
		m4.setNombreMarca("Bimbo");
		
		Producto p4 = new Producto();
		p4.setNombreProducto("Pan Bimbo");
		p4.setPrecioProducto(8);
		p4.setMarca(m2);
		p4.setSeccion(s2);
		
		
		
		
		Marca m5= new Marca();
		m5.setNombreMarca("Nutela");
		
		Producto p5 = new Producto();
		p5.setNombreProducto("Bollos rellenos");
		p5.setPrecioProducto(9);
		p5.setMarca(m3);
		p5.setSeccion(s2);
		
		
		listaSecciones.add(s);
		listaSecciones.add(s1);
		listaSecciones.add(s2);
		listaMarcas.add(m);
		listaMarcas.add(m1);
		listaMarcas.add(m2);
		listaMarcas.add(m3);
		listaMarcas.add(m4);
		listaMarcas.add(m5);
		listaProductos.add(p);
		listaProductos.add(p1);
		listaProductos.add(p2);
		listaProductos.add(p3);
		listaProductos.add(p4);
		listaProductos.add(p5);
		try(Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()){
			insetarProductos(listaProductos);
				
				
			}
		}
	
		
	
	
	
	
}

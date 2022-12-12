package es.deusto.prog3.g01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); 
			 Statement stmt = con.createStatement()) {
			String producto = "CREATE TABLE IF NOT EXISTS PRODUCTO(\n" 
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " Nombre TEXT NOT NULL,\n" 
					+ " Precio INTEGER NOT NULL,\n"
					+ " Marca TEXT NOT NULL,\n"
					+ " Seccion INTEGER NOT NULL,\n"
					
					+ " FOREIGN KEY(Seccion) REFERENCES SECCION(id) ON DELETE CASCADE);";
			
			String seccion = "CREATE TABLE IF NOT EXISTS SECCION(\n" 
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " NombreSeccion TEXT NOT NULL);";
			
			
			
			String compra = "CREATE TABLE IF NOT EXISTS COMPRA(\n" 
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "Fecha DATE NOT NULL,\n" 
					+ "Precio DOUBLE NOT NULL);";

			String usuario = "CREATE TABLE IF NOT EXISTS USUARIO (\n" 
					+ " id INTEGER,\n"
					+ " Correo TEXT NOT NULL,\n"
					+ " Nombre TEXT NOT NULL,\n" 					
					+ " Apellido TEXT NOT NULL,\n" 
					+ " FechaNacimiento DATE NOT NULL,\n" 
					+ " Contraseña TEXT NOT NULL,\n"
					+ " PRIMARY KEY(id,Correo));";
			

			if (!stmt.execute(usuario) && !stmt.execute(seccion)  && !stmt.execute(compra) && !stmt.execute(producto)) {
				System.out.println("- Se ha creado la BBDD");
			}
		} catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}

	// METODO QUE BORRA LA BASE DE DATOS
	public static void borrarBBDD() {
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
	
	public static void borrarProductos() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			String sql = "DELETE  * FROM PRODUCTO";
		}catch(Exception e) {
			System.out.println(e);
			
		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * METODOS PARA INSERTAR DATOS A LA BBDD
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	// Metodo para insertar usuarios en la base de datos
	public static void insertarUsuarios(Usuario... listaUsuariosAInsertar) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			for (Usuario u : listaUsuariosAInsertar) {
				int id = u.getId();
				String nombre = u.getNombre();
				String apellido = u.getApellido();
				String contrasena = u.getContrasena();
				String correo = u.getCorreo();
				int telefono = u.getTelefono();
				
				

				String sql = "INSERT INTO USUARIO (id,Nombre,Apellido,Correo,Contraseña)" + "VALUES('"+id+"" + nombre + "','"
						+ apellido + "','" + correo + "','" + contrasena + "');";
				stmt.executeUpdate(sql);

			}
		} catch (Exception e) {
			System.out.println(String.format("Error insertando usuarios: ", e.getMessage()));
		}
	}

	public static void insetarProductos(Producto... listaProductos) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			for (Producto p : listaProductos) {
				String nombre = p.getNombreProducto();
				Double precio = p.getPrecioProducto();
				String marca = p.getMarca();
				String seccion = p.getSeccion();
				String sql = "INSERT INTO PRODUCTO (Nombre,Marca,Precio,Seccion)" + "VALUES('" + nombre + "','" + marca
						+ "','" + precio + "','" + seccion + "');";

				stmt.executeUpdate(sql);

			}
		} catch (Exception e) {
			System.out.println(String.format("Error insertando productos: ", e.getMessage()));
			e.printStackTrace();
		}
	}
	public static void insetarSeccion(Seccion... listaSecciones) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			for (Seccion s : listaSecciones) {
				String nombre = s.getNombre();
				
				String sql = "INSERT INTO SECCION (NombreSeccion)" + "VALUES('" + nombre + "');";

				stmt.executeUpdate(sql);

			}
		} catch (Exception e) {
			System.out.println(String.format("Error insertando secciones: ", e.getMessage()));
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * METODOS PARA INSERTAR DATOS O DEVOLVER DATOS BBDD
	 * 
	 * USANDO CSVs
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static ArrayList<Usuario> crearListaDeUsuariosConCSV(String ruta) {
		ArrayList<Usuario> listaUsuarios = null;
		try {

			BufferedReader br = new BufferedReader(new FileReader(ruta));
			String strLine = "";
			StringTokenizer st = null;
			listaUsuarios = new ArrayList<>();

			while ((strLine = br.readLine()) != null) {
				// break comma separated line using ","
				st = new StringTokenizer(strLine, ",");
				Usuario u = new Usuario();

				u.setNombre(st.nextToken());

				u.setApellido(st.nextToken());
				String f = st.nextToken();

				/*
				 * aqui cambiamos el String del csv a LocalDateTimeDateTimeFormatter formatter =
				 * DateTimeFormatter. ofPattern("yyyy-MM-dd"); LocalDateTime dateTime =
				 * LocalDateTime. parse(f); u.setFechaNacimiento(dateTime);
				 */

				u.setId(Integer.parseInt((String) st.nextElement()));
				// añadimos el usuario de esta linea a la lista
				listaUsuarios.add(u);

			}
			//ERROR: hay que cerrar el BufferedReader
			br.close();
			System.out.println("NOMBRES:");
			for (Usuario u : listaUsuarios) {
				System.out.println("#" + u.getNombre());
			}

		} catch (Exception e) {
			System.out.println(String.format("Error creando lista usuarios con CSV: ", e.getMessage()));
		}
		return listaUsuarios;

	}

	public static void crearCSVusuarios(ArrayList<Usuario> listaUsuarios, String rutaNuevoFichero) {
		try {
			// Creamos el escritor
			File file = new File(rutaNuevoFichero);
			PrintWriter pr = new PrintWriter(file);
			// Recorremos los usuarios uno por uno
			for (Usuario u : listaUsuarios) {
				pr.println("" + u.getNombre() + ";" + u.getApellido() + ";" + u.getCorreo() + ";" + u.getContrasena()
						+ ";" + u.getId());
				// comprobacion
				System.out.println("#" + u.getNombre());
			}
			pr.close();
		} catch (Exception e) {
			System.out.println(String.format("Error creando CSV usuarios: ", e.getMessage()));
		}

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * METODOS PARA DEVOLVER DATOS DE LA BBDD
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public static ArrayList<Usuario> todosLosUsuarios() throws SQLException {
		//ERROR: la lista estaba sin inicializar
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

			String sql = "SELECT * FROM USUARIO";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				//ERROR: No había constructor sin parámetros para la clase Usuario
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("Nombre"));
				usuario.setApellido(rs.getString("Apellido"));
				// user.setFechaNacimiento(rs.getDate("FECHANACIMIENTO"));
				usuario.setCorreo(rs.getString("Correo"));
				usuario.setContrasena("contrasena");
				listaUsuarios.add(usuario);
			}

		} catch (Exception e) {
			System.out.println(String.format("Error todos los Usuarios: ", e.getMessage()));
			e.printStackTrace();
		}
		
		return listaUsuarios;

	}

	public static ArrayList<Producto> todosLosProductos() throws SQLException {
		ArrayList<Producto> listaProductos = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

			String sql = "SELECT * FROM PRODUCTO";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Producto p = new Producto();
				p.setNombreProducto(rs.getString("Nombre"));
				p.setPrecioProducto((Integer) rs.getInt("Precio"));
				p.setMarca( rs.getString("Marca"));
				p.setSeccion( rs.getString("Seccion"));
				listaProductos.add(p);
			}

		} catch (Exception e) {
			System.out.println(String.format("Error todos los productos: ", e.getMessage()));
			e.printStackTrace();
		}
		return listaProductos;

	}
	public static ArrayList<Seccion> todosLasSeccion() throws SQLException {
		ArrayList<Seccion> listaSecciones = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

			String sql = "SELECT * FROM SECCION";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Seccion s = new Seccion();
				s.setNombre(rs.getString("NombreSeccion"));
				
				listaSecciones.add(s);
			}

		} catch (Exception e) {
			System.out.println(String.format("Error todos los productos: ", e.getMessage()));
			e.printStackTrace();
		}
		return listaSecciones;

	}
	public static boolean existeUsuarioEnBBDD(String correo, String contrasena) {
		try {
			ArrayList<Usuario> usuarios = GestorBD.todosLosUsuarios();
			for (Usuario u : usuarios) {
				if (u.getCorreo() == correo && u.getContrasena() == contrasena) {
					return true;
				} else {
					return false;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static Usuario usuarioPorCorreo(String correo) throws SQLException {
		Usuario usuario = null;
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {

			String sql = "SELECT * FROM USUARIO WHERE Correo = '"+correo+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setNombre(rs.getString("Nombre"));
				usuario.setApellido(rs.getString("Apellido"));
				// user.setFechaNacimiento(rs.getDate("FECHANACIMIENTO"));
				usuario.setCorreo(rs.getString("Correo"));
				usuario.setContrasena("Contrasena");

			}
			return usuario;

		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * METODOS PARA CREAR DATOS PARA LA BBDD
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static void crear100UsuariosRandom() throws SQLException {
		ArrayList<Usuario> listaUsuarios = null;
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
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
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
				Usuario u = new Usuario();
				u.setNombre(randomNombre);
				u.setApellido(randomApellido);
				u.setContrasena(contraseña);
				insertarUsuarios(u);
			}

		}

	}

	// Inicializador de secciones Marcas y Productos
	public static void InitProductosMarcasSecciones() throws SQLException {
		
		

		ArrayList<Producto> listaProductos = new ArrayList<>();
		
		

		

		

		Producto p = new Producto();
		p.setNombreProducto("Leche semidesnatada");
		p.setPrecioProducto(2);
		p.setMarca("Pascual");
		p.setSeccion("Lacteos");
 
	

		Producto p1 = new Producto();
		p1.setNombreProducto("Batido chocolate");
		p1.setPrecioProducto(3);
		p1.setMarca("Puleva");
		p1.setSeccion("Lacteos");

		// ######################################################
		

		

		Producto p2 = new Producto();
		p2.setNombreProducto("Alitas de pollo");
		p2.setPrecioProducto(8);
		p2.setMarca("Pollo S.L");
		p2.setSeccion("Carne");

		

		Producto p3 = new Producto();
		p3.setNombreProducto("Chuleta");
		p3.setPrecioProducto(9);
		p3.setMarca("Eusko Meat");
		p3.setSeccion("Carne");

		// ######################################################
		

		

		Producto p4 = new Producto();
		p4.setNombreProducto("Pan Bimbo");
		p4.setPrecioProducto(8);
		p4.setMarca("Bimbo");
		p4.setSeccion("Panaderia y Bolleria");

		

		Producto p5 = new Producto();
		p5.setNombreProducto("Bollos rellenos");
		p5.setPrecioProducto(9);
		p5.setMarca("Weikis");
		p5.setSeccion("Panaderia y Bolleria");

		
		
		Seccion s = new Seccion();
		s.setNombre("Lacteos");
		
		Seccion s1 = new Seccion();
		s.setNombre("Carne");
		
		Seccion s2 = new Seccion();
		s.setNombre("Panaderia y Bolleria");
		
		
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING); Statement stmt = con.createStatement()) {
			insetarProductos(p);
			insetarProductos(p1);
			insetarProductos(p2);
			insetarProductos(p3);
			insetarProductos(p4);
			insetarProductos(p5);
			
			
			insetarSeccion(s);
			insetarSeccion(s1);
			insetarSeccion(s2);
			

		}
	}
	
	public static void InitUsuarios() {
		
		
		Usuario u = new Usuario();
		u.setId(1);
		u.setNombre("guria");
		u.setApellido("rike");
		u.setCorreo("guriarike@opendeusto.es");
		u.setContrasena("contraseña");
		u.setTelefono(688895837);
		u.setCodigoPostal(48600);
		
		Usuario u2 = new Usuario();
		u2.setId(1);
		u2.setNombre("jonander");
		u2.setApellido("gallastegi");
		u2.setCorreo("jonan@opendeusto.es");
		u.setContrasena("contraseña2");
		u2.setTelefono(688895837);
		u2.setCodigoPostal(48600);
		
		insertarUsuarios(u);
		insertarUsuarios(u2);
	}

}

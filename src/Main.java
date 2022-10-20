import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * ArrayList<Producto> listacompra = new ArrayList<>(); String nombres[] =
		 * {"guria","jaure","jonan", "gipu"}; Random random = new Random(); int index =
		 * 0; for (int i= 0; i<30;i++) { int numero = random.nextInt(3); Producto
		 * producto = new Producto(index, nombres[numero], null, 10);
		 * listacompra.add(producto); }
		 * 
		 * Compra compra = new Compra(1, null, listacompra);
		 * System.out.println(compra.toString());
		 */
		ArrayList<User> list = new ArrayList<>();
		User u = new User();
		u.setNombre("guria");
		u.setApellido("rike");
		u.setFechaNacimiento(null);
		u.setUserName("guriarike");
		u.setId_usuario(1);
		User u1= new User();
		u1.setNombre("asier");
		u1.setApellido("jaure");
		u1.setFechaNacimiento(null);
		u1.setUserName("asierjaure");
		u.setId_usuario(2);
		list.add(u1);
		list.add(u);
		crearCSVusuarios(list, "data//nuevoCSV");
	}

	// METODOS PARA AÑADIR CSVs y TENER OBJETOS CREADOS POR NOSOTROS

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

	/*
	 * CREAR UN CSV CON UN ARRAYLIST DE USUARIOS QUE LE PASEMOS* POR EJEMPLO PARA
	 * PASAR TODOS LOS USUARIOS DE LA BASE DE DATOS A UN CSV
	 */
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

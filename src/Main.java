import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		ArrayList<Producto> listacompra = new ArrayList<>();
		String nombres[] = {"guria","jaure","jonan", "gipu"};
		Random random = new Random();
		int index = 0;
		for (int i= 0; i<30;i++) {
			int numero = random.nextInt(3);
			Producto producto = new Producto(index, nombres[numero], null, 10);
			listacompra.add(producto);
		}
		
		Compra compra = new Compra(1, null, listacompra);
		System.out.println(compra.toString());
*/
		
		
		User u = null;
		try {
			crearListaDeUsuariosConCSV("data//PruebaParaProiectoProg3.csv");

		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	//METODOS PARA AÑADIR CSVs y TENER OBJETOS CREADOS POR NOSOTROS
	//METODOS
		public static void crearListaDeUsuariosConCSV(String ruta) {
			 try{
		                BufferedReader br = new BufferedReader( new FileReader(ruta));
		                String strLine = "";
		                StringTokenizer st = null;
		                ArrayList<User> listaUsuarios = new ArrayList<>();
		                
		                while( (strLine = br.readLine()) != null){
		                        //break comma separated line using ","
		                        st = new StringTokenizer(strLine, ",");
		                        User u= new User("", "", null, "", 0);
		                        
		                        u.setNombre(st.nextToken());	
		                        
		                        u.setApellido(st.nextToken());  
		                        String f = st.nextToken();
		                       
		     /*aqui cambiamos
		      * el String del csv 
		      * a LocalDateTimeDateTimeFormatter formatter = DateTimeFormatter. ofPattern("yyyy-MM-dd"); 
		                        LocalDateTime dateTime = LocalDateTime. parse(f); 
		                        u.setFechaNacimiento(dateTime);  
		                        */
	
		                        u.setUserName(st.nextToken());
		                        u.setId_usuario(Integer.parseInt((String) st.nextElement()));
		                        // añadimos el usuario de esta linea a la lista
		                        listaUsuarios.add(u);   
		                       
		                        
		                }
		                System.out.println("NOMBRES:");
		                for (User u : listaUsuarios) {
		                	System.out.println("#"+u.getNombre());
		                }
		                
		        }
		        catch(Exception e)
		        {
		                System.out.println("Exception while reading csv file: " + e);                  
		        }
			
			
			
		}

}

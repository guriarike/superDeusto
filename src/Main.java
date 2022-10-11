import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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

	}

}

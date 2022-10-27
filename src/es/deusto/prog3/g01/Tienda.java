package es.deusto.prog3.g01;
import java.util.ArrayList;

public class Tienda {
	private int idTienda;
	private String direccion;
	
	private ArrayList<Moto> listaMotos;
	private ArrayList<Producto> listaProductos;
	
	//constructor
	public Tienda(int idTienda, String direccion, ArrayList<Moto> listaMotos,ArrayList<Producto> listaProductos) {
		super();
		this.idTienda = idTienda;
		this.direccion = direccion;
		this.listaMotos = listaMotos;
		this.listaProductos = listaProductos;
	} 
	//setters y getters

	public int getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(int idTienda) {
		this.idTienda = idTienda;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}





	public ArrayList<Moto> getListaMotos() {
		return listaMotos;
	}

	public void setListaMotos(ArrayList<Moto> listaMotos) {
		this.listaMotos = listaMotos;
	}
	
	
	
	

}

package es.deusto.prog3.g01;

public class Producto {
	private int idProducto;
	private String nombreProducto;
	private Marca marca;
	private int precioProducto;
	private Seccion seccion;
	
	//constructor
	/*public Producto(int idProducto, String nombreProducto, Marca marca, double precioProducto) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.marca = marca;
		this.precioProducto = precioProducto;
	}*/
	//getters y setters
	
	
	public int getIdProducto() {
		return idProducto;
	}

	public Seccion getSeccion() {
		return seccion;
	}


	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}


	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(int precioProducto) {
		this.precioProducto = precioProducto;
	}
	
	
	

}

package es.deusto.prog3.g01;

public class Producto {
	private int idProducto;
	private String nombreProducto;
	private String marca;
	private int precioProducto;
	private String seccion;
	
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

	public String getSeccion() {
		return seccion;
	}


	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}


	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", marca=" + marca
				+ ", precioProducto=" + precioProducto + ", seccion=" + seccion + "]";
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(int precioProducto) {
		this.precioProducto = precioProducto;
	}
	
	
	

}

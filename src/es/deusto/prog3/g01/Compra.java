package es.deusto.prog3.g01;
import java.util.ArrayList;
import java.util.Date;

public class Compra {
	private int idCompra;
	private Date fechaCompra;
	private double precioCompra;
	private ArrayList<Producto> listaProductosDeCompra;
	
	/*public Compra(int idCompra, Date fechaCompra,ArrayList<Producto>listaProductosDeCompra) {
		this.idCompra = idCompra;
		this.fechaCompra = fechaCompra;
		this.precioCompra = calcularPrecioCompra(listaProductosDeCompra);
		this.listaProductosDeCompra = listaProductosDeCompra;
		
	}*/
	
	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", fechaCompra=" + fechaCompra + ", precioCompra=" + precioCompra
				+ ", listaProductosDeCompra=" + listaProductosDeCompra + "]";
	}
	

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public ArrayList<Producto> getListaProductosDeCompra() {
		return listaProductosDeCompra;
	}

	public void setListaProductosDeCompra(ArrayList<Producto> listaProductosDeCompra) {
		this.listaProductosDeCompra = listaProductosDeCompra;
	}

	public double calcularPrecioCompra(ArrayList<Producto> listaProductosDeCompra) {
		double total= 0;
		if(listaProductosDeCompra != null && listaProductosDeCompra.size()>= 1) {
			for(Producto p:listaProductosDeCompra) {
				total = total + p.getPrecioProducto();	
			}
			return total;
		}
		return -1;
	}

}

package es.deusto.prog3.g01;
import java.util.ArrayList;
import java.util.Date;

public class Compra {
	private int idCompra;
	private Date fechaCompra;
	private double precioCompra;
	private String detalles;
	private Usuario usuario;
	
	/*public Compra(int idCompra, Date fechaCompra,ArrayList<Producto>listaProductosDeCompra) {
		this.idCompra = idCompra;
		this.fechaCompra = fechaCompra;
		this.precioCompra = calcularPrecioCompra(listaProductosDeCompra);
		this.listaProductosDeCompra = listaProductosDeCompra;
		
	}*/
	
	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", fechaCompra=" + fechaCompra + ", precioCompra=" + precioCompra
				+ ", listaProductosDeCompra=" + "]";
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


	


	public String getDetalles() {
		return detalles;
	}


	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


}

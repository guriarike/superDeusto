package es.deusto.prog3.g01;

public class Seccion {
	private int seccionID;
	private String nombre;
	
	
	/*public Seccion(int seccionID, String nombre) {
		super();
		this.seccionID = seccionID;
		this.nombre = nombre;
	}*/
	public int getSeccionID() {
		return seccionID;
	}
	public void setSeccionID(int seccionID) {
		this.seccionID = seccionID;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Seccion [seccionID=" + seccionID + ", nombre=" + nombre + "]";
	}
	
}

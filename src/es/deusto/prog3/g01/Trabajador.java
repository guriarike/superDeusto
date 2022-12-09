package es.deusto.prog3.g01;

public class Trabajador extends Persona {
	private int NSS;

	public Trabajador(int id, String correo, String contrasena, String nombre, String apellido)
			throws MiExcepcionExplicita {
		super(id, correo, contrasena, nombre, apellido);
		// TODO Auto-generated constructor stub
	}

	public int getNSS() {
		return NSS;
	}

	public void setNSS(int nSS) {
		NSS = nSS;
	}
	
	
	@Override
	public String toString() {
		return "Trabajador [NSS=" + NSS + "]";
	}


}

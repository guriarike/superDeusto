package es.deusto.prog3.g01;

public class Usuario extends  Persona{
	private int telefono;
	private int codigoPostal;
	
	
	// CONSTRUCTOR

	public Usuario(int id, String correo, String contrasena, String nombre, String apellido)
			throws MiExcepcionExplicita {
		super(id, correo, contrasena, nombre, apellido);
		// TODO Auto-generated constructor stub
		telefono = telefono;
		codigoPostal = codigoPostal;
	}
	
	//CONSTRUCTOR SIN PARAMETROS

	//GETTERS Y SETTERS
	
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		telefono = telefono;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		codigoPostal = codigoPostal;
	}
		
}

	

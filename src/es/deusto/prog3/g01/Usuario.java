package es.deusto.prog3.g01;

public class Usuario extends Persona {
	private int telefono;
	private int codigoPostal;

	// CONSTRUCTOR

	public Usuario(int id, String correo, String contrasena, String nombre, String apellido, int telefono, int codigoPostal) {
		super(id, correo, contrasena, nombre, apellido);
		this.telefono = telefono;
		this.codigoPostal = codigoPostal;
	}

	// CONSTRUCTOR SIN PARAMETROS
	public Usuario() {		
	}

	// GETTERS Y SETTERS

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
}

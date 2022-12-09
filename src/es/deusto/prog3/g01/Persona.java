package es.deusto.prog3.g01;

import java.util.regex.Pattern;

public class Persona {
	private int id;
	private String correo;
	private String nombre;
	private String apellido;
	private String contrasena;
	
	
	
	public Persona(int id, String correo, String contrasena, String nombre, String apellido) throws MiExcepcionExplicita {
		super();
		id = id;
		String er = "[A-Z,a-z]{3,20}@gmail.com";
		boolean correoCorrecto = Pattern.matches(er, correo);
		if (!correoCorrecto) {
			throw new MiExcepcionExplicita("El correo no tiene el formato adecuado");
		}
		correo = correo;
		contrasena = contrasena;
		nombre = nombre;
		apellido = apellido;
	}
	
	
	//getters y setters

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Override
	public String toString() {
		return "Persona [correo=" + correo + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	
}

package es.deusto.prog3.g01;

public class Persona {
	private int id;
	private String correo;
	private String nombre;
	private String apellido;
	private String contrasena;

	public Persona() {		
	}
	
	public Persona(int id, String correo, String contrasena, String nombre, String apellido) {
		this.id = id;
		this.correo = correo;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	// getters y setters

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
import java.time.LocalDateTime;
import java.util.Date;

public class Persona {
	private String nombre;
	private String apellido;
	private LocalDateTime fechaNacimiento;
	
	
	
	
	/*public Persona(String nombre, String apellido, LocalDateTime fechaNacimiento2) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento2;
	}
	*/
	//getters y setters
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
	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}

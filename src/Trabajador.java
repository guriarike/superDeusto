import java.util.Date;

public class Trabajador extends Persona{
	private int numeroSeguridadSocial;
	private String email;
	private int numeroHorasSemanales;
	
	//constructor
	public Trabajador(String nombre, String apellido, Date fechaNacimiento, int numeroSeguridadSocial, String email,
			int numeroHorasSemanales) {
		super(nombre, apellido, fechaNacimiento);
		this.numeroSeguridadSocial = numeroSeguridadSocial;
		this.email = email;
		this.numeroHorasSemanales = numeroHorasSemanales;
	}
	
	//getters y setters

	public int getNumeroSeguridadSocial() {
		return numeroSeguridadSocial;
	}

	public void setNumeroSeguridadSocial(int numeroSeguridadSocial) {
		this.numeroSeguridadSocial = numeroSeguridadSocial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumeroHorasSemanales() {
		return numeroHorasSemanales;
	}

	public void setNumeroHorasSemanales(int numeroHorasSemanales) {
		this.numeroHorasSemanales = numeroHorasSemanales;
	}
	
	
	
		
	

}

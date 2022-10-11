
public class Marca {
	private String nombreMarca;
	private int idMarca;
	
	
	//constructor
	public Marca(String nombreMarca, int idMarca) {
		super();
		this.nombreMarca = nombreMarca;
		this.idMarca = idMarca;
	}
//	getters y setters


	public String getNombreMarca() {
		return nombreMarca;
	}


	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}


	public int getIdMarca() {
		return idMarca;
	}


	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	
	
	
}

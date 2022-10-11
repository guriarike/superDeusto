
public class Moto {
	private int idMoto;
	private String modelo;
	private int caballos;
	private int numeroPedidosPortablesSimultaneamente;
	
	//constructor
	public Moto(int idMoto,String modelo, int caballos, int numeroPedidosPortablesSimultaneamente) {
		super();
		this.idMoto= idMoto;
		this.modelo = modelo;
		this.caballos = caballos;
		this.numeroPedidosPortablesSimultaneamente = numeroPedidosPortablesSimultaneamente;
	}

	public int getIdMoto() {
		return idMoto;
	}

	public void setIdMoto(int idMoto) {
		this.idMoto = idMoto;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getCaballos() {
		return caballos;
	}

	public void setCaballos(int caballos) {
		this.caballos = caballos;
	}

	public int getNumeroPedidosPortablesSimultaneamente() {
		return numeroPedidosPortablesSimultaneamente;
	}

	public void setNumeroPedidosPortablesSimultaneamente(int numeroPedidosPortablesSimultaneamente) {
		this.numeroPedidosPortablesSimultaneamente = numeroPedidosPortablesSimultaneamente;
	}
	
	//getters y setters
	

}

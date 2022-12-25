package es.deusto.prog3.g01;

public class Pago extends Usuario{
	
	private String CuentaBancaria;
	private String Caducidad;
	private String Cvv;
	
	public Pago(int id, String correo, String nombre, String apellido, String contrasena, String cuentaBancaria, String caducidad, String cvv) {
		super(id, correo, nombre, apellido, contrasena);
		CuentaBancaria = cuentaBancaria;
		Caducidad = caducidad;
		Cvv = cvv;
		
	}
	
	
	
	public String getCuentaBancaria() {
		return CuentaBancaria;
	}
	
	public void setCuentaBancaria(String cuentaBancaria) {
		CuentaBancaria = cuentaBancaria;
	}
	
	public String getCaducidad() {
		return Caducidad;
	}
	
	public void setCaducidad(String caducidad) {
		Caducidad = caducidad;
	}
	
	public String getCvv() {
		return Cvv;
	}
	
	public void setCvv(String cvv) {
		Cvv = cvv;
	}
	
	public String toString() {
		return "Pago [CuentaBancaria=" + CuentaBancaria + ", Caducidad=" + Caducidad + ", Cvv=" + Cvv +", toString()=" + super.toString() + "]";
	}

}

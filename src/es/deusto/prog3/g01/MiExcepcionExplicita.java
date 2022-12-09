package es.deusto.prog3.g01;

public class MiExcepcionExplicita extends Exception {
	private String mensaje;

	public MiExcepcionExplicita(String m) {
		mensaje = m;
	}

	public String toString() {
		return mensaje;
	}

}

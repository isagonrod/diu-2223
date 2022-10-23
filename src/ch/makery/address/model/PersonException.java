package ch.makery.address.model;

public class PersonException extends Exception {
	private String mensaje;

	public PersonException() {
	}

	public PersonException(String ms) {
		this.mensaje = ms;
	}

	public String imprimirMensaje() {
		return this.mensaje;
	}
}

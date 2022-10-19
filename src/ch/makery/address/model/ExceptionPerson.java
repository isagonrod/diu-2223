package ch.makery.address.model;

public class ExceptionPerson extends Exception {
	private String mensaje;

	public ExceptionPerson() {
	}

	public ExceptionPerson(String ms) {
		this.mensaje = ms;
	}

	public String imprimirMensaje() {
		return this.mensaje;
	}
}

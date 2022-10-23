package ch.makery.address.model;

public class PersonException extends Exception {
	private String message;

	public PersonException() {
	}

	public PersonException(String ms) {
		this.message = ms;
	}

	public String printMessage() {
		return this.message;
	}
}

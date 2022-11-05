package ch.makery.address.model;

/**
 * Exception class for a Person.
 *
 * @author Isa González
 */
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

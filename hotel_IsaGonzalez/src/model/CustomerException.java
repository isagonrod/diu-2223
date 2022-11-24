package model;

/**
 * Clase que controla las excepciones de la clase Customer (cliente).
 *
 * @author Isa González
 */
public class CustomerException extends Exception {
    private String message;

    public CustomerException() {}

    public CustomerException(String message) {
        this.message = message;
    }

    public String printMessage() {
        return this.message;
    }
}

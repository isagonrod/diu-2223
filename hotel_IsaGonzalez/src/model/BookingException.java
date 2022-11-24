package model;

/**
 * Clase que controla las excepciones de la clase Booking (reserva).
 *
 * @author Isa González
 */
public class BookingException extends Exception {
    private String message;

    public BookingException() {}

    public BookingException(String message) {
        this.message = message;
    }

    public String printMessage() {
        return this.message;
    }
}

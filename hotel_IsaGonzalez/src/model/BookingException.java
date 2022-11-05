package model;

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

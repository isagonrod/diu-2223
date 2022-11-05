package model;

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

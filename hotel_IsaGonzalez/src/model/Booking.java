package model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Booking {
    private IntegerProperty codReserva;
    private StringProperty fechaLlegada;
    private StringProperty fechaSalida;
    private IntegerProperty numHabitaciones;
    private StringProperty tipoHabitacion;
    private BooleanProperty fumador;
    private StringProperty regimenAlojamiento;
    private StringProperty dniCliente;

    public Booking(int codReserva, String fechaEntrada) {
        this.codReserva = new SimpleIntegerProperty(codReserva);
        this.fechaLlegada = new SimpleStringProperty(fechaEntrada);
    }
}

package model;

import java.time.LocalDate;

public class BookingVO {
    private int codReserva;
    private LocalDate fechaLlegada;
    private LocalDate fechaSalida;
    private int numHabitaciones;
    private String tipoHabitacion;
    private boolean fumador;
    private String regimenAlojamiento;
    private String dniCliente;

    public BookingVO() {}

    public BookingVO(int codReserva, String fechaEntrada) {
        this.codReserva = codReserva;
        this.fechaLlegada = LocalDate.parse(fechaEntrada);
    }

    public int getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(int codReserva) {
        this.codReserva = codReserva;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public boolean isFumador() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador = fumador;
    }

    public String getRegimenAlojamiento() {
        return regimenAlojamiento;
    }

    public void setRegimenAlojamiento(String regimenAlojamiento) {
        this.regimenAlojamiento = regimenAlojamiento;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String toString() {
        return "RESERVA " + codReserva
                + "\n\tFecha de llegada: " + fechaLlegada + " - Fecha de salida: " + fechaSalida
                + "\n\tNº de habitaciones: " + numHabitaciones + " | Tipo de habitación: " + tipoHabitacion
                + "\n\t¿Fumador? " + fumador
                + "\n\tRégimen de alojamiento: " + regimenAlojamiento;
    }
}

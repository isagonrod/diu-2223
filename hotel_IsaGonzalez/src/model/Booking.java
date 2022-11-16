package model;

import javafx.beans.property.*;
import javafx.scene.control.SpinnerValueFactory;
import util.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class Booking {
    private IntegerProperty codReserva;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaLlegada;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaSalida;
    private IntegerProperty numHabitaciones;
    private StringProperty tipoHabitacion;
    private BooleanProperty fumador;
    private StringProperty regimenAlojamiento;
    private StringProperty dniCliente;

    public Booking() {}

    public Booking(int codReserva, LocalDate fechaEntrada) {
        this.codReserva = new SimpleIntegerProperty(codReserva);
        this.fechaLlegada = fechaEntrada;
    }

    public int getCodReserva() {
        return codReserva.get();
    }

    public IntegerProperty codReservaProperty() {
        return codReserva;
    }

    public void setCodReserva(int codReserva) {
        this.codReserva.set(codReserva);
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

    public SpinnerValueFactory getNumHabitaciones() {
        return numHabitaciones.get();
    }

    public IntegerProperty numHabitacionesProperty() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones.set(numHabitaciones);
    }

	public String getTipoHabitacion() {
		return tipoHabitacion.get();
	}

	public StringProperty tipoHabitacionProperty() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion.set(tipoHabitacion);
	}

	public boolean isFumador() {
        return fumador.get();
    }

    public BooleanProperty fumadorProperty() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador.set(fumador);
    }

    public String getRegimenAlojamiento() {
        return regimenAlojamiento.get();
    }

    public StringProperty regimenAlojamientoProperty() {
        return regimenAlojamiento;
    }

    public void setRegimenAlojamiento(String regimenAlojamiento) {
        this.regimenAlojamiento.set(regimenAlojamiento);
    }

    public String getDniCliente() {
        return dniCliente.get();
    }

    public StringProperty dniClienteProperty() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente.set(dniCliente);
    }
}

package model;

import javafx.beans.property.*;
import util.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Clase para las reservas, donde se indican los 'properties' en sus atributos.
 *
 * @author Isa González
 */
public class Booking {
    private IntegerProperty codReserva;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private ObjectProperty<LocalDate> fechaLlegada;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private ObjectProperty<LocalDate> fechaSalida;
    private IntegerProperty numHabitaciones;
    private StringProperty tipoHabitacion;
    private BooleanProperty fumador;
    private StringProperty regimenAlojamiento;
    private StringProperty dniCliente;

    public Booking() {
        this.codReserva = new SimpleIntegerProperty();
        this.fechaLlegada = new SimpleObjectProperty<>();
        this.fechaSalida = new SimpleObjectProperty<>();
        this.numHabitaciones = new SimpleIntegerProperty();
        this.tipoHabitacion = new SimpleStringProperty();
        this.fumador = new SimpleBooleanProperty();
        this.regimenAlojamiento = new SimpleStringProperty();
        this.dniCliente = new SimpleStringProperty();
    }

    public Booking(int codReserva, LocalDate fechaEntrada) {
        this.codReserva = new SimpleIntegerProperty(codReserva);
        this.fechaLlegada = new SimpleObjectProperty<>(fechaEntrada);
        this.fechaSalida = new SimpleObjectProperty<>();
        this.numHabitaciones = new SimpleIntegerProperty();
        this.tipoHabitacion = new SimpleStringProperty();
        this.fumador = new SimpleBooleanProperty();
        this.regimenAlojamiento = new SimpleStringProperty();
        this.dniCliente = new SimpleStringProperty();
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
        return fechaLlegada.get();
    }

    public ObjectProperty<LocalDate> fechaLlegadaProperty() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada.set(fechaLlegada);
    }

    public LocalDate getFechaSalida() {
        return fechaSalida.get();
    }

    public ObjectProperty<LocalDate> fechaSalidaProperty() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida.set(fechaSalida);
    }

    public int getNumHabitaciones() {
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

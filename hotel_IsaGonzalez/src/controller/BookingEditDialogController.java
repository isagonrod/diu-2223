package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Booking;
import model.HotelModel;
import org.controlsfx.dialog.Dialogs;
import util.DateUtil;

import java.awt.*;

/**
 *
 * @autor Isa González
 */
public class BookingEditDialogController {
    private TextField codReservaField;
    @FXML
    private DatePicker fechaLlegadaField;
    @FXML
    private DatePicker fechaSalidaField;
    @FXML
    private Spinner numHabitacionesField;
    @FXML
    private ComboBox<String> tipoHabitacionField;
    @FXML
    private Checkbox fumadorField;
    @FXML
    private TextField regimenAlojamientoField;

    private Stage dialogStage;
    private Booking booking;
    private boolean okClicked = false;
    private boolean isNew;

	@FXML
    private void initialize() {}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setBooking(Booking booking, boolean isNew) {
        this.booking = booking;
        this.isNew = isNew;

        codReservaField.setText(String.valueOf(booking.getCodReserva()));
        fechaLlegadaField.setValue(booking.getFechaLlegada());
        fechaSalidaField.setValue(booking.getFechaSalida());
        numHabitacionesField.getValueFactory().setValue(booking.getNumHabitaciones());
        tipoHabitacionField.setValue(booking.getTipoHabitacion());
        fumadorField.setState(booking.isFumador());
        regimenAlojamientoField.setText(booking.getRegimenAlojamiento());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void pushOk() {
        if (isInputValid()) {
            try {
                booking.setCodReserva(Integer.parseInt(codReservaField.getText()));
                booking.setFechaLlegada(fechaLlegadaField.getValue());
                booking.setFechaSalida(fechaSalidaField.getValue());
                booking.setNumHabitaciones(Integer.parseInt(String.valueOf(numHabitacionesField.getValueFactory())));
                booking.setTipoHabitacion(tipoHabitacionField.getValue());
                booking.setFumador(fumadorField.getState());
                booking.setRegimenAlojamiento(regimenAlojamientoField.getText());
                if (isNew) {
                    new HotelModel().saveBooking(booking);
                } else {
                    new HotelModel().editBooking(booking);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            okClicked = true;
            dialogStage.close();
        }
    }

    private void pushCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (codReservaField.getText() == null || codReservaField.getText().length() == 0) {
            errorMessage += "Código de reserva no válido";
        }
        if (fechaLlegadaField.getValue() == null || fechaLlegadaField.getValue().equals('0')) {
            errorMessage += "Fecha de llegada no válida";
        }
        if (fechaLlegadaField.getValue() == null || fechaSalidaField.getValue().equals('0')) {
            errorMessage += "Fecha de salida no válida";
        }
        if (numHabitacionesField.getValueFactory() == null || numHabitacionesField.getValueFactory().equals('0')) {
            errorMessage += "Número de habitaciones no válido";
        }
        if (tipoHabitacionField.getValue() == null || tipoHabitacionField.getValue().length() == 0) {
            errorMessage += "Tipo de habitación no válido";
        }
        if (!fumadorField.getState()) {
            errorMessage += "Elección de fumador no válida";
        }
        if (regimenAlojamientoField.getText() == null || regimenAlojamientoField.getText().length() == 0) {
            errorMessage += "Régimen de alojamiento no válido";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Dialogs.create()
                    .title("Campos inválidos")
                    .masthead("Por favor, corrija los campos inválidos")
                    .message(errorMessage)
                    .showError();
            return false;
        }
    }
}

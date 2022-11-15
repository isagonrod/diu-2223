package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
    @FXML
    private TextField codReservaField;
    @FXML
    private TextField fechaLlegadaField;
    @FXML
    private TextField fechaSalidaField;
    @FXML
    private Spinner numHabitacionesField;
    @FXML
    private ComboBox<String> tipoHabitacionField;
    @FXML
    private TextField fumadorField;
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
        fechaLlegadaField.setText(DateUtil.format(booking.getFechaLlegada()));
        fechaSalidaField.setText(DateUtil.format(booking.getFechaSalida()));
        numHabitacionesField.setText(String.valueOf(booking.getNumHabitaciones()));
        tipoHabitacionField.setText(booking.getTipoHabitacion());
        fumadorField.setText(String.valueOf(booking.isFumador()));
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
                booking.setFechaLlegada(DateUtil.parse(fechaLlegadaField.getText()));
                booking.setFechaSalida(DateUtil.parse(fechaSalidaField.getText()));
                booking.setNumHabitaciones(Integer.parseInt(numHabitacionesField.getText()));
                booking.setTipoHabitacion(tipoHabitacionField.getText());
                booking.setFumador(Boolean.parseBoolean(fumadorField.getText()));
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
        if (fechaLlegadaField.getText() == null || fechaLlegadaField.getText().length() == 0) {
            errorMessage += "Fecha de llegada no válida";
        }
        if (fechaLlegadaField.getText() == null || fechaSalidaField.getText().length() == 0) {
            errorMessage += "Fecha de salida no válida";
        }
        if (numHabitacionesField.getText() == null || numHabitacionesField.getText().length() == 0) {
            errorMessage += "Número de habitaciones no válido";
        }
        if (tipoHabitacionField.getText() == null || tipoHabitacionField.getText().length() == 0) {
            errorMessage += "Tipo de habitación no válido";
        }
        if (fumadorField.getText() == null || fumadorField.getText().length() == 0) {
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

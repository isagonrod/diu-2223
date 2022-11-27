package controller;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Booking;
import model.Customer;
import model.HotelModel;
import util.ModalDialog;

/**
 * Controlador de la ventana de edición y creación de reservas.
 *
 * @author Isa González
 */
public class BookingEditDialogController {
	// Datos del cliente
	@FXML
    private TextField dniField;
	@FXML
	private TextField nombreField;
	@FXML
	private TextField apellidosField;
	@FXML
	private TextField direccionField;
	@FXML
	private TextField localidadField;
	@FXML
	private TextField provinciaField;

	// Campos vacíos para la nueva reserva o para editar una reserva
	private TextField codReservaField;
    @FXML
    private DatePicker fechaLlegadaField;
    @FXML
    private DatePicker fechaSalidaField;
    @FXML
    private TextField numHabitacionesField;
    @FXML
    private ComboBox<String> tipoHabitacionField;
    @FXML
    private CheckBox fumadorField;
    @FXML
    private ToggleGroup regimenAlojamientoGroup;
    @FXML
    private RadioButton regimenAlojamientoRadio1;
    @FXML
    private RadioButton regimenAlojamientoRadio2;
    @FXML
    private RadioButton regimenAlojamientoRadio3;

    private Stage dialogStage;
    private Booking booking;
    private boolean okClicked = false;
    private boolean isNew;

	@FXML
    private void initialize() {
        this.tipoHabitacionField.getItems().add("Doble de uso individual");
        this.tipoHabitacionField.getItems().add("Doble");
        this.tipoHabitacionField.getItems().add("Junior Suite");
        this.tipoHabitacionField.getItems().add("Suite");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setBooking(Booking booking, Customer customer, boolean isNew) {
        this.booking = booking;
        this.isNew = isNew;

        if (!isNew){
            codReservaField.setText(String.valueOf(booking.getCodReserva()));
            fechaLlegadaField.setValue(booking.getFechaLlegada());
            fechaSalidaField.setValue(booking.getFechaSalida());
            numHabitacionesField.setText(String.valueOf(booking.getNumHabitaciones()));
            tipoHabitacionField.setValue(booking.getTipoHabitacion());
            fumadorField.setSelected(booking.isFumador());
            switch (booking.getRegimenAlojamiento()) {
                case "Alojamiento y desayuno" -> regimenAlojamientoRadio1.setSelected(true);
                case "Media pensión" -> regimenAlojamientoRadio2.setSelected(true);
                case "Pensión completa" -> regimenAlojamientoRadio3.setSelected(true);
            }
        }
        else {
            numHabitacionesField.setText("0");
        }

        this.dniField.setText(customer.getDni());
        this.nombreField.setText(customer.getNombre());
        this.apellidosField.setText(customer.getApellidos());
        this.direccionField.setText(customer.getDireccion());
        this.localidadField.setText(customer.getLocalidad());
        this.provinciaField.setText(customer.getProvincia());
    }

	/**
	 * Método que comprueba si se ha pulsado o no el botón de editar.
	 *
	 * @return 'true' si se ha pulsado 'Editar', 'false' si se ha pulsado 'Nuevo'.
	 */
    public boolean isOkClicked() {
        return okClicked;
    }

	/**
	 * Método que controla si se ha pulsado el botón 'OK' después de introducir o cambiar los datos de la reserva.
	 * Comprueba, antes de cerrar la ventana, si es una nueva reserva (entonces la guarda en la base de datos) o
	 * si la reserva ya existe (entonces la modifica en la base de datos).
	 */
    @FXML
    private void pushOk() {
        if (isInputValid()) {
            try {
                HotelModel model = new HotelModel();
                booking.setFechaLlegada(fechaLlegadaField.getValue());
                booking.setFechaSalida(fechaSalidaField.getValue());
                booking.setNumHabitaciones(Integer.parseInt(numHabitacionesField.getText()));
                booking.setTipoHabitacion(tipoHabitacionField.getValue());
                booking.setFumador(fumadorField.isSelected());
                booking.setRegimenAlojamiento(
                        ((RadioButton)regimenAlojamientoGroup.selectedToggleProperty().getValue()).getText()
                );
                if (isNew) {
                    booking.setCodReserva(model.saveBooking(booking));
                } else {
                    booking.setCodReserva(Integer.parseInt(codReservaField.getText()));
                    model.editBooking(booking);
                }
                model.getRepository().closeConnection();
            } catch (CommunicationsException ex) {
                ModalDialog.createError("Error de conectividad",
                        "Base de datos no disponible",
                        "Por favor conecte la base de datos y vuelva a ejecutar la aplicación.");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            okClicked = true;
            dialogStage.close();
        }
    }

	/**
	 * Método que controla si se ha pulsado el botón 'Cancelar', cerrando la ventana sin guardar ningún cambio.
	 */
	@FXML
    private void pushCancel() {
        dialogStage.close();
    }

	/**
	 * Método para controlar que los datos se han introducido de forma correcta.
	 *
	 * @return 'true' si se han introducido bien los datos, 'false' si hay algún error en algún campo.
	 */
    private boolean isInputValid() {
        String errorMessage = "";
        if (fechaLlegadaField.getValue() == null) {
            errorMessage += "Fecha de llegada no válida\n";
        }
        if (fechaLlegadaField.getValue() == null) {
            errorMessage += "Fecha de salida no válida\n";
        }
        if (numHabitacionesField.getText() == null || numHabitacionesField.getText().equals("")) {
            errorMessage += "Número de habitaciones no válido\n";
        }
        if (tipoHabitacionField.getValue() == null || tipoHabitacionField.getValue().length() == 0) {
            errorMessage += "Tipo de habitación no válido\n";
        }
        if (!fumadorField.isSelected()) {
            errorMessage += "Elección de fumador no válida\n";
        }
        if (regimenAlojamientoGroup.selectedToggleProperty() == null) {
            errorMessage += "Régimen de alojamiento no válido\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            ModalDialog.createError("Campos inválidos",
                    "Por favor, corrija los campos inválidos",
                    errorMessage);
            return false;
        }
    }
}

package controller;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;
import model.Booking;
import model.Customer;
import model.HotelModel;
import util.ModalDialog;

import java.awt.*;

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
                booking.setCodReserva(Integer.parseInt(codReservaField.getText()));
                booking.setFechaLlegada(fechaLlegadaField.getValue());
                booking.setFechaSalida(fechaSalidaField.getValue());
                booking.setNumHabitaciones(Integer.parseInt(String.valueOf(numHabitacionesField.getValueFactory())));
                booking.setTipoHabitacion(tipoHabitacionField.getValue());
                booking.setFumador(fumadorField.getState());
                booking.setRegimenAlojamiento(regimenAlojamientoField.getText());
                if (isNew) {
                    model.saveBooking(booking);
                } else {
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
            ModalDialog.createError("Campos inválidos",
                    "Por favor, corrija los campos inválidos",
                    errorMessage);
            return false;
        }
    }

	// TODO: Método que rellene los campos del cliente con los datos del que se seleccionó en la ventana anterior
	private void addFieldsCustomer(Customer customer) {
		this.dniField.setText(customer.getDni());
		this.nombreField.setText(customer.getNombre());
		this.apellidosField.setText(customer.getApellidos());
		this.direccionField.setText(customer.getDireccion());
		this.localidadField.setText(customer.getLocalidad());
		this.provinciaField.setText(customer.getProvincia());
	}
}

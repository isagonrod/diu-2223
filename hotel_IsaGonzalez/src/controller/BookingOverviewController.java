package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.HotelMainApp;
import model.Booking;

import javafx.scene.control.TableView;
import model.BookingException;
import model.HotelModel;
import org.controlsfx.dialog.Dialogs;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Controlador para cargar la ventana de reservas de un cliente determinado.
 *
 * @author Isa Gonzalez
 */
public class BookingOverviewController {
	@FXML
	private TableView<Booking> bookingTable;
	@FXML
	private TableColumn<Booking, Number> codReserva;
	@FXML
	private TableColumn<Booking, LocalDate> fechaLlegada;

	@FXML
	private Label codReservaLabel;
	@FXML
	private Label fechaLlegadaLabel;
	@FXML
	private Label fechaSalidaLabel;
	@FXML
	private Label numHabitacionesLabel;
	@FXML
	private Label tipoHabitacionLabel;
	@FXML
	private Label fumadorLabel;
	@FXML
	private Label regimenAlojamientoLabel;

	private HotelMainApp mainApp;

	@FXML
	private void initialize() {
		codReserva.setCellValueFactory(cellData -> cellData.getValue().codReservaProperty());
		fechaLlegada.setCellValueFactory(cellData -> cellData.getValue().fechaLlegadaProperty());
		showBookingDetails(null);
		bookingTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showBookingDetails(newValue)));
	}

	private void showBookingDetails(Booking booking) {
		if (booking != null) {
			codReservaLabel.setText(String.valueOf(booking.getCodReserva()));
			fechaLlegadaLabel.setText(String.valueOf(booking.getFechaLlegada()));
			fechaSalidaLabel.setText(String.valueOf(booking.getFechaSalida()));
			numHabitacionesLabel.setText(String.valueOf(booking.getNumHabitaciones()));
			tipoHabitacionLabel.setText(booking.getTipoHabitacion());
			fumadorLabel.setText(String.valueOf(booking.isFumador()));
			regimenAlojamientoLabel.setText(booking.getRegimenAlojamiento());
		} else {
			codReservaLabel.setText("");
			fechaLlegadaLabel.setText("");
			fechaSalidaLabel.setText("");
			numHabitacionesLabel.setText("");
			tipoHabitacionLabel.setText("");
			fumadorLabel.setText("");
			regimenAlojamientoLabel.setText("");
		}
	}

	/**
	 * Método para borrar una reserva que esté seleccionada al pulsar el botón "Borrar".
	 */
	@FXML
	private void handleDeleteBooking() {
		int selectedIndex = bookingTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			try {
				Booking bookingToDelete = bookingTable.getItems().get(selectedIndex);
				new HotelModel().deleteBooking(bookingToDelete);
				bookingTable.getItems().remove(selectedIndex);
			} catch (BookingException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			Dialogs.create()
					.title("No selection")
					.masthead("No booking selected")
					.message("Please select a booking in the table")
					.showWarning();
		}
	}

	/**
	 * Método para crear una nueva reserva al pulsar sobre el botón "Nuevo".
	 * Al pulsar este botón, se abrirá una ventana emergente donde se tendrán que introducir los datos
	 * de la nueva reserva.
	 */
	@FXML
	private void handleNewBooking() {
		Booking tempBooking = new Booking();
		boolean okClicked = this.showBookingEditDialog(tempBooking, true);
		if (okClicked) {
			mainApp.getBookings().add(tempBooking);
		}
	}

	/**
	 * Método para editar una reserva ya existente en la base de datos al pulsar sobre el botón "Editar".
	 * Al pulsar este botón, se abrirá una ventana emergente donde se tendrán que introducir los datos
	 * de la reserva seleccionada.
	 */
	@FXML
	private void handleEditBooking() {
		Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
		if (selectedBooking != null) {
			boolean okClicked = this.showBookingEditDialog(selectedBooking, false);
			if (okClicked) {
				showBookingDetails(selectedBooking);
			}
		} else {
			Dialogs.create()
					.title("No selection")
					.masthead("No booking selected")
					.message("Please select a booking in the table")
					.showWarning();
		}
	}

	/**
	 * Método para mostrar la ventana de edición y creación de una nueva reserva.
	 *
	 * @param booking Reserva.
	 * @param isNew Si es nueva o no.
	 * @return Si se ha pulsado sobre el botón OK.
	 */
	public boolean showBookingEditDialog(Booking booking, boolean isNew) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HotelMainApp.class.getResource("../view/BookingEditDialog.fxml"));
			AnchorPane pane = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Editar Reserva");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApp.getPrimaryStage());
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);

			BookingEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBooking(booking, isNew);
			booking.codReservaProperty();
			booking.getFechaLlegada();

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public void setHotelMainApp(HotelMainApp mainApp) {
		// TODO: Revisar para que se inicialice la app aunque esté la base de datos apagada
		try {
			this.mainApp = mainApp;
			bookingTable.setItems(mainApp.getBookings());
		} catch (Exception ex) {
			Dialogs.create()
					.title("No database")
					.masthead("Not found any database")
					.message("Please connect some database")
					.showWarning();
		}
	}
}

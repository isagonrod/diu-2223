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

	@FXML
	private void handleNewBooking() {
		Booking tempBooking = new Booking();
		boolean okClicked = this.showBookingEditDialog(tempBooking, true);
		if (okClicked) {
			mainApp.getBookings().add(tempBooking);
		}
	}

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

	public boolean showBookingEditDialog(Booking booking, boolean isNew) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HotelMainApp.class.getResource("view/BookingEditDialog.fxml"));
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
}

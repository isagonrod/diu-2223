package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.HotelMainApp;
import model.Customer;
import model.CustomerException;
import model.HotelModel;
import org.controlsfx.dialog.Dialogs;

import java.io.IOException;

public class CustomerOverviewController {
	@FXML
	private TableView<Customer> customerTable;
	@FXML
	private TableColumn<Customer, String> apellidos;
	@FXML
	private TableColumn<Customer, String> nombre;

	@FXML
	private Label dniLabel;
	@FXML
	private Label nombreLabel;
	@FXML
	private Label apellidosLabel;
	@FXML
	private Label direccionLabel;
	@FXML
	private Label localidadLabel;
	@FXML
	private Label provinciaLabel;

	@FXML
	private TextField dniSearch;

	private HotelMainApp mainApp;

	public CustomerOverviewController() {}

	@FXML
	private void initialize() {
		nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		apellidos.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
		showCustomerDetails(null);
		customerTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCustomerDetails(newValue));
	}

	public void setHotelMainApp(HotelMainApp mainApp) {
		// TODO: Revisar para que se inicialice la app aunque esté la base de datos apagada
		try {
			this.mainApp = mainApp;
			customerTable.setItems(mainApp.getCustomers());
		} catch (Exception ex) {
			Dialogs.create()
					.title("No database")
					.masthead("Not found any database")
					.message("Please connect some database")
					.showWarning();
		}
	}

	private void showCustomerDetails(Customer customer) {
		if (customer != null) {
			dniLabel.setText(customer.getDni());
			nombreLabel.setText(customer.getNombre());
			apellidosLabel.setText(customer.getApellidos());
			direccionLabel.setText(customer.getDireccion());
			localidadLabel.setText(customer.getLocalidad());
			provinciaLabel.setText(customer.getProvincia());
		} else {
			dniLabel.setText("");
			nombreLabel.setText("");
			apellidosLabel.setText("");
			direccionLabel.setText("");
			localidadLabel.setText("");
			provinciaLabel.setText("");
		}
	}

	@FXML
	private void handleDeleteCustomer() {
		int selectedIndex = customerTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			try {
				Customer customerToDelete = customerTable.getItems().get(selectedIndex);
				new HotelModel().deleteCustomer(customerToDelete);
				customerTable.getItems().remove(selectedIndex);
			} catch (CustomerException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			Dialogs.create()
					.title("No selection")
					.masthead("No person selected")
					.message("Please select a person in the table")
					.showWarning();
		}
	}

	@FXML
	private void handleNewCustomer() {
		Customer tempCustomer = new Customer();
		boolean okClicked = this.showCustomerEditDialog(tempCustomer, true);
		if (okClicked) {
			mainApp.getCustomers().add(tempCustomer);
		}
	}

	@FXML
	private void handleEditCustomer() {
		Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
		if (selectedCustomer != null) {
			boolean okClicked = this.showCustomerEditDialog(selectedCustomer, false);
			if (okClicked) {
				showCustomerDetails(selectedCustomer);
			}
		} else {
			Dialogs.create()
					.title("No selection")
					.masthead("No person selected")
					.message("Please select a person in the table")
					.showWarning();
		}
	}

	@FXML
	private void handleBookings() {
		Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
		if (selectedCustomer != null) {
			// TODO: Mandar a la ventana de BookingOverview
			boolean okClicked = this.showBookingOverview(selectedCustomer, false);
		} else {
			Dialogs.create()
					.title("No selection")
					.masthead("No person selected")
					.message("Please select a person in the table")
					.showWarning();
		}
	}

	public boolean showCustomerEditDialog(Customer customer, boolean isNew) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HotelMainApp.class.getResource("../view/CustomerEditDialog.fxml"));
			AnchorPane pane = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Editar Cliente");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApp.getPrimaryStage());
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);

			CustomerEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCustomer(customer, isNew);
			customer.apellidosProperty();
			customer.nombreProperty();

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean showBookingOverview(Customer customer, boolean isNew) {
		// TODO: Mostrar ventana de BookingOverview
		return false;
	}

	@FXML
	public void showCustomerSearchedByDNI(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			try {
				Customer customer = new HotelModel().getCustomer(dniSearch.getText());
				if (customer != null) {
					showCustomerDetails(customer);
				} else {
					Dialogs.create()
							.title("Not found")
							.masthead("Not found")
							.message("No DNI matching the text")
							.showWarning();
				}
			} catch (CustomerException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}

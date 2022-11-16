package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.HotelMainApp;
import model.Customer;
import model.CustomerException;
import model.HotelModel;
import org.controlsfx.dialog.Dialogs;

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
		this.mainApp = mainApp;
		customerTable.setItems(mainApp.getCustomers());
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
}

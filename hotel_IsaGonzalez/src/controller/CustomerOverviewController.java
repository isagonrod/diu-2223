package controller;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.HotelMainApp;
import model.Customer;
import model.CustomerException;
import model.HotelModel;
import util.ModalDialog;

import java.io.IOException;

/**
 * Controlador de la ventana principal del cliente, en la que se muestra una tabla en la parte izquierda
 * y los datos de un cliente concreto seleccionado de dicha tabla en la parte derecha.
 *
 * @author Isa González
 */
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

    public CustomerOverviewController() {
    }

    /**
     * Método para inicializar la ventana y cargar los datos de los clientes desde la base de datos.
     */
    @FXML
    private void initialize() {
        nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidos.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        showCustomerDetails(null);
        customerTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showCustomerDetails(newValue));
    }

    public void setHotelMainApp(HotelMainApp mainApp) {
        this.mainApp = mainApp;
        customerTable.setItems(mainApp.getCustomers());
    }

    /**
     * Método que sirve para mostrar los datos del cliente en la parte derecha de la ventana al pulsar
     * sobre uno de los clientes de la lista de la parte izquierda.
     *
     * @param customer El cliente pulsado en la tabla de la izquierda.
     */
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

    /**
     * Método para borrar un cliente que esté seleccionado al pulsar el botón "Borrar".
     */
    @FXML
    private void handleDeleteCustomer() {
        int selectedIndex = customerTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            try {
                HotelModel model = new HotelModel();
                Customer customerToDelete = customerTable.getItems().get(selectedIndex);
                model.deleteCustomer(customerToDelete);
                customerTable.getItems().remove(selectedIndex);
                model.getRepository().closeConnection();
            } catch (CommunicationsException ex) {
                ModalDialog.createError("Error de conectividad",
                        "Base de datos no disponible",
                        "Por favor conecte la base de datos y vuelva a ejecutar la aplicación.");
            } catch (CustomerException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            ModalDialog.createWarning("Acción vacía",
                    "No se ha seleccionado ningún cliente",
                    "Por favor seleccione un cliente en la tabla.");
        }
    }

    /**
     * Método para crear un nuevo cliente al pulsar sobre el botón "Nuevo".
     * Al pulsar este botón, se abrirá una ventana emergente donde se tendrán que introducir los datos
     * del nuevo cliente.
     */
    @FXML
    private void handleNewCustomer() {
        Customer tempCustomer = new Customer();
        boolean okClicked = this.showCustomerEditDialog(tempCustomer, true);
        if (okClicked) {
            mainApp.getCustomers().add(tempCustomer);
        }
    }

    /**
     * Método para editar un cliente ya existente en la base de datos al pulsar sobre el botón "Editar".
     * Al pulsar este botón, se abrirá una ventana emergente donde se tendrán que introducir los datos
     * del cliente seleccionado.
     */
    @FXML
    private void handleEditCustomer() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            boolean okClicked = this.showCustomerEditDialog(selectedCustomer, false);
            if (okClicked) {
                showCustomerDetails(selectedCustomer);
            }
        } else {
            ModalDialog.createWarning("Acción vacía",
                    "No se ha seleccionado ningún cliente",
                    "Por favor seleccione un cliente en la tabla.");
        }
    }

    /**
     * Método para abrir la ventana de reservas de un cliente seleccionado al pulsar sobre el botón "Reservas".
     * Al pulsar este botón, se abrirá una nueva ventana donde se verá una lista de reservas del cliente
     * seleccionado a la izquierda y a la derecha se ven los datos de una reserva concreta al seleccionarla.
     */
    @FXML
    private void handleBookings() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            // TODO: Mandar a la ventana de BookingOverview
            boolean okClicked = this.showBookingOverview(selectedCustomer, false);
        } else {
            ModalDialog.createWarning("Acción vacía",
                    "No se ha seleccionado ningún cliente",
                    "Por favor seleccione un cliente en la tabla.");
        }
    }

    /**
     * Método para mostrar la ventana de edición y creación de un nuevo cliente.
     *
     * @param customer Cliente
     * @param isNew    Si es nuevo o no
     * @return Si se ha pulsado sobre el botón OK.
     */
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

    /**
     * Método para mostrar la venta de reservas.
     *
     * @param customer Cliente seleccionado.
     * @param isNew    'false' si ya está en la base de datos, 'true' si es nuevo.
     * @return ?
     */
    public boolean showBookingOverview(Customer customer, boolean isNew) {
        // TODO: Mostrar ventana de BookingOverview
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HotelMainApp.class.getResource("../view/BookingOverview.fxml"));
            AnchorPane bookingOverview = loader.load();
            BorderPane rootLayout = null;
            assert false;
            rootLayout.setCenter(bookingOverview);
            BookingOverviewController controller = loader.getController();
            controller.setHotelMainApp(mainApp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Método para mostrar los datos de un cliente en la parte derecha de la ventana al insertar su DNI
     * en la caja de búsqueda y pulsar ENTER.
     *
     * @param event Evento de pulsar la tecla ENTER.
     */
    @FXML
    public void showCustomerSearchedByDNI(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                HotelModel model = new HotelModel();
                Customer customer = model.getCustomer(dniSearch.getText());
                if (customer != null) {
                    showCustomerDetails(customer);
                } else {
                    ModalDialog.createWarning("No encontrado",
                            "Búsqueda sin coincidencias",
                            "Ningún DNI coincide con el solicitado.");
                }
                model.getRepository().closeConnection();
            } catch (CommunicationsException ex) {
                ModalDialog.createError("Error de conectividad",
                        "Base de datos no disponible",
                        "Por favor conecte la base de datos y vuelva a ejecutar la aplicación.");
            } catch (CustomerException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

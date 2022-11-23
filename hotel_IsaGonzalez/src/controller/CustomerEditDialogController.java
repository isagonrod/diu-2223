package controller;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Customer;
import model.HotelModel;
import javafx.scene.control.*;
import org.controlsfx.dialog.Dialogs;

/**
 * Controlador de la ventana de edición y creación de clientes.
 *
 * @author Isa Gonzalez
 */
public class CustomerEditDialogController {
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

    private Stage dialogStage;
    private Customer customer;
    private boolean okClicked = false;
    private boolean isNew;

    @FXML
    private void initialize() {}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCustomer(Customer customer, boolean isNew) {
        this.customer = customer;
        this.isNew = isNew;

        if (!isNew) {
            dniField.setText(customer.getDni());
            nombreField.setText(customer.getNombre());
            apellidosField.setText(customer.getApellidos());
            direccionField.setText(customer.getDireccion());
            localidadField.setText(customer.getLocalidad());
            provinciaField.setText(customer.getProvincia());
        }
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
	 * Método que controla si se ha pulsado el botón 'OK' después de introducir o cambiar los datos del cliente.
	 * Comprueba, antes de cerrar la ventana, si es un nuevo cliente (entonces lo guarda en la base de datos) o
	 * si el cliente ya existe (entonces lo modifica en la base de datos).
	 */
    @FXML
    public void pushOk() {
        if (isInputValid()) {
            try {
                HotelModel model = new HotelModel();
                customer.setDni(dniField.getText());
                customer.setNombre(nombreField.getText());
                customer.setApellidos(apellidosField.getText());
                customer.setDireccion(direccionField.getText());
                customer.setLocalidad(localidadField.getText());
                customer.setProvincia(provinciaField.getText());
                if (isNew) {
                    model.saveCustomer(customer);
                } else {
                    model.editCustomer(customer);
                }
                this.okClicked = true;
                dialogStage.close();
                model.getRepository().closeConnection();
            } catch (CommunicationsException ex) {
                Dialogs.create()
                        .title("Error de conectividad")
                        .masthead("Base de datos no disponible")
                        .message("Por favor conecte la base de datos y vuelva a ejecutar la aplicación.")
                        .showError();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

	/**
	 * Método que controla si se ha pulsado el botón 'Cancelar', cerrando la ventana sin guardar ningún cambio.
	 */
    @FXML
    public void pushCancel() {
        dialogStage.close();
    }

	/**
	 * Método para controlar que los datos se han introducido de forma correcta.
	 *
	 * @return 'true' si se han introducido bien los datos, 'false' si hay algún error en algún campo.
	 */
    public boolean isInputValid() {
        String errorMessage = "";
        if (dniField.getText() == null || dniField.getText().length() == 0) {
            errorMessage += "DNI no válido\n";
        }
        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "Nombre no válido\n";
        }
        if (apellidosField.getText() == null || apellidosField.getText().length() == 0) {
            errorMessage += "Apellido/s no válido/s\n";
        }
        if (direccionField.getText() == null || direccionField.getText().length() == 0) {
            errorMessage += "Dirección no válida\n";
        }
        if (localidadField.getText() == null || localidadField.getText().length() == 0) {
            errorMessage += "Localidad no válida\n";
        }
        if (provinciaField.getText() == null || provinciaField.getText().length() == 0) {
            errorMessage += "Provincia no válida\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Dialogs.create()
                    .title("Campos inválidos")
                    .masthead("Por favor, corrige los campos inválidos")
                    .message(errorMessage)
                    .showError();
            return false;
        }
    }
}

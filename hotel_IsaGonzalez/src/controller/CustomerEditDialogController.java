package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Customer;
import model.HotelModel;
import javafx.scene.control.*;
import org.controlsfx.dialog.Dialogs;

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

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    public void pushOk() {
        if (isInputValid()) {
            try {
                customer.setDni(dniField.getText());
                customer.setNombre(nombreField.getText());
                customer.setApellidos(apellidosField.getText());
                customer.setDireccion(direccionField.getText());
                customer.setLocalidad(localidadField.getText());
                customer.setProvincia(provinciaField.getText());
                if (isNew) {
                    new HotelModel().saveCustomer(customer);
                } else {
                    new HotelModel().editCustomer(customer);
                }
                this.okClicked = true;
                dialogStage.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @FXML
    public void pushCancel() {
        dialogStage.close();
    }

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

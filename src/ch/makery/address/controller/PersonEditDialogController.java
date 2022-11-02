package ch.makery.address.controller;

import ch.makery.address.model.PersonException;
import ch.makery.address.service.PersonService;
import ch.makery.address.util.PersonParse;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import ch.makery.address.model.PersonModel;
import ch.makery.address.util.DateUtil;

/**
 * Dialog to edit details of a person.
 *
 * @author Isa González
 */
public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ProgressIndicator progressIndicator;

    private Stage dialogStage;
    private PersonModel person;
    private boolean okClicked = false;

    private boolean isNew;

    private DoubleProperty personAmount = new SimpleDoubleProperty(0);
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage - The stage's dialog
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person - The person that wants to be changed
     * @param isNew - True if the person is new, False if it is not
     */
    public void setPerson(PersonModel person, boolean isNew) {
        this.person = person;
        this.isNew = isNew;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return okClicked
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    public void setProgressBar(double progress) {
        this.progressBar.setProgress(progress);
        this.progressIndicator.setProgress(progress);
    }

    /**
     * Binds the local size property to the one from the outside and
     * calls for the construction of the change listener.
     *
     * @param personAmountProperty - the property to which we have to bind the value
     */
    public void setPersonAmountProperty(DoubleProperty personAmountProperty) {
        this.personAmount.bindBidirectional(personAmountProperty);
        this.establishPersonAmount(this.personAmount);
    }

    /**
     * Adds a change listener to the size property so that it calculates the
     * new value whenever it is changed.
     *
     * @param personAmountProperty - the property to which we have to add a listener
     */
    private void establishPersonAmount(DoubleProperty personAmountProperty) {
        personAmountProperty.addListener(new ChangeListener() {

            @Override public void changed(ObservableValue o, Object oldVal, Object newVal) {
                progressIndicator.setProgress(personAmountProperty.getValue() / 50);
            }
        });
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            try {
                person.setFirstName(firstNameField.getText());
                person.setLastName(lastNameField.getText());
                person.setStreet(streetField.getText());
                person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
                person.setCity(cityField.getText());
                person.setBirthday(DateUtil.parse(birthdayField.getText()));

                if (isNew) {
                    new PersonService().savePerson(person);
                    this.personAmount.setValue(this.personAmount.get() + 1);
                }
                else {
                    new PersonService().editPerson(person);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialogs.create()
                    .title("Invalid Fields")
                    .masthead("Please correct invalid fields")
                    .message(errorMessage)
                    .showError();
            return false;
        }
    }

}
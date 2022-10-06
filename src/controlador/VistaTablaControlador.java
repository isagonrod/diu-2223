package controlador;

import com.sun.javafx.scene.control.ReadOnlyUnbackedObservableList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import modelo.Personaje;

import java.util.Optional;

public class VistaTablaControlador {
    @FXML
    private Label lbPersonajes;
    @FXML
    private TableView<Personaje> tvPersonajes;
    @FXML
    private ProgressIndicator progreso;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonAdd;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApellidos;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidos;

	private ObservableList<Personaje> personajes;

	public ObservableList<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(ObservableList<Personaje> personajes) {
		this.personajes = personajes;
	}

	@FXML
	private void addPersonajes() {
		if (tfNombre.getText().isEmpty()) {
			Alert dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setContentText("El campo NOMBRE está vacío");
		} else if (tfApellidos.getText().isEmpty()) {
			Alert dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setContentText("El campo APELLIDOS está vacío");
		} else {
			personajes.add(new Personaje(tfNombre.getText(), tfApellidos.getText()));
			progreso.setProgress(personajes.size());
		}
	}

	@FXML
	private void deletePersonajes() {
		if (tvPersonajes.getSelectionModel().getSelectedItem() != null) {
			//Dialogo confirmacion
			Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
			dialogo.setTitle("Confirmar");
			dialogo.setHeaderText(null);
			dialogo.setContentText("Estás seguro de que quieres eliminar el personaje seleccionado?");

			Optional<ButtonType> respuesta = dialogo.showAndWait();

			if (respuesta.get() == ButtonType.OK){
				personajes.remove(tvPersonajes.getSelectionModel().getSelectedItem());
				progreso.setProgress(personajes.size());
			} else {
				System.out.println("No se ha borrado el personaje");
			}

		} else {
			Alert dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("Error");
			dialogo.setHeaderText(null);
			dialogo.setContentText("No se ha seleccionado un personaje.");

			dialogo.showAndWait();
		}
	}

}

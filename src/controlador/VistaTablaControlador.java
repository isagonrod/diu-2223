package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import modelo.Personaje;

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

}

package controlador;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Personaje;

import java.io.IOException;

public class VistaTabla extends Application {

    private Stage escenario;
    private AnchorPane rootLayout;


    @Override
    public void start(Stage escenarioPrincipal) {
        this.escenario = escenarioPrincipal;
        this.escenario.setTitle("Vista de Tabla con Progreso");

        initRootLayout();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VistaTabla.class.getResource("../vista/VistaTablaProgreso.fxml"));
            rootLayout = loader.load();

            rellenarTablaPersonajes(((TableView<Personaje>)((Pane)rootLayout.getChildren().get(0)).getChildren().get(1)));
            double progreso = ((TableView<Personaje>)((Pane)rootLayout.getChildren().get(0)).getChildren().get(1)).getItems().size() / 10.0;
            ((ProgressIndicator)((Pane)rootLayout.getChildren().get(0)).getChildren().get(2)).setProgress(progreso);

            Scene scene = new Scene(rootLayout);
            escenario.setScene(scene);
            escenario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getEscenario() {
        return escenario;
    }

    public TableView<Personaje> rellenarTablaPersonajes(TableView<Personaje> tvPersonajes) {
        final ObservableList<Personaje> personajes = FXCollections.observableArrayList(
                new Personaje("Pepito", "Grillo"),
                new Personaje("Bob", "Esponja"),
                new Personaje("Juan", "Sin Miedo"),
                new Personaje("Perico", "De Los Palotes"),
                new Personaje("Juana", "La Loca")
        );
        TableColumn<Personaje, String> columnaNombre = new TableColumn<>("Nombre");
        TableColumn<Personaje, String> columnaApellidos = new TableColumn<>("Apellidos");

        tvPersonajes.getColumns().add(columnaNombre);
        tvPersonajes.getColumns().add(columnaApellidos);
        tvPersonajes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tvPersonajes.setEditable(true);

        columnaNombre.setMinWidth(123);
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidos.setMinWidth(123);
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        tvPersonajes.setItems(personajes);

        return tvPersonajes;
    }

    public static void main(String[] args) {
        launch(args);
    }

}

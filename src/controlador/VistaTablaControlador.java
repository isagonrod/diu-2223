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
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator progreso;


    public Label getLbPersonajes() {
        return lbPersonajes;
    }

    public void setLbPersonajes(Label lbPersonajes) {
        this.lbPersonajes = lbPersonajes;
    }

    public TableView<Personaje> getTvPersonajes() {
        return tvPersonajes;
    }

    public void setTvPersonajes(TableView<Personaje> tvPersonajes) {
        this.tvPersonajes = tvPersonajes;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public ProgressIndicator getProgreso() {
        return progreso;
    }

    public void setProgreso(ProgressIndicator progreso) {
        this.progreso = progreso;
    }
}

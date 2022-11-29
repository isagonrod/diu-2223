package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MonedaMain;
import main.model.MonedaModelo;

import java.io.IOException;

public class RootLayoutController {
    private MonedaMain mainApp;
    private MonedaModelo modelo;

    public RootLayoutController() {}

    public MonedaModelo getModelo() {
        return modelo;
    }

    public void setModelo(MonedaModelo modelo) {
        this.modelo = modelo;
    }

    @FXML
    private void initialize() {}

    public void setMainApp(MonedaMain mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void showNewWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MonedaMain.class.getResource("view/MonedaVentana.fxml"));
            AnchorPane pane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Monedas");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            dialogStage.getIcons().add(new Image("file:resources/images/coin.ico"));

            dialogStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

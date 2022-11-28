package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MonedaMain;

import java.io.IOException;

public class RootLayoutController {
    private MonedaMain mainApp;

    public RootLayoutController() {}

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

            dialogStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

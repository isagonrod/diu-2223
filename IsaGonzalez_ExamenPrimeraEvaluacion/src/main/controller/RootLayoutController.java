package main.controller;

import Modelo.ExcepcionMoneda;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MonedaMain;
import main.model.Moneda;
import main.model.MonedaModelo;
import main.model.repository.MonedaRepository;

import java.io.IOException;

public class RootLayoutController {
    private MonedaMain mainApp;
    private MonedaModelo modelo;
	private MonedaRepository repository;

	@FXML
	private ComboBox<String> listaMonedas;
	@FXML
	private TextField euros;
	@FXML
	private TextField otraMoneda;
	@FXML
	private Label textMoneda;

    public RootLayoutController() {}

    public MonedaModelo getModelo() {
        return modelo;
    }

    public void setModelo(MonedaModelo modelo) {
        this.modelo = modelo;
    }

    @FXML
    private void initialize() {
		this.listaMonedas.getItems().add("Yen");
		this.listaMonedas.getItems().add("Lira");
		this.listaMonedas.getItems().add("Dólar Americano");
		this.listaMonedas.getItems().add("Libra esterlina");
	}

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

            dialogStage.getIcons().add(new Image("file:src/resources/images/coin.ico"));

            dialogStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

	@FXML
	private void handleConvertCoin() {
		if (!this.listaMonedas.getItems().isEmpty()) {
			this.textMoneda.setText(this.listaMonedas.getValue());

			if (this.euros == null && this.otraMoneda != null) {

			}
		}
	}

	@FXML
	private void handleDeleteCoin() {
		int selectedCoin = listaMonedas.getSelectionModel().getSelectedIndex();
		if (listaMonedas.getItems().get(selectedCoin).equalsIgnoreCase(repository.getClass().getName())) {
			try {
				Moneda coinToDelete = new Moneda();
				coinToDelete.setNombre(listaMonedas.getItems().get(selectedCoin));
				new MonedaModelo().deleteCoin(coinToDelete);
				listaMonedas.getItems().remove(selectedCoin);
			} catch (ExcepcionMoneda ex) {
				throw new RuntimeException(ex);
			}
		} else {
			new Alert(Alert.AlertType.WARNING, "Ninguna moneda seleccionada");
		}
	}
}

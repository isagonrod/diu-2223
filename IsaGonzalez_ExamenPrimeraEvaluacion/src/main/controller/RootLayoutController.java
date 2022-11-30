package main.controller;

import Modelo.ExcepcionMoneda;
import javafx.collections.ObservableList;
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

	@FXML
	private ComboBox<Moneda> listaMonedas;
	@FXML
	private TextField euros;
	@FXML
	private TextField otraMoneda;
	@FXML
	private Label textMoneda;

    public RootLayoutController() {}

    @FXML
    private void initialize() {}

    public void setMainApp(MonedaMain mainApp) {
        this.mainApp = mainApp;
    }

	public void setListaMonedas(ObservableList<Moneda> list) {
		listaMonedas.setItems(list);
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
	private void handleCurrencySelection() {
		this.textMoneda.setText(this.listaMonedas.getValue().getNombre());
	}

	@FXML
	private void handleConvertCurrency() {
		if (!this.listaMonedas.getItems().isEmpty()) {
			if (this.euros == null && this.otraMoneda != null) {

			}
		}
	}

	@FXML
	private void handleDeleteCurrency() {
		int selectedCurrencyIndex = listaMonedas.getSelectionModel().getSelectedIndex();
		if (selectedCurrencyIndex >= 0) {
			try {
				Moneda selectedCurrency = listaMonedas.getItems().get(selectedCurrencyIndex);
				this.mainApp.getModelo().deleteCurrency(selectedCurrency); //borrar de bbdd
				this.mainApp.getMonedasDatos().remove(selectedCurrency); //borrar del observable
				//listaMonedas.getItems().remove(selectedCurrencyIndex); //borrar del combobox
			} catch (ExcepcionMoneda ex) {
				throw new RuntimeException(ex);
			}
		} else {
			new Alert(Alert.AlertType.WARNING, "Ninguna moneda seleccionada").show();
		}
	}
}

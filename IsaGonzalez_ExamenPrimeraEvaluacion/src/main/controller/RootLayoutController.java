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

	// Falta el método para que funcione la conversión utilizando la tecla ENTER

	@FXML
	private void handleConvertCurrency() {
		Moneda selectedCurrency;
		try {
			selectedCurrency = this.getCurrentCurrency();
			if (!this.listaMonedas.getItems().isEmpty()) {
				if (this.euros.getText().length() == 0 && this.otraMoneda.getText().length() != 0) {
					this.euros.setText(String.valueOf(Float.parseFloat(this.otraMoneda.getText()) * (2 - selectedCurrency.getMultiplicador())));
				} else if (this.euros.getText().length() != 0 && this.otraMoneda.getText().length() == 0) {
					this.otraMoneda.setText(String.valueOf(Float.parseFloat(this.euros.getText()) * selectedCurrency.getMultiplicador()));
				} else {
					new Alert(Alert.AlertType.INFORMATION, "Borra el campo de la otra moneda").show();
				}
			}
		} catch (ExcepcionMoneda e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	private void handleDeleteCurrency() {
		try {
			Moneda selectedCurrency = this.getCurrentCurrency();
			this.mainApp.getModelo().deleteCurrency(selectedCurrency); //borra de bbdd
			this.mainApp.getMonedasDatos().remove(selectedCurrency); //borra del observable (y del combobox)
		} catch (ExcepcionMoneda ex) {
			ex.printStackTrace();
		}
	}

	private Moneda getCurrentCurrency() throws ExcepcionMoneda {
		Moneda selectedCurrency;

		int selectedCurrencyIndex = listaMonedas.getSelectionModel().getSelectedIndex();
		if (selectedCurrencyIndex >= 0) {
			selectedCurrency = listaMonedas.getItems().get(selectedCurrencyIndex);
		} else {
			new Alert(Alert.AlertType.WARNING, "Ninguna moneda seleccionada").show();
			throw new ExcepcionMoneda("Ninguna moneda seleccionada");
		}

		return selectedCurrency;
	}
}
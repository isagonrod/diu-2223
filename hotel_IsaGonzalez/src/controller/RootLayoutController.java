package controller;

import javafx.fxml.FXML;
import main.HotelMainApp;

/**
 * Controlador para el diseño raíz.
 * Este proporciona el diseño básico de la aplicación que contiene una barra de menú
 * y un espacio donde se pueden controlar otros elementos de JavaFX.
 *
 * @author Isa González
 */
public class RootLayoutController {
	private HotelMainApp mainApp;

	public void setHotelMainApp(HotelMainApp hotelMainApp) {
		this.mainApp = hotelMainApp;
	}

	@FXML
	private void handleShowStatistics() {
		mainApp.showStatistics();
	}
}

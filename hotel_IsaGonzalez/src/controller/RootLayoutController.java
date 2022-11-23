package controller;

import javafx.fxml.FXML;
import main.HotelMainApp;

/**
 * Controlador para el diseño raíz.
 * Este proporciona el diseño básico de la aplicación que contiene una barra de menú
 * y un espacio donde se pueden controlar otros elementos de JavaFX.
 *
 * @author Isa Gonzalez
 */
public class RootLayoutController {
	private HotelMainApp mainApp;

	public void setHotelMainApp(HotelMainApp hotelMainApp) {
		this.mainApp = hotelMainApp;
	}

	/**
	 * Método que sirve para cargar la ventana de Estadísticas al pulsar la opción correspondiente del menú.
	 */
	@FXML
	private void handleShowStatistics() {
		mainApp.showStatistics();
	}

	/**
	 * Método que sirve para cargar la ventana de Galería de fotos al pulsar la opción correspondiente del menú.
	 */
	@FXML
	private void handleShowPhotoGallery() {
		mainApp.showPhotoGallery();
	}

	/**
	 * Método que sirve para cargar el Javadoc del proyecto en formato HTML en un navegador.
	 */
	@FXML
	private void handleShowJavadoc() {
		mainApp.showJavadoc();
	}
}

package main.controller;

import javafx.fxml.FXML;
import main.model.MonedaModelo;

import java.awt.*;

public class MonedaVentanaController {

//	@FXML
	private TextField totalMonedasDisponible;
	private MonedaModelo modelo;

	public MonedaVentanaController() {
		this.modelo = new MonedaModelo();
	}

//	Este método no está funcionando, ya que no consigo que coja el tamaño de la tabla del repositorio para mostrarlo
//	@FXML
//	private void initialize() {
//		try {
//			this.totalMonedasDisponible.setText(String.valueOf(this.modelo.getCurrencyList().size()));
//		} catch (ExcepcionMoneda ex) {
//			ex.printStackTrace();
//		}
//	}
}

package main;

import main.controller.ControlConversor;
import main.model.ConversorEurosPesetas;
import main.view.InterfazVista;
import main.view.VentanaConversor;

// MAIN
public class ProgramaDeConversion {
	public static void main(String[] args) {
		// el modelo
		ConversorEurosPesetas modelo = new ConversorEurosPesetas();

		// la vista
		InterfazVista vista = new VentanaConversor();

		// el control
		ControlConversor control = new ControlConversor(vista, modelo);

		// configuración de la vista
		vista.setControlador(control);

		// arranca la interfaz (vista)
		vista.arranca();
	}
}

package main.controller;

import main.model.ConversorEurosPesetas;
import main.view.InterfazVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlConversor implements ActionListener {
	private InterfazVista vista;
	private ConversorEurosPesetas modelo;

	public ControlConversor(InterfazVista vista, ConversorEurosPesetas modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		double cantidad = vista.getCantidad();

		if (event.getActionCommand().equals(InterfazVista.AEUROS)) {
			vista.escribeCambio(cantidad + " € son: " + modelo.eurosApesetas(cantidad) + " pesetas.");
		} else {
			vista.escribeCambio("ERROR");
		}
	}
}

package ejemplo_CalculadoraEurosPesetas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// CONTROLLER
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
			vista.escribeCambio(cantidad + " pesetas son: " + modelo.pesetasAeuros(cantidad) + " €");
		} else if (event.getActionCommand().equals(InterfazVista.APESETAS)) {
			vista.escribeCambio(cantidad + " € son: " + modelo.eurosApesetas(cantidad) + " pesetas.");
		} else {
			vista.escribeCambio("ERROR");
		}
	}
}

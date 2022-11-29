package ejemplo_CalculadoraEurosPesetas;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// VIEW TEXTUAL
public class InterfazTextualConversor implements InterfazVista {
    private ControlConversor controlador;

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private int leerOpcion() {
        String s = null;
        try {
            s = in.readLine();
            return Integer.parseInt(s);
        } catch (Exception ex) {
            operacionIncorrecta();
            return 0;
        }
    }

    private double leeCantidad() {
        String s = null;
        try {
            s = in.readLine();
            return Double.parseDouble(s);
        } catch (Exception ex) {
            System.out.println("Error en formato del número, tiene que ser 99.99");
            return leeCantidad();
        }
    }

    private void solicitarOperacion() {
        System.out.println("Indica la operación que quiere realizar");
        System.out.println("1. Convertir pesetas a euros");
        System.out.println("2. Convertir euros a pesetas");
        System.out.println("0. Salir");
    }

    private void procesaNuevaOperacion() {
        int operacion;
        solicitarOperacion();
        operacion = leerOpcion();

        if (operacion == 0) {
            System.out.println("Adiós");
            System.exit(0);
        }

        if (operacion == 1) {
            controlador.actionPerformed(new ActionEvent(this, operacion, AEUROS));
        }

        if (operacion == 2) {
            controlador.actionPerformed(new ActionEvent(this, operacion, APESETAS));
        }

        operacionIncorrecta();
    }

    private void operacionIncorrecta() {
        System.out.println("Operación incorrecta");
        procesaNuevaOperacion();
    }

    @Override
    public void setControlador(ControlConversor c) {
        controlador = c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public double getCantidad() {
        System.out.println("Cantidad a convertir (formato 99.99): ");
        return leeCantidad();
    }

    @Override
    public void escribeCambio(String s) {
        System.out.println(s);
        procesaNuevaOperacion();
    }
}

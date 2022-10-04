package controlador;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class VentanaContadorPulsacionesControlador {

    @FXML
    private Button botonPulsame;
    @FXML
    private Label  pulsacionesLabel;

    private int numPulsaciones = 0;

    @FXML
    private void initialize(){
        pulsacionesLabel.setAlignment(Pos.CENTER);
    //Aquí podemos asignar modelos a controles
    //También podemos registrar manejadores de eventos
}

    @FXML
    private void botonPulsado() {
        numPulsaciones++;
        if (numPulsaciones == 1) {
            pulsacionesLabel.setText("El botón se ha pulsado una vez");
        } else {
            pulsacionesLabel.setText("El botón se ha pulsado "+ numPulsaciones+" veces");
        }
    }
}

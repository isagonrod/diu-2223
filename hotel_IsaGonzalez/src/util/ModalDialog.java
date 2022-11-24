package util;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import java.util.Map;

/**
 * Clase que sirve para crear ventanas modales.
 *
 * @author Isa González
 */
public class ModalDialog {

    /**
     * Crea un modal de aviso con un solo botón.
     *
     * @param title Título de la alerta
     * @param headerText Encabezado de texto (descartado si ya hay un encabezado)
     * @param text Texto de la alerta
     */
    public static void createWarning(String title, String headerText, String text) {
        createAlert(AlertType.WARNING, title, null, headerText, text, null, false, true);
    }

    /**
     * Crea un modal de error con un solo botón.
     *
     * @param title Título de la alerta
     * @param headerText Encabezado de texto (descartado si ya hay un encabezado)
     * @param text Texto de la alerta
     */
    public static void createError(String title, String headerText, String text) {
        createAlert(AlertType.ERROR, title, null, headerText, text, null, false, true);
    }

    /**
     * Método auxiliar que permite crear un diálogo de alerta a medida.
     *
     * @param alertType      El tipo de alerta (constante estática en AlertType)
     * @param title          Título de la alerta
     * @param graphicElement Un elemento gráfico (encabezado si no hay uno)
     * @param headerText     Encabezado de texto (descartado si ya hay un encabezado)
     * @param text           Texto de la alerta
     * @param buttons        Lista de textos y tipos de botones a añadir
     * @param isResizable    Si el usuario puede cambiar de tamaño la alerta
     * @param pausesAll      Si la alerta bloquea toda acción o no
     * @return El botón en el que se ha hecho clic
     */
    public static ButtonData createAlert(AlertType alertType, String title, Node graphicElement,
                                         String headerText, String text, Map<String, ButtonData> buttons,
                                         boolean isResizable, boolean pausesAll) {
        Alert alert;
        ButtonType type;

        alert = new Alert(alertType);
        alert.setTitle(title);
        if (headerText != null) {
            alert.setHeaderText(headerText);
        }
        if (graphicElement != null) {
            alert.setGraphic(graphicElement);
        }
        alert.setContentText(text);

        if (buttons != null) {
            for (Map.Entry<String, ButtonData> button : buttons.entrySet()) {
                type = new ButtonType(button.getKey(), button.getValue());
                alert.getDialogPane().getButtonTypes().add(type);
            }
        }

        alert.setResizable(isResizable);

        if (pausesAll) {
            alert.showAndWait();
        } else {
            alert.show();
        }

        return alert.getResult().getButtonData();
    }
}

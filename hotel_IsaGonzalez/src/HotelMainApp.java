import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Aplicación principal para la gestión del hotel.
 * Contiene el método MAIN y lanza el 'framework' o ventana principal de gestión.
 *
 * @author Isa González
 */
public class HotelMainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public static void main(String[] args) {
        launch(args);
    }
}

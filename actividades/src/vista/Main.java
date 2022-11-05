package vista;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        IntegerProperty numPulsacionesProperties= (IntegerProperty) new SimpleIntegerProperty(0);
        Botones botones1= new Botones();
        botones1.setNumPulsacionesProperties(numPulsacionesProperties);

        botones1.setStage(primaryStage);
        Stage stage2=new Stage();
        Botones botones2= new Botones();
        botones2.setNumPulsacionesProperties(numPulsacionesProperties);
        botones2.setStage(stage2);
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}

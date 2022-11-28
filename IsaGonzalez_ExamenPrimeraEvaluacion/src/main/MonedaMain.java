package main;

import Modelo.*;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.model.MonedaModelo;

import java.util.Iterator;

public class MonedaMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MonedaRepositoryImpl repository = new MonedaRepositoryImpl();

        MonedaVO nuevaMoneda = new MonedaVO("Yen", '1');
        nuevaMoneda.setCodigo(3);
        repository.addMoneda(nuevaMoneda);

        MonedaModelo monedaModelo = new MonedaModelo();
        monedaModelo.setRepository(repository);
        Iterator it = monedaModelo.obtenerListaMonedas().iterator();

        do {
            System.out.println("$ " + ((MonedaVO) it.next()).getNombre());
        } while (it.hasNext());

//        Parent root;
//        root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
//        primaryStage.setTitle("Hello world");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }
}

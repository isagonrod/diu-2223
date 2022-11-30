package main;

import Modelo.*;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.controller.RootLayoutController;
import main.util.ConversorVO;
import main.model.Moneda;

import java.io.IOException;
import java.util.List;

public class MonedaMain extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Moneda> monedasDatos = FXCollections.observableArrayList();

    public MonedaMain() {
        MonedaRepositoryImpl repository = new MonedaRepositoryImpl();
    }

    public ObservableList<Moneda> getMonedasDatos() {
        return monedasDatos;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Monedas");

        this.primaryStage.getIcons().add(new Image("file:src/resources/images/coin.ico"));

        initRootLayout();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MonedaMain.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

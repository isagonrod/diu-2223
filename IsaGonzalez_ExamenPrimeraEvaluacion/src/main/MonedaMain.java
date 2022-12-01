package main;

import Modelo.ExcepcionMoneda;
import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.controller.RootLayoutController;
import main.model.Moneda;
import main.model.MonedaModelo;

import java.io.IOException;

public class MonedaMain extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private MonedaModelo modelo;

	private ObservableList<Moneda> monedasDatos = FXCollections.observableArrayList();

	public MonedaMain() {
		MonedaRepository repository = new MonedaRepositoryImpl();
		this.modelo = new MonedaModelo();
		modelo.setRepository(repository);
	}

	public ObservableList<Moneda> getMonedasDatos() {
		return monedasDatos;
	}

	public MonedaModelo getModelo() {
		return modelo;
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
			this.monedasDatos.addAll(modelo.getCurrencyList());
		} catch (ExcepcionMoneda ex) {
			new Alert(Alert.AlertType.ERROR, "Error al cargar las monedas de BBDD.").show();
		}
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MonedaMain.class.getResource("view/RootLayout.fxml"));
			rootLayout = loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			controller.setListaMonedas(this.monedasDatos);

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

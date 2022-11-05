package ch.makery.money;

import Modelo.*;
import Modelo.repository.impl.MonedaRepositoryImpl;
import ch.makery.money.model.MonedaModelo;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Iterator;

public class MainMoney extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		MonedaRepositoryImpl repository = new MonedaRepositoryImpl();

		MonedaVO monedaNueva = new MonedaVO("Yen", '1');
		monedaNueva.setCodigo(3);
		repository.editMoneda(monedaNueva);

		MonedaModelo monedaModelo = new MonedaModelo();
		monedaModelo.setRepository(repository);
		Iterator it = monedaModelo.obtenerListaMonedas().iterator();

		do {
			System.out.println("$ " + ((MonedaVO) it.next()).getNombre());
		} while (it.hasNext());


//		Iterator iteradorMonedas = repository.ObtenerListaMonedas().iterator();
//		do {
//			System.out.println("$ " + ((MonedaVO) iteradorMonedas.next()).getNombre());
//		} while (iteradorMonedas.hasNext());
//		Parent root;
//		root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
//		primaryStage.setTitle("Hello World");
//		primaryStage.setScene(new Scene(root, 300, 275));
//		primaryStage.show();
	}
}

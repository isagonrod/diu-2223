package main;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import controller.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import model.repository.impl.HotelRepositoryImpl;
import util.BookingParse;
import util.CustomerParse;
import util.ModalDialog;

import java.io.IOException;
import java.util.List;

/**
 * Aplicación principal para la gestión del hotel.
 * Contiene el método MAIN y lanza el 'framework' o ventana principal de gestión.
 *
 * @author Isa González
 */
public class HotelMainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
	private ObservableList<Customer> customers = FXCollections.observableArrayList();
	private ObservableList<Booking> bookings = FXCollections.observableArrayList();

	/**
	 * Constructor de la aplicación principal.
	 */
	public HotelMainApp() {
		try {
			HotelRepositoryImpl repository = new HotelRepositoryImpl();
			List<CustomerVO> customerVOList = repository.loadCustomerList();
			for (CustomerVO customerVO : customerVOList) {
				this.customers.add(CustomerParse.parseToCustomer(customerVO));
			}
			List<BookingVO> bookingVOList = repository.loadBookingList();
			for (BookingVO bookingVO : bookingVOList) {
				this.bookings.add(BookingParse.parseToBooking(bookingVO));
			}
			repository.closeConnection();
		} catch (CommunicationsException ex) {
			ModalDialog.createError("Error de conectividad",
					"Base de datos no disponible",
					"Por favor conecte la base de datos y vuelva a ejecutar la aplicación.");
		} catch (CustomerException | BookingException ex) {
			throw new RuntimeException(ex);
		}
	}

	public ObservableList<Customer> getCustomers() {
		return customers;
	}

	public ObservableList<Booking> getBookings() {
		return bookings;
	}

	/**
	 * Método para arrancar la aplicación, en el que se define el título o cabecera que aparecerá en la parte superior
	 * y el icono de la misma.
	 *
	 * @param primaryStage PrimaryStage de esta aplicación, en la que la escena se puede configurar.
	 * El primaryStage se integrará en el navegador si la aplicación se inició como un subprograma.
	 * Las aplicaciones pueden crear otras etapas, si es necesario, pero no serán etapas primarias
	 * y no se integrarán en el navegador.
	 *
	 * @throws Exception excepción
	 */
	@Override
    public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HOTEL");
		this.primaryStage.getIcons().add(new Image("file:resources/icon.png"));
		initRootLayout();
		showCustomerOverview();
    }

	/**
	 * Método para cargar la ventana principal de la aplicación, la cual consta de una barra de menú superior
	 * con dos pestañas (Aplicación y Gestión) y una zona inferior vacía donde se cargará la ventana del cliente.
	 * En la pestaña Aplicación está la opción "Visualizar Javadoc".
	 * En la pestaña Gestión están las opciones "Estadísticas de ocupación por meses" y "Galería de fotos".
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HotelMainApp.class.getResource("../view/RootLayout.fxml"));
			rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			RootLayoutController controller = loader.getController();
			controller.setHotelMainApp(this);
			primaryStage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Método para cargar la ventana del Cliente.
	 * Esta está dividida en dos partes: en la columna de la izquierda hay una lista donde se muestran
	 * los apellidos y los nombres de los clientes, al pulsar sobre uno de ellos, aparecerán en la parte
	 * derecha de la ventana todos sus datos.
	 * En la parte izquierda también aparecerán varios botones: Nuevo, Editar, Eliminar y Reservas.
	 */
	public void showCustomerOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HotelMainApp.class.getResource("../view/CustomerOverview.fxml"));
			AnchorPane customerOverview = loader.load();
			rootLayout.setCenter(customerOverview);
			CustomerOverviewController controller = loader.getController();
			controller.setHotelMainApp(this);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Método para cargar la ventana de Estadísticas desde la ventana principal al pulsar la opción correspondiente
	 * del menú situado en la barra superior, en la pestaña de Gestión.
	 */
	public void showStatistics() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HotelMainApp.class.getResource("../view/Statistics.fxml"));
			AnchorPane pane = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("ESTADISTICAS");
			dialogStage.initModality(Modality.NONE);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);

			StatisticsController controller = loader.getController();
			controller.setBookingData(bookings);

			dialogStage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Método para cargar la ventana de Galería de fotos desde la ventana principal al pulsar la opción correspondiente
	 * del menú situado en la barra superior, en la pestaña de Gestión.
	 */
	public void showPhotoGallery() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HotelMainApp.class.getResource("../view/PhotoGallery.fxml"));

			AnchorPane pane = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Galería de fotos");
			dialogStage.initModality(Modality.NONE);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);

			PhotoGalleryController controller = loader.getController();
			controller.setBookingData(bookings);

			dialogStage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Método para cargar el navegador con el Javadoc del proyecto en formato HTML al pulsar la opción correspondiente
	 * del menú situado en la barra superior, en la pestaña Aplicación.
	 */
	public void showJavadoc() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HotelMainApp.class.getResource("../view/Javadoc.fxml"));
			AnchorPane pane = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("JavaDocs");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.getPrimaryStage());
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);

			JavadocController controller = loader.getController();
			controller.loadPage();

			dialogStage.showAndWait();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public static void main(String[] args) {
        launch(args);
    }
}

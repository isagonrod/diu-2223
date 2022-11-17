package main;

import controller.CustomerOverviewController;
import controller.RootLayoutController;
import controller.StatisticsController;
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

	public HotelMainApp() {
		HotelRepositoryImpl repository = new HotelRepositoryImpl();
		try {
			List<CustomerVO> customerVOList = repository.loadCustomerList();
			for (CustomerVO customerVO : customerVOList) {
				this.customers.add(CustomerParse.parseToCustomer(customerVO));
			}
			List<BookingVO> bookingVOList = repository.loadBookingList();
			for (BookingVO bookingVO : bookingVOList) {
				this.bookings.add(BookingParse.parseToBooking(bookingVO));
			}
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

    @Override
    public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HOTEL");
		this.primaryStage.getIcons().add(new Image("file:resources/icon.png"));
		initRootLayout();
		showCustomerOverview();
    }

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HotelMainApp.class.getResource("view/RootLayout.fxml"));
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

	public void showCustomerOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HotelMainApp.class.getResource("view/CustomerOverview.fxml"));
			AnchorPane customerOverview = loader.load();
			rootLayout.setCenter(customerOverview);
			CustomerOverviewController controller = loader.getController();
			controller.setHotelMainApp(this);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void showStatistics() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HotelMainApp.class.getResource("view/Statistics.fxml"));
			AnchorPane pane = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("ESTADÍSTICAS");
			dialogStage.initModality(Modality.WINDOW_MODAL);
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

	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}

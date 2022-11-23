package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import model.Booking;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.util.List;
import java.util.Objects;

/**
 * Controlador de la ventana de la Galería de fotos, donde se muestra una imagen de cada uno de los cuatro tipos
 * de habitación que existe en el hotel con un indicador de progreso debajo, que muestra el porcentaje de ocupación
 * de cada uno de los tipos de habitación.
 *
 * @author Isa Gonzalez
 */
public class PhotoGalleryController {
	@FXML
	ImageView image1;
	@FXML
	ImageView image2;
	@FXML
	ImageView image3;
	@FXML
	ImageView image4;
	@FXML
	ProgressIndicator porc1;
	@FXML
	ProgressIndicator porc2;
	@FXML
	ProgressIndicator porc3;
	@FXML
	ProgressIndicator porc4;

	/**
	 * Método para inicializar la ventana.
	 */
	@FXML
	private void initialize() {
		Image doble_individual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/doble_individual.jpg")));
		this.image1 = new ImageView((Element) doble_individual);

		Image doble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/doble.jpg")));
		this.image2 = new ImageView((Element) doble);

		Image suite_junior = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/suite_junior.jpg")));
		this.image3 = new ImageView((Element) suite_junior);

		Image suite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/suite.jpg")));
		this.image4 = new ImageView((Element) suite);
	}

	/**
	 * Método para comprobar los datos de las reservas en la base de datos.
	 *
	 * @param bookings Lista de reservas.
	 */
	public void setBookingData(List<Booking> bookings) {
		int dobleIndividualCounter = 0;
		int dobleCounter = 0;
		int suiteJuniorCounter = 0;
		int suiteCounter = 0;

		for (Booking booking : bookings) {
			String tipoHabitacion = booking.getTipoHabitacion();
			if (tipoHabitacion.equalsIgnoreCase("doble")) {
				dobleCounter++;
			} else if (tipoHabitacion.equalsIgnoreCase("suite junior")) {
				suiteJuniorCounter++;
			} else if (tipoHabitacion.equalsIgnoreCase("suite")) {
				suiteCounter++;
			} else {
				dobleIndividualCounter++;
			}
		}
		this.porc1 = new ProgressIndicator((float)dobleIndividualCounter / 20);
		this.porc2 = new ProgressIndicator((float)dobleCounter / 80);
		this.porc3 = new ProgressIndicator((float)suiteJuniorCounter / 15);
		this.porc4 = new ProgressIndicator((float)suiteCounter / 5);
	}
}

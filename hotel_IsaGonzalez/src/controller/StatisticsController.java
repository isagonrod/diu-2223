package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import model.Booking;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Controlador de la ventana de Estadísticas, donde se muestra una gráfica con los datos de ocupación por meses.
 *
 * @author Isa González
 */
public class StatisticsController {
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    private ObservableList<String> monthNames = FXCollections.observableArrayList();

	/**
	 * Método que inicializa la ventana.
	 */
    @FXML
    private void initialize() {
        String[] months = DateFormatSymbols.getInstance(Locale.getDefault()).getMonths();
        monthNames.addAll(Arrays.asList(months));
        xAxis.setCategories(monthNames);
        xAxis.setTickLabelRotation(75.0);
    }

	/**
	 * Método que toma los datos desde la base de datos y los vuelca en la gráfica.
	 *
	 * @param bookings Lista de reservas.
	 */
    public void setBookingData(List<Booking> bookings) {
        int[] monthCounter = new int[12];
        for (Booking booking : bookings) {
            int monthBooking = booking.getNumHabitaciones();
            monthCounter[monthBooking]++;
        }
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        barChart.getData().add(series);
    }
}

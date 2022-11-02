package ch.makery.address;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

import ch.makery.address.model.PersonException;
import ch.makery.address.model.PersonModel;
import ch.makery.address.model.PersonListWrapper;
import ch.makery.address.controller.BirthdayStatisticsController;
import ch.makery.address.controller.PersonEditDialogController;
import ch.makery.address.controller.PersonOverviewController;
import ch.makery.address.controller.RootLayoutController;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import ch.makery.address.util.PersonParse;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Main application
 *
 * @author Isa González
 */
public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<PersonModel> personData = FXCollections.observableArrayList();

	public double getPersonAmount() {
		return personAmount.get();
	}

	public DoubleProperty getPersonAmountProperty() {
		return personAmount;
	}

	private DoubleProperty personAmount;
	/**
	 * Constructor where the program add a list of persons
	 */
	public MainApp() {
		PersonRepositoryImpl repository = new PersonRepositoryImpl();
		try {
			List<PersonVO> bd = repository.loadPersonList();
			for (PersonVO personVO : bd) {
				personData.add(PersonParse.parseToPerson(personVO));
			}
			this.personAmount = new SimpleDoubleProperty(this.personData.size());
		} catch (PersonException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the data as an observable list of Persons.
	 * @return personData
	 */
	public ObservableList<PersonModel> getPersonData() {
		return personData;
	}

	/**
	 * Start the application
	 *
	 * @param primaryStage the primary stage for this application, onto which
	 * the application scene can be set. The primary stage will be embedded in
	 * the browser if the application was launched as an applet.
	 * Applications may create other stages, if needed, but they will not be
	 * primary stages and will not be embedded in the browser.
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");

		//Set the application icon.
		this.primaryStage.getIcons().add(new Image("file:resources/images/addressbook.png"));

		initRootLayout();

		showPersonOverview();
	}

	/**
	 * Initializes the root layout and tries to load the last opened
	 * person file.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Try to load last opened person file.
		File file = getPersonFilePath();
		if (file != null) {
			loadPersonDataFromFile(file);
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showPersonOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the main app.
			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 *
	 * @return new File(filePath) if the answer is true, and null if the answer is false
	 */
	public File getPersonFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 *
	 * @param file the file or null to remove the path
	 */
	public void setPersonFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			primaryStage.setTitle("AddressApp - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			primaryStage.setTitle("AddressApp");
		}
	}

	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 *
	 * @param file - The file where the program takes the person data
	 */
	public void loadPersonDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext
					.newInstance(PersonListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

			personData.clear();
			personData.addAll(wrapper.getPersons());

			// Save the file path to the registry.
			setPersonFilePath(file);

		} catch (Exception e) { // catches ANY exception
			Dialogs.create()
					.title("Error")
					.masthead("Could not load data from file:\n" + file.getPath())
					.showException(e);
		}
	}

	/**
	 * Saves the current person data to the specified file.
	 *
	 * @param file - The file where the program saves the person data
	 */
	public void savePersonDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext
					.newInstance(PersonListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			PersonListWrapper wrapper = new PersonListWrapper();
			wrapper.setPersons(personData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			setPersonFilePath(file);
		} catch (Exception e) { // catches ANY exception
			Dialogs.create().title("Error")
					.masthead("Could not save data to file:\n" + file.getPath())
					.showException(e);
		}
	}

	/**
	 * Opens a dialog to show birthday statistics.
	 */
	public void showBirthdayStatistics() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Birthday Statistics");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the persons into the controller.
			BirthdayStatisticsController controller = loader.getController();
			controller.setPersonData(personData);

			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * @return primaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
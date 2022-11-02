package ch.makery.address.model;

import java.time.LocalDate;

import ch.makery.address.util.LocalDateAdapter;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Model class for a Person.
 *
 * @author Isa González
 */
public class PersonModel {
	private final IntegerProperty id;
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty street;
	private final IntegerProperty postalCode;
	private final StringProperty city;
	private final ObjectProperty<LocalDate> birthday;

	private DoubleProperty personNumber = new SimpleDoubleProperty();

	/**
	 * Default constructor.
	 */
	public PersonModel() { this(null, null); }

	/**
	 * Constructor with some initial data.
	 *
	 * @param firstName - The first name of the new person
	 * @param lastName - The last name of the new person
	 */
	public PersonModel(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);

		// Some initial dummy data, just for convenient testing.
		this.id = new SimpleIntegerProperty(0);
		this.street = new SimpleStringProperty("some street");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.city = new SimpleStringProperty("some city");
		this.birthday = new SimpleObjectProperty<>(LocalDate.of(1999, 2, 21));
	}

	public int getId() {
		return id.get();
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}

	public StringProperty streetProperty() {
		return street;
	}

	public int getPostalCode() {
		return postalCode.get();
	}

	public void setPostalCode(int postalCode) {
		this.postalCode.set(postalCode);
	}

	public IntegerProperty postalCodeProperty() {
		return postalCode;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public StringProperty cityProperty() {
		return city;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getBirthday() {
		return birthday.get();
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}

	public ObjectProperty<LocalDate> birthdayProperty() {
		return birthday;
	}

	public double getPersonNumber() {
		return personNumber.get();
	}

	public DoubleProperty personNumberProperty() {
		return personNumber;
	}

	public void setPersonNumberProperty(DoubleProperty personNumber) {
		this.updatePersonNumber(this.personNumber);
		this.personNumber.bindBidirectional(personNumber);
	}

	public void setPersonNumber(double personNumber) {
		this.personNumber.set(personNumber);
	}

	public void updatePersonNumber(DoubleProperty personNumber) {
		personNumber.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				personNumber.set(newValue.intValue());
			}
		});
	}
}
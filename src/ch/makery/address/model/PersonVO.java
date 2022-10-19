package ch.makery.address.model;

import java.time.LocalDate;

public class PersonVO {
	private int id;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private int postalCode;
	private LocalDate birthday;

	public PersonVO (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Contacto {" +
				"id=" + id +
				", First Name ='" + firstName + '\'' +
				", Last Name ='" + lastName + '\'' +
				", Street ='" + street + '\'' +
				", City ='" + city + '\'' +
				", Postal Code =" + postalCode +
				", Birthday =" + birthday +
				'}';
	}
}

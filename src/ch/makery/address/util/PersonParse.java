package ch.makery.address.util;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;

public class PersonParse {
	// TODO: Clase para pasar de PersonVO a Person
	private Person person;
	private PersonVO personVO;

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public PersonVO getPersonVO() {
		return this.personVO;
	}

	public void setPersonVO(PersonVO personVO) {
		this.personVO = personVO;
	}

	public PersonParse() {}

	public void parseToPersonVO() {
		this.personVO.setId(this.person.getId());
		this.personVO.setFirstName(this.person.getFirstName());
		this.personVO.setLastName(this.person.getLastName());
		this.personVO.setStreet(this.person.getStreet());
		this.personVO.setCity(this.person.getCity());
		this.personVO.setPostalCode(this.person.getPostalCode());
		this.personVO.setBirthday(this.person.getBirthday());
	}

	public void parseToPerson() {
		this.person.setId(this.personVO.getId());
		this.person.setFirstName(this.personVO.getFirstName());
		this.person.setLastName(this.personVO.getLastName());
		this.person.setStreet(this.personVO.getStreet());
		this.person.setCity(this.personVO.getCity());
		this.person.setPostalCode(this.personVO.getPostalCode());
		this.person.setBirthday(this.personVO.getBirthday());
	}
}

package ch.makery.address.util;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;

public class PersonParse {
	public static PersonVO parseToPersonVO(Person person) {
		PersonVO personVO = new PersonVO();
		personVO.setId(person.getId());
		personVO.setFirstName(person.getFirstName());
		personVO.setLastName(person.getLastName());
		personVO.setStreet(person.getStreet());
		personVO.setCity(person.getCity());
		personVO.setPostalCode(person.getPostalCode());
		personVO.setBirthday(person.getBirthday());
		return personVO;
	}

	public static Person parseToPerson(PersonVO personVO) {
		Person person = new Person();
		person.setId(personVO.getId());
		person.setFirstName(personVO.getFirstName());
		person.setLastName(personVO.getLastName());
		person.setStreet(personVO.getStreet());
		person.setCity(personVO.getCity());
		person.setPostalCode(personVO.getPostalCode());
		person.setBirthday(personVO.getBirthday());
		return person;
	}
}

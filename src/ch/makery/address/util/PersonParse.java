package ch.makery.address.util;

import ch.makery.address.model.PersonModel;
import ch.makery.address.model.PersonVO;

/**
 * Class to convert a PersonModel to a PersonVO and vice versa.
 *
 * @author Isa González
 */
public class PersonParse {
	public static PersonVO parseToPersonVO(PersonModel person) {
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

	public static PersonModel parseToPerson(PersonVO personVO) {
		PersonModel person = new PersonModel();
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

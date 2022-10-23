package ch.makery.address.model.repository;

import ch.makery.address.model.PersonException;
import ch.makery.address.model.PersonVO;

import java.util.ArrayList;

public interface PersonRepository {
	void savePerson(PersonVO newPerson) throws PersonException;
	void deletePerson(int id) throws PersonException;
	void editPerson(int id) throws PersonException;
	ArrayList<PersonVO> loadPersonList() throws PersonException;
}

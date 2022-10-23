package ch.makery.address.model.repository;

import ch.makery.address.model.ExceptionPerson;
import ch.makery.address.model.PersonVO;

import java.util.ArrayList;

public interface PersonRepository {
	void savePerson(PersonVO newPerson) throws ExceptionPerson;
	void deletePerson(int id) throws ExceptionPerson;
	void editPerson(int id) throws ExceptionPerson;
	ArrayList<PersonVO> loadPersonList() throws ExceptionPerson;
}

package ch.makery.address.model.repository;

import ch.makery.address.model.PersonException;
import ch.makery.address.model.PersonVO;

import java.util.List;

/**
 * Interface that defines how to implement a Person repository.
 */
public interface PersonRepository {
	/**
	 * Opens a database connection and inserts a new person.
	 *
	 * @param newPerson - the object with the new data to insert
	 * @throws PersonException Exception thrown if a controlled error happens
	 */
	void savePerson(PersonVO newPerson) throws PersonException;

	/**
	 * Opens a database connection and deletes a specific person.
	 *
	 * @param id - the ID of the person to delete
	 * @throws PersonException Exception thrown if a controlled error happens
	 */
	void deletePerson(int id) throws PersonException;

	/**
	 * Opens a database connection and updates the received person.
	 *
	 * @param person - the data to send to the database
	 * @throws PersonException Exception thrown if a controlled error happens
	 */
	void editPerson(PersonVO person) throws PersonException;

	/**
	 * Opens a database connection and queries all the persons in the database.
	 *
	 * @return ArrayList<PersonVO> - The list of persons in the database
	 * @throws PersonException Exception thrown if a controlled error happens
	 */
	List<PersonVO> loadPersonList() throws PersonException;
}

package ch.makery.address.service;

import ch.makery.address.model.PersonException;
import ch.makery.address.model.PersonModel;
import ch.makery.address.model.repository.PersonRepository;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import ch.makery.address.util.PersonParse;

/**
 * The class of person service to connect the application with the repository.
 *
 * @author Isa González
 */
public class PersonService {
    private PersonRepository repository;

    public PersonService() {
        this.repository = new PersonRepositoryImpl();
    }

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonRepository getRepository() {
        return this.repository;
    }

    public void setRepository(PersonRepository repository) {
        this.repository = repository;
    }

    public void savePerson(PersonModel person) throws PersonException {
        repository.savePerson(PersonParse.parseToPersonVO(person));
    }

    public void editPerson(PersonModel person) throws PersonException {
        repository.editPerson(PersonParse.parseToPersonVO(person));
    }

    public void deletePerson(PersonModel personToDelete) throws PersonException {
        repository.deletePerson(personToDelete.getId());
    }
}

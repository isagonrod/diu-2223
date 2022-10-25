package ch.makery.address.model;

import ch.makery.address.model.repository.PersonRepository;

import java.util.List;

public class PersonModel {
	private PersonRepository repository;

	public PersonModel() {}

	public PersonRepository getRepository() {
		return this.repository;
	}

	public void setRepository(PersonRepository repository) {
		this.repository = repository;
	}

	public List<PersonVO> loadPersonList() throws PersonException {
		return this.repository.loadPersonList();
	}
}

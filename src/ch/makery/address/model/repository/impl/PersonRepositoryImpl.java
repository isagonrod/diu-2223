package ch.makery.address.model.repository.impl;

import ch.makery.address.model.PersonException;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.PersonRepository;

import java.util.ArrayList;

public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public void savePerson(PersonVO newPerson) throws PersonException {

    }

    @Override
    public void deletePerson(int id) throws PersonException {

    }

    @Override
    public void editPerson(int id) throws PersonException {

    }

    @Override
    public ArrayList<PersonVO> loadPersonList() throws PersonException {


        return null;
    }
}

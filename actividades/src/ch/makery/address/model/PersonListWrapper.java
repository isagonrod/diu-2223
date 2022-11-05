package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 *
 * @author Isa González
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<PersonModel> persons;

    @XmlElement(name = "person")
    public List<PersonModel> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonModel> persons) {
        this.persons = persons;
    }
}
package ch.makery.address.model.repository.impl;

import ch.makery.address.model.PersonException;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.PersonRepository;
import ch.makery.address.util.DatabaseConnection;
import ch.makery.address.util.DatabaseStatement;
import ch.makery.address.util.DateUtil;
import ch.makery.address.util.MySqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {
    private DatabaseConnection conn;
    private DatabaseStatement stmt;

    public PersonRepositoryImpl() {
        this.conn = new MySqlConnection("localhost", "3306", "agenda", "root", "");
        this.conn.connectToDataBase();
        this.stmt = this.conn.getNewStatement();
    }
    @Override
    public void savePerson(PersonVO newPerson) throws PersonException {
        String fields = "firstName, lastName, street, city, postalCode, birthday";
        String values = String.format("%s, %s, %s, %s, %d, %s",
                newPerson.getFirstName(), newPerson.getLastName(),
                newPerson.getStreet(), newPerson.getCity(),
                newPerson.getPostalCode(), DateUtil.format(newPerson.getBirthday()));

        newPerson.setId(this.stmt.getNextId("person")); //TODO: o quizás devolver el objeto entero al controlador

        if (this.stmt.insert(fields, values, "person") == -1) {
            this.stmt.closeStatement();
            this.conn.closeDataBase();
            throw new PersonException("Error al guardar la persona");
        }

        this.stmt.closeStatement();
        this.conn.closeDataBase();
    }

    @Override
    public void deletePerson(int id) throws PersonException {
        if (this.stmt.delete("person", "id=" + id) == -1) {
            this.stmt.closeStatement();
            this.conn.closeDataBase();
            throw new PersonException("Error al borrar la persona");
        }

        this.stmt.closeStatement();
        this.conn.closeDataBase();
    }

    @Override
    public void editPerson(PersonVO person) throws PersonException {
        String fields = String.format("firstName='%s', lastName='%s', street='%s', city='%s', postalCode=%d, birthday='%s'",
                person.getFirstName(), person.getLastName(),
                person.getStreet(), person.getCity(),
                person.getPostalCode(), DateUtil.format(person.getBirthday()));

        if (this.stmt.update(fields, "person", "id=" + person.getId()) == -1) {
            this.stmt.closeStatement();
            this.conn.closeDataBase();
            throw new PersonException("Error al actualizar la persona");
        }

        this.stmt.closeStatement();
        this.conn.closeDataBase();
    }

    @Override
    public List<PersonVO> loadPersonList() throws PersonException {
        ResultSet rs = this.stmt.select("*", "person", null, null);
        List<PersonVO> result = new ArrayList<>();
        PersonVO tmp;

        try {
            while (rs.next()) {
                tmp = new PersonVO();

                tmp.setId(rs.getInt("id"));
                tmp.setFirstName(rs.getString("firstName"));
                tmp.setLastName(rs.getString("lastName"));
                tmp.setStreet(rs.getString("street"));
                tmp.setCity(rs.getString("city"));
                tmp.setPostalCode(rs.getInt("postalCode"));
                tmp.setBirthday(rs.getDate("birthday").toLocalDate());

                result.add(tmp);
            }

            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            this.stmt.closeStatement();
            this.conn.closeDataBase();
            throw new PersonException("Error al listar las personas");
        }

        this.stmt.closeStatement();
        this.conn.closeDataBase();

        return result;
    }
}

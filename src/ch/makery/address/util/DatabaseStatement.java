package ch.makery.address.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class to encapsulate a Statement instance with helper methods.
 */
public class DatabaseStatement {
    private Statement stmt;

    /**
     * Builds a DatabaseStatement instance from a Statement one.
     *
     * @param stmt - the statement instance to use
     */
    public DatabaseStatement(Statement stmt) {
        this.stmt = stmt;
    }

    /**
     * Closes the current statement.
     * Will make inaccessible any derived ResultSet instances.
     */
    public void closeStatement() {
        try {
            this.stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simple method that returns the ResultSet from doing a regular Select query.
     *
     * @param query - the string query to send to the database
     * @return ResultSet - the result set if there was no exception
     */
    public ResultSet queryResult(String query) {
        ResultSet rs = null;
        try {
            rs = this.stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Simple method that returns the integer response by the server when doing resultless queries.
     *
     * @param query - the string query to send to the database
     * @return int - returns -1 if the query was not successful (an exception was thrown)
     */
    public int queryUpdate(String query) {
        int res = -1;
        try {
            res = this.stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Method that builds a Select query to a table based on the fields, conditions and order indicated.<br>
     * Fields (when querying many) must be separated by commas within the String.
     * Conditions must be a String like "field='value'".<br>
     * Order parameters must indicate the name of the field to use and the ASC or DESC as needed.
     *
     * @param fields - a string of the fields to be called, separated by a comma
     * @param table - the table name or names to be queried
     * @param conditions - the conditions to be used in the query (if any)
     * @param order - the order to be used in the query (if any)
     * @return ResultSet - the result set if there was no exception
     */
    public ResultSet select(String fields, String table, String conditions, String order) {
        String query;

        query = "SELECT " + fields + " FROM " + table;
        if (conditions != null && conditions.compareTo("") != 0) {
            query = query + " WHERE " + conditions;
        }
        if (order != null && order.compareTo("") != 0) {
            query = query + " ORDER BY " + order;
        }

        query = query + ";";
        return this.queryResult(query);
    }
}

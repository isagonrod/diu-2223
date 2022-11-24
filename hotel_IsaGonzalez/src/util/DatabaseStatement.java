package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase para crear el Statement necesario para hacer las consultas y modificaciones en la base de datos.
 *
 * @author Isa González
 */
public class DatabaseStatement {
    private Statement stmt;

    public DatabaseStatement(Statement stmt) {
        this.stmt = stmt;
    }

    public void closeStatement() {
        try {
            this.stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet queryResult(String query) {
        ResultSet rs = null;
        try {
            rs = this.stmt.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public int queryUpdate(String query) {
        int result = -1;
        try {
            result = this.stmt.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ResultSet select(String fields, String table, String conditions, String order) {
        String query = "SELECT " + fields + " FROM " + table;
        if (conditions != null && !conditions.equalsIgnoreCase("")) {
            query += " WHERE " + conditions;
        }
        if (order != null && !order.equalsIgnoreCase("")) {
            query += " ORDER BY " + order;
        }
        query += ";";
        return this.queryResult(query);
    }

    public int insert(String fields, String values, String table) {
        String query = "INSERT INTO " + table;
        if (fields != null && !fields.equalsIgnoreCase("")) {
            query += " (" + fields + ")";
        }
        query += " VALUES (" + values + ");";
        return this.queryUpdate(query);
    }

    public int delete(String table, String conditions) {
        String query = "DELETE FROM " + table;
        if (conditions != null && !conditions.equalsIgnoreCase("")) {
            query += " WHERE " + conditions;
        }
        query += ";";
        return this.queryUpdate(query);
    }

    public int update(String fields, String table, String conditions) {
        String query = "UPDATE " + table + " SET " + fields;
        if (conditions != null && !conditions.equalsIgnoreCase("")) {
            query += " WHERE " + conditions;
        }
        query += ";";
        return this.queryUpdate(query);
    }

    public int getNextCodReserva(String table, String idFieldName) {
        ResultSet rs = this.queryResult("SELECT IFNULL(MAX(" + idFieldName + ")+1,1) " + idFieldName + " FROM " + table + ";");
        int result = -1;
        try {
            rs.next();
            result = rs.getInt(idFieldName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public String getNextDni(String table, String idFieldName) {
        ResultSet rs = this.queryResult("SELECT IFNULL(MAX(" + idFieldName + ")+1,1) " + idFieldName + " FROM " + table + ";");
        String result = null;
        try {
            rs.next();
            result = rs.getString(idFieldName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}

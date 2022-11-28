package main.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        int res = -1;
        try {
            res = this.stmt.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet select(String fields, String table, String conditions, String order) {
        String query;

        query = "SELECT " + fields + " FROM " + table;
        if (conditions != null && conditions.compareToIgnoreCase("") != 0) {
            query += " WHERE " + conditions;
        }
        if (order != null && order.compareToIgnoreCase("") != 0) {
            query += " ORDER BY " + order;
        }

        query += ";";
        return this.queryResult(query);
    }

    public int delete(String table, String conditions) {
        String query;

        query = "DELETE FROM " + table;
        if (conditions != null && conditions.compareTo("") != 0) {
            query += " WHERE " + conditions;
        }

        query += ";";
        return this.queryUpdate(query);
    }

    public int update(String fields, String table, String conditions) {
        String query;

        query = "UPDATE " + table + " SET " + fields;
        if (conditions != null && conditions.compareTo("") != 0) {
            query += " WHERE " + conditions;
        }

        query += ";";
        return this.queryUpdate(query);
    }

    public int getNextId(String table, String idFieldName) {
        ResultSet rs = this.queryResult("SELECT IFNULL(MAX(" + idFieldName + ")+1,1) " + idFieldName + " FROM " + table + ";");
        int result = -1;

        try {
            rs.next();
            result = rs.getInt(idFieldName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}

package util;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public abstract class DatabaseConnection {
    protected Connection conn;
    protected String dbHost;
    protected String dbSchema;
    protected String dbPort;
    protected String dbUser;
    protected String dbPassword;
    protected String className;

    public DatabaseConnection() {
        throw new RuntimeException(("No se puede instanciar una base de datos sin datos"));
    }

    public DatabaseConnection(String className, String configFileName) {
        Map<String, String> config = loadDbConfig(configFileName);
        this.className = className;
        this.dbHost = config.get("dbHost");
        this.dbPort = config.get("dbPort");
        this.dbSchema = config.get("dbSchema");
        this.dbUser = config.get("dbUser");
        this.dbPassword = config.get("dbPassword");
    }

    public DatabaseConnection(String className, String dbHost, String dbPort,
                              String dbSchema, String dbUser, String dbPassword) {
        this.className = className;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbSchema = dbSchema;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public void connectToDataBase() {
        try {
            Class.forName(className);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public DatabaseStatement getNewStatement() {
        Statement stmt;
        DatabaseStatement dbStmt = null;
        try {
            stmt = this.conn.createStatement();
            dbStmt = new DatabaseStatement(stmt);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dbStmt;
    }

    public void closeDataBase() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            SQLException exceptionIteration = ex;
            System.out.println("\n-- SQLException caption --");
            while (exceptionIteration != null) {
                System.out.println("Message: " + exceptionIteration.getMessage());
                System.out.println("SQLState: " + exceptionIteration.getSQLState());
                System.out.println("ErrorCode: " + exceptionIteration.getErrorCode() + "\n");
                exceptionIteration = exceptionIteration.getNextException();
            }
        }
    }

    public Map<String, String> loadDbConfig(String fileName) {
        Map<String, String> result = new HashMap<>();
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                result.put(st.split("=")[0], st.split("=")[1]);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo de configuración de BDD.");
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            System.out.println("Error de lectura del archivo de configuración de BDD.");
            throw new RuntimeException(ex);
        }
        return result;
    }
}

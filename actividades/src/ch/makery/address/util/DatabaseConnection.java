package ch.makery.address.util;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Top-level class that specifies the basics of a Database connection,
 * but is not bound to any specific database brand.
 * <p>
 * Any new type of SQL database we want for our code must inherit this
 * abstract class, and only through them can a connection be established.
 *
 * @author Isa González
 */
public abstract class DatabaseConnection {
    protected Connection conn;
    protected String dbHost;
    protected String dbSchema;
    protected String dbPort;
    protected String dbUser;
    protected String dbPass;
    protected String className;

    /**
     * Default constructor. Do not use.
     */
    public DatabaseConnection() {
        throw new RuntimeException("Cannot instance a Database connection without data");
    }

    /**
     * Creates a connection using the default configuration data in the specified file
     * using the required className for the driver.
     * @param className - the JDBC class name
     * @param configFileName - the configuration file name
     */
    public DatabaseConnection(String className, String configFileName) {
        Map<String, String> config = loadDbConfig(configFileName);

        this.className = className;
        this.dbHost = config.get("dbHost");
        this.dbPort = config.get("dbPort");
        this.dbSchema = config.get("dbSchema");
        this.dbUser = config.get("dbUser");
        this.dbPass = config.get("dbPass");
    }

    /**
     * Creates a connection element but does not start it yet.
     *
     * @param className - the Java driver name to use
     * @param dbHost - the address in which the server is
     * @param dbPort - the port for the server
     * @param dbSchema - the schema/database name to connect to
     * @param dbUser - the user for the server
     * @param dbPass - the password for the user
     */
    public DatabaseConnection(String className, String dbHost, String dbPort,
                              String dbSchema, String dbUser, String dbPass) {
        this.className = className;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbSchema = dbSchema;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    /**
     * Default method that opens a database connection in the subclasses
     * that inherit this class. Here, it will only instance the database driver.
     */
    public void connectToDataBase() {
        try {
            Class.forName(className);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Function that creates a new DatabaseStatement instance for running queries.
     *
     * @return the new statement object
     */
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

    /**
     * Function that closes the database connection.
     */
    public void closeDataBase() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            SQLException exceptionIteration = ex;
            System.out.println("\n-- SQLException caption --\n");
            while (exceptionIteration != null) {
                System.out.println("Message: " + exceptionIteration.getMessage());
                System.out.println("SQLState: " + exceptionIteration.getSQLState());
                System.out.println("ErrorCode: " + exceptionIteration.getErrorCode() + "\n");
                exceptionIteration = exceptionIteration.getNextException();
            }
        }
    }

    /**
     * Loads database configuration from a specified file.
     *
     * @param fileName - the file to read
     * @return Map - A map with the config values
     */
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
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo de configuración de BDD.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error de lectura del archivo de configuración de BDD.");
            throw new RuntimeException(e);
        }

        return result;
    }
}

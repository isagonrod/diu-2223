package ch.makery.address.util;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Subclass that inherits base functionality from DatabaseConnection
 * and defines the specific connection data and drivers to be used
 * to run queries against an Oracle SQL Database.
 *
 * @author Isa González
 */
public class OracleConnection extends DatabaseConnection {
    /**
     * Creates a connection element but does not start it yet.
     *
     * @param dbHost - the address in which the server is
     * @param dbPort - the port for the server
     * @param dbUser - the user for the server
     * @param dbPass - the password for the user
     */
    public OracleConnection(String dbHost, String dbPort, String dbUser, String dbPass) {
        super("oracle.jdbc.driver.OracleDriver", dbHost, dbPort, "XE", dbUser, dbPass);
    }

    /**
     * Instances the Java driver for this database and opens a connection.
     */
    public void connectToDataBase() {
        super.connectToDataBase();

        try {
            this.conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@" + dbHost + ":" + dbPort + ":" + dbSchema, dbUser, dbPass
            );
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

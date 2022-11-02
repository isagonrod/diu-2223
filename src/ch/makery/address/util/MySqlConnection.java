package ch.makery.address.util;

import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Subclass that inherits base functionality from DatabaseConnection
 * and defines the specific connection data and drivers to be used
 * to run queries against a MySQL/MariaDB Database.
 *
 * @author Isa González
 */
public class MySqlConnection extends DatabaseConnection {
    /**
     * Creates a connection using the default configuration data in the .MySqlConfig file.
     */
    public MySqlConnection() {
        super("com.mysql.cj.jdbc.Driver", Paths.get("src/ch/makery/address/util/.MySqlConfig").toAbsolutePath().normalize().toString());
    }

    /**
     * Creates a connection element but does not start it yet.
     *
     * @param dbHost - the address in which the server is
     * @param dbPort - the port for the server
     * @param dbSchema - the schema/database name to connect to
     * @param dbUser - the user for the server
     * @param dbPass - the password for the user
     */
    public MySqlConnection(String dbHost, String dbPort, String dbSchema, String dbUser, String dbPass) {
        super("com.mysql.cj.jdbc.Driver", dbHost, dbPort, dbSchema, dbUser, dbPass);
    }

    /**
     * Instances the Java driver for this database and opens a connection.
     */
    public void connectToDataBase() {
        super.connectToDataBase();

        try {
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbSchema + "?useSSL=false&allowPublicKeyRetrieval=true", dbUser, dbPass
            );
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

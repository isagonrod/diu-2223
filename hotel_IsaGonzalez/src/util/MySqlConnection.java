package util;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection extends DatabaseConnection {
    public MySqlConnection() {
        super("com.mysql.cj.jdbc.Driver", Paths.get("src/util/.MySqlConfig").toAbsolutePath().normalize().toString());
    }

    public MySqlConnection(String dbHost, String dbPort, String dbSchema, String dbUser, String dbPassword) {
        super("com.mysql.cj.jdbc.Driver", dbHost, dbPort, dbSchema, dbUser, dbPassword);
    }

    public void connectToDataBase() throws CommunicationsException {
        super.connectToDataBase();
        try {
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbSchema + "?useSSL=false&allowPublicKeyRetrieval=true", dbUser, dbPassword
            );
        } catch (CommunicationsException ex) {
            throw new CommunicationsException("Error de conexión con la base de datos", ex);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

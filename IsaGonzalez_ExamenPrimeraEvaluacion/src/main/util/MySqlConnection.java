package main.util;

import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection extends DatabaseConnection {
    public MySqlConnection() {
        super("com.mysql.cj.jdbc.Driver", Paths.get("src/main/util/.MySqlConfig").toAbsolutePath().normalize().toString());
    }

    public MySqlConnection(String dbHost, String dbPort, String dbSchema, String dbUser, String dbPass) {
        super("com.mysql.cj.jdbc.Driver", dbHost, dbPort, dbSchema, dbUser, dbPass);
    }

    public void connectToDataBase() {
        super.connectToDataBase();

        try {
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbSchema + "?useSSL=false&allowPublicKeyRetrieval=true", dbUser, dbPassword
            );
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

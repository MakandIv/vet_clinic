import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The class responsible for connecting to the database. The connection is performed by the "connect" method.
 */
public class JDBCConnection {

    /**
     * Performs a connection to the database.
     * @return database connection
     * @throws SQLException if an error occurs during connection.
     */
    public static Connection connect() throws SQLException {
        Properties properties = new Properties();
        String jdbcURL = null;
        String login = null;
        String password = null;
        try {
            properties.load(new FileReader("jdbc.properties"));
            jdbcURL = properties.getProperty("jdbcURL");
            login = properties.getProperty("login");
            password = properties.getProperty("password");
        } catch (IOException ignored) {}

        if (jdbcURL == null || login == null || password == null) { // Hardcoded data if jbdc.properies not exist.
            jdbcURL = "jdbc:postgresql://localhost:5433/vet_clinic";
            login = "postgres";
            password = "adminad";
        }

        return DriverManager.getConnection(jdbcURL, login, password);
    }
}

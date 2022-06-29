import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        String jdbcURL = "jdbc:postgresql://localhost:5433/vet_clinic";
        String login = "postgres";
        String password = "adminad";
        return DriverManager.getConnection(jdbcURL, login, password);
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Authentication {

    public static Connection authenticate() {
        String jdbcURL = "jdbc:postgresql://localhost:5433/vet_clinic";
        String login = "postgres";
        String password = "adminad";

        try {
            return DriverManager.getConnection(jdbcURL, login, password);
        } catch (SQLException e) {
            System.out.println("Database connection error");
        }
        return null;
    }
}

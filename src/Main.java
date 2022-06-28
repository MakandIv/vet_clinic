import command.CommandReader;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Connection connection = Authentication.authenticate();
        if (connection != null) {
            System.out.println("Connected");

            System.out.println("Enter \"help\" for help on commands.");
            System.out.println("Enter the command: ");
            Scanner scanner = new Scanner(System.in);

            String command = scanner.nextLine();

            int isCycle = 0;

            while (isCycle == 0) {
                isCycle = CommandReader.readCommand(connection, command);
            }
        }
    }

}

import command.CommandReader;
import command.Return;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = JDBCConnection.connect()) {
            System.out.println("Connected");

            Return isOk = Return.SUCCESS;
            while (!isOk.equals(Return.EXCEPTION) && !isOk.equals(Return.END)) {
                if (isOk.equals(Return.CANCELED)) {
                    System.out.println("Operation canceled.");
                }
                if (isOk.equals(Return.UNDEFINED)) {
                    System.out.println("Invalid command.");
                }
                System.out.println("\nEnter \"help\" for help on commands. Enter \"exit\" for end the program.");
                System.out.println("Enter the command: ");

                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                isOk = CommandReader.readCommand(connection, command);
            }
            System.out.println("The program has ended.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

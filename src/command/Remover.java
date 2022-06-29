package command;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Remover {

    /**
     * The main method that handles a request to delete a record in the database. Calls individual methods to operate on a specific table.
     * @param connection the current database connection.
     * @param commandArray command options.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    public static Return remove(Connection connection, String[] commandArray) {
        String objectToBeRemoved;
        if (commandArray.length < 2) {
            System.out.println("What do you want to remove? Enter \"patient\" for remove patient. Enter \"exit\" for exit remover.");
            Scanner scanner = new Scanner(System.in);
            objectToBeRemoved = scanner.nextLine();
        } else {
            objectToBeRemoved = commandArray[3];
        }
        return switch (objectToBeRemoved) {
            case "patient" -> removePatient(connection, commandArray);
            case "exit" -> Return.SUCCESS;
            default -> Return.CANCELED;
        };
    }

    /**
     * Displays a deletion warning. Tells the console who is being removed and what else will be removed. Requests confirmation.
     * @param connection The current database connection.
     * @param id patient ID
     * @return true if the confirmation has been received, false otherwise.
     * @throws SQLException Throws an exception if the patient data could not be retrieved.
     */
    private static boolean warnRemoving(Connection connection, int id) throws SQLException {
        String sql = "SELECT surname, name, patronymic FROM patients WHERE id_patient = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            System.out.println("Patient not found.");
            return false;
        }

        String surname = resultSet.getString("surname");
        String name = resultSet.getString("name");
        String patronymic = resultSet.getString("patronymic");

        sql = "SELECT COUNT(*) FROM appointments WHERE id_patient = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        resultSet.next();
        long countAppointments = resultSet.getLong("count");

        System.out.println("Are you sure you want to remove " + name + " " + patronymic + " " + surname + "?");
        System.out.println("This will also delete " + countAppointments + " appointments.");
        System.out.println("Enter \"y\" or \"yes\" for confirm to remove.");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine();
        return confirmation.equals("yes") || confirmation.equals("y");
    }

    /**
     * Removes a patient from the database by ID. All appointments are also removed in a cascade.
     * @param connection the current database connection.
     * @param commandArray command options.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    private static Return removePatient(Connection connection, String[] commandArray) {
        int id;
        if (commandArray.length < 3) { // if not exist id
            System.out.println("Enter the ID of the patient you want to remove.");
            Scanner scanner = new Scanner(System.in);
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You didn't enter a number.");
                return Return.CANCELED;
            }
        } else { // if exist id
            try {
                id = Integer.parseInt(commandArray[3]);
            } catch (NumberFormatException e) {
                System.out.println("You didn't enter a number.");
                return Return.CANCELED;
            }
        }
        try {
            if (warnRemoving(connection, id)) {
                String sql = "DELETE FROM patients WHERE id_patient = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                int rows = statement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Success");
                    return Return.SUCCESS;
                } else {
                    System.out.println("Remove failed.");
                    return Return.EXCEPTION;
                }

            } else {
                return Return.CANCELED;
            }
        } catch (SQLException e) {
            return Return.EXCEPTION;
        }
    }
}

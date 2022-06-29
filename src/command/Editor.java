package command;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The class is responsible for performing data changes and database updates.
 */
public class Editor {

    /**
     *The main method that handles a request to update a record in the database. Calls individual methods to operate on a specific table.
     * @param connection the current database connection.
     * @param commandArray command options.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    public static Return edit(@NotNull Connection connection, @NotNull String[] commandArray) {
        String editedObject = null;
        if (commandArray.length >= 2) {
            editedObject = commandArray[1];
        }
        if (editedObject == null) {
            System.out.println("What do you want to edit? Enter patient/appointmentstatus. Enter \"exit\" for exit creator.");
            Scanner scanner = new Scanner(System.in);
            editedObject = scanner.nextLine();
        }
        return switch (editedObject) {
            case "patient" -> editPatient(connection, commandArray);
            case "appointmentstatus" -> editAppointmentStatus(connection, commandArray);
            case "exit" -> Return.SUCCESS;
            default -> Return.CANCELED;
        };
    }

    /**
     * Updates the status of an appointment.
     * @param connection the current database connection.
     * @param commandArray command options.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    private static Return editAppointmentStatus(@NotNull Connection connection, @NotNull String[] commandArray) {
        int id;
        if (commandArray.length < 3) {// if not exist id
            System.out.println("Enter the ID of the appointment whose status you want to change.");
            Scanner scanner = new Scanner(System.in);
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You didn't enter a number.");
                return Return.CANCELED;
            }
        } else {
            try {
                id = Integer.parseInt(commandArray[3]);
            } catch (NumberFormatException e) {
                System.out.println("You didn't enter a number.");
                return Return.CANCELED;
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new status (new, process, canceled, awaiting payment, completed): ");
        String status = scanner.nextLine();
        if (!status.equals("new")
                && !status.equals("process")
                && !status.equals("canceled")
                && !status.equals("awaiting payment")
                && !status.equals("completed")) {
            System.out.println("You have entered an invalid status.");
            return Return.CANCELED;
        }

        String sql = "UPDATE appointments SET status = ? WHERE id_appointment = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Success");
                return Return.SUCCESS;
            } else {
                System.out.println("Error update data");
                return Return.EXCEPTION;
            }
        } catch (SQLException e) {
            System.out.println("Error update data");
            return Return.EXCEPTION;
        }
    }

    /**
     * Updates patient data.
     * @param connection the current database connection.
     * @param commandArray command options.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    private static Return editPatient(@NotNull Connection connection, @NotNull String... commandArray) {
        int id;
        if (commandArray.length < 3) { // if not exist id
            System.out.println("Enter the ID of the patient whose name you want to change.");
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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Surname: ");
        String surname = scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Patronymic (if it doesn't exist, leave the field blank.): ");
        String patronymic = scanner.nextLine();

        String sql = "UPDATE patients SET surname = ?, name = ?, patronymic = ? WHERE id_patient = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, surname);
            statement.setString(2, name);
            statement.setString(3, patronymic);
            statement.setInt(4, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Success");
                return Return.SUCCESS;
            } else {
                System.out.println("Error update data");
                return Return.EXCEPTION;
            }
        } catch (SQLException e) {
            System.out.println("Error update data");
            return Return.EXCEPTION;
        }
    }
}

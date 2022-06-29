package command;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Scanner;

public class Editor {

    public static int edit(@NotNull Connection connection, @NotNull String[] commandArray) {
        String editedObject = null;
        if (commandArray.length >= 2) {
            editedObject = commandArray[1];
        }
        if (editedObject == null) {
            System.out.println("What do you want to edit? Enter patient/receptionstatus. Enter \"exit\" for exit creator.");
            Scanner scanner = new Scanner(System.in);
            editedObject = scanner.nextLine();
        }
        return switch (editedObject) {
            case "patient" -> editPatient(connection, commandArray);
            case "receptionstatus" -> editReceptionStatus(connection, commandArray);
            case "exit" -> 0;
            default -> -1;
        };
    }

    private static int editReceptionStatus(@NotNull Connection connection, @NotNull String[] commandArray) {
        int id;
        if (commandArray.length < 3) {// if not exist id
            System.out.println("Enter the ID of the reception whose status you want to change.");
            Scanner scanner = new Scanner(System.in);
            id = scanner.nextInt();
            scanner.close();
        } else {
            try {
                id = Integer.parseInt(commandArray[3]);
            } catch (NumberFormatException e) {
                System.out.println("You didn't enter a number.");
                return -1;
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
            return -1;
        }

        String sql = "UPDATE patients SET status = ? WHERE id_reception = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                return 0;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            return -1;
        }
    }

    private static int editPatient(@NotNull Connection connection, @NotNull String... commandArray) {
        int id;
        if (commandArray.length < 3) { // if not exist id
            System.out.println("Enter the ID of the patient whose name you want to change.");
            Scanner scanner = new Scanner(System.in);
            id = scanner.nextInt();
            scanner.close();
        } else { // if exist id
            try {
                id = Integer.parseInt(commandArray[3]);
            } catch (NumberFormatException e) {
                System.out.println("You didn't enter a number.");
                return -1;
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Surname: ");
        String surname = scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Patronymic (if it doesn't exist, leave the field blank.): ");
        String patronymic = scanner.nextLine();
        scanner.close();

        String sql = "UPDATE patients SET surname = ?, name = ?, patronymic = ? WHERE id_patient = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, surname);
            statement.setString(2, name);
            statement.setString(3, patronymic);
            statement.setInt(4, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                return 0;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            return -1;
        }
    }
}

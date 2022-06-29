package command;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Viewer {

    /**
     * Displays a styled data table.
     * @param header table header
     * @param data table data
     */
    private static void viewTable(String[] header, String[][] data) {
        for (String ignored : header) {
            System.out.format("+------------------------");
        }
        System.out.format("+%n");
        for (String name : header) {
            System.out.format("| %-23s", name);
        }
        System.out.format("|%n");
        for (String ignored : header) {
            System.out.format("+------------------------");
        }
        System.out.format("+%n");
        for (String[] row : data) {
            for (String cell : row) {
                System.out.format("| %-23s", cell);
            }
            System.out.format("|%n");
        }
        if (data.length > 0) {
            for (String ignored : header) {
                System.out.format("+------------------------");
            }
            System.out.format("+%n");
        }
    }

    /**
     * The main method that handles the request to select data from the database. Calls individual method that operates on a specific table.
     * @param connection the current database connection.
     * @param commandArray command options.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    public static Return view(Connection connection, String[] commandArray) {
        String objectToBeViewed = null;
        if (commandArray.length >= 2) {
            objectToBeViewed = commandArray[1];
        }
        if (objectToBeViewed == null) {
            System.out.println("What do you want to view? Enter patientlist/appointmentlist. Enter \"exit\" for exit creator.");
            Scanner scanner = new Scanner(System.in);
            objectToBeViewed = scanner.nextLine();
        }
        return switch (objectToBeViewed) {
            case "patientlist" -> viewPatientList(connection);
            case "appointmentlist" -> viewAppointmentList(connection, commandArray);
            case "exit" -> Return.SUCCESS;
            default -> Return.CANCELED;
        };
    }

    /**
     * Selects from the database the data on doctor's appointments for a particular patient. Calls the viewTable method to display data.
     * @param connection the current database connection.
     * @param commandArray command options.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    private static Return viewAppointmentList(Connection connection, String[] commandArray) {
        int id = -1;
        if (commandArray.length < 3) {
            System.out.println("Which patient's appointments do you want to display?");
            Scanner scanner = new Scanner(System.in);
            id = scanner.nextInt();
        }
        String sql = "SELECT appointments.id_appointment," +
                " appointments.appointment_timestamp," +
                " appointments.status, doctors.surname," +
                " doctors.name, doctors.patronymic" +
                " FROM appointments, doctors" +
                " WHERE appointments.id_patient = ? AND appointments.id_doctor = doctors.id_doctor" +
                " ORDER BY id_appointment";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            String[] header = new String[] {"ID", "Appointments timestamp", "Doctor"};
            ArrayList<String[]> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new String[] {
                        String.valueOf(resultSet.getInt("id_appointment")),
                        String.valueOf(resultSet.getTimestamp("appointment_timestamp")),
                        resultSet.getString("surname") + " " + resultSet.getString("name") +
                                " " + resultSet.getString("patronymic")
                });
            }
            try {
                viewTable(header, list.toArray(String[][]::new));
                return Return.SUCCESS;
            } catch (ClassCastException e) {
                System.out.println("Data read error.");
                return Return.EXCEPTION;
            }

        } catch (SQLException e) {
            System.out.println("Data read error.");
            return Return.EXCEPTION;
        }
    }

    /**
     * Selects from the database for all patients. Calls the viewTable method to display data.
     * @param connection the current database connection.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    private static Return viewPatientList(Connection connection) {
        String sql = "SELECT * FROM patients ORDER BY id_patient";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            String[] header = new String[] {"ID", "Surname", "Name", "Patronymic", "Registration Date"};
            ArrayList<String[]> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new String[] {
                        String.valueOf(resultSet.getInt("id_patient")),
                        resultSet.getString("surname"),
                        resultSet.getString("name"),
                        resultSet.getString("patronymic"),
                        resultSet.getTimestamp("registration_timestamp").toLocalDateTime()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                });
            }
            try {
                viewTable(header, list.toArray(String[][]::new));
                return Return.SUCCESS;
            } catch (ClassCastException e) {
                System.out.println("Data read error.");
                return Return.EXCEPTION;
            }
        } catch (SQLException e) {
            System.out.println("Data read error.");
            return Return.EXCEPTION;
        }
    }
}

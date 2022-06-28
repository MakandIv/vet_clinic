package command;

import object.Doctor;
import object.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 * The class is responsible for creating records in the database.
 */
public class Creator {

    /**
     * The main method that handles a request to create a new record in the database. Calls individual methods to work with a specific table.
     * @param connection The current database connection.
     * @param commandArray Command options.
     * @return Returns 0 if the operation was successful, otherwise -1.
     */
    public static int create(Connection connection, String[] commandArray) {
        String createdObject = null;
        if (commandArray.length >= 2) {
            createdObject = commandArray[1];
        }
        if (createdObject == null) {
            System.out.println("What do you want to create? Enter patient/doctor. Enter \"exit\" for exit creator.");
            Scanner scanner = new Scanner(System.in);
            createdObject = scanner.nextLine();
        }
        return switch (createdObject) {
            case "patient" -> createPatient(connection, commandArray);
            case "doctor" -> createDoctor(connection, commandArray);
            case "exit" -> 0;
            default -> -1;
        };
    }

    /**
     * Creates an entry in the "doctors" table.
     * @param connection The current database connection.
     * @param fullName New doctor details.
     * @return Returns 0 if the operation was successful, otherwise -1.
     */
    private static int createDoctor(Connection connection, String... fullName) {
        Doctor doctor;
        if (fullName.length < 3) {
            doctor = new Doctor();
        } else if (fullName.length == 4) {
            doctor = new Doctor(fullName[2], fullName[3]);
        } else if (fullName.length >= 5) {
            doctor = new Doctor(fullName[2], fullName[3], fullName[4]);
        } else {
            return -1;
        }
        String sql = "INSERT INTO doctors (surname, name, patronymic) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doctor.getSurname());
            statement.setString(2, doctor.getName());
            statement.setString(3, doctor.getPatronymic());

            int rows = statement.executeUpdate(sql);

            if (rows > 0) {
                return 0;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            return -1;
        }

    }

    /**
     * Creates an entry in the "patients" table.
     * @param connection The current database connection.
     * @param fullName New patient details.
     * @return Returns 0 if the operation was successful, otherwise -1.
     */
    private static int createPatient(Connection connection, String... fullName) {
        Patient patient;
        if (fullName.length < 3) {
            patient = new Patient();
        } else if (fullName.length == 4) {
            patient = new Patient(fullName[2], fullName[3]);
        } else if (fullName.length >= 5) {
            patient = new Patient(fullName[2], fullName[3], fullName[4]);
        } else {
            return -1;
        }
        String sql = "INSERT INTO patients (surname, name, patronymic) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patient.getSurname());
            statement.setString(2, patient.getName());
            statement.setString(3, patient.getPatronymic());
            statement.setTimestamp(4, Timestamp.from(patient.getTimestamp()));

            int rows = statement.executeUpdate(sql);

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

package command;

import object.Doctor;
import object.Patient;
import object.Appointment;

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
     * The main method that handles a request to delete a record in the database. Calls individual methods to operate on a specific table.     * @param connection the current database connection.
     * @param commandArray command options.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    public static Return create(Connection connection, String[] commandArray) {
        String objectToBeCreated = null;
        if (commandArray.length >= 2) {
            objectToBeCreated = commandArray[1];
        }
        if (objectToBeCreated == null) {
            System.out.println("What do you want to create? Enter patient/doctor/appointment. Enter \"exit\" for exit creator.");
            Scanner scanner = new Scanner(System.in);
            objectToBeCreated = scanner.nextLine();
        }
        return switch (objectToBeCreated) {
            case "patient" -> createPatient(connection, commandArray);
            case "doctor" -> createDoctor(connection, commandArray);
            case "appointment" -> createAppointment(connection);
            case "exit" -> Return.SUCCESS;
            default -> Return.CANCELED;
        };
    }

    /**
     * Creates an entry in the "appointments" table.
     * @param connection The current database connection.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    private static Return createAppointment(Connection connection) {
        Appointment appointment;
        appointment = new Appointment();
        if (appointment.getTimestamp() == null) {
            System.out.println("Timestamp failed.");
            return Return.CANCELED;
        }
        String sql = "INSERT INTO appointments (status, id_doctor, id_patient, appointment_timestamp) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, appointment.getStatus());
            statement.setInt(2, appointment.getIdDoctor());
            statement.setInt(3, appointment.getIdPatient());
            statement.setTimestamp(4, Timestamp.from(appointment.getTimestamp()));

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Success");
                return Return.SUCCESS;
            } else {
                System.out.println("Insert data error.");
                return Return.EXCEPTION;
            }
        } catch (SQLException e) {
            System.out.println("Insert data error.");
            return Return.EXCEPTION;
        }
    }

    /**
     * Creates an entry in the "doctors" table.
     * @param connection The current database connection.
     * @param fullName New doctor details.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    private static Return createDoctor(Connection connection, String... fullName) {
        Doctor doctor;
        if (fullName.length < 3) { // if not exist data
            doctor = new Doctor();
        } else if (fullName.length == 4) { // if exist surname and name
            doctor = new Doctor(fullName[2], fullName[3]);
        } else if (fullName.length >= 5) { // if exist all data
            doctor = new Doctor(fullName[2], fullName[3], fullName[4]);
        } else {
            System.out.println("The number of parameters is incorrect.");
            return Return.CANCELED;
        }
        String sql = "INSERT INTO doctors (surname, name, patronymic) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doctor.getSurname());
            statement.setString(2, doctor.getName());
            statement.setString(3, doctor.getPatronymic());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Success");
                return Return.SUCCESS;
            } else {
                System.out.println("Insert data error.");
                return Return.EXCEPTION;
            }
        } catch (SQLException e) {
            System.out.println("Insert data error.");
            return Return.EXCEPTION;
        }
    }

    /**
     * Creates an entry in the "patients" table.
     * @param connection The current database connection.
     * @param fullName New patient details.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    private static Return createPatient(Connection connection, String... fullName) {
        Patient patient;
        if (fullName.length < 3) { // if not exist data
            patient = new Patient();
        } else if (fullName.length == 4) { // if exist surname and name
            patient = new Patient(fullName[2], fullName[3]);
        } else if (fullName.length >= 5) { // if exist all data
            patient = new Patient(fullName[2], fullName[3], fullName[4]);
        } else {
            System.out.println("The number of parameters is incorrect.");
            return Return.CANCELED;
        }
        String sql = "INSERT INTO patients (surname, name, patronymic, registration_timestamp) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patient.getSurname());
            statement.setString(2, patient.getName());
            statement.setString(3, patient.getPatronymic());
            statement.setTimestamp(4, Timestamp.from(patient.getTimestamp()));

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Success");
                return Return.SUCCESS;
            } else {
                System.out.println("Insert data error.");
                return Return.EXCEPTION;
            }
        } catch (SQLException e) {
            System.out.println("Insert data error.");
            return Return.EXCEPTION;
        }

    }

}

package object;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.Scanner;

/**
 * The class creates all the necessary fields for entering into the table.
 */
public class Appointment {
    private final int idPatient;
    private final int idDoctor;
    private final Instant timestamp;
    private final String status;

    public Appointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the doctor ID: ");
        int idDoctor = scanner.nextInt();
        System.out.println("Enter the patient ID: ");
        int idPatient = scanner.nextInt();
        scanner.nextLine(); // skip \n after int
        System.out.println("Enter the date (example: 2022-06-29): ");
        String dateString = scanner.nextLine();
        System.out.println("Enter the time (example: 19:31): ");
        String timeString = scanner.nextLine();
        Instant timestamp = null;
        int attempting = 2;
        while (timestamp == null && attempting > 0) {
            try {
                attempting--;
                StringBuilder timestampBuilder = new StringBuilder();
                timestamp = Instant.parse(timestampBuilder.append(dateString).append("T").append(timeString).append(":00.00Z"));
            } catch (DateTimeException e) {
                System.out.println("Invalid date or time. Try to again.");
                System.out.println("Enter the date (example: 2022-06-29): ");
                dateString = scanner.nextLine();
                System.out.println("Enter the time (example: 19:31): ");
                timeString = scanner.nextLine();
            }
        }
        this.idDoctor = idDoctor;
        this.idPatient = idPatient;
        this.timestamp = timestamp;
        this.status = "new";
    }

    public int getIdPatient() {
        return idPatient;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }
}

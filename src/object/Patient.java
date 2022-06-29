package object;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Scanner;

/**
 * The class creates all the necessary fields for entering into the table.
 */
public class Patient {

    private final String surname; // patient surname
    private final String name; // patient name
    private final String patronymic; // patient patronymic
    private final Instant timestamp; // creation timestamp;

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Constructs a new Patient. Patient with all fields: surname, name and patronymic. The timestamp is self-created using Instant.now().
     * @param surname patient surname string
     * @param name patient name string
     * @param patronymic patient patronymic string
     */
    public Patient(@NotNull String surname, @NotNull String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.timestamp = Instant.now();
    }

    /**
     * Constructs a new Patient. Patient with the fields: surname and name, without patronymic. The timestamp is self-created using Instant.now().
     * @param surname doctor surname string
     * @param name doctor name string
     */
    public Patient(String surname, String name) {
        this.surname = surname;
        this.name = name;
        this.patronymic = "";
        this.timestamp = Instant.now();
    }

    /**
     * Constructs a new Patient. Patient without the fields. When called, requests data from the console. The timestamp is self-created using Instant.now().
     */
    public Patient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Surname: ");
        String surname = scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Patronymic (if it doesn't exist, leave the field blank.): ");
        String patronymic = scanner.nextLine();
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.timestamp = Instant.now();
    }
}

package object;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * The class creates all the necessary fields for entering into the table.
 */
public class Doctor {

    private final String surname; // doctor surname
    private final String name; // doctor name
    private final String patronymic; // doctor patronymic

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Constructs a new Doctor. Doctor with all fields: surname, name and patronymic.
     * @param surname doctor surname string
     * @param name doctor name string
     * @param patronymic doctor patronymic string
     */
    public Doctor(@NotNull String surname, @NotNull String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    /**
     * Constructs a new Doctor. Doctor with the fields: surname and name, without patronymic.
     * @param surname doctor surname string
     * @param name doctor name string
     */
    public Doctor(@NotNull String surname, @NotNull String name) {
        this.surname = surname;
        this.name = name;
        this.patronymic = "";
    }

    /**
     * Constructs a new Doctor. Doctor without the fields. When called, requests data from the console.
     */
    public Doctor() {
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
    }
}

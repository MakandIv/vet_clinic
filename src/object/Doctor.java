package object;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Doctor {
    /* A class  */

    private String surname; // doctor surname
    private String name; // doctor name
    private String patronymic; // doctor patronymic


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
     * Constructs a new repository.Doctor with all fields: surname, name and patronymic.
     *
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
     * Constructs a new repository.Doctor with the fields: surname and name, without patronymic.
     *
     * @param surname doctor surname string
     * @param name doctor name string
     */
    public Doctor(@NotNull String surname, @NotNull String name) {
        new Doctor(surname, name, "");
    }

    /**
     * Constructs a new repository.Doctor without the fields. When called, requests data from the console.
     */
    public Doctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Surname: ");
        String surname = scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Patronymic (if it doesn't exist, leave the field blank.): ");
        String patronymic = scanner.nextLine();
        if (patronymic.equals("")) {
            new Doctor(surname, name);
        } else {
            new Doctor(surname, name, patronymic);
        }
    }
}

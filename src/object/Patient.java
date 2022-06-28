package object;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Scanner;

public class Patient {

    private String surname; // patient surname
    private String name; // patient name
    private String patronymic; // patient patronymic

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    private Instant timestamp; // creation timestamp;

    public Patient(@NotNull String surname, @NotNull String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.timestamp = Instant.now();
    }

    public Patient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Surname: ");
        String surname = scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Patronymic (if it doesn't exist, leave the field blank.): ");
        String patronymic = scanner.nextLine();
        if (patronymic.equals("")) {
            new Patient(surname, name);
        } else {
            new Patient(surname, name, patronymic);
        }
    }

    public Patient(String surname, String name) {
        new Patient(surname, name, "");
    }
}

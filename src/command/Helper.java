package command;

public class Helper {

    public static int help() {
        System.out.println("help - Command help");
        System.out.println("create doctor [<surname> <name>[ <patronymic>]] - Create new doctor");
        System.out.println("create patient - Create new patient");
        System.out.println("create reception - Create new patient reception");
        System.out.println("view receptionlist[ <id_patient>] - View a reception list for a specific patient");
        System.out.println("view patientlist - View a patient list");
        System.out.println("edit patient[ <id_patient>] - Edit patient name");
        System.out.println("edit receptionstatus - Edit status of reception");
        System.out.println("remove patient[ <id_patient>] - Delete patient from database");
        System.out.println("exit - Exit the program");
        return 0;
    }
}

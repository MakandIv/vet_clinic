package command;

public class Helper {

    /**
     * Displays help for all commands.
     * @return Always Return.SUCCESS, since there are no exceptions or cancels. Made to track the state of the program.
     */
    public static Return help() {
        System.out.println("help - Command help");
        System.out.println("create doctor [<surname> <name>[ <patronymic>]] - Create new doctor");
        System.out.println("create patient - Create new patient");
        System.out.println("create appointment - Create new patient appointment");
        System.out.println("view appointmentlist <id_patient> - View a appointment list for a specific patient");
        System.out.println("view patientlist - View a patient list");
        System.out.println("edit patient[ <id_patient>] - Edit patient name");
        System.out.println("edit appointmentstatus[ <id_appointment>] - Edit status of appointment");
        System.out.println("remove patient[ <id_patient>] - Delete patient from database");
        System.out.println("exit - Exit the program");
        return Return.SUCCESS;
    }
}

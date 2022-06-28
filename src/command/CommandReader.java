package command;

import java.sql.Connection;

public class CommandReader {

    private static CommandType getCommandType(String commandType) {
        return switch (commandType) {
            case "create" -> CommandType.CREATE;
            case "edit" -> CommandType.EDIT;
            case "remove" -> CommandType.REMOVE;
            case "help" -> CommandType.HELP;
            case "exit" -> CommandType.EXIT;
            default -> CommandType.UNDEFINED;
        };
    }

    public static int readCommand(Connection connection, String command) {
        CommandType commandType = getCommandType((command.split(" "))[0]);

        return switch (commandType) {
            case CREATE -> Creator.create(connection, command.split(" "));
            case EDIT -> Editor.edit(connection, command.split(" "));
            case REMOVE -> Remover.remove(connection, command.split(" "));
            case HELP -> Helper.help();
            case EXIT -> 1;
            case UNDEFINED -> -1;
        };
    }
}

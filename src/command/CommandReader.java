package command;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;

/**
 * User command parser. Has one public method "readCommand" that parses the command type.
 */
public class CommandReader {

    /**
     * Specifies the command type.
     * @param commandType command keyword that specifies its type
     * @return if it is an existing command type, returns its CommandType, otherwise returns CommandType.UNDEFINED.
     */
    private static CommandType getCommandType(@NotNull String commandType) {
        return switch (commandType) {
            case "create" -> CommandType.CREATE;
            case "edit" -> CommandType.EDIT;
            case "remove" -> CommandType.REMOVE;
            case "help" -> CommandType.HELP;
            case "exit" -> CommandType.EXIT;
            default -> CommandType.UNDEFINED;
        };
    }

    public static int readCommand(@NotNull Connection connection, @NotNull String command) {
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

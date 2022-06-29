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
            case "view" -> CommandType.VIEW;
            case "help" -> CommandType.HELP;
            case "exit" -> CommandType.EXIT;
            default -> CommandType.UNDEFINED;
        };
    }

    /**
     * The main command parsing method that determines the operation to be performed.
     * @param connection The current database connection.
     * @param command Command string.
     * @return returns Return.SUCCESS if the operation was successful, Return.CANCELED if the user made an error during execution, Return.EXCEPTION if an error occurred beyond the user's control.
     */
    public static Return readCommand(@NotNull Connection connection, @NotNull String command) {
        CommandType commandType = getCommandType((command.split(" "))[0]);

        return switch (commandType) {
            case CREATE -> Creator.create(connection, command.split(" "));
            case EDIT -> Editor.edit(connection, command.split(" "));
            case REMOVE -> Remover.remove(connection, command.split(" "));
            case VIEW -> Viewer.view(connection, command.split(" "));
            case HELP -> Helper.help();
            case EXIT -> Return.END;
            case UNDEFINED -> Return.UNDEFINED;
        };
    }
}

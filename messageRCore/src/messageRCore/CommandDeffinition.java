package messageRCore;

/**
 *
 * @author maritn
 */
public class CommandDeffinition {

    private final String commandName;
    private final int argumentsCount;

    public CommandDeffinition(String commandName, int argumentsCount) {
        this.commandName = commandName;
        this.argumentsCount = argumentsCount;
    }

    public String getCommandName() {
        return commandName;
    }

    public int getArgumentsCount() {
        return argumentsCount;
    }

}

package messageRCore.Commands;

import messageRCore.Contracts.CommandInterpetator;

/**
 *
 * @author maritn
 */
public class CommandDeffinition {

    private final String commandName;
    private final int argumentsCount;
    private final CommandInterpetator interpretator;

    public CommandDeffinition(String commandName, int argumentsCount, CommandInterpetator interpretator) {
        this.commandName = commandName;
        this.argumentsCount = argumentsCount;
        this.interpretator = interpretator;
    }

    public String getCommandName() {
        return commandName;
    }

    public int getArgumentsCount() {
        return argumentsCount;
    }

    public CommandInterpetator getInterpretator() {
        return interpretator;
    }
}

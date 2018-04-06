package messageRCore;

import java.util.List;

/**
 *
 * @author maritn
 */
public class CommandInput {

    private String commandName;
    private List<String> arguments;

    public String getCommandName() {
        return commandName;
    }

    public List<String> getCommandArguments() {
        return arguments;
    }

    public CommandInput(String commandName, List<String> arguments) {
        this.commandName = commandName;
        this.arguments = arguments;
    }
}

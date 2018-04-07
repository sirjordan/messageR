package messageRCore.Commands;

import java.util.List;
import messageRCore.Contracts.CommandInterpetator;

/**
 *
 * @author maritn
 */
public class CommandInput {
    
    private final String commandName;
    private final List<String> arguments;
    private final CommandInterpetator interpretator;
    
    public String getCommandName() {
        return commandName;
    }
    
    public List<String> getCommandArguments() {
        return arguments;
    }
    
    public CommandInput(String commandName, List<String> arguments, CommandInterpetator interpretator) {
        this.commandName = commandName;
        this.arguments = arguments;
        this.interpretator = interpretator;
    }
    
    public void Execute() {
        interpretator.ExecuteCommand(this.arguments);
    }
}

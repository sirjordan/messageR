package messagerclient;

import java.util.HashSet;
import java.util.Set;
import messageRCore.CommandDeffinition;
import messageRCore.Contracts.CommandsDefinitionSource;

/**
 *
 * @author maritn
 */
public class ClientCommands implements CommandsDefinitionSource {

    @Override
    public Set<CommandDeffinition> getCommandsDefinition() {
        Set<CommandDeffinition> commands = new HashSet<CommandDeffinition>() {
            {
                add(new CommandDeffinition("exit", 0));
                add(new CommandDeffinition("help", 0));
            }
        };

       return commands;
    }
}

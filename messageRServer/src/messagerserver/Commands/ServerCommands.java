package messagerserver.Commands;

import java.util.HashSet;
import java.util.Set;
import messageRCore.Commands.CommandDeffinition;
import messageRCore.Contracts.CommandsDefinitionSource;

/**
 *
 * @author maritn
 */
public class ServerCommands implements CommandsDefinitionSource {

    @Override
    public Set<CommandDeffinition> getCommandsDefinition() {
        Set<CommandDeffinition> commands = new HashSet<CommandDeffinition>() {
            {
                add(new CommandDeffinition("w", 1, new Whisper()));
                add(new CommandDeffinition("gr", 1, new Greetings()));
            }
        };

        return commands;
    }

}

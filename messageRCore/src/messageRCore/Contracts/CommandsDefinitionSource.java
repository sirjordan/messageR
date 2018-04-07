package messageRCore.Contracts;

import java.util.Set;
import messageRCore.Commands.CommandDeffinition;

/**
 *
 * @author maritn
 */
public interface CommandsDefinitionSource {
    Set<CommandDeffinition> getCommandsDefinition();
}

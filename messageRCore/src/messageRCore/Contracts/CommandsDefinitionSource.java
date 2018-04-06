package messageRCore.Contracts;

import java.util.Set;
import messageRCore.CommandDeffinition;

/**
 *
 * @author maritn
 */
public interface CommandsDefinitionSource {
    Set<CommandDeffinition> getCommandsDefinition();
}

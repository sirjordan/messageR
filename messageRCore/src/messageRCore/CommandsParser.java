package messageRCore;

import java.util.ArrayList;
import java.util.List;
import messageRCore.Contracts.CommandsDefinitionSource;

/**
 *
 * @author maritn
 */
public class CommandsParser {

    public static final String COMMANDS_PREFIX = "-";
    private final CommandsDefinitionSource commandTokens;

    public CommandsParser(CommandsDefinitionSource commandTokens) {
        this.commandTokens = commandTokens;
    }

    public List<CommandInput> extractCommands(String text) {
        List<CommandInput> commands = new ArrayList<>();
        String[] lines = text.split(" ");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.startsWith(COMMANDS_PREFIX)) {
                String cmd = line.substring(1);
                // check if that commands exists in the commands definition
                for (CommandDeffinition commandDeffinition : commandTokens.getCommandsDefinition()) {
                    if (commandDeffinition.getCommandName().equals(cmd)) {
                        i++;
                        // get arguments for this command
                        List<String> arguments = new ArrayList<>();
                        for (int j = 0; j < commandDeffinition.getArgumentsCount(); j++) {
                            if (i < lines.length) {
                                arguments.add(lines[i]);
                                i++;
                            } else {
                                break;
                            }
                        }

                        commands.add(new CommandInput(cmd, arguments));
                    }
                }
            }
        }

        return commands;
    }

    public String cleanFromCommands(String textWithCommands) {
        throw new UnsupportedOperationException();
    }
}
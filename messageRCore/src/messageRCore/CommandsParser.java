package messageRCore;

import java.util.*;
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

    public Queue<CommandInput> extractCommands(String text) {
        Queue<CommandInput> commands = new LinkedList<>();
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
        Queue<CommandInput> commands = extractCommands(textWithCommands);

        CommandInput cmd = commands.poll();
        do {
            textWithCommands = textWithCommands.replace(COMMANDS_PREFIX + cmd.getCommandName(), "");
            for (String commandArgument : cmd.getCommandArguments()) {
                textWithCommands = textWithCommands.replace(commandArgument, "");
            }
        } while (commands.poll() != null);

        return textWithCommands;
    }
}

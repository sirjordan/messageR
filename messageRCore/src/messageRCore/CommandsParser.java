package messageRCore;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maritn
 */
public class CommandsParser {

    public static final String COMMANDS_PREFIX = "-";

    public static List<Command> parseCommands(String text) {
        List<Command> commands = new ArrayList<>();
        String[] lines = text.split(" ");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.startsWith(COMMANDS_PREFIX)) {
                String cmd = line.substring(1);
                String arg = null;
                if (i < lines.length - 1) {
                    arg = lines[i + 1];
                }

                commands.add(new Command(cmd, arg));
            }
        }

        return commands;
    }
}

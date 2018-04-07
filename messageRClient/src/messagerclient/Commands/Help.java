package messagerclient.Commands;

import java.util.List;
import messageRCore.Contracts.CommandInterpetator;
import messagerclient.SystemWriter;

/**
 *
 * @author maritn
 */
public class Help implements CommandInterpetator {

    private final SystemWriter writer;

    public Help(SystemWriter writer) {
        this.writer = writer;
    }

    @Override
    public void ExecuteCommand(List<String> args) {
        writer.PrintBorder();
        writer.PrintLine();
        writer.PrintLine("Commands:", -1);
        writer.PrintLine("-exit / exit messageR /", -1);
        writer.PrintLine("-help / prints help /", -1);
        writer.PrintLine("-w [user] / whisper to [user] /", -1);
        writer.PrintLine("-gr [user] / greetings to [user] /", -1);
        writer.PrintLine();
        writer.PrintBorder();
    }
}

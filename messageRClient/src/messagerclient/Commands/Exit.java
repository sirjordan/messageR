package messagerclient.Commands;

import java.util.List;
import messageRCore.Contracts.CommandInterpetator;

/**
 *
 * @author maritn
 */
public class Exit implements CommandInterpetator {

    @Override
    public void ExecuteCommand(List<String> args) {
        System.err.println("Exiting...");
    }
}

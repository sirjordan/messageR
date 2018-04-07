package messagerclient.Commands;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import messageRCore.Contracts.CommandInterpetator;

/**
 *
 * @author maritn
 */
public class Exit implements CommandInterpetator {

    @Override
    public void ExecuteCommand(List<String> args) {
        try {
            System.err.println("Exiting...");
            Thread.sleep(1000);
            System.exit(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(Exit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package messageRCore.Contracts;

import java.util.List;

/**
 *
 * @author maritn
 */
public interface CommandInterpetator {

    void ExecuteCommand(List<String> args);
}

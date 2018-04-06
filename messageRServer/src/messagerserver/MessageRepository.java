package messagerserver;

import java.util.*;

/**
 *
 * @author maritn
 */
public class MessageRepository {

    private final Map<String, List<String>> messages;

    public MessageRepository() {
        this.messages = new HashMap<>();
    }

    public Enumeration<String> getLastMessages(String userName) {
        List<String> last = this.messages.get(userName);
        if (last != null) {
            this.messages.remove(userName);
            return Collections.enumeration(last);
        }

        return null;
    }

    public void postMessage(String receiver, String message) {
        List<String> allForReceiver = this.messages.get(receiver);
        if (allForReceiver == null) {
            allForReceiver = new ArrayList<>();
            allForReceiver.add(message);
            this.messages.put(receiver, allForReceiver);
        } else {
            allForReceiver.add(message);
        }
    }
}

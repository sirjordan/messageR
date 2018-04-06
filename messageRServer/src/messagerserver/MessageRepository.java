package messagerserver;

import java.util.*;

public class MessageRepository {
    private final Map<String, Vector<String>> messages;
    
    public MessageRepository(){
        this.messages = new HashMap<>();
    }
    
    public Enumeration<String> getLastMessages(String userName){
        Vector<String> last = this.messages.get(userName);
        if (last != null) {
            this.messages.remove(userName);
            return last.elements();
        }
        
        return null;
    }
    
    public void postMessage(String receiver, String message){
        Vector<String> allForReceiver = this.messages.get(receiver);
        if (allForReceiver == null) {
            allForReceiver = new Vector<>();
            allForReceiver.add(message);
            this.messages.put(receiver, allForReceiver);
        }else{
            allForReceiver.add(message);
        }
    }
}

package messagerserver;

import java.util.*;

public class MessageRepository {
    private Map<Integer, Vector<String>> messages;
    
    public MessageRepository(){
        this.messages = new HashMap<Integer, Vector<String>>();
    }
    
    public Enumeration<String> getLastMessages(Integer id){
        Vector<String> last = this.messages.get(id);
        if (last != null) {
            this.messages.remove(id);
            return last.elements();
        }
        
        return null;
    }
    
    public void postMessage(int receiver, String message){
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

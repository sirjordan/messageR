package messagerserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author maritn
 */
public class ChannelsManager {
    
    private static final Channel PUBLIC_CH = new Channel("@PUBLIC");
    private final Set<Channel> channels;
    
    public ChannelsManager() {
        this.channels = new HashSet<>();
    }
    
    public Enumeration<Channel> getChannels() {
        List<Channel> all = new ArrayList<>();
        all.add(PUBLIC_CH);
        all.addAll(channels);
        
        return Collections.enumeration(all);
    }
    
    public void createChannel(String name) {
        Channel ch = new Channel(name);
        if (!channels.contains(ch)) {
            channels.add(ch);
        }
    }
    
    public void joinPublicChannel(String user) {
        PUBLIC_CH.joinUser(user);
    }
    
    public void join(String channel, String user) {
        throw new UnsupportedOperationException();
    }
}

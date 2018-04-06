package messagerserver;

import java.util.HashSet;
import java.util.Set;

public class Channel {

    private final String name;
    private final Set<String> users;

    public Set<String> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public void joinUser(String user) {
        users.add(user);
    }

    public Channel(String name) {
        this.name = name;
        this.users = new HashSet<>();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Channel)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        return name.equals(((Channel) obj).name);
    }
}


package messagerserver;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author maritn
 */
public class UsersManager {

    private final Set<String> users;

    public Enumeration<String> getUsers() {
        return Collections.enumeration(users);
    }
    
    public void addUser(String user){
        users.add(user);
    }
    
    public UsersManager() {
        users = new HashSet<>();
    }
}

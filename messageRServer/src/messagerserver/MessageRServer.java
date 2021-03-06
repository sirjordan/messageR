package messagerserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.logging.*;
import messageRCore.Settings;

/**
 *
 * @author maritn
 */
public class MessageRServer {

    private static final MessageRepository MSG_REPO = new MessageRepository();
    private static final UsersManager LOGGED_USERS = new UsersManager();
    private static final ChannelsManager CHANNELS_MANAGER = new ChannelsManager();

    public static void main(String[] args) {

        try {
            int port = 619;

            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            server.createContext("/" + Settings.Actions.SUBMIT, new SubmitHandler());
            server.createContext("/" + Settings.Actions.CHECK, new CheckHandler());
            server.createContext("/" + Settings.Actions.LOGIN, new LoginHandler());

            server.setExecutor(null);
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(MessageRServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static class LoginHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            Headers h = t.getRequestHeaders();
            String user = h.getFirst(Settings.Headers.USER_NAME);

            String response = "Logged as @" + user;
            LOGGED_USERS.addUser(user);
            CHANNELS_MANAGER.joinPublicChannel(user);

            HttpHelper.postResponseMessage(t, response);
        }
    }

    static class SubmitHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            // Get receiver
            Map<String, String> qStrings = HttpHelper.getQueryStringValues(t.getRequestURI().getQuery());
            String receiver = qStrings.get("user");

            // Put the msg in the repo
            String msgBody = HttpHelper.getRequestBody(t);
            MSG_REPO.postMessage(receiver, msgBody);

            HttpHelper.postResponseMessage(t, "Msg was send successfully");
        }
    }

    static class CheckHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            Map<String, String> qStrings = HttpHelper.getQueryStringValues(t.getRequestURI().getQuery());
            String user = qStrings.get("user");

            Enumeration<String> lastMessages = MSG_REPO.getLastMessages(user);

            String response = "";
            if (lastMessages != null) {
                List headerValuesList = Collections.list(lastMessages);
                response = String.join("\n", headerValuesList);
            }

            HttpHelper.postResponseMessage(t, response);
        }
    }
}

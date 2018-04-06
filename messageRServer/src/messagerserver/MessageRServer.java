package messagerserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.io.*;

public class MessageRServer {
    private static int port = 619;
    private static String submit = "submit";
    private static String check = "check";
    private static MessageRepository msgRepo = new MessageRepository();

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    static class SubmitHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // Get req msg body 
            InputStreamReader isr =  new InputStreamReader(t.getRequestBody(),"utf-8");
            BufferedReader br = new BufferedReader(isr);

            int b;
            StringBuilder msgBody = new StringBuilder(512);
            while ((b = br.read()) != -1) {
                msgBody.append((char) b);
            }

            br.close();
            isr.close();
            
            // Get receiver id
            Map<String, String> qStrings = queryToMap(t.getRequestURI().getQuery());
            Integer receiverId = Integer.parseInt(qStrings.get("id"));
            
            // Put the msg in the repo
            msgRepo.postMessage(receiverId, msgBody.toString());
            
            String response = "Msg was send successfully";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    static class CheckHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            Map<String, String> qStrings = queryToMap(t.getRequestURI().getQuery());           
            Integer id = Integer.parseInt(qStrings.get("id"));
            
            Enumeration<String> lastMessages = msgRepo.getLastMessages(id);
            
            String response = "";
            if (lastMessages != null) {
                List headerValuesList = Collections.list(lastMessages);
                response = String.join("\n", headerValuesList);
            }
           
            t.sendResponseHeaders(200, response.length());
            
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }
}

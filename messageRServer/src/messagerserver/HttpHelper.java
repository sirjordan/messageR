package messagerserver;

import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author maritn
 */
public final class HttpHelper {

    public static Map<String, String> getQueryStringValues(String queryString) {
        Map<String, String> result = new HashMap<>();
        for (String param : queryString.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }

    public static String getRequestBody(HttpExchange t) throws IOException {
        InputStreamReader isr = new InputStreamReader(t.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);

        int b;
        StringBuilder msgBody = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            msgBody.append((char) b);
        }

        br.close();
        isr.close();

        return msgBody.toString();
    }

    public static void postResponseMessage(HttpExchange t, String message) throws IOException {
        t.sendResponseHeaders(200, message.length());
        OutputStream os = t.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
}

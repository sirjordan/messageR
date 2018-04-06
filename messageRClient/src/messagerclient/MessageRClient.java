package messagerclient;

import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.Environment;

public class MessageRClient {

    private static String serverUrl = "http://localhost:619";
    private static String submit = "submit";
    private static String check = "check";
    private static String login = "login";

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Log as: ");
            String usrName = br.readLine();

            //System.out.println("Partner Name: ");
            //String partner = br.readLine();

            //System.out.println("Connecting as" + usrName + "... to " + "partner " + partner);

            //System.out.println("Type message or \"exit\" to exit:");
            //String msg = "";

            //Scanner reader = new Scanner(System.in);

            connect(usrName);

            /*
            do {
                msg = reader.nextLine();
                postMessage(partner, msg);
            } while (msg != "exit");

            reader.close();

            System.exit(0);
             */
        } catch (Exception ex) {
            Logger.getLogger(MessageRClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void connect(String loginName) {
        try {
            URL obj = new URL(serverUrl + "/" + login);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("login", loginName);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.getOutputStream().write(new byte[0]);

            Reader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;) {
                sb.append((char) c);
            }

            String response = sb.toString();
            System.err.println(response);
        } catch (Exception ex) {
            Logger.getLogger(MessageRClient.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error connecting to server.");
        }
    }

    private static void initiServerListener() {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {

                        Thread.sleep(2000);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MessageRClient.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Error at Server Listener!");
                }
            }
        };

        th.start();
    }

    private static void postMessage(String postTo, String message) throws Exception {
        URL obj = new URL(serverUrl + "/" + submit);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        String urlParameters = "partnerId=" + postTo;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        System.out.println("\nSent to : " + postTo);
    }
}

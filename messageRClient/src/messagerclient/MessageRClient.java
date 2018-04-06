package messagerclient;

import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageRClient {
    private static String serverUrl = "http://localhost:619";
    private static String submit = "submit";
    private static String check = "check";

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            System.out.println("Log as: ");
            String usrName = br.readLine();
            
            System.out.println("Partner Name: ");
            String partner = br.readLine();
            
            System.out.println("Connecting as" + usrName + "... to " + "partner " + partner);

            System.out.println("Type message fowolled or \"exit\" to exit:");
            String msg = "";

            Scanner reader = new Scanner(System.in);

            do {
                msg = reader.nextLine();
                postMessage(partner, msg);
            } while (msg != "exit");

            reader.close();
        } catch (Exception ex) {
            Logger.getLogger(MessageRClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void postMessage(String postTo, String message) throws Exception {
        URL obj = new URL(serverUrl + "/" + submit);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();

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

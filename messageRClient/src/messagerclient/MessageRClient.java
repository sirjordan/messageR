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
            System.out.println("Enter Your Id (int):");
            int id = Integer.parseInt(br.readLine());
            
            System.out.println("Enter Partner Id (int):");
            int partnerId = Integer.parseInt(br.readLine());
            
            System.out.println("Connecting with user id " + id + "... to " + "partner " + partnerId);

            System.out.println("Type message fowolled or \"exit\" to exit:");
            String msg = "";

            Scanner reader = new Scanner(System.in);

            do {
                msg = reader.nextLine();
                postMessage(partnerId, msg);
            } while (msg != "exit");

            reader.close();
           
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        } catch (Exception ex) {
            Logger.getLogger(MessageRClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void postMessage(int partnerId, String message) throws Exception {
        URL obj = new URL(serverUrl + "/" + submit);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        
        String urlParameters = "partnerId=" + partnerId;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        System.out.println("\nSent to : " + partnerId);
    }
}

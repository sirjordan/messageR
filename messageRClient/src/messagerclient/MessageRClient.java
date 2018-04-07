package messagerclient;

import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import messageRCore.Commands.CommandInput;
import messageRCore.Commands.CommandsParser;
import messageRCore.Settings;
import messagerclient.Commands.ClientCommands;

/**
 *
 * @author maritn
 */
public class MessageRClient {

    private static final Boolean RUN = true;
    private static final SystemWriter writer = new SystemWriter(50);

    public static void main(String[] args) {
        printGreetings();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Log as: ");
            String usrName = br.readLine();
            //connect(usrName);

            Scanner scanner = new Scanner(System.in);
            CommandsParser commandParser = new CommandsParser(new ClientCommands());

            String line;
            while (RUN) {
                line = scanner.next();

                // Execute commands
                Queue<CommandInput> commands = commandParser.extractCommands(line);
                CommandInput cmd = commands.poll();
                while (cmd != null) {
                    cmd.Execute();
                    cmd = commands.poll();
                }

                // Post textWithoutCommands to server
                String textWithoutCommands = commandParser.cleanFromCommands(line);
                // TODO: 
                System.out.println(textWithoutCommands);
            }
            while (!(line = scanner.next()).equals("exit")) {

            }

            scanner.close();
        } catch (Exception ex) {
            Logger.getLogger(MessageRClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void connect(String loginName) {
        try {
            URL obj = new URL(Settings.Endpoints.LOGIN);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty(Settings.Headers.USER_NAME, loginName);
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
        URL obj = new URL(Settings.Endpoints.SUBMIT);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

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

    private static void printGreetings() {
        writer.PrintBorder();
        writer.PrintLine();
        writer.PrintLine("messageR", 0);
        writer.PrintLine();
        writer.PrintLine("2018 | M.Marinov @ NBU", 0);
        writer.PrintLine();
        writer.PrintLine("-help /for help /", -1);
        writer.PrintLine();
        writer.PrintBorder();
    }
}

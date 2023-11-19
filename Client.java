import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        int port = 12321;
        String host = "localhost";

        Socket clientSocket = null;
        try {
            clientSocket = new Socket(host, port);
            PrintWriter requestWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Connected to server.");

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            System.exit(1);

        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.err.println("Error while closing the socket: " + e.getMessage());
            } // closes catch
        } // closes finally
    } //closes main
} //closes class


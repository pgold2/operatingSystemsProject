import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 12321;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for connections...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");
            PrintWriter responseWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader requestReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            System.out.println("Finished sending jobs");

            serverSocket.close(); // Close the server socket when done
        } //closes try
        catch (IOException e) {
            System.err.println("Exception caught when running the server: " + e.getMessage());
        } //closes catch
    } //closes main
}//closes class
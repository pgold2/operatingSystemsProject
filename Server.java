
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
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            char jobHandler = (char)in.read();
            //calls the algorithm class to figure out which job to do
            Algorithm algorithmObj = new Algorithm();
            algorithmObj.getsJobType(jobHandler);//sends the job type to the algorithm class

            System.out.println("Finished sending jobs");

            serverSocket.close(); // Close the server socket when done
        } //closes try
        catch (IOException e) {
            System.err.println("Exception caught when running the server: " + e.getMessage());
        } //closes catch
    } //closes main
}//closes class
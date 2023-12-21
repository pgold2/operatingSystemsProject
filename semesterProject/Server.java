package semesterProject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        if (args.length != 1)
        {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for connections...");

            //as long as clients want to connect (true)
            while (true) {
                //waits for client to connect
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                //creates a new thread to handle the client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Exception caught when running the server: " + e.getMessage());
        }
    }
} //closes server class


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 12321;
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

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            char jobHandler = (char) in.read();

            //calls the algorithm class to figure out which job to do
            Algorithm algorithmObj = new Algorithm();
            algorithmObj.getsJobType(jobHandler);

            System.out.println("Finished sending jobs");

            //close client socket when done
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Exception caught when handling client: " + e.getMessage());
        }
    }//closes run method
} //closes client handler class

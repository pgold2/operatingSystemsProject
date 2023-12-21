package semesterProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

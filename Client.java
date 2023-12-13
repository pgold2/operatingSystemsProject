
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.*;

public class Client {
    public static void main(String[] args) {

        int port = 12321;
        String host = "localhost";

        Socket clientSocket = null;
        try {
            clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Connected to server.");

            Scanner scanner = new Scanner(System.in);// creates new scanner object
            System.out.println("Welcome!");
            System.out.println("Which job would you like to do? (A or  B): ");
            char chooseJob = scanner.next().charAt(0);

            if (chooseJob == 'A'){
                out.println("A");//sends to server
                System.out.println("Job (A) request sent");
            } // closes if 1
            else if (chooseJob == 'B' ){
                out.println("B"); //sends to server
                System.out.println("Job (B) request sent");
            } // closes if 2
            else {
                System.out.println("\nERROR! this is an invalid choice - make sure to enter a capital letter");
                System.out.println("\nRe-enter your choice below: ");
                System.out.println("choose job A or B: ");
                chooseJob = scanner.next().charAt(0);
            }
            scanner.close();


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
}

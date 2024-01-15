package semesterProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) {
        int port = 13531;
        String host = "localhost";
        Socket clientSocket = null;

        try {
            clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Connected to server.");

            Scanner scanner = new Scanner(System.in);
            boolean continueLoop = true;

            while (true) {
                System.out.println("Welcome Client 1!");
                System.out.println("Which job would you like to do? (A or B) (or exit to stop): ");
                String chooseJob = scanner.nextLine();

                if (chooseJob.equalsIgnoreCase("exit")) {
                    continueLoop = false;
                    break;
                }

                System.out.println("Enter the ID for your job request");
                String jobID = scanner.nextLine();

                Request requestObj = new Request(jobID, chooseJob);

                if (chooseJob.equalsIgnoreCase("A")) {
                    out.println(requestObj.getJobID());
                    out.println(requestObj.getJobType());
                    System.out.println("Job (A) request sent");
                } else if (chooseJob.equalsIgnoreCase("B")) {
                    out.println(requestObj.getJobID());
                    out.println(requestObj.getJobType());
                    System.out.println("Job (B) request sent");
                } else {
                    System.out.println("\nERROR! This is an invalid choice - choose A or B");
                    System.out.println("\nRe-enter your choice below: ");
                    System.out.println("Choose job A or B: ");
                    chooseJob = scanner.nextLine();
                }
            }

            // Close the scanner after the loop
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
            }
        }
    }
}

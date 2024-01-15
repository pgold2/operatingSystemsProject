package semesterProject;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Both Slave A and SlaveB do the two types of jobs and sleep according to diff
 * amount of seconds specified in project requirements
 *
 **/
public class SlaveA {
    public static void main(String[] args) {
        int port = 13531;
        String host = "localhost";
        try (
                Socket slaveASocket = new Socket(host, port);
                ObjectOutputStream output = new ObjectOutputStream(slaveASocket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(slaveASocket.getInputStream());

        ) {
            System.out.println("Slave A Connected To Master");
            //read a job from the master
            Request job = (Request) input.readObject();
            System.out.println("received by slave A: " + job);

            while (job != null) {

                //if its job type a, sleep for 2 seconds
                if (job.getJobType().equalsIgnoreCase("A")) {
                    try {
                        System.out.println("Slave A, Working on job type A");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //if its type B, sleep for 10 seconds
                else {
                    try {
                        System.out.println("Slave A, working on job type B");
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println();
                System.out.println("Slave A finished working on " + job);
                System.out.println("Returning to Master...");
                System.out.println();

                //once job complete, return to master
                output.writeObject(job);

                //read the next job
                job = (Request) input.readObject();
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    host);
            System.err.println(e.getStackTrace());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Error, class not found ");
        }


    }
}
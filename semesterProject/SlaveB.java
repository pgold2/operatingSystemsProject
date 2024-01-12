package semesterProject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Both Slave A and SlaveB do the two types of jobs and sleep according to diff
 * amount of seconds specified in project requirements
 */
public class SlaveB {
    public static void main(String[] args){
    String host = "localhost";
    int port = 13531;

    //socket: slave B to master
    try (Socket slaveBSocket = new Socket(host, port);
         ObjectOutputStream output = new ObjectOutputStream(slaveBSocket.getOutputStream());
         ObjectInputStream input = new ObjectInputStream(slaveBSocket.getInputStream());
    ){
        System.out.println("Slave B Connected to Master");
        //read a job from the master
        Request job = (Request) input.readObject();
        System.out.println("received by slave B: " + job);

        while( job  != null) {
            //if job is type b sleep for two seconds
            if(job.getJobType().equalsIgnoreCase("B")) {
                try {
                    System.out.println("Slave B, working on Job type B");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //if job is type a, sleep for 10 seconds
            else {
                try {
                    System.out.println("Slave B, working on Job type A");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
            System.out.println("Slave B finished working on  " + job );
            System.out.println("Returning to Master...");
            System.out.println();

            //once job is complete, return to master
            output.writeObject(job);

            //read next job from master
            job = (Request) input.readObject();
        }
    }
    catch (UnknownHostException e) {
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

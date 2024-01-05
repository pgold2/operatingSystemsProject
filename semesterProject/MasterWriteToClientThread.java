package semesterProject;

import java.io.PrintWriter;
import java.util.ArrayList;

public class MasterWriteToClientThread extends Thread{
    private PrintWriter writeToClient;
    private ArrayList<Request> completedJobs;

    public MasterWriteToClientThread(PrintWriter writeToClient, ArrayList<Request>job) {
        this.writeToClient = writeToClient;
        this.completedJobs = job;

    }

    @Override
    public void run() {
        while(true) {

            if(!completedJobs.isEmpty()) {

                System.out.println("Master returning to client " + completedJobs.get(0).getClientID() + ": " + completedJobs.get(0) );
                System.out.println();

                //send message to client
                writeToClient.println("Job Type: " + completedJobs.get(0).getJobType()+ " Job ID: " + completedJobs.get(0).getJobID()+" completed.");

                //remove first job from list
                synchronized(completedJobs) {
                    completedJobs.remove(0);
                }
            }
            //if the completed jobs Array is empty, sleep for half a second.
            else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

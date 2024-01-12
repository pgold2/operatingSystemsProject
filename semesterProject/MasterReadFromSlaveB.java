package semesterProject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MasterReadFromSlaveB extends Thread {

    private ObjectInputStream readObjectFromSlaveB;
    private ArrayList<Request> completedJobsA;
    private ArrayList<Request> completedJobsB;
    private SlaveWaitTime counterA;
    private SlaveWaitTime counterB;
    public MasterReadFromSlaveB(ObjectInputStream readObjectFromSlaveB, ArrayList<Request> completedJobs1, ArrayList<Request> completedJobs2, SlaveWaitTime counterA, SlaveWaitTime counterB) {
        this.readObjectFromSlaveB = readObjectFromSlaveB;
        this.completedJobsA = completedJobs1;
        this.completedJobsB = completedJobs2;
        this.counterA = counterA;
        this.counterB = counterB;

}

    @Override
    public void run() {
        Request finishedJob;
        try {

            //read in first completed job from slaveA
            finishedJob = (Request) readObjectFromSlaveB.readObject();
            while (true) {
                System.out.println("Master Received From Slave B== " + finishedJob);
                System.out.println();

                //as long as there are jobs coming in loop
                if (finishedJob != null) {
                    //if job is from client 1, add to array of completedJobs1
                    if (finishedJob.getClientID() == 1) {
                        synchronized (completedJobsA) {
                            completedJobsA.add(finishedJob);
                        }
                    }
                    //if job is from client 2, add to array of completedJobs2
                    else {
                        synchronized (completedJobsB) {
                            completedJobsB.add(finishedJob);
                        }
                    }
                    System.out.println(finishedJob + " completed by slave B. \nSending back to client... " /*+ finishedJob.getClientID()*/ );
                    System.out.println();

                    //if jobtype is B, remove 2 from counterB
                    if (finishedJob.getJobType().equalsIgnoreCase("B")) {
                        synchronized (counterB) {
                            counterB.removeCounter(2);

                        }

                    }
                    //if jobtype is A, remove 10 from counterA
                    else {
                        synchronized (counterB) {
                            counterB.removeCounter(10);
                        }

                    }
                    //read the next finished job
                    finishedJob = (Request) readObjectFromSlaveB.readObject();
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error, class not found ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

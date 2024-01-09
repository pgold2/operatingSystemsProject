package semesterProject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class MasterReadFromSlaveA extends Thread {

    private ObjectInputStream readObjectFromSlaveA;
    private ArrayList<Request> completedJobsA;
    private ArrayList<Request> completedJobsB;
    private SlaveWaitTime counterA;
    private SlaveWaitTime counterB;


    public MasterReadFromSlaveA(ObjectInputStream readObjectFromSlaveA, ArrayList<Request> completedJobs1, ArrayList<Request> completedJobs2, SlaveWaitTime counterA, SlaveWaitTime counterB) {
        this.readObjectFromSlaveA = readObjectFromSlaveA;
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
            finishedJob = (Request) readObjectFromSlaveA.readObject();
            while (true) {
                System.out.println("Master Received From Slave A == " + finishedJob);
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
                    /*
                    System.out.println("Completed Jobs list 1: ");
                    System.out.println(completedJobsA);;


                    System.out.println("Completed Jobs list 2: ");
                    System.out.println(completedJobsB);
                    System.out.println();
*/
                    System.out.println(finishedJob + " completed by slave A. \nSending back to client... " /*+ finishedJob.getClientID()*/ );
                    System.out.println();

                    //if jobtype is A, remove 2 from counterA
                    if (finishedJob.getJobType().equalsIgnoreCase("A") ){
                        synchronized (counterA) {
                            counterA.removeCounter(2);
                        }
                        System.out.println("2 removed from slave A counter ");
                        System.out.println();

                    }
                    //if jobtype is B, remove 10 from counterB
                    else {
                        synchronized (counterB) {
                            counterB.removeCounter(10);
                        }
                        System.out.println("10 removed from slave B counter ");
                        System.out.println();


                    }
                    //read the next finished job
                    finishedJob = (Request) readObjectFromSlaveA.readObject();
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

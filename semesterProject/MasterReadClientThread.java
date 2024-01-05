package semesterProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class MasterReadClientThread extends Thread {

    private BufferedReader readClient;
    private ArrayList<Request> jobArray;
    private int clientID;

    public MasterReadClientThread(BufferedReader readClient, ArrayList<Request> JobArray, int clientID) {
        this.readClient = readClient;
        this.jobArray = JobArray;
        this.clientID = clientID;
    }

    @Override
    public void run() {
        System.out.println("Waiting for request from client " + clientID);
        System.out.println();

        try {
            //initialize job id counter and String
            String jobId = "";
            String jobType;

            while (true) {
                String clientRequest1 = readClient.readLine();
                String clientRequest2 = readClient.readLine();

                if (clientRequest1 != null && clientRequest2 != null) {
                    jobId = clientRequest1;
                    jobType = clientRequest2;

                    //create new job and assign job type, jobId, and clientID
                    Request newJob = new Request(jobId, jobType, clientID);
                    System.out.println("Received from client: Job Type: " + newJob.getJobType() + " Job ID: " + newJob.getJobID());
                    System.out.println();


                    //add new job to job array
                    synchronized (jobArray) {
                        jobArray.add(newJob);
                    }

                } else {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        } catch(IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

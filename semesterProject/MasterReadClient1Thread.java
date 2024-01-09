package semesterProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class MasterReadClient1Thread extends Thread {

    private BufferedReader readClient;
    private ArrayList<Request> jobArray;
    private int clientID;

    public MasterReadClient1Thread(BufferedReader readClient, ArrayList<Request> JobArray, int clientID) {
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
                String readFromClientJobID = readClient.readLine();
                String clientRequest2 = readClient.readLine();

                if (readFromClientJobID != null && clientRequest2 != null) {
                    jobId = readFromClientJobID;
                    jobType = clientRequest2;

                    //create new job and assign job type, jobId, and clientID
                    Request newJob = new Request(jobId, jobType, clientID);
                    System.out.println("Received from client: " +
                            "\n\t\t\tJob Type: " + newJob.getJobType() + " " +
                            "\n\t\t\tJob ID: " + newJob.getJobID());
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

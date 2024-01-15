package semesterProject;

import java.io.PrintWriter;
import java.util.Random;

public class ClientWriteToMasterThread extends Thread {

    private PrintWriter writeToMaster;

    public ClientWriteToMasterThread(PrintWriter writeToMaster) {
        this.writeToMaster = writeToMaster;
    }

    @Override
    public void run() {

        Random random = new Random();
        int numJobs = 30;
        String job;


        //send jobs to master
        for(int id = 1; id <= numJobs; id++) {
            //randomly send A or B
            if(random.nextInt() % 2 == 0 ) {
                job = "A";
            }
            else {
                job = "B";
            }

            System.out.println("sending JobType: " + job + " ID: " + id + " to master");
            System.out.println();

            job = job.concat(String.valueOf(id));

            //write the job to the master
            writeToMaster.println(job);

            //sleep two seconds before sending the next job
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

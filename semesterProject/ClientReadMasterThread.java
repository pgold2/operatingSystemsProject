package semesterProject;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReadMasterThread extends Thread {

    private BufferedReader readMaster;

    public ClientReadMasterThread(BufferedReader readMaster) {
        this.readMaster = readMaster;
    }

    @Override
    public void run() {

        try {

            //initialize
            String masterResponse;
            while(true) {
                //read input from master
                if((masterResponse = readMaster.readLine())!=null) {

                    //print out message
                    System.out.println(masterResponse);
                    System.out.println();

                }
                else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

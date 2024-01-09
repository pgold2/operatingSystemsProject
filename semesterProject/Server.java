package semesterProject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        //hardcode port number
        int port = 13531;

        ArrayList<Request> jobArray = new ArrayList<Request>();
        SlaveWaitTime counterA = new SlaveWaitTime(0);
        SlaveWaitTime counterB = new SlaveWaitTime(0);



        try(ServerSocket serverSocket = new ServerSocket(port);){
                System.out.println("Server started. Waiting for connections...");
                Socket slaveSocketA = serverSocket.accept();
                System.out.println("Slave A connected...");

                 Socket slaveSocketB = serverSocket.accept();
                 System.out.println("Slave B connected...");
                Socket clientSocket1 = serverSocket.accept();
                System.out.println("Client One connected...");
                Socket clientSocket2 = serverSocket.accept();
                System.out.println("Client Two connected...");




                //created TWO client sockets and individual buffered readers and writers
                PrintWriter writeToClient1 = new PrintWriter(clientSocket1.getOutputStream(),true);
                BufferedReader readFromClient1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
                PrintWriter writeToClient2 = new PrintWriter(clientSocket2.getOutputStream(),true);
                BufferedReader readFromClient2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));


                //Writing and Reading job objects to and from slaves
                ObjectOutputStream writeObjectToSlaveA = new ObjectOutputStream(slaveSocketA.getOutputStream());
                ObjectOutputStream writeObjectToSlaveB = new ObjectOutputStream(slaveSocketB.getOutputStream());
                ObjectInputStream readObjectFromSlaveA = new ObjectInputStream(slaveSocketA.getInputStream());
                ObjectInputStream readObjectFromSlaveB = new ObjectInputStream(slaveSocketB.getInputStream());



                //create thread to read from client 1
                Thread readClient1Thread = new MasterReadClient1Thread(readFromClient1, jobArray, 1);

                //create thread to read from client 2
                Thread readClient2Thread = new MasterReadClient2Thread(readFromClient2, jobArray, 2);

            //create thread to write to slaves
            Thread writeToSlaves = new MasterWriteToSlaveThread (writeObjectToSlaveA, writeObjectToSlaveB, jobArray, counterA, counterB);

            //run thread


            //arrays to hold completed jobs, one for each client
            ArrayList <Request> completedJobs1 = new ArrayList<Request> ();
            ArrayList <Request> completedJobs2 = new ArrayList<Request> ();

            //create thread to read from slaveA
            Thread masterReadFromSlaveA = new MasterReadFromSlaveA(readObjectFromSlaveA, completedJobs1, completedJobs2, counterA, counterB);
            Thread masterReadFromSlaveB = new MasterReadFromSlaveB(readObjectFromSlaveB, completedJobs1, completedJobs2, counterA, counterB);



            //write back to clients with completed jobs
            Thread writeToClient1Thread = new MasterWriteToClientThread(writeToClient1, completedJobs1);
            Thread writeToClient2Thread = new MasterWriteToClientThread(writeToClient2, completedJobs2);

            //run threads
            readClient1Thread.start();
            readClient2Thread.start();
            writeToSlaves.start();
            masterReadFromSlaveA.start();
            masterReadFromSlaveB.start();
            writeToClient1Thread.start();
            writeToClient2Thread.start();

            try {
                readClient1Thread.join();
                readClient2Thread.join();
                writeToSlaves.join();
                masterReadFromSlaveA.join();
                masterReadFromSlaveB.join();
                writeToClient1Thread.join();
                writeToClient2Thread.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
} //closes server class


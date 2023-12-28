package semesterProject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        if (args.length != 1)
        {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        ArrayList<Request> jobArray = new ArrayList<Request>();
        /*SlaveCounter counterA = new SlaveCounter(0);
        SlaveCounter counterB = new SlaveCounter(0);*/
        int port = Integer.parseInt(args[0]);


        try(ServerSocket serverSocket = new ServerSocket(port);){

                Socket clientSocket1 = serverSocket.accept();
                System.out.println("client one connect");
                Socket clientSocket2 = serverSocket.accept();
                System.out.println("client two connect");

                Socket slaveSocketA = serverSocket.accept();
                System.out.println("slave a connect");

                Socket slaveSocketB = serverSocket.accept();
                System.out.println("slave b connect");


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
                Thread readClient1Thread = new MasterReadClientThread (readFromClient1, jobArray, 1);

                //create thread to read from client 2
                Thread readClient2Thread = new MasterReadClientThread (readFromClient2, jobArray, 2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
} //closes server class


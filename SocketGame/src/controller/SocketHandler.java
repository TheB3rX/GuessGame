package controller;

import models.ServerManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketHandler extends Thread {
    private Socket socket;
    private int number = 0;
    private Controller controller;

    public SocketHandler(Socket socket) {
        this.socket = socket;
        run();
    }

    @Override
    public void run() {
        super.run();
        int goalNumber = ServerManager.getNumber();
        System.out.println("goalNumber = " + goalNumber);
        short attempts = 0;
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//            System.out.println("Client connected, client address: " + socket.getLocalAddress());
            while (isEqual(goalNumber) != true) {
                //Read number that comes from client
                this.number = dis.readInt();
                //Send if is close or not
                dos.writeUTF(getNumGoal(goalNumber));
                //send true or false depending if is the same number
                dos.writeBoolean(isEqual(goalNumber));
                attempts++;
//                System.out.println(isEqual(goalNumber) ? "Client guessed the number" : "Client tried to guess");
            }
            dos.writeUTF(String.valueOf(attempts));
//            System.out.println(socket.getInetAddress() + " got disconnected");
            //Send number of attempts and time to client to save it in database
            new Controller().addUser(socket.getInetAddress().toString(),dis.readUTF(), String.valueOf(attempts)+" attempts");
            dos.writeBoolean(isEqual(goalNumber));
            socket.close();
        } catch (IOException e) {
        }
    }


    public String getNumGoal(int numGoal) {
        String data = "";
        if (numGoal < number) data = "Number is >";
        if (numGoal > number) data = "Number is <";
        if (numGoal == number) data = "Number is = you won :D";
        return data;
    }

    public Boolean isEqual(int numGoal) {
        return number == numGoal;
    }
}

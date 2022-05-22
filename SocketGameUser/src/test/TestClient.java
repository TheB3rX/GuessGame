package test;

import models.timeManager.Chronometer;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) {
        Chronometer chrono = new Chronometer();
        Socket socket;
        try {
            socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 2077);
            //Start the chronometer
            chrono.start();
            Scanner sc = new Scanner(System.in);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            boolean runnable = false;
            while (!runnable) {
                dos.writeInt(sc.nextInt());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                String data = dis.readUTF();
                System.out.println(data);
                runnable = dis.readBoolean();
            }
            chrono.stop();
            //send time formatted to server
            dos.writeUTF(chrono.getFormattedTime());
        } catch (
                IOException e) {
            System.out.println("impossible to connect");
        }
    }
}

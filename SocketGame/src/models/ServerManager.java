package models;

import controller.SocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager extends Thread {
    private int port = 0;
    private static int number = 0;
    private ServerSocket serverSocket;
    private SocketHandler sockHandler;

    public ServerManager(int port, int number) {
        this.port = port;
        this.number = number;
        run();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
//            System.out.println("Server Port: " + serverSocket.getLocalPort() + "\nServer IP: " + serverSocket.getInetAddress() + "\nWaiting for users to connect...");
            while (true) {
                try {
                    Socket sock = serverSocket.accept();
                    sockHandler = new SocketHandler(sock);
                } catch (IOException e) {
                    System.out.println("Couldn't connect client!");
                }
            }
        } catch (IOException e) {
            System.out.println("Can't put server specified information");
        }
    }

    public static int getNumber() {
    //Generate a random value between 1 and the number
        return (int) (Math.random() * number) + 1;
    }
}
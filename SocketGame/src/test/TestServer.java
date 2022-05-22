package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(2077);
            while (true) {
                System.out.println(ss.getLocalPort());
                Socket cs = ss.accept();
                System.out.println("Welcome user: " + cs.getLocalAddress());
            }
        } catch (IOException e) {
            System.out.println("Impossible to start server");
        }
    }
}

package controller;

import models.UserInfo;
import models.UserManager;
import models.timeManager.Chronometer;
import views.CoderAbout;
import views.SplashApp;
import views.UserGUI;

import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import static models.UserManager.*;

public class Controller implements ActionListener {
    private UserGUI userGUI;
    private UserInfo userInfo;
    private SplashApp splashApp;
    private Socket socket;
    private Chronometer chrono;

    public Controller(String ip, int port) {
        super();
        startFiles();
        this.splashApp = new SplashApp();
        this.startClient(ip, port);
        userInfo = new UserInfo();
    }

    private void startClient(String ip, int port) {
        //Create socket and connect to server
        chrono = new Chronometer();
        Scanner scanner = new Scanner(System.in);
        try {
            socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 2077);
            userGUI = new UserGUI(this);
            userGUI.setTimeoutAlert();
            //Start the chronometer
            chrono.start();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            boolean runnable = false;
            socket.setSoTimeout(300000);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (!runnable) {
                String data = dis.readUTF();
                showMessage(data);
                runnable = dis.readBoolean();
                Thread.sleep(1000);
            }
            chrono.stop();
            addUserAttempt(chrono.getFormattedTime(),dis.readUTF());
            //send time formatted to server
            dos.writeUTF(chrono.getFormattedTime());
            userGUI.show(false);
            //Show Reconnect message
            if (userGUI.showReconnection()) {
                startClient(ip, port);
                userGUI.resetInput();
            } else {
                userGUI.showDisconnection();
            }
            socket.close();
            System.exit(0);
        } catch (
                IOException e) {
            userGUI.showDisconnection();
            System.exit(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void addUserAttempt(String time, String attempts) {
        UserManager.addAttempt(time, attempts);
    }

    private void showMessage(String data) {
        userGUI.showMessage(data);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getActionCommand().equals("Verify")) {
                Thread.sleep(800);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(userGUI.getNumber());
            }
            if (actionEvent.getActionCommand().equals("exit")) {
                socket.close();
                System.exit(0);
            }
            if (actionEvent.getActionCommand().equals("about")) {
                new CoderAbout();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void startFiles() {
        createDirectory();
        createFile();
        readList();
    }
}

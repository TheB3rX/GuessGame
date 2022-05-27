package controller;

import models.UserInfo;
import models.UserManager;
import models.timeManager.Chronometer;
import views.CoderAbout;
import views.ShowRecords;
import views.SplashApp;
import views.UserGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
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
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
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
        } catch (Exception e){
            System.out.println("e = " + e);
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
//            if(actionEvent.getActionCommand().equals("history")){
//                showRecords();
//            }
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

    public void showRecords() throws IOException, ClassNotFoundException {
        //Get client list from ObjectInputStream and show it on the screen
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ArrayList<UserInfo> list = (ArrayList<UserInfo>) ois.readObject();
        new ShowRecords(list).addListInfo(list);
    }
}

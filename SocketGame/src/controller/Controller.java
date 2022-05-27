package controller;

import models.ServerManager;
import persistence.Client;
import persistence.ClientManager;

import static persistence.ClientManager.*;

public class Controller extends Thread {

    private int port = 0;
    private int number = 0;

    public Controller(String port, String number) {
        this();
        try {
            this.port = Integer.parseInt(port);
        } catch (Exception e) {
            System.out.println("Imposible recibir el puerto, intente de nuevo");
        }
        try {
            this.number = Integer.parseInt(number);
        } catch (Exception e) {
            System.out.println("Imposible recibir el numero a pensar");
        }
        this.serverGame();

    }

    public Controller() {
        this.startFiles();
    }

    private void startFiles() {
        createDirectory();
        createFile();
        readList();
    }

    private void serverGame() {
        ServerManager uManager = new ServerManager(port, number);
    }

    public void getList(){

    }
    public void addUser(String ip,String time, String tries) {
        ClientManager.addClient(new Client(ip), time, tries);
//        ClientManager.printClients();
    }
}

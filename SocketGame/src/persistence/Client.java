package persistence;

import java.util.ArrayList;

public class Client {
    private String ip;
    private ArrayList<String> timeRecords;
    private ArrayList<String> attemptRecords;

    public Client(){

    }
    public Client(String ip) {
        this();
        this.ip = ip;
        timeRecords = new ArrayList<String>();
        attemptRecords = new ArrayList<String>();
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getIp() {
        return ip;
    }

    public void addAttempt(String time, String attempt) {
        timeRecords.add(time);
        attemptRecords.add(attempt);
    }

    public ArrayList<String> getTimeRecords() {
        return timeRecords;
    }

    public ArrayList<String> getAttemptRecords() {
        return attemptRecords;
    }
}

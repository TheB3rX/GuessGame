package models;

import java.util.ArrayList;

public class UserInfo {
    private ArrayList<String> number;
    private ArrayList<String> time;

    public UserInfo() {
        this.number = new ArrayList<>();
        this.time = new ArrayList<>();
    }
    //Void constructor

    //Getters and setters
    public ArrayList<String> getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number.add(number);
    }

    public ArrayList<String> getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time.add(time);
    }

    public void addAttempts(String time, String number) {
        this.number.add(number+" Attempts");
        this.time.add(time);
    }
}
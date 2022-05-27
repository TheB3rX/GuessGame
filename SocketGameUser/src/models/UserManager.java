package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserManager {
    private static final String PATH_DIRECTORY = "assets";
    private static final String PATH_FILE = "assets/userInfo.json";
    private static UserInfo userInfo = new UserInfo();
    private static Gson gson;
    private static GsonBuilder builder;

    public static boolean createDirectory() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        var file = new File(PATH_DIRECTORY);
        return file.mkdir();
    }

    public static boolean createFile() {
        var file = new File(PATH_FILE);
        if (!file.exists()) {
            try {
                var out = new PrintWriter(new FileWriter(file, false));
                out.close();
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static void initList() {
        var file = new File(PATH_FILE);
        PrintWriter out = null;
        if (userInfo==null){
            try{
                out = new PrintWriter(new FileWriter(file, false));
                userInfo = new UserInfo();
                var jsonList = gson.toJson(userInfo);
                out.println(jsonList);
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void addAttempt(String time, String tries){
        var file = new File(PATH_FILE);
        try{
            PrintWriter out = new PrintWriter(new FileWriter(file,false));
            userInfo.addAttempts(time,tries);
            var jsonList = gson.toJson(userInfo);
            out.println(jsonList);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean readList() {
        var file = new File(PATH_FILE);
        try {
            if (file.exists()) {
                var jsonList = "";
                BufferedReader reader = new BufferedReader(new FileReader(file));
                jsonList = reader.lines().collect(Collectors.joining("\n"));
                if (jsonList != null && !jsonList.equals("")) {
                    userInfo = gson.fromJson(jsonList, new UserInfo().getClass());
                    reader.close();
                    return true;
                }
                initList();
                return false;

            } else {
                initList();
                return false;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

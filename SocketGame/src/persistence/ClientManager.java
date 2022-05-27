package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientManager {
    private static final String PATH_DIRECTORY = "assets";
    private static final String PATH_FILE = "assets/clients.json";
    private static ArrayList<Client> clients;
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static void initList() {
        var file = new File(PATH_FILE);
        PrintWriter out = null;
        if (clients == null) {
            try {
                out = new PrintWriter(new FileWriter(file, false));
                clients = new ArrayList<>();
                var jsonList = gson.toJson(clients);
                out.println(jsonList);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean addClient(Client client, String time, String tries) {
        var file = new File(PATH_FILE);
        try {
            PrintWriter out = new PrintWriter(new FileWriter(file, false));
            if (clientExist(client)) {
                for (Client c:clients) {
                    if (c.getIp().equals(client.getIp())) {
                        c.addAttempt(time,tries);
                        var jsonList = gson.toJson(clients);
                        out.println(jsonList);
                        out.close();
                    }
                }

            }else{
                clients.add(client);
                client.addAttempt(time,tries);
                var jsonList = gson.toJson(clients);
                out.println(jsonList);
                out.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean clientExist(Client client) {
        for (Client c : clients)
            if (c.getIp().equals(client.getIp())) return true;
        return false;
    }

    public static boolean readList() {
        var file = new File(PATH_FILE);
        try {
            if (file.exists()) {
                var jsonList = "";
                BufferedReader reader = new BufferedReader(new FileReader(file));
                jsonList = reader.lines().collect(Collectors.joining("\n"));
                if (jsonList != null && !jsonList.equals("")) {
                    clients = gson.fromJson(jsonList, new TypeToken<List<Client>>() {
                    }.getType());
                    reader.close();
                    return true;
                }
                initList();
                return false;
            } else {
                initList();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Client> searchClient(String name) {
        ArrayList<Client> searchQuery = new ArrayList<Client>();
        for (Client client : clients) {
            if (client.getIp().toLowerCase().contains(name)) {
                try {
                    searchQuery.add(client);
                    return searchQuery;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //Get user list
    public static ArrayList<Client> getClients() {
        ArrayList<Client> searchQuery = new ArrayList<Client>();
        return searchQuery;
    }
}



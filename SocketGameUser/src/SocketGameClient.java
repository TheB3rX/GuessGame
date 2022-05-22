import controller.Controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SocketGameClient {

    public static void main(String[] args) {
            Controller con = new Controller(args[0], Integer.parseInt(args[1]));
    }
}

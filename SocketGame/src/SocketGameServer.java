import controller.Controller;

public class SocketGameServer {
    public static void main(String[] args) {
        try {
            Controller con = new Controller(args[0], args[1]);
        } catch (Exception e) {
            System.out.println("Unable to start JAR.\nPlease follow the next parameters:\n[0] Port to be used \n[1] Number to be used");
        }
    }
}

package B_Cliente.main;

import MessageReader.MessageReader;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMenu {
    public static String host = "127.0.0.1";
    public static int port = 9000;
    public static Socket clientSocket = null;
    //public static ClientSession clientSession = null;
    public void run(){
        try {
            clientSocket = new Socket(host, port);
            System.out.println("conectando al server . . . ");
            //new Window(800,600, "Space Invaders", new Game());// Executes the window
            UserSession userSession= new UserSession(clientSocket);
            Thread clientSessionThread = new Thread(userSession);
            clientSessionThread.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

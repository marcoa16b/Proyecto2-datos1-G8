package B_Cliente.main;

import B_Cliente.enums.DataCheckers;
import B_Cliente.game_handler.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMenu {

    public static String host = "127.0.0.1";
    public static int port = 50800; // 9000;

    public static Socket connection = null;
    private DataInputStream fromServer;
    private DataOutputStream toServer;


    public void run(){

        try {

            connection = new Socket(host, port);
            System.out.println("conectando al server . . . ");
            new Window(800,600, "Space Invaders", new Game());// Executes the window

            //UserSession userSession= new UserSession(clientSocket);
            //Thread clientSessionThread = new Thread(userSession);
            //clientSessionThread.start();

            fromServer = new DataInputStream(connection.getInputStream());
            toServer = new DataOutputStream(connection.getOutputStream());

            //Player player = new Player();

            DataCheckers.ID.setValue(fromServer.readInt());
            System.out.println("my id is: "+DataCheckers.ID.getValue());

            Controller task = new Controller(fromServer, toServer);
            //setup(task);
            new Thread(task).start();


        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setup(Controller c){

    }

}

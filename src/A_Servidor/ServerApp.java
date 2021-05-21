package A_Servidor;

import A_Servidor.Session.HandleSession;
import A_Servidor.enumConst.DataChecks;

import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerApp extends JFrame {

    // Frame
    private JScrollPane scroll;
    private JTextArea information;
    private JLabel title;

    // Network
    private ServerSocket serverSocket;
    int sessionNo;

    public ServerApp(){
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        title = new JLabel("Server");
        information = new JTextArea();
        scroll = new JScrollPane(information);

        add(title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    public void startRunning(){

        try{

            int portt = 50800;

            // Create a server
            serverSocket = new ServerSocket(portt);
            information.append(serverSocket.getInetAddress().getHostAddress());
            information.append(new Date() + ":- Server start at port "+ portt + " \n");
            sessionNo = 1;
            int numOfPlayers = 0;

            while (true){

                information.append(new Date()+ ":- Session "+ sessionNo + " is started\n");

                // Wait for conecition with player 1
                Socket player1 = serverSocket.accept();
                information.append(new Date() + ":- player1 joined at");
                information.append(player1.getInetAddress().getHostAddress());
                information.append("\n");
                numOfPlayers++;

                // Notification to player1 that's he's connected
                new DataOutputStream(player1.getOutputStream()).writeInt(DataChecks.PLAYER_ONE.getValue());

                // Wait for conecition with player 2
                Socket player2 = serverSocket.accept();
                information.append(new Date() + ":- player2 joined at");
                information.append(player2.getInetAddress().getHostAddress());
                information.append("\n");
                numOfPlayers++;

                // Notification to player2 that's he's connected
                new DataOutputStream(player2.getOutputStream()).writeInt(DataChecks.PLAYER_TWO.getValue());

                // Increase the session number
                sessionNo++;

                // create a new thread for this session of two players
                if (numOfPlayers == 2) {
                    System.out.println("el numero de jugadores es 2");
                    HandleSession task = new HandleSession(player1, player2);
                    new Thread(task).start();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

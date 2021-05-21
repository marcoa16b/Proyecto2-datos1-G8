package A_Servidor;

import javax.swing.*;

public class ServerMain {

    public static void main(String[] args) {
        ServerApp server = new ServerApp();
        server.setSize(400, 250);
        server.setVisible(true);
        server.setTitle("Space Invaders server");
        server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // start conection
        server.startRunning();
    }

}

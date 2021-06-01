package game.handler;

import java.io.*;
import java.net.Socket;

public class Controller implements Runnable{

    private final InputStreamReader fromServer;
    private final OutputStreamWriter toServer;
    private int port;
    private String host;
    
    private boolean gameActive = true;
    private boolean receiveMessage = true;

    public Controller(InputStreamReader input, OutputStreamWriter output, int port, String host){
        this.fromServer = input;
        this.toServer = output;
        this.port = port;
        this.host = host;
    }

    @Override
    public void run() {
        while (gameActive){
            try {
                if (receiveMessage == true){
                    Socket sc = new Socket(host, port);
                    gettingMessages(sc);
                } else if (receiveMessage == false){
                    setReceivedMessages();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    private void setReceivedMessages() {
    }

    private void gettingMessages(Socket sc) {
    }
}

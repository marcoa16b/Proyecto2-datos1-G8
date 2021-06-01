package game.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.Game;
import game.Window;
import tec.MessageReader;
import tec.communications.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller implements Runnable{

    private static ObjectMapper mapper = new ObjectMapper();

    private int port;
    private String host;
    private int ID;

    private ServerSocket socket;

    
    private boolean gameActive = true;
    private boolean receiveMessage = true;

    public Controller(int port, String host) throws IOException {
        this.port = port;
        this.host = host;
        this.socket = new ServerSocket(port);
        this.ID = Game.getIdPlayer();
    }

    @Override
    public void run() {
        while (gameActive){
            try(
                    var inboundConection = this.socket.accept();
                    var reader = new InputStreamReader(inboundConection.getInputStream());
                    var writer = new OutputStreamWriter(inboundConection.getOutputStream());
                    ){
                Message message = this.readAndParse(reader);
                if (message.getOperation() == ID){ // si el id es diferente al in del juego
                    message = this.readAndParse(reader);
                    if (message.getOperation() == 5){ // 5 is code of move

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    private Message readAndParse(InputStreamReader reader) throws IOException {
        String data = MessageReader.readMessage(reader, 1024);
        return mapper.readValue(data, Message.class);
    }

    private void setReceivedMessages() {
    }

    private void gettingMessages(Socket sc) {
    }
}

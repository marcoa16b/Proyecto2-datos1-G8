package game.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.Game;
import game.Window;
import game.enums.ObjectId;
import game.objects.Player2;
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
    private static Boolean secondPlayerActive = false;

    private ServerSocket socket;

    
    private boolean gameActive = true;
    private boolean receiveMessage = true;

    public Controller(int port, String host) throws IOException {
        this.port = port;
        this.host = host;
        this.socket = new ServerSocket(port);
        this.ID = Game.getIdPlayer();
    }

    public static boolean secPlayerActive(){
        return secondPlayerActive;
    }

    @Override
    public void run() {
        System.out.println("server funcionando");
        while (gameActive){
            try(
                    var inboundConection = this.socket.accept();
                    var reader = new InputStreamReader(inboundConection.getInputStream());
                    var writer = new OutputStreamWriter(inboundConection.getOutputStream());
                    ){

                Message message = this.readAndParse(reader);
                if (message.getOperation() == ID){ // si el id es diferente al in del juego
                    System.out.println(message);
                    //writer.write("0");
                    message = this.readAndParse(reader);
                    if (message.getOperation() == 5){ // 5 is code of move
                        System.out.println(message);

                        //writer.write("0");
                        message = this.readAndParse(reader);

                        System.out.println(message);

                        SecPlayerController.setposXPlayer(message.getOperation());

                        writer.write("0");
                        //action to move the second player
                    }
                } else if (message.getOperation() == 4){
                    writer.write("OK");
                    secondPlayerActive = true;
                    SecPlayerController.setPlayer2();
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

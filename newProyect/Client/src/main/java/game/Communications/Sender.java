package game.Communications;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.Game;
import game.Window;
import game.handler.Controller;
import tec.MessageReader;
import tec.communications.Message;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.TreeMap;


/**
 * Clase encargada de enviar datos al servidor, luego de enviado se queda esperando una respuesta por lo
 * que es necesario que el servidor retorne algo. la clase encargada de escuchar es controller
 * */


public class Sender {
    private static ObjectMapper mapper = new ObjectMapper();
    private static boolean appTrue = true;

    public static int playerID;
    public static int portP1 = 9200;
    public static int portP2 = 9400;

    public static void sendSecMessage (int port, String host, Message message) throws IOException{
        try (
                var outgoing = new Socket(host, port);
                var writer = new OutputStreamWriter(outgoing.getOutputStream());
                var reader = new InputStreamReader(outgoing.getInputStream())
        ) {
            writer.write(mapper.writeValueAsString(message));
            writer.flush();
            outgoing.shutdownOutput();

            var line = MessageReader.readMessage(reader, 1024);
            if (line.startsWith("P1"))
            {
                playerID = 1;
                System.out.println("Server accepted the request");
                System.out.println("My id is: "+playerID);
                new Window(800,600, "Space Invaders", new Game(), playerID);

                Controller task = new Controller(portP1, host);
                new Thread(task).start();

            }
            else if (line.startsWith("P2"))
            {
                playerID = 2;
                System.out.println("Server accepted the request");
                System.out.println("My id is: "+playerID);
                new Window(800,600, "Space Invaders", new Game(), playerID);

                Controller task = new Controller(portP2, host);
                new Thread(task).start();

                Message sms = new Message();

            }
            else if (line.startsWith("OK"))
            {
                System.out.println("colocar segundo cliente");
            }
            else if (line.startsWith("0"))
            {}
            else
            {
                System.out.println("Server rejected the request");
            }
        }
    }

    public static void sendData(int port, String host, Message message) throws IOException {
        try (
                var outgoing = new Socket(host, port);
                var writer = new OutputStreamWriter(outgoing.getOutputStream());
                var reader = new InputStreamReader(outgoing.getInputStream())
        ) {
            writer.write(mapper.writeValueAsString(message));
            writer.flush();
            outgoing.shutdownOutput();

            var line = MessageReader.readMessage(reader, 1024);
            if (line.startsWith("P1")) {
                playerID = 1;
                System.out.println("Server accepted the request");
                System.out.println("My id is: "+playerID);
                new Window(800,600, "Space Invaders", new Game(), playerID);

                Controller task = new Controller(portP1, host);
                new Thread(task).start();

            } else if (line.startsWith("P2")) {
                playerID = 2;
                System.out.println("Server accepted the request");
                System.out.println("My id is: "+playerID);
                new Window(800,600, "Space Invaders", new Game(), playerID);

                Controller task = new Controller(portP2, host);
                new Thread(task).start();

                Message sms = new Message();
                sms.setOperation(4);

                sendSecMessage(port, host, sms);

            }else {
                System.out.println("Server rejected the request");
            }
        }
    }

    public static void receiveData(int port, String host){

        while (appTrue) {
            try (
                    var outgoing = new Socket(host, port);
                    //var writer = new OutputStreamWriter(outgoing.getOutputStream());
                    var reader = new InputStreamReader(outgoing.getInputStream())
            ) {
                //writer.write(mapper.writeValueAsString(message));
                //writer.flush();
                //outgoing.shutdownOutput();

                var line = MessageReader.readMessage(reader, 1024);

                //int data1 = reader.read();
                if (line.startsWith("P1")) {
                    playerID = 1;
                    System.out.println("Server accepted the request");
                    System.out.println("My id is: "+playerID);
                } else if (line.startsWith("P1")) {
                    playerID = 2;
                    System.out.println("Server accepted the request");
                    System.out.println("My id is: "+playerID);
                }else {
                    System.out.println("Server rejected the request");
                }
            } catch (UnknownHostException e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }

    public void stopReceiveData(){
        this.appTrue = false;
    }

    public int getPlayerID(){
        return playerID;
    }

}

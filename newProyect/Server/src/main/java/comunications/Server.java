package comunications;

import com.fasterxml.jackson.databind.ObjectMapper;
import tec.MessageReader;
import tec.communications.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Clase servidor, escucha a ambos clientes y dependiendo la instruccion retorna una nueva orden
 * a alguno de los clientes.
 * */



public class Server {

    private static ObjectMapper mapper = new ObjectMapper();

    private static final int PORT = 9000;
    private static final int PORTP1 = 9200;
    private static final int PORTP2 = 9400;

    private ServerSocket socket;
    private boolean isActive = true;

    private int numPlayers = 0;

    public Server() throws IOException {
        this.socket = new ServerSocket(PORT);
    }

    public void run() throws IOException {
        System.out.println("Server ready for connections");
        while (this.isActive) {
            try (
                    var inboundConnection = this.socket.accept();
                    var reader = new InputStreamReader(inboundConnection.getInputStream());
                    var writer = new OutputStreamWriter(inboundConnection.getOutputStream());
            ) {
                Message toServer = this.readAndParse(reader);
                if (toServer.getOperation() == 1) // operacion de nuevo cliente, se envia un ID
                {
                    if (numPlayers == 0){
                        numPlayers++;
                        writer.write("P1");
                        System.out.println("ok es el numero 1");
                    } else if (numPlayers == 1) {
                        numPlayers++;
                        writer.write("P2");
                        System.out.println("ok es el numero 2");
                    }
                }
                else if (toServer.getOperation() == 2) // Si es el ciente 1
                {
                    //writer.write("0");
                    toServer = this.readAndParse(reader);
                    if (toServer.getOperation() == 5){

                        //writer.write("0");
                        toServer = this.readAndParse(reader);

                        Message sms = new Message();

                        sms.setOperation(2); //
                        ServerSender.sendData(PORTP2, "localhost", sms);

                        sms.setOperation(5);
                        ServerSender.sendData(PORTP2, "localhost", sms);

                        sms.setOperation(toServer.getOperation());
                        ServerSender.sendData(PORTP2, "localhost", sms);

                        writer.write("0");
                    }
                }
                else if (toServer.getOperation() == 3) // Si es el ciente 2
                {
                    //writer.write("0");
                    toServer = this.readAndParse(reader);
                    if (toServer.getOperation() == 5){

                        //writer.write("0");
                        toServer = this.readAndParse(reader);

                        Message sms = new Message();

                        sms.setOperation(1); //
                        ServerSender.sendData(PORTP1, "localhost", sms);

                        sms.setOperation(5);
                        ServerSender.sendData(PORTP1, "localhost", sms);

                        sms.setOperation(toServer.getOperation());
                        ServerSender.sendData(PORTP1, "localhost", sms);

                        writer.write("0");
                    }
                }
                else if (toServer.getOperation() == 4) //
                {
                    writer.write("OK");
                    Message sms = new Message();
                    sms.setOperation(4);
                    ServerSender.sendData(PORTP1, "localhost", sms);
                    ServerSender.sendData(PORTP2, "localhost", sms);
                }
                else if (toServer.getOperation() == 5) //
                {

                }
                else
                {
                    writer.write("NOT ACK");
                }
                writer.flush();
                writer.flush();
            }
        }
    }

    private Message readAndParse(InputStreamReader reader) throws IOException {
        String data = MessageReader.readMessage(reader, 1024);
        return mapper.readValue(data, Message.class);
    }

    public void stop() {
        this.isActive = false;
    }
}

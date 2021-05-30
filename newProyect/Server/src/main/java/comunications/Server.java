package comunications;

import com.fasterxml.jackson.databind.ObjectMapper;
import tec.MessageReader;
import tec.communications.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private static ObjectMapper mapper = new ObjectMapper();

    private static final int PORT = 9000;

    private ServerSocket socket;
    private boolean isActive = true;

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
                Message message = this.readAndParse(reader);
                if (message.getOperation() == 1) {
                    System.out.println("ok es 1");
                    writer.write(""+1);
                } else {
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

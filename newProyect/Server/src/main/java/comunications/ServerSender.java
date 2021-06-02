package comunications;

import com.fasterxml.jackson.databind.ObjectMapper;
import tec.MessageReader;
import tec.communications.Message;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ServerSender {

    private static ObjectMapper mapper = new ObjectMapper();

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
            if (line.startsWith("OK")) {
                System.out.println("Client accept the request");
            } else if (line.startsWith("0")) {

            }else {
                System.out.println("Client rejected the request");
            }
        }
    }
}

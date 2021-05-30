package tec.communications;

import com.fasterxml.jackson.databind.ObjectMapper;
import tec.MessageReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Sender {
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
            if (line.startsWith("1")) {
                System.out.println("Server accepted the request");
            } else {
                System.out.println("Server rejected the request");
            }
        }
    }

}

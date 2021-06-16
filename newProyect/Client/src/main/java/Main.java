import tec.communications.Message;
import game.Communications.Sender;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Message message = new Message();
        message.setOperation(1);

        Sender.sendData(9000, "127.0.0.1", message);
    }
}

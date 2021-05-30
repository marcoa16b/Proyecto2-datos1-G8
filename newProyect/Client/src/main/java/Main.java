import tec.communications.Message;
import tec.communications.Sender;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Message message = new Message();
        message.setOperation(1);

        /*for (var i = 0; i < 10; i++) {
            Sender.sendData(9000, "127.0.0.1", message);
        }*/
        Sender.sendData(9000, "127.0.0.1", message);
    }
}

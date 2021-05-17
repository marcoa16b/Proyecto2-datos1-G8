package A_Servidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2345);
        System.out.println("ServerSocket: " + serverSocket);
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("A new Client is connected: " + socket);

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                System.out.println("Assigning new thread for this Client");

                Thread t = new MultiClientHandler(socket, dis, dos);
                t.start();
            } catch (Exception e) {
                socket.close();
                System.out.println(e);
            }
        }
    }
}

//clienthandler class
class MultiClientHandler extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket socket;

    // constructor
    public MultiClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.socket = s;
        this.dis = dis;
        this.dos = dos;
    }

    public void run() {
        String received;
        while (true) {
            try {
                dos.writeUTF("Send message to server (Type Exit to terminate connection)");
                received = dis.readUTF();

                if (received.equals("Exit")) {
                    System.out.println("Client " + this.socket + " sends exit...");
                    System.out.println("Closing the connection");
                    this.socket.close();
                    System.out.println("Connection Closed");
                    break;
                }
                dos.writeUTF(received);
                System.out.println("Response of client: " + received);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        try {
            this.dis.close();
            this.dos.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}

package A_Servidor;
import MessageReader.MessageReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer implements Runnable{
    public final int port=9000;
    public static ServerSocket EchoSocket;
    public static void startEchoServer(){
        EchoServer echoServer=new EchoServer();
        Thread echoThread = new Thread(echoServer);
        echoThread.start();
    }
    public static void stopEchoServer() throws IOException{
        System.out.println("Server Close");
        EchoSocket.close();
    }
    @Override
    public void run() {
        System.out.println("Server Started:"+port);
        try {
            EchoSocket= new ServerSocket(port);
            do{
                System.out.println("Awaiting connection");
                Socket client=EchoSocket.accept();
                System.out.println("Client Connected");
                ServerSession serverSession= new ServerSession(client);
                Thread serverSessionThread = new Thread(serverSession);
                serverSessionThread.start();
            }while (EchoSocket.isBound());
            stopEchoServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startEchoServer();
    }













/*    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9000);
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
    }*/


}


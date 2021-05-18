package A_Servidor;

import B_Cliente.Listas.ListasEnlazadas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private int nave1PX;
    private int nave2PX;
    private int nave3PX;
    private int nave4PX;
    private static ExecutorService pool= Executors.newFixedThreadPool(4);
    public static void main(String[] args) {

        ServerSocket server = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        final int PUERTO = 5000;

        try {
            server = new ServerSocket(PUERTO); // Crea uel socket
            System.out.println("Servidor iniciado");

            while (true) {
                sc = server.accept(); // Acepta al cliente
                in = new DataInputStream(sc.getInputStream()); // Con esto recibe datos del cliente
                out = new DataOutputStream(sc.getOutputStream()); // con esto envia datos al cliente
                String mensaje = in.readUTF(); // guarda el mensaje del cliente en la variable mensaje
                System.out.println(mensaje);
                out.writeUTF("¡Hola Mundo desde el servidor!"); // Envia un mensaje al cliente
                //sc.close(); // cierra la coneccion con el cliente
                //System.out.println("Cliente desconectado");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDataList(){}

    // ID, px,
    public void llenarLista(ListasEnlazadas lista){}
}

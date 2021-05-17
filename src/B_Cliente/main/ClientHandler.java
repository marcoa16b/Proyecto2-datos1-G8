package B_Cliente.main;

import A_Servidor.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    public ClientHandler(Socket clientSocket) throws IOException {
        this.client=clientSocket;
        in= new BufferedReader(new InputStreamReader(client.getInputStream()));
        out=new PrintWriter(client.getOutputStream());
    }
    @Override
    public void run() {
        try {
            while (true){
                out.write("Helllo from client");
                in.read();
            }
        } catch (IOException e){
            System.err.println("Error");
        }
            finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}

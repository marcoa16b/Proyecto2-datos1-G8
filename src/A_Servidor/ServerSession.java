package A_Servidor;

import MessageReader.MessageReader;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ServerSession implements Runnable{
    private Socket socket;


    public ServerSession(Socket socket){
        this.socket=socket;
    }
     @Override
    public void run() {
         OutputStream os = null;
         //DataOutputStream dos;
         OutputStreamWriter osw;
         BufferedWriter bw;
         InputStream is = null;
         //DataInputStream dis;
         InputStreamReader isr;
         BufferedReader br;
         long lastSentTime=0;
         try {
             //System.out.println("working");
             os = socket.getOutputStream();
             //dos = new DataOutputStream(os);
             osw = new OutputStreamWriter(os);
             bw = new BufferedWriter(osw);
             is = socket.getInputStream();
             //dis = new DataInputStream(is);
             isr = new InputStreamReader(is);
             br = new BufferedReader(isr);
             do{

                     String[] completeCommand = MessageReader.readSplitMessage(br);
                     String command = completeCommand[0];
                     System.out.println(Arrays.toString(MessageReader.readSplitMessage(br)));


             }while (socket.isBound());

         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}




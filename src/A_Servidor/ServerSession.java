package A_Servidor;

import MessageReader.MessageReader;

import java.io.*;
import java.net.Socket;

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
             os = socket.getOutputStream();
             //dos = new DataOutputStream(os);
             osw = new OutputStreamWriter(os);
             bw = new BufferedWriter(osw);
             is = socket.getInputStream();
             //dis = new DataInputStream(is);
             isr = new InputStreamReader(is);
             br = new BufferedReader(isr);
             do{
                 if(is.available()>0){
                     String[] completeCommand = MessageReader.readSplitMessage(br);
                     String command = completeCommand[0];
                     switch (command){
                         case MessageReader.CMD_START:{
                             String a=MessageReader.readMessage(br);
                             System.out.println(a);
                             MessageReader.writeMessage(bw,null,"Hello");

                         }

                     }



















                 }
             }while (socket.isBound());







         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}

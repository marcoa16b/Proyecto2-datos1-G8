package B_Cliente.main;

import MessageReader.MessageReader;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.net.Socket;
import java.util.Random;

public class UserSession implements Runnable{
    Socket clientSocket;
    BufferedWriter bw;
    double posicionX = 0;
    double lastPosicionX = 0;
    String myId;
    String myLaserId;
    public UserSession(Socket clientSocket){
        this.clientSocket=clientSocket;
    }
    @Override
    public void run() {
        OutputStream os = null;
        OutputStreamWriter osw;
        //BufferedWriter bw;
        InputStream is = null;
        InputStreamReader isr;
        BufferedReader br;
        myId = generateId();
        myLaserId = generateId();
        myId = generateId();
        myLaserId = generateId();
        //long lastSentTime = 0;

        try {
            os = clientSocket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            is = clientSocket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            //MessageReader.writeMessage(bw, MessageReader.CMD_START, myId + " " + myLaserId);
            System.out.println("mi id es " + myId);
            do{
                try {

                        String[] completeCommand = MessageReader.readSplitMessage(br);
                        String command = completeCommand[0];
                        MessageReader.writeMessage(bw,"AAAAAAAAAAAA", MessageReader.CMD_START);
                        MessageReader.writeMessage(bw,"Hello");
                        //MessageReader.readMessage(br);
                        break;


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }while (clientSocket.isConnected());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ///////////////Mouse overrides
    /*@Override
    public void mouseDragged(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }*/
    ///////////////////////////////////////////

    public static String generateId() {
        Random random = new Random();
        int value = random.nextInt();
        return String.valueOf(value);
    }
}

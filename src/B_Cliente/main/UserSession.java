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
        InputStream is = null;
        InputStreamReader isr;
        BufferedReader br;
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
            MessageReader.writeMessage(bw, MessageReader.CMD_START, myId + " " + myLaserId);
            MessageReader.writeMessage(bw,MessageReader.CMD_START, "HOLA");
            System.out.println("mi id es " + myId);
            MessageReader.readMessage(br);
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

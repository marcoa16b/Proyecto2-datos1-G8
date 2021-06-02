package game.objects;

import game.Communications.Sender;
import game.Game;
import game.enums.DataCheckers;
import game.enums.ObjectId;
import game.handler.Controller;
import tec.communications.Message;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class Player extends GameObject implements MouseListener, MouseMotionListener {

    private int serverPort = 9000;
    Graphics gr;

    DataInputStream input;
    DataOutputStream output;

    public Player(int x, int y, ObjectId Id, Canvas c) {
        super(x, y, Id);
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
        //receivedInOut();
    }

    @Override
    public void tick(LinkedList<GameObject> object) {}

    @Override
    public void render(Graphics g) {
        this.gr = g;
        //g.setColor(Color.CYAN);
        //g.fillRect((int)x, (int)y,22, 32);
        BufferedImage playerImg = Load.imageLoad("/player.png");
        //Image nave = new ImageIcon(getClass().getResource("/player.png")).getImage();
        g.drawImage(playerImg, (int)x, (int)y, null);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getVelX() {
        return 0;
    }

    @Override
    public int getVelY() {
        return 0;
    }

    @Override
    public void setVelX(int velX) {}

    @Override
    public void setVelY(int velY) {}

    @Override
    public ObjectId getId() {
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Bullet bull = new Bullet(getX(), getY(), ObjectId.bullet);
        Game.getHandler().addObject(bull);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        int nx = e.getX();
        if (nx > 750 ) {
            setX((int)750);
        } else if (nx < 0) {
            setX((int)0);
        } else {
            setX((int)nx-11);
            sendPosition(nx);
            //Sender.sendData(serverPort, "localhost", );
        }
    }
    public void sendPosition(int X){
        Message sms = new Message();
        if (Controller.secPlayerActive()) // verifica que el segundo jugador se haya conectado.
        {
            try {
                sms.setOperation(Game.getIdPlayer() + 1); // if is client 1 ore 2, the id to server is 2 or 3.
                Sender.sendData(serverPort, "localhost", sms);

                sms.setOperation(5); // operation to move the player
                Sender.sendData(serverPort, "localhost", sms);

                sms.setOperation(X); // position in X of player
                Sender.sendData(serverPort, "localhost", sms);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

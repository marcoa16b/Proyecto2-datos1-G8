package game.objects;

import game.Game;
import game.enums.DataCheckers;
import game.enums.ObjectId;
import game.handler.Controller;

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
            //setX(nx-11);
            /*try {
                if (nx-11 < getX())
                    if(nx != 99) {
                        //output.writeInt(DataCheckers.ID.getValue());
                        //output.writeInt(5);
                        //output.writeInt(nx);
                    }
            } catch (IOException ioException) {
                System.out.println("error aqui");
                //ioException.printStackTrace();*/
            //}
            setX((int)nx-11);
        }
    }

    /*public void receivedInOut(){
        input = Controller.getInput();
        output = Controller.getOutput();
    }*/
}

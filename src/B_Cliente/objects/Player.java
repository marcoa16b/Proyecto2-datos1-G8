package B_Cliente.objects;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Player extends GameObject implements MouseListener, MouseMotionListener {

    public Player(float x, float y, ObjectId Id, Canvas c) {
        super(x, y, Id);
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int)x, (int)y,32, 32);
        //BufferedImage playerImg = Load.imageLoad("/player.png");
        //g.drawImage(playerImg, (int)x, (int)y, null);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getVelX() {
        return 0;
    }

    @Override
    public float getVelY() {
        return 0;
    }

    @Override
    public void setVelX(float velX) {}

    @Override
    public void setVelY(float velY) {}

    @Override
    public ObjectId getId() {
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int nx = e.getX();
        if (nx > 750 ) {
            setX((float)750);
        } else if (nx < 0) {
            setX((float)0);
        } else {
            setX((float)nx);
        }
    }
}

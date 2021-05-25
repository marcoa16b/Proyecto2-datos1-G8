package B_Cliente.objects;

import B_Cliente.main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Player2 extends GameObject {
    public Player2(int x, int y, ObjectId Id) {
        super(x, y, Id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
    }
    @Override
    public void render(Graphics g) {
        BufferedImage playerImg = Load.imageLoad("/player.png");
        g.drawImage(playerImg, (int)x, (int)y, null);
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public void setX(float x) {

    }

    @Override
    public void setY(float y) {

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
    public void setVelX(float velX) {

    }

    @Override
    public void setVelY(float velY) {

    }

    @Override
    public ObjectId getId() {
        return null;
    }

}

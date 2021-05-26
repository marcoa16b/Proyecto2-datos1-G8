package B_Cliente.objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Player2 extends GameObject{

    public Player2(int x, int y, ObjectId Id) {
        super(x, y, Id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        //setX(10);
        //setY(510);
    }

    @Override
    public void render(Graphics g) {
        BufferedImage playerImg = Load.imageLoad("/player.png");
        g.drawImage(playerImg, x, y, null);
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
        return velX;
    }

    @Override
    public int getVelY() {
        return velY;
    }

    @Override
    public void setVelX(int velX) {
        this.velX = velX;
    }

    @Override
    public void setVelY(int velY) {
        this.velY = velY;
    }

    @Override
    public ObjectId getId() {
        return Id;
    }
}

package B_Cliente.objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

public class Enemy extends GameObject{

    private BufferedImage playerImg;

    public Enemy(float x, float y, ObjectId Id) {
        super(x, y, Id);
        playerImg = Load.imageLoad("../../resources/alien.png");
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(playerImg, (int)x, (int)y, null);

        //g.setColor(Color.RED);
        //g.fillRect((int)x, (int)y,32, 32);
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

package B_Cliente.objects;

import B_Cliente.main.Game;

import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject{

    public Bullet(float x, float y, ObjectId Id) {
        super(x, y, Id);
        setVelY(5);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if (y > 0) {
            setY(getY() - getVelY());
        } else {
            //Game.getHandler().removeObject(this);
            Game.getHandler().removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int) getX()+24, (int) getY(), 5, 8);
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
        return velX;
    }

    @Override
    public float getVelY() {
        return velY;
    }

    @Override
    public void setVelX(float velX) {
        this.velX = velX;
    }

    @Override
    public void setVelY(float velY) {
        this.velY = velY;
    }

    @Override
    public ObjectId getId() {
        return Id;
    }

}

package B_Cliente.objects;

import B_Cliente.main.Game;

import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject{

    public Bullet(int x, int y, ObjectId Id) {
        super(x, y, Id);
        setVelY(5);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        if (y > 0) {
            setY(getY() - getVelY());
        } else {
            Game.getHandler().removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int) getX()+24, (int) getY(), 5, 8);
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

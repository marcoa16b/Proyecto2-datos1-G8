package B_Cliente.objects;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    protected int x,y;
    protected ObjectId Id;
    protected int velX = 0, velY = 0;

    public GameObject(int x, int y, ObjectId Id){
        this.x = x;
        this.y = y;
    }

    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);

    public abstract int getX();
    public abstract int getY();
    public abstract void setX(int x);
    public abstract void setY(int y);

    public abstract int getVelX();
    public abstract int getVelY();
    public abstract void setVelX(int velX);
    public abstract void setVelY(int velY);

    public abstract ObjectId getId();
}

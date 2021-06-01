package game.enemys;

import game.Listas.ListasEnlazadas;
import game.enums.ObjectId;

import java.awt.*;

public abstract class AbsAliens {

    protected int x, y, velX, velY;
    protected boolean isKing;
    ObjectId Id;

    public AbsAliens(int x, int y, ObjectId Id, boolean isKing){
        this.x = x;
        this.y = y;
        this.Id = Id;
        this.isKing = isKing;
    }

    public abstract void tick(ListasEnlazadas<AbsAliens> aliens);
    public abstract void render(Graphics g);

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public int getVelX(){
        return velX;
    }
    public int getVelY(){
        return velY;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }

    public boolean getIsKing(){
        return isKing;
    }
    public void setIsKing(boolean isKing){
        this.isKing = isKing;
    }

    public ObjectId getId(){
        return Id;
    }
}

package B_Cliente.objects.enemys;

import B_Cliente.Listas.ListasEnlazadas;
import B_Cliente.objects.Load;
import B_Cliente.objects.ObjectId;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Aliens extends AbsAliens{

    BufferedImage alien = Load.imageLoad("/alien (2).png");

    public Aliens(int x, int y, ObjectId Id, boolean isKing) {
        super(x, y, Id, isKing);
        setVelX(1);
        setVelY(15);
    }

    @Override
    public void tick(ListasEnlazadas<AbsAliens> object) {
        // Movimiento del alien
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(alien, getX(), getY(), null);
    }
}

package B_Cliente.game_handler;

import B_Cliente.Listas.ListasEnlazadas;
import B_Cliente.objects.GameObject;
import B_Cliente.objects.enemys.AbsAliens;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    public ListasEnlazadas<AbsAliens> aliens = new ListasEnlazadas<AbsAliens>();

    private GameObject tempObject;
    private AbsAliens tempAlien;

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            tempObject.tick(object);
        }

        for (int i = 0; i<aliens.getSize(); i++){
            tempAlien = aliens.gett(i);
            tempAlien.tick(aliens);
        }

        /*for ( int i = 0; i < enemys.getSize(); i++){
            tempEnemy = enemys.get(i);
            tempObject.tick(object); // update(object)
        }*/
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            tempObject.render(g);
        }

        for (int i = 0; i<aliens.getSize(); i++){
            tempAlien = aliens.gett(i);
            tempAlien.render(g);
        }

    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void addAlien(AbsAliens alien){
        this.aliens.insertLast(alien);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public void removeAlien(AbsAliens alien){
        for (int i = 0; i<aliens.getSize(); i++){
            if (aliens.gett(i) == alien){
                this.aliens.delete(i);
            }
        }
    }

    public int getindexObject(GameObject obj){
        return object.indexOf(obj);
    }

    public int getIndexAlien(AbsAliens alien){
        int val = 0;
        for (int i = 0; i<aliens.getSize(); i++){
            if (aliens.gett(i) == alien){
                val = i;
            }
        }
        return val;
    }

    public GameObject getObject(GameObject obj){
        return object.get(getindexObject(obj));
    }

    public AbsAliens getAlien(AbsAliens alien){
        AbsAliens alienTemp = null;
        for (int i = 0; i<aliens.getSize(); i++){
            if (aliens.gett(i) == alien){
                alienTemp = aliens.gett(i);
            }
        }
        return alienTemp;
    }

}
